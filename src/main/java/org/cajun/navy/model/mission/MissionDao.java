package org.cajun.navy.model.mission;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MissionDao {

    Mission create(Mission mission);

    List<Mission> findAll();

    Mission findByMissionId(String missionId);

    List<Mission> findByStatus(String status);

    List<Mission>  getByResponder(String responderId);


}
