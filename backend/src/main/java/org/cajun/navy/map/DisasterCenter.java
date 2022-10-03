package org.cajun.navy.map;

import java.math.BigDecimal;

public class DisasterCenter {
    private String name;
    private BigDecimal lon;
    private BigDecimal lat;
    private BigDecimal zoom;

    public DisasterCenter() {
    }

    public DisasterCenter(String name, BigDecimal lon, BigDecimal lat, BigDecimal zoom) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.zoom = zoom;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLon() {
        return this.lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return this.lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getZoom() {
        return this.zoom;
    }

    public void setZoom(BigDecimal zoom) {
        this.zoom = zoom;
    }

}