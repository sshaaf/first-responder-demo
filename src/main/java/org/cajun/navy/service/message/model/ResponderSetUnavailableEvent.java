package org.cajun.navy.service.message.model;

import org.cajun.navy.model.responder.ResponderEntity;

public class ResponderSetUnavailableEvent {

    public ResponderEntity responder;

    public String status = "success";

    public String statusMessage = "Responder updated";


    public static class Builder{

        private ResponderSetUnavailableEvent event;

        public Builder(ResponderEntity responder){
            event = new ResponderSetUnavailableEvent();
            event.responder = responder;
        }

        public ResponderSetUnavailableEvent build(){
            return event;
        }
    }

    @Override
    public String toString() {
        return "{\"ResponderSetUnavailableEvent\":{"
                + ",\"status\":\"" + status + "\""
                + ",\"statusMessage\":\"" + statusMessage + ",\""
                + "\"responder\":{"+
                "\"id\":\"" + responder.getId() + "\""
                + ",\"name\":\"" + responder.getName() + "\""
                + ",\"phoneNumber\":\"" + responder.getPhoneNumber() + "\""
                + ",\"latitude\":\"" + responder.getLatitude() + "\""
                + ",\"longitude\":\"" + responder.getLongitude() + "\""
                + ",\"boatCapacity\":\"" + responder.getBoatCapacity() + "\""
                + ",\"medicalKit\":\"" + responder.getMedicalKit() + "\""
                + ",\"available\":\"" + responder.isAvailable() + "\""
                + "}}";
    }
}
