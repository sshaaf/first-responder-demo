package org.cajun.navy.service;

import io.smallrye.reactive.messaging.kafka.api.KafkaMetadataUtil;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.incident.IncidentDao;
import org.cajun.navy.util.JsonMapper;
import org.eclipse.microprofile.reactive.messaging.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;


@ApplicationScoped
public class IncidentService {

    @Inject
    IncidentDao incidentDao;

    @Inject
    @Channel("incident")
    Emitter<Incident> emitter;

    public Incident createIncident(Incident incident) {
        Incident item = incidentDao.create(incident);
        send(item);
        return item;
    }


    public List<Incident> findByStatus(String status) {
        return incidentDao.findByStatus(status);
    }

    public List<Incident> findAll() {
        return incidentDao.findAll();
    }

    public List<Incident> findByName(String pattern) {
        return incidentDao.findByName(pattern);
    }

    public Incident findByIncidentId(String incidentId) {
        return incidentDao.findByIncidentId(incidentId);
    }

    public void deleteAllIncidents(){
        incidentDao.deleteAll();
    }

    // Emit incident
    public void send(Incident incident){
        emitter.send(incident);
    }


    @Incoming("incident")
    @Outgoing("send-event")
    public String handle(Incident incident) {
        // Get the incident from the channel and forward it as a String.
        // any processing should be done here before its changed into String,
        // since the intention is to send the JSON into the kafka topic
        return JsonMapper.getJson(incident);
    }

    @Incoming("send-event")
    @Outgoing("incident-reported-event")
    public Message<String> sendToKafka(String msg) {

        Message<String> m = Message.of(msg);

        // Create Metadata containing the Kafka key
        OutgoingKafkaRecordMetadata<Integer> md = OutgoingKafkaRecordMetadata
                .<Integer>builder()
                .build();

        // The returned message will have the metadata added
        return KafkaMetadataUtil.writeOutgoingKafkaMetadata(m, md);
    }



}
