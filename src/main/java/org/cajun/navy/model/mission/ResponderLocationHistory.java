package org.cajun.navy.model.mission;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Access(AccessType.FIELD)
@Table(name = "responder_location_history")
public class ResponderLocationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "time_stamp")
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "mission_id", insertable = false, updatable = false)
    private Mission mission;


    public ResponderLocationHistory(BigDecimal latitude, BigDecimal longitude, long timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public ResponderLocationHistory() {

    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static ResponderLocationHistory.Builder builder(BigDecimal lat, BigDecimal lon) {
        return new ResponderLocationHistory.Builder(lat, lon);
    }

    public static class Builder {

        private final ResponderLocationHistory history = new ResponderLocationHistory();

        public Builder(BigDecimal latitude, BigDecimal longitude) {
            history.latitude = latitude;
            history.longitude = longitude;
        }

        public ResponderLocationHistory build() {
            return history;
        }
    }
}
