package org.cajun.navy.model.incident;

import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Access(AccessType.FIELD)
@SequenceGenerator(name="ReportedIncidentSeq", sequenceName="REPORTED_INCIDENT_SEQ", allocationSize = 10)
@Table(name = "reported_incident")
@NamedQueries({
        @NamedQuery(name = "Incident.findAll", query = "SELECT i from Incident i"),
        @NamedQuery(name = "Incident.byIncidentId", query = "SELECT i FROM Incident i WHERE i.incidentId = :incidentId"),
        @NamedQuery(name = "Incident.byStatus", query = "SELECT i from Incident i WHERE i.status = :status"),
        @NamedQuery(name = "Incident.findByName", query = "SELECT i from Incident i WHERE LOWER(i.victimName) LIKE :pattern"),
        @NamedQuery(name = "Incident.deleteAll", query = "DELETE FROM Incident")
})
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="ReportedIncidentSeq")
    private long id;

    @Column(name = "incident_id")
    private String incidentId;

    @Column(name = "latitude", scale = 5, precision = 7)
    private BigDecimal latitude;

    @Column(name = "longitude", scale = 5, precision = 7)
    private BigDecimal longitude;

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

}
