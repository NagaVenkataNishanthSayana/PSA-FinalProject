package org.TSP.Graph;

import static org.junit.Assert.assertEquals;

import org.apache.lucene.util.SloppyMath;
import org.junit.Test;

public class EdgeTest {

    @Test
    public void testGetWeight() {
        // Create vertices for the edge
        Vertex source = new Vertex("London", 51.5074, -0.1278);
        Vertex destination = new Vertex("Paris", 48.8566, 2.3522);

        // Create the edge
        Edge edge = new Edge(source, destination);

        // Check that the weight is correct (within a small delta)
        double expectedWeight = SloppyMath.haversinMeters(source.getLatitude(), source.getLongitude(), destination.getLatitude(), destination.getLongitude());
        assertEquals(expectedWeight, edge.getWeight(), 0.001);
    }

    @Test
    public void testGetSource() {
        Vertex source = new Vertex("London", 51.5074, -0.1278);
        Vertex destination = new Vertex("Paris", 48.8566, 2.3522);
        Edge e = new Edge(source, destination);
        assertEquals(source, e.getSource());
    }

    @Test
    public void testGetDestination() {
        Vertex source = new Vertex("London", 51.5074, -0.1278);
        Vertex destination = new Vertex("Paris", 48.8566, 2.3522);
        Edge e = new Edge(source, destination);
        assertEquals(destination, e.getDestination());
    }

    @Test
    public void testSetSource() {
        Vertex source = new Vertex("London", 51.5074, -0.1278);
        Vertex destination = new Vertex("Paris", 48.8566, 2.3522);
        Edge e = new Edge(source, destination);
        Vertex newSource = new Vertex("Bangalore", 77.5946, 12.9716);
        e.setSource(newSource);
        assertEquals(newSource, e.getSource());
    }

    @Test
    public void testSetDestination() {
        Vertex source = new Vertex("London", 51.5074, -0.1278);
        Vertex destination = new Vertex("Paris", 48.8566, 2.3522);
        Edge e = new Edge(source, destination);
        Vertex newDestination = new Vertex("Bangalore", 77.5946, 12.9716);
        e.setDestination(newDestination);
        assertEquals(newDestination, e.getDestination());
    }
}