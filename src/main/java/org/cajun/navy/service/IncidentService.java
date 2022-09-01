package org.cajun.navy.service;

import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.incident.IncidentDao;
import org.cajun.navy.model.incident.IncidentStatus;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


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
        Incident item = incidentDao.create(incident);
        missionService.create(item);

        // fireEvent to Kafka
        fireEvent(item);
        return item;
    }

    // Emit incident
    public void fireEvent(Incident incident){
        incidentEventEmitter.send(incident);
        incidentCommandEmitter.send(incident);
    }

    public void updateStatus(IncidentStatus status, String incidentId){
        Incident incident = findByIncidentId(incidentId);
        incident.setStatus(status.toString());
        incidentDao.merge(incident);
        fireEvent(incident);
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

    @Transactional
    public void deleteAllIncidents(){
        incidentDao.deleteAll();
    }

}
