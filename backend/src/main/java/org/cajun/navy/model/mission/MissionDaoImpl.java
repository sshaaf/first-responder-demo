package org.cajun.navy.model.mission;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class MissionDaoImpl implements MissionDao{

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public MissionEntity create(MissionEntity mission) {
        entityManager.persist(mission);
        return mission;
    }

    @Override
    public List<MissionEntity> findAll() {
        return entityManager.createNamedQuery("Mission.findAll", MissionEntity.class).getResultList();
    }

    @Override
    public List<ResponderLocationHistoryEntity> getResponderLocationHistoryByMission(String missionId) {
        return entityManager.createNamedQuery("Mission.findResponderLocationHistory", ResponderLocationHistoryEntity.class)
                .setParameter("missionId", missionId)
                .getResultList();
    }

    @Override
    public List<MissionStepEntity> getMissionStepsByMission(String missionId) {
        return entityManager.createNamedQuery("Mission.findMissionSteps", MissionStepEntity.class)
                .setParameter("missionId", missionId)
                .getResultList();
    }

    @Override
    public MissionEntity findByMissionId(String missionId) {
        if(missionId == null || missionId.isEmpty())
            return null;
        else {
            List<MissionEntity> missions = entityManager.createNamedQuery("Mission.byMissionId", MissionEntity.class)
                    .setParameter("missionId", missionId)
                    .getResultList();
            if(missions.isEmpty())
                return null;
            return missions.get(0);
        }
    }

    @Override
    public List<MissionEntity> findByStatus(String status) {
        return entityManager.createNamedQuery("Mission.byStatus", MissionEntity.class)
                .setParameter("status", status.toUpperCase()).getResultList();
    }


    @Override
    public List<MissionEntity> getByResponder(String responderId) {
        return entityManager.createNamedQuery("Mission.byResponderId", MissionEntity.class)
                .setParameter("responderId", responderId).getResultList();

    }

    @Override
    public List<MissionEntity> getCreatedAndUpdated(){
        return entityManager.createNamedQuery("Mission.byCreateOrUpdated", MissionEntity.class).getResultList();
    }

    @Override
    @Transactional
    public MissionEntity merge(MissionEntity mission) {
        MissionEntity m = entityManager.merge(mission);
        entityManager.flush();
        return m;
    }

}
