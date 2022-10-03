package org.cajun.navy.service.message.model;

import org.cajun.navy.service.model.Mission;

import java.math.BigDecimal;

// "{\"incidentId\":\"1e8a4df3-f0dc-49ca-9b7f-03c8390c7df5\",\"responderId\":\"23\",\"responderStartLat\":34.23880,\"responderStartLong\":-77.81675,\"incidentLat\":34.23842,\"incidentLong\":-77.77396,\"destinationLat\":34.2461,\"destinationLong\":-77.9519,\"processId\":\"36\"}"
public class MissionCommandMessage {

    public String incidentId;

    public String responderId;

    public String status;

    public BigDecimal responderStartLatitude;

    public BigDecimal responderStartLongitude;

    public BigDecimal incidentLatitude;

    public BigDecimal incidentLongtitude;

    public BigDecimal destinationLatitude;

    public BigDecimal destinationLongtitude;

    public int processId = 36;

    public static class Builder{
        private MissionCommandMessage missionCommandMessage;

        public Builder(String incidentId){
            missionCommandMessage = new MissionCommandMessage();
            missionCommandMessage.incidentId = incidentId;
        }

        public Builder responderId(String responderId){
            missionCommandMessage.responderId = responderId;
            return this;
        }
        public Builder status(String status){
            missionCommandMessage.status = status;
            return this;
        }
        public Builder responderStartLatitude(BigDecimal responderStartLatitude){
            missionCommandMessage.responderStartLatitude = responderStartLatitude;
            return this;
        }
        public Builder responderStartLongitude(BigDecimal responderStartLongitude){
            missionCommandMessage.responderStartLongitude = responderStartLongitude;
            return this;
        }
        public Builder incidentLatitude(BigDecimal incidentLatitude){
            missionCommandMessage.incidentLatitude = incidentLatitude;
            return this;
        }
        public Builder incidentLongitude(BigDecimal incidentLongitude){
            missionCommandMessage.incidentLongtitude = incidentLongitude;
            return this;
        }
        public Builder desitnationLatitude(BigDecimal desitnationLatitude){
            missionCommandMessage.destinationLatitude = desitnationLatitude;
            return this;
        }
        public Builder desitnationLongitude(BigDecimal desitnationLongitude){
            missionCommandMessage.destinationLongtitude = desitnationLongitude;
            return this;
        }

        public MissionCommandMessage build(){
            return  missionCommandMessage;
        }

    }
}
