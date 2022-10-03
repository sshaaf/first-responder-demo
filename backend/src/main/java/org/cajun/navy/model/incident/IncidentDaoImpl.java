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
    public IncidentEntity create(IncidentEntity incident) {
        String incidentId = UUID.randomUUID().toString();
        long reportedTimestamp = System.currentTimeMillis();

        incident.setIncidentId(incidentId);
        incident.setReportedTime(Instant.ofEpochMilli(reportedTimestamp));
        incident.setStatus(IncidentStatus.REPORTED.toString());
        entityManager.persist(incident);
        return incident;
    }

    @Override
    public List<IncidentEntity> findAll() {
        return entityManager.createNamedQuery("Incident.findAll", IncidentEntity.class).getResultList();
    }

    @Override
    public IncidentEntity findByIncidentId(String incidentId) {
        if (incidentId == null || incidentId.isEmpty()) {
            return null;
        }
        List<IncidentEntity> incidents = entityManager.createNamedQuery("Incident.byIncidentId", IncidentEntity.class)
                .setParameter("incidentId", incidentId)
                .getResultList();
        if (incidents.isEmpty()) {
            return null;
        }
        return incidents.get(0);
    }

    @Override
    @Transactional
    public IncidentEntity merge(IncidentEntity incident) {
        IncidentEntity r = entityManager.merge(incident);
        entityManager.flush();
        return r;
    }

    @Override
    public List<IncidentEntity> findByStatus(String status) {
        return entityManager.createNamedQuery("Incident.byStatus", IncidentEntity.class)
                .setParameter("status", status.toUpperCase()).getResultList();
    }

    @Override
    public List<IncidentEntity> findByName(String pattern) {
        return entityManager.createNamedQuery("Incident.findByName", IncidentEntity.class)
                .setParameter("pattern", pattern.toLowerCase()).getResultList();
    }

    @Override
    @Transactional
    public void deleteAll() {
        Query deleteAll = entityManager.createNamedQuery("Incident.deleteAll");
        deleteAll.executeUpdate();
    }

}
