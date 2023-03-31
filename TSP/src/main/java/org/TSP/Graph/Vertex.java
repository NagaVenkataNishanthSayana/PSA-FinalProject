package org.TSP.Graph;

public class Vertex {

    private String id;
    private int degree;
    private double latitude;
    private double longitude;

    public Vertex() {
    }

    public Vertex(int degree, double latitude, double longitude) {
        this.degree = degree;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void incrementDegree(){
        this.degree++;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
