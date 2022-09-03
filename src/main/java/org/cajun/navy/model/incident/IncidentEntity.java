package org.cajun.navy.model.incident;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Access(AccessType.FIELD)
@SequenceGenerator(name="ReportedIncidentSeq", sequenceName="REPORTED_INCIDENT_SEQ", allocationSize = 10)
@Table(name = "reported_incident")
@NamedQueries({
        @NamedQuery(name = "Incident.findAll", query = "SELECT i from IncidentEntity i"),
        @NamedQuery(name = "Incident.byIncidentId", query = "SELECT i FROM IncidentEntity i WHERE i.incidentId = :incidentId"),
        @NamedQuery(name = "Incident.byStatus", query = "SELECT i from IncidentEntity i WHERE i.status = :status"),
        @NamedQuery(name = "Incident.findByName", query = "SELECT i from IncidentEntity i WHERE LOWER(i.victimName) LIKE :pattern"),
        @NamedQuery(name = "Incident.deleteAll", query = "DELETE FROM IncidentEntity")
})
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="ReportedIncidentSeq")
    private long id;

    @Column(name = "incident_id")
    private String incidentId;

    @Column(name = "latitude", scale = 5, precision = 7)
    private BigDecimal lat;

    @Column(name = "longitude", scale = 5, precision = 7)
    private BigDecimal lon;

    @Column(name = "number_of_people")
    private int numberOfPeople;

    @Column(name = "medical_needed")
    private boolean medicalNeeded;

    @Column(name = "victim_name")
    private String victimName;

    @Column(name = "victim_phone")
    private String victimPhoneNumber;

    @Basic
    @Column(name = "reported_time")
    private Instant reportedTime;

    @Column(name = "incident_status")
    private String status;

    @Column(name = "version")
    @Version
    private long version;

    public long getId() {
        return id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal latitude) {
        this.lat = latitude;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal longitude) {
        this.lon = longitude;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Boolean isMedicalNeeded() {
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

    public long getTimestamp() {
        return reportedTime.toEpochMilli();
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

    public long getVersion() {
        return version;
    }


    public static class Builder{

        private IncidentEntity incidentEntity;

        public Builder(String incidentId){
            incidentEntity = new IncidentEntity();
            incidentEntity.incidentId = incidentId;
        }

        public Builder lat(BigDecimal lat){
            incidentEntity.lat = lat;
            return this;
        }

        public Builder lon(BigDecimal lon){
            incidentEntity.lon = lon;
            return this;
        }

        public Builder numberOfPeople(int numberOfPeople){
            incidentEntity.numberOfPeople = numberOfPeople;
            return this;
        }

        public IncidentEntity.Builder medicatNeeded(boolean medicalNeeded){
            incidentEntity.medicalNeeded = medicalNeeded;
            return this;
        }

        public Builder victimName(String victimName){
            incidentEntity.victimName = victimName;
            return this;
        }

        public Builder victimPhoneNumber(String victimPhoneNumber){
            incidentEntity.victimPhoneNumber = victimPhoneNumber;
            return this;
        }

        public Builder reportedTime(Instant reportedTime){
            incidentEntity.reportedTime = reportedTime;
            return this;
        }
        public Builder status(String status){
            incidentEntity.status = status;
            return this;
        }

        public IncidentEntity build(){
            return incidentEntity;
        }
    }


}
