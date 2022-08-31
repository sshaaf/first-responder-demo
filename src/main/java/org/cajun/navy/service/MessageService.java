package org.cajun.navy.service;

import io.smallrye.reactive.messaging.kafka.api.KafkaMetadataUtil;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.mission.MissionEntity;
import org.eclipse.microprofile.reactive.messaging.*;

import javax.enterprise.context.Dependent;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@Dependent
public class MessageService {

    private static final Jsonb jsonb = JsonbBuilder.create();

    @Incoming("incident")
    @Outgoing("incident-event")
    public Message<String> sendToKafka(Incident incident) {

        String data = jsonb.toJson(incident);
        Message<String> m = Message.of(data);

        // Create Metadata containing the Kafka key
        OutgoingKafkaRecordMetadata<String> md = OutgoingKafkaRecordMetadata
                .<String>builder().withKey(incident.getIncidentId())
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
