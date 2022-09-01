package org.cajun.navy.service.message;

public interface MessageConfig {

    public enum IncidentEvent {

        IncidentReportedEvent, IncidentUpdatedEvent

    }

    public String INCIDENT_MESSAGE_SOURCE ="emergency-response/incident-service";
    public String INCIDENT_UPDATE_COMMAND="UpdateIncidentCommand";


}
