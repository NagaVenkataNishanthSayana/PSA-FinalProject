package org.TSP.Graph;

import org.apache.lucene.util.SloppyMath;

public class Edge {

    private Vertex source;
    private Vertex destinantion;
    private double weight;

    public Edge(Vertex source, Vertex destinantion) {
        this.source = source;
        this.destinantion = destinantion;
        this.weight=SloppyMath.haversinMeters(source.getLatitude(), source.getLongitude(), destinantion.getLatitude(), destinantion.getLongitude());
    }

    public double getWeight(){
        return weight;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestinantion() {
        return destinantion;
    }

    public void setDestinantion(Vertex destinantion) {
        this.destinantion = destinantion;
    }
}
