package org.cajun.navy.model.mission;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Access(AccessType.FIELD)
@Table(name = "mission_table")
@NamedQueries({
        @NamedQuery(name = "Mission.findAll", query = "SELECT m from Mission m"),
        @NamedQuery(name = "Mission.byMissionId", query = "SELECT m FROM Mission m WHERE m.missionId = :missionId"),
        @NamedQuery(name = "Mission.byStatus", query = "SELECT m from Mission m WHERE m.status = :status"),
        @NamedQuery(name = "Mission.byCreateOrUpdated", query = "SELECT m from Mission m WHERE m.status = 'CREATED' OR m.status = 'UPDATED'"),
        @NamedQuery(name = "Mission.byResponderId", query = "SELECT m from Mission m WHERE m.responderId = :responderId"),
})

public class Mission {

    @Id
    @Column(name = "mission_id")
    private String missionId;

    @Column(name = "incident_id")
    private String incidentId;

    @Column(name = "responder_id")
    private String responderId;

    @Column(name = "responder_start_latitude", scale = 5, precision = 7)
    private BigDecimal responderStartLatitude;

    @Column(name = "responder_start_longitude", scale = 5, precision = 7)
    private BigDecimal responderStartLongitude;

    @Column(name = "incident_latitude", scale = 5, precision = 7)
    private BigDecimal incidentLatitude;

    @Column(name = "incident_longitude", scale = 5, precision = 7)
    private BigDecimal incidentLongtitude;

    @Column(name = "destination_latitude", scale = 5, precision = 7)
    private BigDecimal destinationLatitude;

    @Column(name = "destination_longitude", scale = 5, precision = 7)
    private BigDecimal destinationLongtitude;

    @Column(name = "mission_status")
    private String status;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "mission_id")
    private List<ResponderLocationHistory> responderLocationHistory;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "mission_id")
    private List<MissionStep> steps;

    public Mission() {
        missionId = UUID.randomUUID().toString();
        responderLocationHistory = new ArrayList<>();
        steps = new ArrayList<>();
    }

    public String getMissionId() {
        return missionId;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public String getResponderId() {
        return responderId;
    }

    public BigDecimal getResponderStartLatitude() {
        return responderStartLatitude;
    }

    public BigDecimal getResponderStartLongitude() {
        return responderStartLongitude;
    }

    public BigDecimal getIncidentLatitude() {
        return incidentLatitude;
    }

    public BigDecimal getIncidentLongtitude() {
        return incidentLongtitude;
    }

    public BigDecimal getDestinationLatitude() {
        return destinationLatitude;
    }

    public BigDecimal getDestinationLongtitude() {
        return destinationLongtitude;
    }

    public List<ResponderLocationHistory> getResponderLocationHistory() {
        return responderLocationHistory;
    }

    public String getStatus() {
        return status;
    }


    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    public void setResponderStartLatitude(BigDecimal responderStartLatitude) {
        this.responderStartLatitude = responderStartLatitude;
    }

    public void setResponderStartLongitude(BigDecimal responderStartLongitude) {
        this.responderStartLongitude = responderStartLongitude;
    }

    public void setIncidentLatitude(BigDecimal incidentLatitude) {
        this.incidentLatitude = incidentLatitude;
    }

    public void setIncidentLongtitude(BigDecimal incidentLongtitude) {
        this.incidentLongtitude = incidentLongtitude;
    }

    public void setDestinationLatitude(BigDecimal destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public void setDestinationLongtitude(BigDecimal destinationLongtitude) {
        this.destinationLongtitude = destinationLongtitude;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponderLocationHistory(List<ResponderLocationHistory> responderLocationHistory) {
        this.responderLocationHistory = responderLocationHistory;
    }

    public void setSteps(List<MissionStep> steps) {
        this.steps = steps;
    }

    public Mission status(MissionStatus status) {
        this.status = status.name();
        return this;
    }

    public List<MissionStep> getSteps() {
        return steps;
    }

    public Location responderLocation() {
        return Location.of(responderStartLatitude, responderStartLongitude);
    }

    public Location incidentLocation() {
        return Location.of(incidentLatitude, incidentLongtitude);
    }

    public Location destinationLocation() {
        return Location.of(destinationLatitude, destinationLongtitude);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return Objects.equals(responderId, mission.responderId) && Objects.equals(incidentId, mission.incidentId);
    }


    public String getKey(){
        return this.incidentId + ":" + this.responderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }
}
