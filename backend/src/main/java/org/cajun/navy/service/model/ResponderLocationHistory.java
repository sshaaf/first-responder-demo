package org.cajun.navy.service.model;

import java.math.BigDecimal;

public class ResponderLocationHistory {

    private BigDecimal lat;

    private BigDecimal lon;

    private long timestamp;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class Builder {

        private final ResponderLocationHistory responderLocaitonHistory;

        public Builder(){
            responderLocaitonHistory = new ResponderLocationHistory();
        }

        public Builder latitude(BigDecimal latitude){
            responderLocaitonHistory.lat = latitude;
            return this;
        }

        public Builder longitude(BigDecimal longitude){
            responderLocaitonHistory.lon = longitude;
            return this;
        }

        public Builder timestamp(long timestamp){
            responderLocaitonHistory.timestamp = timestamp;
            return this;
        }

        public ResponderLocationHistory build(){
            return responderLocaitonHistory;
        }

    }

}
