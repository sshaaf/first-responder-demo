package org.cajun.navy.map;

import org.cajun.navy.model.mission.Location;

import java.math.BigDecimal;

public class Shelter {
    private String id;
    private String name;

    private BigDecimal longitude;

    private BigDecimal latitude;

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

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return this.latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public int getRescued() {
        return this.rescued;
    }

    public void setRescued(int rescued) {
        this.rescued = rescued;
    }

    public Location shelterLocation() {
        return Location.of(latitude, longitude);
    }

}
