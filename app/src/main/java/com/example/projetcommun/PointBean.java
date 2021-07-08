package com.example.projetcommun;

public class PointBean {
    private int id_lieux;
    private double longitude;
    private double lattitude;


    public PointBean (int id_lieux, double longitude, double lattitude ){
        this.id_lieux= id_lieux;
        this.longitude= longitude;
        this.lattitude= lattitude;

    }

    public int getId_lieux() {
        return id_lieux;
    }

    public void setId_lieux(int id_lieux) {
        this.id_lieux = id_lieux;
    }

    public double getLongitude(){ return longitude;}

    public void setLongitude(double longitude){ this.longitude = longitude;}

    public double getLattitude(){ return lattitude;}

    public void setLattitude(double lattitude) { this.lattitude = lattitude;}

}
