package org.cajun.navy.model.responder;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
@SequenceGenerator(name="ResponderSeq", sequenceName="responder_table_sequence", allocationSize = 10)
@Table(name = "responder_table")
@NamedQueries({
        @NamedQuery(name = "Responder.allResponders", query = "SELECT r FROM ResponderEntity r"),
        @NamedQuery(name = "Responder.findByName", query = "SELECT r FROM ResponderEntity r WHERE r.name = :name"),
        @NamedQuery(name = "Responder.availableResponders", query = "SELECT r FROM ResponderEntity r WHERE r.available = true and r.enrolled = true"),
        @NamedQuery(name = "Responder.availableRespondersOrderedByPerson", query = "SELECT r FROM ResponderEntity r WHERE r.available = true and r.enrolled = true ORDER BY r.person DESC NULLS LAST, r.id ASC"),
        @NamedQuery(name = "Responder.persons", query = "SELECT r FROM ResponderEntity r where r.person = true"),
        @NamedQuery(name = "Responder.nonPersons", query = "SELECT r from ResponderEntity r where r.person = false"),
        @NamedQuery(name = "Responder.countEnrolled", query = "SELECT COUNT(r.id) FROM ResponderEntity r WHERE r.enrolled = true"),
        @NamedQuery(name = "Responder.countActive", query = "SELECT COUNT(r.id) FROM ResponderEntity r WHERE r.enrolled = true AND r.available = false"),
        @NamedQuery(name = "Responder.deleteAll", query = "DELETE FROM ResponderEntity"),
        @NamedQuery(name = "Responder.deleteNonPersons", query = "DELETE FROM ResponderEntity r where r.person = false"),
        @NamedQuery(name = "Responder.clearNonPersons", query = "UPDATE ResponderEntity r SET r.available = false, r.enrolled = false WHERE r.person = false"),
        @NamedQuery(name = "Responder.reset", query = "UPDATE ResponderEntity r SET r.available = true, r.enrolled = false WHERE r.person = false"),
        @NamedQuery(name = "Responder.resetPerson", query = "UPDATE ResponderEntity r SET r.available = true, r.enrolled = false, latitude = null,  longitude = null WHERE r.person = true")
})
public class ResponderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="ResponderSeq")
    @Column(name = "responder_id")
    private long id;

    @Column(name = "responder_name")
    private String name;

    @Column(name = "responder_phone_number")
    private String phoneNumber;

    @Column(name = "responder_current_gps_lat", scale = 5, precision = 7)
    private BigDecimal latitude;

    @Column(name = "responder_current_gps_long", scale = 5, precision = 7)
    private BigDecimal longitude;

    @Column(name = "boat_capacity")
    private Integer boatCapacity;

    @Column(name = "has_medical_kit")
    private Boolean medicalKit;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "person")
    private Boolean person;

    @Column(name = "enrolled")
    private Boolean enrolled;

    @JsonbTransient
    @Column(name = "version")
    @Version
    private long version;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Integer getBoatCapacity() {
        return boatCapacity;
    }

    public Boolean getMedicalKit() {
        return medicalKit;
    }

    public Boolean isAvailable() {
        return available;
    }

    public Boolean isPerson() {
        return person;
    }

    public Boolean isEnrolled() {
        return enrolled;
    }

    public long getVersion() {
        return version;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setBoatCapacity(Integer boatCapacity) {
        this.boatCapacity = boatCapacity;
    }

    public void setMedicalKit(Boolean medicalKit) {
        this.medicalKit = medicalKit;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setPerson(Boolean person) {
        this.person = person;
    }

    public void setEnrolled(Boolean enrolled) {
        this.enrolled = enrolled;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponderEntity responder = (ResponderEntity) o;

        if (!(id ==responder.id)) return false;
        if (!Objects.equals(name, responder.name)) return false;
        if (!Objects.equals(phoneNumber, responder.phoneNumber))
            return false;
        if (!Objects.equals(latitude, responder.latitude)) return false;
        if (!Objects.equals(longitude, responder.longitude)) return false;
        if (!Objects.equals(boatCapacity, responder.boatCapacity))
            return false;
        if (!Objects.equals(medicalKit, responder.medicalKit)) return false;
        return Objects.equals(available, responder.available);
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (boatCapacity != null ? boatCapacity.hashCode() : 0);
        result = 31 * result + (medicalKit != null ? medicalKit.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }


    public static class Builder {

        private final ResponderEntity responder;

        public Builder() {
            this.responder = new ResponderEntity();
        }

        public Builder(long id) {
            this.responder = new ResponderEntity();
            responder.id = id;
        }

        public Builder(long id, long version) {
            this.responder = new ResponderEntity();
            responder.id = id;
            responder.version = version;
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

        public ResponderEntity build() {
            return responder;
        }

    }

}