package org.cajun.navy.map;


import java.util.List;

public class InclusionZone {
    private String id;
    private List<double[]> points;

    public InclusionZone() {
    }

    public InclusionZone(String id, List<double[]> points) {
        this.id = id;
        this.points = points;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<double[]> getPoints() {
        return this.points;
    }

    public void setPoints(List<double[]> points) {
        this.points = points;
    }

    public InclusionZone id(String id) {
        this.id = id;
        return this;
    }

    public InclusionZone points(List<double[]> points) {
        this.points = points;
        return this;
    }
}