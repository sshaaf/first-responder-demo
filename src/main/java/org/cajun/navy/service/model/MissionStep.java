package org.cajun.navy.service.model;

import java.math.BigDecimal;

public class MissionStep {

    private BigDecimal latitude;

    private BigDecimal longitude;

    private boolean wayPoint = false;

    private boolean destination = false;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public boolean isWayPoint() {
        return wayPoint;
    }

    public void setWayPoint(boolean wayPoint) {
        this.wayPoint = wayPoint;
    }

    public boolean isDestination() {
        return destination;
    }

    public void setDestination(boolean destination) {
        this.destination = destination;
    }

    public static class Builder {
        private final MissionStep missionStep;

        public Builder(){
            missionStep = new MissionStep();
        }

        public Builder latitude(BigDecimal latitude){
            missionStep.latitude = latitude;
            return this;
        }

        public Builder longitude(BigDecimal longitude) {
            missionStep.longitude = longitude;
            return this;
        }

        public Builder wayPoint(boolean wayPoint){
            missionStep.wayPoint = wayPoint;
            return this;
        }

        public Builder destination(boolean destination){
            missionStep.wayPoint = destination;
            return this;
        }

        public MissionStep build(){
            return missionStep;
        }

    }


}
