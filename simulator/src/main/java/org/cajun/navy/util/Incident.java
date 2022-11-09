package org.cajun.navy.util;

import java.math.BigDecimal;

public class Incident {

    private BigDecimal lat;
    private BigDecimal lon;
    private int numberOfPeople = 0;
    private boolean isMedicalNeeded = false;
    private String victimName = null;
    private String victimPhoneNumber = null;


    public BigDecimal getLat() {
        return lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public boolean isMedicalNeeded() {
        return isMedicalNeeded;
    }

    public String getVictimName() {
        return victimName;
    }

    public String getVictimPhoneNumber() {
        return victimPhoneNumber;
    }

    public static class Builder{

        private Incident incident;

        public Builder(){
            incident = new Incident();
        }

        public Builder lat(double lat){
            incident.lat = BigDecimal.valueOf(lat);
            return this;
        }

        public Builder lon(double lon){
            incident.lon = BigDecimal.valueOf(lon);
            return this;
        }

        public Builder numberOfPeople(int numberOfPeople){
            incident.numberOfPeople = numberOfPeople;
            return this;
        }

        public Builder medicalNeeded(boolean medicalNeeded){
            incident.isMedicalNeeded = medicalNeeded;
            return this;
        }

        public Builder victimName(String victimName){
            incident.victimName = victimName;
            return this;
        }

        public Builder victimPhoneNumber(String victimPhoneNumber){
            incident.victimPhoneNumber = victimPhoneNumber;
            return this;
        }

        public Incident build(){
            return incident;
        }
    }

}
