package org.cajun.navy.map;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

// For simplicity only using Shelters, omitting DisasterCenter and InclusionZones
public class DisasterInfo {

    // TODO: if ever we extend, its possible to load this directly from a Json file instead.
    // The following shelters are the destination/s for all missions to route to.
    public static Map<String, Shelter> shelters;
    static {
        shelters = new HashMap<>();
        shelters.put("1", Shelter.from( "1","Port City Marina", BigDecimal.valueOf(-77.9519),BigDecimal.valueOf(34.2461),0));
        shelters.put("2", Shelter.from( "2","Wilmington Marine Center",BigDecimal.valueOf(-77.949),BigDecimal.valueOf(34.1706),0));
        shelters.put("3", Shelter.from( "3","Carolina Beach Yacht Club",BigDecimal.valueOf(-77.8885),BigDecimal.valueOf(34.0583),0));
    }


    public static Shelter getFirstShelter(){
        return shelters.get(1);
    }

    public static Shelter getRandomShelter(){
        int min = 1;
        int max = shelters.size();
        String key = String.valueOf((int) (Math.random() * (max - min)) + min);
        return shelters.get(key);
    }

}
