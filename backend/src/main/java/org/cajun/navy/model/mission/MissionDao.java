package org.cajun.navy.model.mission;


import java.util.List;

public interface MissionDao {

    MissionEntity create(MissionEntity mission);

    List<MissionEntity> findAll();

    MissionEntity findByMissionId(String missionId);

    List<MissionEntity> findByStatus(String status);

    List<MissionEntity>  getByResponder(String responderId);

    List<MissionEntity> getCreatedAndUpdated();

    List<ResponderLocationHistoryEntity> getResponderLocationHistoryByMission(String missionId);

    List<MissionStepEntity> getMissionStepsByMission(String missionId);

    MissionEntity merge(MissionEntity mission);
}
