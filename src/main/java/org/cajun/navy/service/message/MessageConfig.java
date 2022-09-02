package org.cajun.navy.service.message;

import org.cajun.navy.model.mission.MissionStatus;

public interface MessageConfig {

    public String INCIDENT_MESSAGE_SOURCE ="emergency-response/incident-service";
    public String INCIDENT_UPDATE_COMMAND="UpdateIncidentCommand";
    public enum IncidentEvent {
        IncidentReportedEvent, IncidentUpdatedEvent
    }

    public String MISSION_MESSAGE_SOURCE ="emergency-response/mission-service";
    public String MISSION_CREATED_COMMAND="CreateMissionCommand";
    public enum MissionEvent {
        MissionStartedEvent, MissionPickedUpEvent, MissionCompletedEvent;

        public static String getEventforStatus(String missionStatus){
            if(missionStatus.equals(MissionStatus.CREATED.toString()))
                return MissionStartedEvent.name();
            if(missionStatus.equals(MissionStatus.UPDATED.toString()))
                return MissionPickedUpEvent.name();
            if(missionStatus.equals(MissionStatus.COMPLETED.toString()))
                return MissionCompletedEvent.name();
            else
                throw new IllegalArgumentException("no mission status found for type: "+missionStatus);
        }

    }

}
