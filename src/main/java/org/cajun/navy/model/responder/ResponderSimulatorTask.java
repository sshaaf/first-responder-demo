package org.cajun.navy.model.responder;

import org.cajun.navy.model.mission.MissionEntity;
import org.cajun.navy.service.MissionService;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public class ResponderSimulatorTask {

    private static final Logger logger = Logger.getLogger(ResponderSimulatorTask.class.getName());

    @Inject
    MissionService missionService;

    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void doMoveResponderLocation(){
        List<MissionEntity> missions = missionService.getAllCreatedOrUpdated();
        logger.info("Are there any missions for next move? "+missions.size());
        missions.forEach(mission -> {
            missionService.doResponderNextMove(mission);
        });
    }
}
