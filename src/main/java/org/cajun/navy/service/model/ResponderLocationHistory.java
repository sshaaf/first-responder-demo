package org.cajun.navy.service.model;

import java.math.BigDecimal;

public class ResponderLocationHistory {

    private BigDecimal latitude;

    private BigDecimal longitude;

    private long timestamp;

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
            responderLocaitonHistory.latitude = latitude;
            return this;
        }

        public Builder longitude(BigDecimal longitude){
            responderLocaitonHistory.longitude = longitude;
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
