package org.cajun.navy.service.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Incident {

    private String id;

    private BigDecimal lat;

    private BigDecimal lon;

    private int numberOfPeople;

    private boolean medicalNeeded;

    private String victimName;

    private String victimPhoneNumber;

    private Instant reportedTime;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public boolean isMedicalNeeded() {
        return medicalNeeded;
    }

    public void setMedicalNeeded(boolean medicalNeeded) {
        this.medicalNeeded = medicalNeeded;
    }

    public String getVictimName() {
        return victimName;
    }

    public void setVictimName(String victimName) {
        this.victimName = victimName;
    }

    public String getVictimPhoneNumber() {
        return victimPhoneNumber;
    }

    public void setVictimPhoneNumber(String victimPhoneNumber) {
        this.victimPhoneNumber = victimPhoneNumber;
    }

    public Instant getReportedTime() {
        return reportedTime;
    }

    public void setReportedTime(Instant reportedTime) {
        this.reportedTime = reportedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Builder{

        private Incident incident;

        public Builder(String incidentId){
            incident = new Incident();
            incident.id = incidentId;
        }

        public Builder lat(BigDecimal lat){
            incident.lat = lat;
            return this;
        }

        public Builder lon(BigDecimal lon){
            incident.lon = lon;
            return this;
        }

        public Builder numberOfPeople(int numberOfPeople){
            incident.numberOfPeople = numberOfPeople;
            return this;
        }

        public Builder medicatNeeded(boolean medicalNeeded){
            incident.medicalNeeded = medicalNeeded;
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

        public Builder reportedTime(Instant reportedTime){
            incident.reportedTime = reportedTime;
            return this;
        }
        public Builder status(String status){
            incident.status = status;
            return this;
        }

        public Incident build(){
            return incident;
        }
    }
}
