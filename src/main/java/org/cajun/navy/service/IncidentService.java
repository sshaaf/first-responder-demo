package org.cajun.navy.service;

import org.cajun.navy.model.incident.Incident;
import org.cajun.navy.model.incident.IncidentDao;
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
    @Channel("incident")
    Emitter<Incident> incidentEmitter;

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
        incidentEmitter.send(incident);
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
