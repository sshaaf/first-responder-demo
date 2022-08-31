package org.cajun.navy.service.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Mission {

    private String missionId;

    private String incidentId;

    private String responderId;

    private String status;

    private BigDecimal responderStartLatitude;

    private BigDecimal responderStartLongitude;

    private BigDecimal incidentLatitude;

    private BigDecimal incidentLongtitude;

    private BigDecimal destinationLatitude;

    private BigDecimal destinationLongtitude;

    private List<ResponderLocationHistory> responderLocationHistory;

    private List<MissionStep> steps;


    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
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

    public BigDecimal getResponderStartLatitude() {
        return responderStartLatitude;
    }

    public void setResponderStartLatitude(BigDecimal responderStartLatitude) {
        this.responderStartLatitude = responderStartLatitude;
    }

    public BigDecimal getResponderStartLongitude() {
        return responderStartLongitude;
    }

    public void setResponderStartLongitude(BigDecimal responderStartLongitude) {
        this.responderStartLongitude = responderStartLongitude;
    }

    public BigDecimal getIncidentLatitude() {
        return incidentLatitude;
    }

    public void setIncidentLatitude(BigDecimal incidentLatitude) {
        this.incidentLatitude = incidentLatitude;
    }

    public BigDecimal getIncidentLongtitude() {
        return incidentLongtitude;
    }

    public void setIncidentLongtitude(BigDecimal incidentLongtitude) {
        this.incidentLongtitude = incidentLongtitude;
    }

    public BigDecimal getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(BigDecimal destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public BigDecimal getDestinationLongtitude() {
        return destinationLongtitude;
    }

    public void setDestinationLongtitude(BigDecimal destinationLongtitude) {
        this.destinationLongtitude = destinationLongtitude;
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
            mission.missionId = missionId;
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
            mission.responderStartLatitude = responderStartLatitude;
            return this;
        }
        public Builder responderStartLongitude(BigDecimal responderStartLongitude){
            mission.responderStartLongitude = responderStartLongitude;
            return this;
        }
        public Builder incidentLatitude(BigDecimal incidentLatitude){
            mission.incidentLatitude = incidentLatitude;
            return this;
        }
        public Builder incidentLongitude(BigDecimal incidentLongitude){
            mission.incidentLongtitude = incidentLongitude;
            return this;
        }
        public Builder desitnationLatitude(BigDecimal desitnationLatitude){
            mission.destinationLatitude = desitnationLatitude;
            return this;
        }
        public Builder desitnationLongitude(BigDecimal desitnationLongitude){
            mission.destinationLongtitude = desitnationLongitude;
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


    public String getKey(){
        return this.incidentId + ":" + this.responderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }

}
