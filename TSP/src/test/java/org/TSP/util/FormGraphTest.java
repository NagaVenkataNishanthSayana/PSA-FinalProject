package org.TSP.util;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FormGraphTest {

    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private Set<Vertex> oddVertices;

    @BeforeEach
    void setUp() {
        v1 = new Vertex("London", 51.5074, -0.1278);
        v2 = new Vertex("Paris", 48.8566, 2.3522);
        v3 = new Vertex("Bangalore", 77.5946, 12.9716);

        oddVertices = new HashSet<>();
        oddVertices.add(v1);
        oddVertices.add(v2);
        oddVertices.add(v3);
    }

    @Test
    void getGraph() {
        HashMap<Vertex, List<Edge>> disconnectedGraph = FormGraph.getGraph(oddVertices);

        // Check if all vertices are present in the graph
        assertTrue(disconnectedGraph.containsKey(v1));
        assertTrue(disconnectedGraph.containsKey(v2));
        assertTrue(disconnectedGraph.containsKey(v3));

        // Check if all vertices are connected to each other
        assertTrue(disconnectedGraph.get(v1).stream().anyMatch(edge -> edge.getDestination().equals(v2)));
        assertTrue(disconnectedGraph.get(v1).stream().anyMatch(edge -> edge.getDestination().equals(v3)));
        assertTrue(disconnectedGraph.get(v2).stream().anyMatch(edge -> edge.getDestination().equals(v1)));
        assertTrue(disconnectedGraph.get(v2).stream().anyMatch(edge -> edge.getDestination().equals(v3)));
        assertTrue(disconnectedGraph.get(v3).stream().anyMatch(edge -> edge.getDestination().equals(v1)));
        assertTrue(disconnectedGraph.get(v3).stream().anyMatch(edge -> edge.getDestination().equals(v2)));

        // Check if vertices are not connected to themselves
        assertFalse(disconnectedGraph.get(v1).stream().anyMatch(edge -> edge.getDestination().equals(v1)));
        assertFalse(disconnectedGraph.get(v2).stream().anyMatch(edge -> edge.getDestination().equals(v2)));
        assertFalse(disconnectedGraph.get(v3).stream().anyMatch(edge -> edge.getDestination().equals(v3)));
    }
}
