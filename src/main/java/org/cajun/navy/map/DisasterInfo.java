package org.cajun.navy.map;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.*;

/**
 * Gets the details of a disaster from the json file placed under resources.
 * At this point the default json file is resources/willmington.json.
 * config included Shelters, InclusionZones, DisasterCenter
 * */

@ApplicationScoped
public class DisasterInfo {

    private Shelter[] shelters;

    private InclusionZone[] inclusionZones;

    DisasterCenter center;

    public Shelter[] getShelters() {
        return shelters;
    }

    public DisasterCenter getCenter() {
        return center;
    }

    public static List<InclusionZone> getZones() {
        return zones;
    }

    public static List<InclusionZone> zones;

    public Shelter getFirstShelter(){
        return shelters[0];
    }

    public DisasterInfo(){
        loadModel();
    }

    // ensure we can load the disaster information from the json into our model.
    public void loadModel(){
        Jsonb jsonb = JsonbBuilder.create();
        JsonObject jsonObject = jsonb.fromJson(this.getClass().getClassLoader().getResourceAsStream("wilmington.json"), JsonObject.class);
        shelters = jsonb.fromJson(jsonObject.getJsonArray("shelters").toString(), Shelter[].class);
        inclusionZones = jsonb.fromJson(jsonObject.getJsonArray("inclusionZones").toString(), InclusionZone[].class);
        center = jsonb.fromJson(jsonObject.getJsonObject("center").toString(), DisasterCenter.class);
    }

    // just get a random shleter from the loaded shelters.
    public Shelter getRandomShelter(){
        int min = 0;
        int max = shelters.length-1;
        int key = (int) (Math.random() * (max - min)) + min;
        return shelters[key];
    }

    public InclusionZone[] getInclusionZones(){
        return inclusionZones;
    }

}
