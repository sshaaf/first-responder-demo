package org.cajun.navy.service;

import org.cajun.navy.map.DisasterInfo;
import org.cajun.navy.map.RoutePlanner;
import org.cajun.navy.map.Shelter;
import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.mission.*;
import org.cajun.navy.model.responder.Responder;
import org.cajun.navy.model.responder.ResponderDao;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;


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
    @Channel("mission")
    Emitter<Mission> missionEmitter;

    public List<Mission> findAll(){
        responderService.availableResponders();
        return missionDao.findAll();
    }

    public Mission findByMissionId(String missionId){
        return missionDao.findByMissionId(missionId);
    }

    public List<Mission> findByStatus(String status){
        return missionDao.findByStatus(status);
    }

    public List<Mission> getByResponder(String responderId){
        return missionDao.getByResponder(responderId);
    }

    public List<Mission> getAllCreatedOrUpdated(){
        return missionDao.getCreatedAndUpdated();
    }

    // Emit mission
    public void fireEvent(Mission mission){
        missionEmitter.send(mission);
    }

    @Transactional
    public void create(Incident incident){

        Mission mission = new Mission();
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
        Shelter shelter = DisasterInfo.getRandomShelter();
        mission.setDestinationLatitude(shelter.getLatitude());
        mission.setDestinationLongtitude(shelter.getLongitude());

        // Get directions for the mission
        mission.setSteps(getAllSteps(mission.responderLocation(), shelter.shelterLocation(), mission.incidentLocation()));


        missionDao.create(mission);
        logger.info("Created new mission "+mission.getMissionId());

        // fireEvent to Kafka
        fireEvent(mission);
    }

    @Transactional
    public void doResponderNextMove(Mission mission){
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
                    MissionStep thisStep = mission.getSteps().get(mission.getResponderLocationHistory().size());
                    ResponderLocationHistory locationHistory = getNewLocation(thisStep);
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

    private ResponderLocationHistory getNewLocation(MissionStep step){
        if(step != null) {
            return new ResponderLocationHistory(step.getLatitude(), step.getLongitude(), System.currentTimeMillis());
        }
        else throw new IllegalArgumentException("Step is null cannot be added to responders location history");
    }


    // get directions
    // origin = respondersLocation, destination = shelterLocation , waypoint
    public List<MissionStep> getAllSteps(Location origin, Location destination, Location wayPoint){
        return  routePlanner.getDirections(origin, destination, wayPoint);

    }

}
