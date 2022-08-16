package org.cajun.navy.model.mission;

import java.util.List;


public interface MissionDao {

    Mission create(Mission mission);

    List<Mission> findAll();

    Mission findByMissionId(String missionId);

    List<Mission> findByStatus(String status);

    List<Mission>  getByResponder(String responderId);


}
