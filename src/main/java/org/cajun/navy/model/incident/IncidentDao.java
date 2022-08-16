package org.cajun.navy.model.incident;

import java.util.List;


public interface IncidentDao {
    Incident create(Incident incident);

    List<Incident> findAll();

    Incident findByIncidentId(String incidentId);

    Incident merge(Incident incident);

    List<Incident> findByStatus(String status);

    List<Incident> findByName(String pattern);

    void deleteAll();
}
