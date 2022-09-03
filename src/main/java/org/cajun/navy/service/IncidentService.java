package org.cajun.navy.service;

import org.cajun.navy.model.incident.IncidentDao;
import org.cajun.navy.model.incident.IncidentEntity;
import org.cajun.navy.model.incident.IncidentStatus;
import org.cajun.navy.service.model.Incident;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RequestScoped
public class IncidentService {

    @Inject
    IncidentDao incidentDao;

    @Inject
    MissionService missionService;

    @Inject
    @Channel("incident-event")
    Emitter<Incident> incidentEventEmitter;

    @Inject
    @Channel("incident-command")
    Emitter<Incident> incidentCommandEmitter;

    @Transactional
    public Incident createIncident(Incident incident) {
        IncidentEntity item = incidentDao.create(toIncidentEntity(incident));
        missionService.create(item);

        Incident createdIncident = fromIncidentEntity(item);

        // fireEvent to Kafka
        fireEvent(createdIncident);
        return createdIncident;
    }

    // Emit incident
    public void fireEvent(Incident incident){
        incidentEventEmitter.send(incident);
        incidentCommandEmitter.send(incident);
    }

    public void updateStatus(IncidentStatus status, String incidentId){
        IncidentEntity incident = incidentDao.findByIncidentId(incidentId);
        incident.setStatus(status.toString());
        incidentDao.merge(incident);
        fireEvent(fromIncidentEntity(incident));
    }

    public List<Incident> findByStatus(String status) {
        return incidentDao.findByStatus(status).stream().map(
                this::fromIncidentEntity
        ).collect(Collectors.toList());
    }

    public List<Incident> findAll() {
        return incidentDao.findAll().stream().map(
                this::fromIncidentEntity
        ).collect(Collectors.toList());
    }

    public List<Incident> findByName(String pattern) {
        return incidentDao.findByName(pattern).stream().map(
                this::fromIncidentEntity
        ).collect(Collectors.toList());
    }

    public Incident findByIncidentId(String incidentId) {
        return fromIncidentEntity(incidentDao.findByIncidentId(incidentId));
    }

    @Transactional
    public void deleteAllIncidents(){
        incidentDao.deleteAll();
    }

    private Incident fromIncidentEntity(IncidentEntity incidentEntity){
        return new Incident.Builder(incidentEntity.getIncidentId())
                .victimName(incidentEntity.getVictimName())
                .victimPhoneNumber(incidentEntity.getVictimPhoneNumber())
                .reportedTime(incidentEntity.getReportedTime())
                .status(incidentEntity.getStatus())
                .numberOfPeople(incidentEntity.getNumberOfPeople())
                .medicatNeeded(incidentEntity.isMedicalNeeded())
                .lat(incidentEntity.getLat())
                .lon(incidentEntity.getLon())
                .build();
    }

    private IncidentEntity toIncidentEntity(Incident incident){
        if(incident != null) {
            return new IncidentEntity.Builder(incident.getId())
                    .victimName(incident.getVictimName())
                    .victimPhoneNumber(incident.getVictimPhoneNumber())
                    .reportedTime(incident.getReportedTime())
                    .status(incident.getStatus())
                    .numberOfPeople(incident.getNumberOfPeople())
                    .medicatNeeded(incident.isMedicalNeeded())
                    .lat(incident.getLat())
                    .lon(incident.getLon())
                    .build();
        }
        else throw new IllegalArgumentException("Incident was null");

    }


}
