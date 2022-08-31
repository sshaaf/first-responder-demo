package org.cajun.navy.service;

import org.cajun.navy.map.DisasterInfo;
import org.cajun.navy.map.RoutePlanner;
import org.cajun.navy.map.Shelter;
import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.mission.*;
import org.cajun.navy.model.responder.Responder;
import org.cajun.navy.model.responder.ResponderDao;
import org.cajun.navy.service.model.Mission;
import org.cajun.navy.service.model.Mission.Builder;

import org.cajun.navy.service.model.MissionStep;
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
    @Channel("mission")
    Emitter<MissionEntity> missionEmitter;

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
    public void fireEvent(MissionEntity mission){
        missionEmitter.send(mission);
    }

    @Transactional
    public void create(Incident incident){

        MissionEntity mission = new MissionEntity();
        mission.setStatus(MissionStatus.CREATED.toString());

        // Set incident id and location
        mission.setIncidentId(incident.getIncidentId());
        mission.setIncidentLatitude(incident.getLatitude());
        mission.setIncidentLongtitude(incident.getLongitude());

        // Set responders id and current location
        Responder responder = responderService.getFirstAvailableResponder();
        mission.setResponderId(String.valueOf(responder.getId()));
        mission.setResponderStartLatitude(responder.getLatitude());
        mission.setResponderStartLongitude(responder.getLongitude());


        // Set destination location
        Shelter shelter = disasterInfo.getRandomShelter();
        mission.setDestinationLatitude(shelter.getLat());
        mission.setDestinationLongtitude(shelter.getLon());

        // Get directions for the mission
        mission.setSteps(getAllSteps(mission.responderLocation(), shelter.shelterLocation(), mission.incidentLocation()));


        missionDao.create(mission);
        logger.info("Created new mission "+mission.getMissionId());

        // fireEvent to Kafka
        fireEvent(mission);
    }


    @Transactional
    public void doResponderNextMove(MissionEntity mission){
        if(mission != null || mission.getMissionId() != null){
            if(!mission.getSteps().isEmpty()) {
                // if the steps from the routeplan equals the movement of the responder
                if (mission.getSteps().size() == mission.getResponderLocationHistory().size()) {
                    mission.setStatus(MissionStatus.COMPLETED.toString());
                }

                // if the mission was just created, we assume this is the first time to move and hence status is updated.
                if (mission.getStatus().equals(MissionStatus.CREATED.toString())) {
                    mission.setStatus(MissionStatus.UPDATED.toString());
                }
                // Make next move while status is still in UPDATED, this is also true for the CREATED state since we change it to updated.
                if (mission.getStatus().equals(MissionStatus.UPDATED.toString())){
                    // Make next move
                    MissionStepEntity thisStep = mission.getSteps().get(mission.getResponderLocationHistory().size());
                    ResponderLocationHistoryEntity locationHistory = getNewLocation(thisStep);
                    mission.getResponderLocationHistory().add(locationHistory);
                    logger.info(mission.getMissionId()+": updating with new move "+locationHistory.getLatitude() +" , "+locationHistory.getLongitude());
                }
                missionDao.merge(mission);
                // fireEvent to Kafka
                fireEvent(mission);
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
