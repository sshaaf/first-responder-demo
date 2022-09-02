package org.cajun.navy.service.model;

import java.math.BigDecimal;

public class MissionStep {

    private BigDecimal lat;

    private BigDecimal lon;

    private boolean wayPoint = false;

    private boolean destination = false;

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
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
            missionStep.lat = latitude;
            return this;
        }

        public Builder longitude(BigDecimal longitude) {
            missionStep.lon = longitude;
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
