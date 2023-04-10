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

}