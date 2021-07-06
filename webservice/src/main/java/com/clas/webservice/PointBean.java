package com.clas.webservice;

public class PointBean {
    private double lat_point;
    private double lon_point;
    private int id_point;

    public PointBean() {
    }

    public double getLat_point() {
        return lat_point;
    }

    public void setLat_point(double lat_point) {
        this.lat_point = lat_point;
    }

    public double getLon_point() {
        return lon_point;
    }

    public void setLon_point(double lon_point) {
        this.lon_point = lon_point;
    }

    public int getId_point() {
        return id_point;
    }

    public void setId_point(int id_point) {
        this.id_point = id_point;
    }
}
