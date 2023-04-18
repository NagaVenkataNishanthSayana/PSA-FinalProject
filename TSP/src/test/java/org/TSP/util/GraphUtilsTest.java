package org.TSP.util;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphUtilsTest {

    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private HashMap<Vertex, List<Edge>> graph;

    @BeforeEach
    void setUp() {
        v1 = new Vertex("London", 51.5074, -0.1278);
        v2 = new Vertex("Paris", 48.8566, 2.3522);
        v3 = new Vertex("Bangalore", 77.5946, 12.9716);

        graph = FormGraph.getGraph(new HashSet<>(Arrays.asList(v1, v2, v3)));

        // Set degrees for the vertices
        v1.setDegree(2);
        v2.setDegree(2);
        v3.setDegree(2);
    }

    @Test
    void findVerticesWithOddDegree() {
        // Set the degree of vertex v3 to 3 (odd)
        v3.setDegree(3);

        Set<Vertex> oddVertices = GraphUtils.findVerticesWithOddDegree(graph);

        assertEquals(1, oddVertices.size());
        assertTrue(oddVertices.contains(v3));
    }

    @Test
    void createSubgraph() {
        Set<Vertex> vertices = new HashSet<>(Arrays.asList(v1, v3));
        HashMap<Vertex, List<Edge>> subgraph = GraphUtils.createSubgraph(graph, vertices);

        assertEquals(2, subgraph.size());
        assertTrue(subgraph.containsKey(v1));
        assertTrue(subgraph.containsKey(v3));
    }

    @Test
    void calculateTotalDistance() {
        List<Vertex> tour = Arrays.asList(v1, v2, v3);
        int totalDistance = GraphUtils.calculateTotalDistance(tour, graph);

        int distance12 = 0;
        int distance23 = 0;
        int distance31 = 0;

        for (Edge edge : graph.get(v1)) {
            if (edge.getDestination().equals(v2)) {
                distance12 = (int) edge.getWeight();
            }
        }

        for (Edge edge : graph.get(v2)) {
            if (edge.getDestination().equals(v3)) {
                distance23 = (int) edge.getWeight();
            }
        }

        for (Edge edge : graph.get(v3)) {
            if (edge.getDestination().equals(v1)) {
                distance31 = (int) edge.getWeight();
            }
        }

        int expectedTotalDistance = distance12 + distance23 + distance31;
        assertEquals(expectedTotalDistance, totalDistance);
    }
}
