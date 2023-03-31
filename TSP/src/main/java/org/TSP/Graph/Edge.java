package org.TSP.Graph;

import org.apache.lucene.util.SloppyMath;

public class Edge {

    private Vertex vertex1;
    private Vertex vertex2;
    private double weight;

    public Edge(Vertex vertex1, Vertex vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight=getWeight();
    }

    private double getWeight(){
        return SloppyMath.haversinMeters(vertex1.getLatitude(),vertex1.getLongitude(),vertex2.getLatitude(),vertex2.getLongitude());
    }

    public Vertex getVertex1() {
        return vertex1;
    }

    public void setVertex1(Vertex vertex1) {
        this.vertex1 = vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }

    public void setVertex2(Vertex vertex2) {
        this.vertex2 = vertex2;
    }
}
