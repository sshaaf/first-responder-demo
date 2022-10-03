package org.cajun.navy.service.message.model;

import java.math.BigDecimal;

public class ResponderMessageCommand {

    public long id;

    public String name;

    public String phoneNumber;

    public BigDecimal latitude;

    public BigDecimal longitude;

    public int boatCapacity;

    public Boolean medicalKit;

    public Boolean available;

    public static class Builder{

        private ResponderMessageCommand responderMessageCommand;

        public Builder(long id){
            responderMessageCommand = new ResponderMessageCommand();
            responderMessageCommand.id = id;
        }

        public Builder name(String name){
            responderMessageCommand.name = name;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            responderMessageCommand.phoneNumber = phoneNumber;
            return this;
        }

        public Builder latitude(BigDecimal latitude){
            responderMessageCommand.latitude = latitude;
            return this;
        }
        public Builder longitude(BigDecimal longitude){
            responderMessageCommand.longitude = longitude;
            return this;
        }

        public Builder boatCapacity(int boatCapacity){
            responderMessageCommand.boatCapacity = boatCapacity;
            return this;
        }

        public Builder medicalkit(boolean medicalKit){
            responderMessageCommand.medicalKit = medicalKit;
            return this;
        }

        public Builder available(boolean available){
            responderMessageCommand.available = available;
            return this;
        }

        public ResponderMessageCommand build(){
            return responderMessageCommand;
        }

    }

    @Override
    public String toString() {
        return "{\"responder\":{"
                + "" +
                "\"id\":\"" + id + "\""
                + ",\"name\":\"" + name + "\""
                + ",\"phoneNumber\":\"" + phoneNumber + "\""
                + ",\"latitude\":\"" + latitude + "\""
                + ",\"longitude\":\"" + longitude + "\""
                + ",\"boatCapacity\":\"" + boatCapacity + "\""
                + ",\"medicalKit\":\"" + medicalKit + "\""
                + ",\"available\":\"" + available + "\""
                + "}}";
    }
}
