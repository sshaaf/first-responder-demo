package org.cajun.navy.model.incident;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


public class IncidentDaoImpl implements IncidentDao {


    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public Incident create(Incident incident) {
        return doCreate(incident);
    }

    private Incident doCreate(Incident incident){
        String incidentId = UUID.randomUUID().toString();
        long reportedTimestamp = System.currentTimeMillis();

        incident.setIncidentId(incidentId);
        incident.setReportedTime(Instant.ofEpochMilli(reportedTimestamp));
        incident.setStatus(IncidentStatus.REPORTED.toString());
        entityManager.persist(incident);
        return incident;
    }

    @Override
    public List<Incident> findAll() {
        return entityManager.createNamedQuery("Incident.findAll", Incident.class).getResultList();
    }

    @Override
    public Incident findByIncidentId(String incidentId) {
        if (incidentId == null || incidentId.isEmpty()) {
            return null;
        }
        List<Incident> incidents = entityManager.createNamedQuery("Incident.byIncidentId", Incident.class)
                .setParameter("incidentId", incidentId)
                .getResultList();
        if (incidents.isEmpty()) {
            return null;
        }
        return incidents.get(0);
    }

    @Override
    @Transactional
    public Incident merge(Incident incident) {
        Incident r = entityManager.merge(incident);
        entityManager.flush();
        return r;
    }

    @Override
    public List<Incident> findByStatus(String status) {
        return entityManager.createNamedQuery("Incident.byStatus", Incident.class)
                .setParameter("status", status.toUpperCase()).getResultList();
    }

    @Override
    public List<Incident> findByName(String pattern) {
        return entityManager.createNamedQuery("Incident.findByName", Incident.class)
                .setParameter("pattern", pattern.toLowerCase()).getResultList();
    }

    @Override
    @Transactional
    public void deleteAll() {
        Query deleteAll = entityManager.createNamedQuery("Incident.deleteAll");
        deleteAll.executeUpdate();
    }

}
