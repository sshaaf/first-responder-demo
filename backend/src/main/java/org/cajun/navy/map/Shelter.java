package org.cajun.navy.map;

import org.cajun.navy.model.mission.Location;

import java.math.BigDecimal;

public class Shelter {
    private String name;
    private String id;

    private BigDecimal lon;

    private BigDecimal lat;

    private int rescued;

    protected Shelter() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getRescued() {
        return this.rescued;
    }

    public void setRescued(int rescued) {
        this.rescued = rescued;
    }

    public Location shelterLocation() {
        return Location.of(lat, lon);
    }

}
