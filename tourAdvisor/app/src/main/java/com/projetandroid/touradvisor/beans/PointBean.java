package com.projetandroid.touradvisor.beans;

public class PointBean {
    private int id_point;
    private Double lat_point;
    private Double lon_point;

    // Getter & Setter
    public int getId_point() {
        return id_point;
    }

    public void setId_point(int id_point) {
        this.id_point = id_point;
    }

    public Double getLat_point() {
        return lat_point;
    }

    public void setLat_point(Double lat_point) {
        this.lat_point = lat_point;
    }

    public Double getLon_point() {
        return lon_point;
    }

    public void setLon_point(Double lon_point) {
        this.lon_point = lon_point;
    }

    // Constructor
    public PointBean(int id_point, Double lat_point, Double lon_point) {
        this.id_point = id_point;
        this.lat_point = lat_point;
        this.lon_point = lon_point;
    }

    public PointBean(Double lat_point, Double lon_point) {
        this.lat_point = lat_point;
        this.lon_point = lon_point;
    }

    public PointBean() {
    }
}
