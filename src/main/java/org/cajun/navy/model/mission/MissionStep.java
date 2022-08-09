package org.cajun.navy.model.mission;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Access(AccessType.FIELD)
@Table(name = "mission_step")
public class MissionStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "way_point")
    private boolean wayPoint = false;

    @Column(name = "destination")
    private boolean destination = false;

    @ManyToOne
    @JoinColumn(name = "mission_id", insertable = false, updatable = false)
    private Mission mission;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public boolean isWayPoint() {
        return wayPoint;
    }

    public boolean isDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissionStep step = (MissionStep) o;

        return (latitude != null && step.latitude != null && latitude.compareTo(step.latitude) == 0) || (latitude == null && step.latitude == null) &&
                (longitude != null && step.longitude != null && longitude.compareTo(step.longitude) == 0) || (latitude == null && step.latitude == null) &&
                step.destination == destination &&
                step.wayPoint == wayPoint;
    }

    public static Builder builder(BigDecimal lat, BigDecimal lon) {
        return new Builder(lat, lon);
    }

    public static class Builder {

        private final MissionStep missionStep = new MissionStep();

        public Builder(BigDecimal latitude, BigDecimal longitude) {
            missionStep.latitude = latitude;
            missionStep.longitude = longitude;
        }

        public Builder wayPoint(boolean waypoint) {
            missionStep.wayPoint = waypoint;
            return this;
        }

        public Builder destination(boolean destination) {
            missionStep.destination = destination;
            return this;
        }

        public MissionStep build() {
            return missionStep;
        }
    }

}
