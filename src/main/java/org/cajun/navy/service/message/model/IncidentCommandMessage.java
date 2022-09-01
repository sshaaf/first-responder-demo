package org.cajun.navy.service.message.model;


//{\"incident\":{\"id\":\"a6246742-6153-47cb-9fbf-d9fcc8bf5981\",\"status\":\"ASSIGNED\"}}
public class IncidentCommandMessage {

    private String id;
    private String status;

    public IncidentCommandMessage(String id, String status){
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Incident:{" +
                "id:'" + id + '\'' +
                ", status:'" + status + '\'' +
                '}';
    }
}
