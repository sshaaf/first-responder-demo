package org.cajun.navy.service;

import io.smallrye.reactive.messaging.kafka.api.KafkaMetadataUtil;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import org.cajun.navy.model.incident.IncidentStatus;
import org.cajun.navy.service.message.MessageConfig;
import org.cajun.navy.service.message.model.IncidentCommandMessage;
import org.cajun.navy.service.message.model.MissionCommandMessage;
import org.cajun.navy.service.message.model.ResponderLocationMessage;
import org.cajun.navy.service.message.model.ResponderMessageCommand;
import org.cajun.navy.service.model.Incident;
import org.cajun.navy.service.model.Mission;
import org.cajun.navy.service.model.Responder;
import org.eclipse.microprofile.reactive.messaging.*;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import javax.enterprise.context.Dependent;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

import static org.cajun.navy.service.message.MessageConfig.*;

@Dependent
public class MessageService {

    private static final Jsonb jsonb = JsonbBuilder.create();

    // Create cloud events
    private CloudEvent eventBuilder(String id, String type, String source, String data){
        final CloudEvent event = CloudEventBuilder.v1()
                .withId(id)
                .withType(type)
                .withSource(URI.create(source))
                .withDataContentType(MediaType.APPLICATION_JSON)
                .withData(data.getBytes(StandardCharsets.UTF_8))
                .withTime(OffsetDateTime.from(OffsetDateTime.now().toZonedDateTime()))
                .build();
        return event;
    }

    // decorate all outgoing messages as CloudEvents
    private Message<CloudEvent> decorateMessage(String id, String type, String source, String data){
        // Create Metadata containing the Kafka key
        OutgoingKafkaRecordMetadata<CloudEvent> md = OutgoingKafkaRecordMetadata
                .<CloudEvent>builder()
                .build();

        // The returned message will have the metadata added
        return KafkaMetadataUtil.writeOutgoingKafkaMetadata(Message.of(eventBuilder(id, type, source, data)), md);
    }

    @Incoming("incident-command")
    @Outgoing("incident-command-to-kafka")
    public Message<CloudEvent> sendCommandToKafka(Incident incident) {
        // IncidentCommandMessage is not the same as the incident, extract whats required
        String data = new IncidentCommandMessage.Builder(incident.getId())
                .status(incident.getStatus().toString())
                .build().toString();

        // The returned message will have the metadata added
        return decorateMessage(incident.getId(), INCIDENT_UPDATE_COMMAND, INCIDENT_MESSAGE_SOURCE, data);
    }

    @Incoming("incident-event")
    @Outgoing("incident-event-to-kafka")
    public Message<CloudEvent> sendEventToKafka(Incident incident) {
        // get the right event for the status
        MessageConfig.IncidentEvent eventType = MessageConfig.IncidentEvent.IncidentUpdatedEvent;
        if(incident.getStatus().equals(IncidentStatus.REPORTED.toString()))
            eventType = MessageConfig.IncidentEvent.IncidentReportedEvent;

        // converting to json for cloud event
        String data = jsonb.toJson(incident);

        // The returned message will have the metadata added
        return decorateMessage(incident.getId(), eventType.toString(), INCIDENT_MESSAGE_SOURCE, data);
    }


    @Incoming("responder-event")
    @Outgoing("responder-event-to-kafka")
    public Message<CloudEvent> sendResponderEventToKafka(Responder responder) {

        // converting to json for cloud event
        String data = jsonb.toJson(responder);

        // The returned message will have the metadata added
        return decorateMessage(String.valueOf(responder.getId()), ResponderEvent.getEventforStatus(responder.isAvailable(), responder.isEnrolled()), RESPONDER_MESSAGE_SOURCE, data);
    }

    @Incoming("responder-command")
    @Outgoing("responder-command-to-kafka")
    public Message<CloudEvent> sendResponderCommandToKafka(Responder responder) {

        ResponderMessageCommand message = new ResponderMessageCommand.Builder(responder.getId())
                .name(responder.getName())
                .phoneNumber(responder.getPhoneNumber())
                .longitude(responder.getLongitude())
                .latitude(responder.getLatitude())
                .boatCapacity(responder.getBoatCapacity())
                .medicalkit(responder.getMedicalKit())
                .available(responder.isAvailable())
                .build();

        // The returned message will have the metadata added
        return decorateMessage(String.valueOf(responder.getId()), ResponderCommand.getEventforStatus(responder.isAvailable(), responder.isEnrolled()) , RESPONDER_MESSAGE_SOURCE, message.toString());
    }


    @Incoming("responder-location")
    @Outgoing("responder-location-to-kafka")
    public Message<CloudEvent> sendResponderLocationEventToKafka(ResponderLocationMessage message) {

        // converting to json for cloud event
        String data = jsonb.toJson(message);

        // The returned message will have the metadata added
        return decorateMessage(message.incidentId, RESPONDER_LOCATION_UPDATED_EVENT, RESPONDER_MESSAGE_SOURCE, data);
    }




    @Incoming("mission-command")
    @Outgoing("mission-command-to-kafka")
    public Message<CloudEvent> sendCommandToKafka(Mission mission) {

        // MissionCommandMessages are not the same as the mission object, extracing data for the command.
        MissionCommandMessage message = new MissionCommandMessage.Builder(mission.getIncidentId())
                .responderId(mission.getResponderId())
                .responderStartLongitude(mission.getResponderStartLong())
                .responderStartLatitude(mission.getResponderStartLat())
                .incidentLatitude(mission.getIncidentLat())
                .incidentLongitude(mission.getIncidentLong())
                .desitnationLatitude(mission.getDestinationLat())
                .desitnationLongitude(mission.getDestinationLong())
                .build();

        // converting to json for cloud event
        String data = jsonb.toJson(message);

        // The returned message will have the metadata added
        return decorateMessage(mission.getId(), MISSION_CREATED_COMMAND, MISSION_MESSAGE_SOURCE, data);
    }


    @Incoming("mission-event")
    @Outgoing("mission-event-to-kafka")
    public Message<CloudEvent> sendEventToKafka(Mission mission) {
        // get the right status for the event from mission
        String eventType = MissionEvent.getEventforStatus(mission.getStatus());

        // converting to json for cloud event
        String data = jsonb.toJson(mission);

        // The returned message will have the metadata added
        return decorateMessage(mission.getId(), eventType, MISSION_MESSAGE_SOURCE, data);
    }


}
