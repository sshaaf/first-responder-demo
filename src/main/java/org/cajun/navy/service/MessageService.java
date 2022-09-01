package org.cajun.navy.service;

import io.smallrye.reactive.messaging.kafka.api.KafkaMetadataUtil;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.incident.IncidentStatus;
import org.cajun.navy.model.mission.MissionEntity;
import org.cajun.navy.service.message.MessageConfig;
import org.cajun.navy.service.message.model.IncidentCommandMessage;
import org.eclipse.microprofile.reactive.messaging.*;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import javax.enterprise.context.Dependent;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

import static org.cajun.navy.service.message.MessageConfig.INCIDENT_MESSAGE_SOURCE;
import static org.cajun.navy.service.message.MessageConfig.INCIDENT_UPDATE_COMMAND;

@Dependent
public class MessageService {

    private static final Jsonb jsonb = JsonbBuilder.create();




    private CloudEvent eventBuilder(String id, String type, String source, String data){
        final CloudEvent event = CloudEventBuilder.v1()
                .withId(id)
                .withType(type)
                .withSource(URI.create(source))
                .withData(data.getBytes(StandardCharsets.UTF_8))
                .withTime(OffsetDateTime.from(OffsetDateTime.now().toZonedDateTime()))
                .build();
        return event;
    }


    @Incoming("incident-command")
    @Outgoing("incident-command-to-kafka")
    public Message<CloudEvent> sendCommandToKafka(Incident incident) {

        String data = new IncidentCommandMessage(incident.getIncidentId(), incident.getStatus().toString()).toString();

        CloudEvent event = eventBuilder(incident.getIncidentId(), INCIDENT_UPDATE_COMMAND, INCIDENT_MESSAGE_SOURCE, data);
        Message<CloudEvent> m = Message.of(event);

        // Create Metadata containing the Kafka key
        OutgoingKafkaRecordMetadata<CloudEvent> md = OutgoingKafkaRecordMetadata
                .<CloudEvent>builder()
                .build();

        // The returned message will have the metadata added
        return KafkaMetadataUtil.writeOutgoingKafkaMetadata(m, md);
    }

    @Incoming("incident-event")
    @Outgoing("incident-event-to-kafka")
    public Message<CloudEvent> sendEventToKafka(Incident incident) {
        MessageConfig.IncidentEvent eventType = MessageConfig.IncidentEvent.IncidentUpdatedEvent;
        if(incident.getStatus().equals(IncidentStatus.REPORTED.toString()))
            eventType = MessageConfig.IncidentEvent.IncidentReportedEvent;

        String data = jsonb.toJson(incident);

        CloudEvent event = eventBuilder(incident.getIncidentId(), eventType.toString(), INCIDENT_MESSAGE_SOURCE, data);
        Message<CloudEvent> m = Message.of(event);

        // Create Metadata containing the Kafka key
        OutgoingKafkaRecordMetadata<CloudEvent> md = OutgoingKafkaRecordMetadata
                .<CloudEvent>builder()
                .build();

        // The returned message will have the metadata added
        return KafkaMetadataUtil.writeOutgoingKafkaMetadata(m, md);
    }



    @Incoming("mission")
    @Outgoing("mission-event")
    public Message<String> sendToKafka(MissionEntity mission) {

        String data = jsonb.toJson(mission);
        Message<String> m = Message.of(data);

        // Create Metadata containing the Kafka key
        OutgoingKafkaRecordMetadata<String> md = OutgoingKafkaRecordMetadata
                .<String>builder().withKey(mission.getIncidentId())
                .build();

        // The returned message will have the metadata added
        return KafkaMetadataUtil.writeOutgoingKafkaMetadata(m, md);
    }


}
