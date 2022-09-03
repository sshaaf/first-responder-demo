package org.cajun.navy.service.model;

import org.cajun.navy.model.incident.IncidentEntity;
import org.cajun.navy.model.responder.ResponderEntity;

import java.math.BigDecimal;

public class Responder {

    private long id;

    private String name;

    private String phoneNumber;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Integer boatCapacity;

    private Boolean medicalKit;

    private Boolean available;

    private Boolean person;

    private Boolean enrolled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Integer getBoatCapacity() {
        return boatCapacity;
    }

    public void setBoatCapacity(Integer boatCapacity) {
        this.boatCapacity = boatCapacity;
    }

    public Boolean getMedicalKit() {
        return medicalKit;
    }

    public void setMedicalKit(Boolean medicalKit) {
        this.medicalKit = medicalKit;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getPerson() {
        return person;
    }

    public void setPerson(Boolean person) {
        this.person = person;
    }

    public Boolean getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Boolean enrolled) {
        this.enrolled = enrolled;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public boolean isPerson() {
        return person;
    }

    public boolean isMedicalKit() {
        return medicalKit;
    }

    public static class Builder {

        private final Responder responder;

        public Builder(long id) {
            this.responder = new Responder();
            responder.id = id;
        }

        public Builder name(String name) {
            responder.name = name;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            responder.phoneNumber = phoneNumber;
            return this;
        }

        public Builder latitude(BigDecimal latitude) {
            responder.latitude = latitude;
            return this;
        }

        public Builder longitude(BigDecimal longitude) {
            responder.longitude = longitude;
            return this;
        }

        public Builder boatCapacity(Integer boatCapacity) {
            responder.boatCapacity = boatCapacity;
            return this;
        }

        public Builder medicalKit(Boolean medicalKit) {
            responder.medicalKit = medicalKit;
            return this;
        }

        public Builder available(Boolean available) {
            responder.available = available;
            return this;
        }

        public Builder person(Boolean person) {
            responder.person = person;
            return this;
        }

        public Builder enrolled(Boolean enrolled) {
            responder.enrolled = enrolled;
            return this;
        }

        public Responder build() {
            return responder;
        }

    }


}
