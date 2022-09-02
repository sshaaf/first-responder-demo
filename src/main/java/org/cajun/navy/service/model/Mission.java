package org.cajun.navy.service.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Mission {

    private String id;

    private String incidentId;

    private String responderId;

    private String status;

    private BigDecimal responderStartLat;

    private BigDecimal responderStartLong;

    private BigDecimal incidentLat;

    private BigDecimal incidentLong;

    private BigDecimal destinationLat;

    private BigDecimal destinationLong;

    private List<ResponderLocationHistory> responderLocationHistory;

    private List<MissionStep> steps;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getResponderId() {
        return responderId;
    }

    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getResponderStartLat() {
        return responderStartLat;
    }

    public void setResponderStartLat(BigDecimal responderStartLat) {
        this.responderStartLat = responderStartLat;
    }

    public BigDecimal getResponderStartLong() {
        return responderStartLong;
    }

    public void setResponderStartLong(BigDecimal responderStartLong) {
        this.responderStartLong = responderStartLong;
    }

    public BigDecimal getIncidentLat() {
        return incidentLat;
    }

    public void setIncidentLat(BigDecimal incidentLat) {
        this.incidentLat = incidentLat;
    }

    public BigDecimal getIncidentLong() {
        return incidentLong;
    }

    public void setIncidentLong(BigDecimal incidentLong) {
        this.incidentLong = incidentLong;
    }

    public BigDecimal getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(BigDecimal destinationLat) {
        this.destinationLat = destinationLat;
    }

    public BigDecimal getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(BigDecimal destinationLong) {
        this.destinationLong = destinationLong;
    }

    public List<ResponderLocationHistory> getResponderLocationHistory() {
        return responderLocationHistory;
    }

    public void setResponderLocationHistory(List<ResponderLocationHistory> responderLocationHistory) {
        this.responderLocationHistory = responderLocationHistory;
    }

    public List<MissionStep> getSteps() {
        return steps;
    }

    public void setSteps(List<MissionStep> steps) {
        this.steps = steps;
    }

    public static class Builder {

        private final Mission mission;

        public Builder(String missionId){
            this.mission = new Mission();
            mission.id = missionId;
        }

        public Builder incidentId(String incidentId){
            mission.incidentId = incidentId;
            return this;
        }
        public Builder responderId(String responderId){
            mission.responderId = responderId;
            return this;
        }
        public Builder status(String status){
            mission.status = status;
            return this;
        }
        public Builder responderStartLatitude(BigDecimal responderStartLatitude){
            mission.responderStartLat = responderStartLatitude;
            return this;
        }
        public Builder responderStartLongitude(BigDecimal responderStartLongitude){
            mission.responderStartLong = responderStartLongitude;
            return this;
        }
        public Builder incidentLatitude(BigDecimal incidentLatitude){
            mission.incidentLat = incidentLatitude;
            return this;
        }
        public Builder incidentLongitude(BigDecimal incidentLongitude){
            mission.incidentLong = incidentLongitude;
            return this;
        }
        public Builder desitnationLatitude(BigDecimal desitnationLatitude){
            mission.destinationLat = desitnationLatitude;
            return this;
        }
        public Builder desitnationLongitude(BigDecimal desitnationLongitude){
            mission.destinationLong = desitnationLongitude;
            return this;
        }
        public Builder responderLocationHistory(List<ResponderLocationHistory> responderLocationHistory){
            mission.responderLocationHistory = responderLocationHistory;
            return this;
        }
        public Builder missionSteps(List<MissionStep> missionSteps){
            mission.steps = missionSteps;
            return this;
        }

        public Mission build(){
            return mission;
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return Objects.equals(responderId, mission.responderId) && Objects.equals(incidentId, mission.incidentId);
    }


    private String getKey(){
        return this.incidentId + ":" + this.responderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }

}
