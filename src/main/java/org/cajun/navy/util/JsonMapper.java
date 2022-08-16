package org.cajun.navy.util;

import org.cajun.navy.model.incident.Incident;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class JsonMapper {

    private static Jsonb jsonb = JsonbBuilder.create();

    public static String getJson(Incident incident){
        // Convert the Object into JSON representation
        return jsonb.toJson(incident);
    }

    public static Incident getIncident(String json){
        return jsonb.fromJson(json, Incident.class);
    }


}
