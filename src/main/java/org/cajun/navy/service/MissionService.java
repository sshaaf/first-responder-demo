package org.cajun.navy.service;



import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import io.smallrye.reactive.messaging.kafka.api.KafkaMetadataUtil;
import org.cajun.navy.map.DisasterInfo;
import org.cajun.navy.map.RoutePlanner;
import org.cajun.navy.map.Shelter;
import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.mission.*;
import org.cajun.navy.model.responder.Responder;
import org.cajun.navy.util.JsonMapper;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

@ApplicationScoped

public class MissionService {

    @Inject
    MissionDao missionDao;

    public Mission create(Mission mission){
        return missionDao.create(mission);
    }

    public List<Mission> findAll(){
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


    @Incoming("in-incident-reported-event")
    public CompletionStage<Void> recieveCreatedIncident(Message<String> message){

        IncomingKafkaRecordMetadata<Integer, String> md = KafkaMetadataUtil.readIncomingKafkaMetadata(message).get();
        String msg =
                "Received from Kafka, storing it in database\n" +
                        "\t%s\n" +
                        "\tkey: %d; partition: %d, topic: %s";
        msg = String.format(msg, message.getPayload(), md.getKey(), md.getPartition(), md.getTopic());
        System.out.println(msg);


        return message.ack();
    }


    //        Incident incident = JsonMapper.getIncident(message.getPayload());
//        Mission m = doCreateMission(incident);
//        missionDao.create(m);
/*    public Mission doCreateMission(Incident incident){

        Responder responder = responderService.getFirstAvailableResponder();

        Mission mission = new Mission();
        mission.setStatus(MissionStatus.CREATED.toString());

        // Set incident id and location
        mission.setIncidentId(incident.getIncidentId());
        mission.setIncidentLatitude(incident.getLatitude());
        mission.setIncidentLongtitude(incident.getLongitude());

        // Set responders id and current location
        // TODO: Fix this
        mission.setResponderId(String.valueOf(responder.getId()));
        mission.setResponderStartLatitude(responder.getLatitude());
        mission.setResponderStartLongitude(responder.getLatitude());

        //mission.setSteps(getAllSteps(mission));

        return mission;

    }
*/
/*    public List<MissionStep> getAllSteps(Mission mission){
        Shelter shelter = DisasterInfo.getRandomShelter();
        // get directions
        // origin = respondersLocation, waypoint = incidentsLocation, destination = shelterLocation
        return  routePlanner.getDirections(mission.responderLocation(), shelter.shelterLocation(), mission.incidentLocation());

    }
*/
}
