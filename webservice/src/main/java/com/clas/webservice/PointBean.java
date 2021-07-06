package com.clas.webservice;

import javax.persistence.*;

@Entity
@Table (name = "point")
public class PointBean {
    //@Id -> primary key / @GenerateValue (strategy = GenerationType.IDENTITY) -> pour l'auto Incrémentation
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_point;
    private double lat_point;
    private double lon_point;

    //Constructeur vide obligatoire //Constructeur vide obligatoire
    public PointBean() {
    }

    //Getter et Setter
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
