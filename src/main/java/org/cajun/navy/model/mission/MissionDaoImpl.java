package org.cajun.navy.model.mission;

import org.cajun.navy.map.RoutePlanner;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateful
public class MissionDaoImpl implements MissionDao{

    @Inject
    EntityManager entityManager;

    @Override
    public Mission create(Mission mission) {
        mission.setStatus(MissionStatus.CREATED.toString());
        List<MissionStep> steps = RoutePlanner.getDirections(mission.responderLocation(), mission.destinationLocation(), mission.incidentLocation());
        mission.setSteps(steps);
        entityManager.persist(mission);
        return mission;
    }

    @Override
    public List<Mission> findAll() {
        return entityManager.createNamedQuery("Mission.findAll", Mission.class).getResultList();
    }

    @Override
    public Mission findByMissionId(String missionId) {
        if(missionId == null || missionId.isEmpty())
            return null;
        else {
            List<Mission> missions = entityManager.createNamedQuery("Mission.byMissionId", Mission.class)
                    .setParameter("missionId", missionId)
                    .getResultList();
            if(missions.isEmpty())
                return null;
            return missions.get(0);
        }
    }

    @Override
    public List<Mission> findByStatus(String status) {
        return entityManager.createNamedQuery("Mission.byStatus", Mission.class)
                .setParameter("status", status.toUpperCase()).getResultList();
    }


    @Override
    public List<Mission> getByResponder(String responderId) {
        return entityManager.createNamedQuery("Mission.byResponderId", Mission.class)
                .setParameter("responderId", responderId).getResultList();
    }
}
