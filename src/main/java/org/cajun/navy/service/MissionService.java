package org.cajun.navy.service;

import org.cajun.navy.map.DisasterInfo;
import org.cajun.navy.map.RoutePlanner;
import org.cajun.navy.map.Shelter;
import org.cajun.navy.model.incident.IncidentEntity;
import org.cajun.navy.model.incident.IncidentStatus;
import org.cajun.navy.model.mission.*;
import org.cajun.navy.model.responder.ResponderEntity;
import org.cajun.navy.model.responder.ResponderDao;
import org.cajun.navy.service.message.model.ResponderLocationMessage;
import org.cajun.navy.service.model.Mission;

import org.cajun.navy.service.model.MissionStep;
import org.cajun.navy.service.model.Responder;
import org.cajun.navy.service.model.ResponderLocationHistory;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RequestScoped
public class MissionService {

    private static final Logger logger = Logger.getLogger(MissionService.class.getName());

    @Inject
    MissionDao missionDao;

    @Inject
    ResponderService responderService;

    @Inject
    ResponderDao responderDao;

    @Inject
    RoutePlanner routePlanner;

    @Inject
    DisasterInfo disasterInfo;

    @Inject
    IncidentService incidentService;

    @Inject
    @Channel("mission-event")
    Emitter<Mission> missionEventEmitter;

    @Inject
    @Channel("mission-command")
    Emitter<Mission> missionCommandEmitter;

    @Inject
    @Channel("responder-location")
    Emitter<ResponderLocationMessage> responderLocationEmitter;

    public List<Mission> findAll(){
        return missionDao
                .findAll()
                .stream()
                .map(missionEntity -> toMission(missionEntity))
                .collect(Collectors.toList());
    }

    public Mission findByMissionId(String missionId){
        MissionEntity me = missionDao.findByMissionId(missionId);
        return toMission(me);
    }

    public List<MissionEntity> findByStatus(String status){
        return missionDao.findByStatus(status);
    }

    public List<MissionEntity> getByResponder(String responderId){
        return missionDao.getByResponder(responderId);
    }

    public List<MissionEntity> getAllCreatedOrUpdated(){
        return missionDao.getCreatedAndUpdated();
    }

    // Emit mission
    public void fireEvent(Mission mission){
        missionEventEmitter.send(mission);

        // as per the requirements, only send the command once mission is created.
        if(mission.getStatus().equals(MissionStatus.CREATED.toString()))
            missionCommandEmitter.send(mission);
    }

    // Emit responder Location
    public void fireEvent(ResponderLocationMessage message){
        responderLocationEmitter.send(message);
    }

    @Transactional
    public void create(IncidentEntity incident){

        MissionEntity missionEntity = new MissionEntity();
        missionEntity.setStatus(MissionStatus.CREATED.toString());

        // Set incident id and location
        missionEntity.setIncidentId(incident.getIncidentId());
        missionEntity.setIncidentLatitude(incident.getLat());
        missionEntity.setIncidentLongtitude(incident.getLon());

        // Set responders id and current location
        Responder responder = responderService.getFirstAvailableResponder();
        missionEntity.setResponderId(String.valueOf(responder.getId()));
        missionEntity.setResponderStartLatitude(responder.getLatitude());
        missionEntity.setResponderStartLongitude(responder.getLongitude());

        // Ensure responder is no longer available
        responder.setAvailable(false);
        responderService.update(responder);

        // Set destination location
        Shelter shelter = disasterInfo.getRandomShelter();
        missionEntity.setDestinationLatitude(shelter.getLat());
        missionEntity.setDestinationLongtitude(shelter.getLon());

        // Get directions for the mission
        missionEntity.setSteps(getAllSteps(missionEntity.responderLocation(), shelter.shelterLocation(), missionEntity.incidentLocation()));


        missionDao.create(missionEntity);
        logger.info("Created new mission "+missionEntity.getMissionId());

        // fireEvent to Kafka
        fireEvent(toMission(missionEntity));
    }


    @Transactional
    public void doResponderNextMove(MissionEntity mission){
        if(mission != null || mission.getMissionId() != null){
            if(!mission.getSteps().isEmpty()) {
                // if the steps from the routeplan equals the movement of the responder
                if (mission.getSteps().size() == mission.getResponderLocationHistory().size()) {
                    mission.setStatus(MissionStatus.COMPLETED.toString());
                    incidentService.updateStatus(IncidentStatus.RESCUED, mission.getIncidentId());

                    //ensure responder is available again
                    Responder responder = responderService.findById(Integer.valueOf(mission.getResponderId()));
                    responder.setEnrolled(true);
                    if(responder.isPerson())
                        responder.setAvailable(false);
                    else
                        responder.setAvailable(true);
                    responderService.update(responder);

                    // fireEvent to Kafka
                    fireEvent(toMission(mission));
                }

                // if the mission was just created, we assume this is the first time to move and hence status is updated.
                if (mission.getStatus().equals(MissionStatus.CREATED.toString())) {
                    mission.setStatus(MissionStatus.UPDATED.toString());
                }
                // Make next move while status is still in UPDATED, this is also true for the CREATED state since we change it to updated.
                if (mission.getStatus().equals(MissionStatus.UPDATED.toString())){
                    // Make next move
                    MissionStepEntity thisStep = mission.getSteps().get(mission.getResponderLocationHistory().size());
                    if(thisStep.isWayPoint()){
                        incidentService.updateStatus(IncidentStatus.PICKEDUP, mission.getIncidentId());
                        // fireEvent to Kafka
                        fireEvent(toMission(mission));
                    }
                    ResponderLocationHistoryEntity locationHistory = getNewLocation(thisStep);
                    mission.getResponderLocationHistory().add(locationHistory);

                    // creating the responder message to fire with, we do this here, becasue we have the reponders location history at this exact point
                    ResponderLocationMessage locationMessage = new ResponderLocationMessage.Builder(mission.getResponderId())
                            .missionId(mission.getMissionId())
                            .incidentId(mission.getIncidentId())
                            .status(mission.getStatus(), thisStep.isWayPoint())
                            .lat(locationHistory.getLatitude())
                            .lon(locationHistory.getLongitude()).build();

                    // send updated responder location
                    fireEvent(locationMessage);
                    logger.info(mission.getMissionId()+": updating with new move "+locationHistory.getLatitude() +" , "+locationHistory.getLongitude());
                }
                missionDao.merge(mission);

            } else throw new IllegalArgumentException("Mission steps not found, check if the Routeplanner executed correctly.");
        }
        else throw new IllegalArgumentException("Cant do next move - Mission should not be null");
    }

    private ResponderLocationHistoryEntity getNewLocation(MissionStepEntity step){
        if(step != null) {
            return new ResponderLocationHistoryEntity(step.getLatitude(), step.getLongitude(), System.currentTimeMillis());
        }
        else throw new IllegalArgumentException("Step is null cannot be added to responders location history");
    }


    // get directions
    // origin = respondersLocation, destination = shelterLocation , waypoint
    public List<MissionStepEntity> getAllSteps(Location origin, Location destination, Location wayPoint){
        return  routePlanner.getDirections(origin, destination, wayPoint);

    }


    private Mission toMission(MissionEntity entity){
        if(entity != null) {
            Mission m = new Mission.Builder(entity.getMissionId())
                    .incidentId(entity.getIncidentId())
                    .responderId(entity.getResponderId())
                    .status(entity.getStatus())
                    .responderStartLatitude(entity.getResponderStartLatitude())
                    .responderStartLongitude(entity.getResponderStartLongitude())
                    .incidentLatitude(entity.getIncidentLatitude())
                    .incidentLongitude(entity.getIncidentLongtitude())
                    .desitnationLatitude(entity.getDestinationLatitude())
                    .desitnationLongitude(entity.getDestinationLongtitude())
                    .responderLocationHistory(toResponderLocationHistory(missionDao.getResponderLocationHistoryByMission(entity.getMissionId())))
                    .missionSteps(toMissionStep(missionDao.getMissionStepsByMission(entity.getMissionId())))
                    .build();
            return m;
        }
        else throw new IllegalArgumentException("toMission expects a MissionEntity and not null");
    }

    private List<ResponderLocationHistory> toResponderLocationHistory(List<ResponderLocationHistoryEntity> entities){
        return entities.stream()
                .map(responderLocationHistoryEntity -> toResponderLocationHistory(responderLocationHistoryEntity))
                .collect(Collectors.toList());
    }

    private ResponderLocationHistory toResponderLocationHistory(ResponderLocationHistoryEntity entity){
        return new ResponderLocationHistory
                .Builder()
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .timestamp(entity.getTimestamp())
                .build();

    }

    private List<MissionStep> toMissionStep(List<MissionStepEntity> entities){
        return entities.stream()
                .map(missionStepEntity -> toMissionStep(missionStepEntity))
                .collect(Collectors.toList());
    }

    private MissionStep toMissionStep(MissionStepEntity missionStepEntity){
        return  new MissionStep
                .Builder()
                .latitude(missionStepEntity.getLatitude())
                .longitude(missionStepEntity.getLongitude())
                .wayPoint(missionStepEntity.isWayPoint())
                .destination(missionStepEntity.isDestination())
                .build();
    }


}
