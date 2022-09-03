package org.cajun.navy.service.message;

import org.cajun.navy.model.mission.MissionStatus;

public interface MessageConfig {

    String INCIDENT_MESSAGE_SOURCE ="emergency-response/incident-service";
    String INCIDENT_UPDATE_COMMAND="UpdateIncidentCommand";
    enum IncidentEvent {
        IncidentReportedEvent, IncidentUpdatedEvent
    }

    String RESPONDER_LOCATION_UPDATED_EVENT="ResponderLocationUpdatedEvent";
    String RESPONDER_MESSAGE_SOURCE ="emergency-response/responder-service";

    enum ResponderCommand {
        SetResponderUnavailableCommand, UpdateResponderCommand;

        public static String getEventforStatus(boolean available, boolean enrolled){
            if(!available)
                return SetResponderUnavailableCommand.name();
            else
                return UpdateResponderCommand.name();
        }

    }

    enum ResponderEvent {
        ResponderSetUnavailableEvent, ResponderUpdatedEvent;

        public static String getEventforStatus(boolean available, boolean enrolled){
            if(!available && !enrolled)
                return ResponderSetUnavailableEvent.name();
            else
                return ResponderUpdatedEvent.name();
        }

        }


    String MISSION_MESSAGE_SOURCE ="emergency-response/mission-service";
    String MISSION_CREATED_COMMAND="CreateMissionCommand";
    enum MissionEvent {
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
