package org.cajun.navy.model.incident;

import java.util.List;


public interface IncidentDao {
    IncidentEntity create(IncidentEntity incident);

    List<IncidentEntity> findAll();

    IncidentEntity findByIncidentId(String incidentId);

    IncidentEntity merge(IncidentEntity incident);

    List<IncidentEntity> findByStatus(String status);

    List<IncidentEntity> findByName(String pattern);

    void deleteAll();
}
