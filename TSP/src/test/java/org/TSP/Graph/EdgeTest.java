package org.TSP.Graph;

import static org.junit.Assert.assertEquals;

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
        assertEquals(343902.0, edge.getWeight(), 0.1);
    }

    private static final double DELTA = 1e-15; // tolerance for floating point errors

    @Test
    public void testDistance() {
        // test case data
        double lat1 = 37.7749;
        double lon1 = -122.4194;
        double lat2 = 40.7128;
        double lon2 = -74.0060;
        double expected = 2568.63; // expected distance in kilometers

        Vertex source=new Vertex("City A ",lon1,lat1);
        Vertex destination=new Vertex("City B ",lon2,lat2);
        // compute distance
        double result = new Edge(source,destination).getWeight();

        // assert result within tolerance of expected value
        assertEquals(expected*1000, result, DELTA);
    }

}