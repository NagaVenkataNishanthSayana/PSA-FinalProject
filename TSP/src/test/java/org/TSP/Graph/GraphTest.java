package org.TSP.Graph;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTest {

    @Test
    public void testAddVertex() {
        Graph g = new Graph();
        Vertex v1 = new Vertex("London", 51.5074, -0.1278);
        g.addVertex(v1);
        assertTrue(g.getGraph().containsKey(v1));
    }

    @Test
    public void testGetAdjVertices() {
        Graph g = new Graph();
        Vertex v1 = new Vertex("London", 51.5074, -0.1278);
        Vertex v2 = new Vertex("Paris", 48.8566, 2.3522);
        Vertex v3 = new Vertex("Bangalore", 77.5946, 12.9716);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addEdge(v1, v2);
        g.addEdge(v1, v3);
        List<Vertex> adj = g.getAdjVertices(v1);
        assertTrue(adj.contains(v2));
        assertTrue(adj.contains(v3));
        assertFalse(adj.contains(v1));
    }

}
