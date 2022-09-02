package org.cajun.navy.service.message.model;

import org.cajun.navy.model.mission.MissionStatus;

import javax.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;

public class ResponderLocationMessage {

    public String responderId;

    public String missionId;

    public String incidentId;

    public String status;

    public BigDecimal lat;

    public BigDecimal lon;

    // the following two props are set to a default, this will cause the UI not ta take any input from the user and keep on moving.
    @JsonbProperty("human")
    public boolean isHuman = false;

    @JsonbProperty("continue")
    public boolean shouldContinue = true;


    public static class Builder{

        private ResponderLocationMessage responderLocation;

        public Builder(String responderId){
            this.responderLocation = new ResponderLocationMessage();
            responderLocation.responderId = responderId;
        }

        public Builder missionId(String missionId){
            responderLocation.missionId = missionId;
            return this;
        }

        public Builder incidentId(String incidentId){
            responderLocation.incidentId = incidentId;
            return this;
        }

        public Builder status(String status, boolean isWayPoint){

            responderLocation.status = ResponderStatus.getEventforStatus(status, isWayPoint);
            return this;
        }

        public Builder lat(BigDecimal lat){
            responderLocation.lat = lat;
            return this;
        }

        public Builder lon(BigDecimal lon){
            responderLocation.lon = lon;
            return this;
        }

        public Builder isHuman(boolean human){
            responderLocation.isHuman = human;
            return this;
        }

        public Builder shouldContinue(boolean shouldContinue){
            responderLocation.shouldContinue = shouldContinue;
            return this;
        }

        public ResponderLocationMessage build(){
            return responderLocation;
        }

    }

    public enum ResponderStatus {
        PICKEDUP, DROPPED, MOVING;

        public static String getEventforStatus(String missionStatus, boolean isWayPoint){
            if(isWayPoint)
                return PICKEDUP.name();
            if(missionStatus.equals(MissionStatus.CREATED.toString()))
                return MOVING.name();
            if(missionStatus.equals(MissionStatus.UPDATED.toString()))
                return MOVING.name();
            if(missionStatus.equals(MissionStatus.COMPLETED.toString()))
                return DROPPED.name();
            else
                throw new IllegalArgumentException("no mission status found for type: "+missionStatus);
        }

    }

}
