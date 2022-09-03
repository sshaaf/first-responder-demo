package org.cajun.navy.service.message.model;


//{\"incident\":{\"id\":\"a6246742-6153-47cb-9fbf-d9fcc8bf5981\",\"status\":\"ASSIGNED\"}}
public class IncidentCommandMessage {

    public String id;
    public String status;

    public static class Builder{

        private IncidentCommandMessage incidentCommandMessage;

        public Builder(String id){
            incidentCommandMessage = new IncidentCommandMessage();
            incidentCommandMessage.id = id;
        }

        public Builder status(String status){
            incidentCommandMessage.status = status;
            return this;
        }

        public IncidentCommandMessage build(){
            return incidentCommandMessage;
        }
    }

    @Override
    public String toString() {
        return "{\"incident\":{\"id\":\""+id+"\",\"status\":\""+status+"\"}}";
    }
}
