package org.TSP.TSPSteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.GreedyMatching;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class GreedyMatchingTest {
    @Test
    public void testGreedyMatch() {
        // Create a sample graph with three vertices
        HashMap<Vertex, List<Edge>> graph = new HashMap<>();
        Vertex v1 = new Vertex("London", 51.5074, -0.1278);
        Vertex v2 = new Vertex("Paris", 48.8566, 2.3522);
        Vertex v3 = new Vertex("Bangalore", 77.5946, 12.9716);
        graph.put(v1, new ArrayList<Edge>());
        graph.put(v2, new ArrayList<Edge>());
        graph.put(v3, new ArrayList<Edge>());

        // Add edges to the graph
        graph.get(v1).add(new Edge(v1, v2));
        graph.get(v1).add(new Edge(v1, v3));
        graph.get(v2).add(new Edge(v2, v1));
        graph.get(v2).add(new Edge(v2, v3));
        graph.get(v3).add(new Edge(v3, v1));
        graph.get(v3).add(new Edge(v3, v2));

        // Create a list of the vertices in the graph
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        // Compute the greedy matching
        List<Edge> matching = GreedyMatching.greedyMatch(graph, vertices);

        // Check that the size of the matching is correct
        assertEquals(1, matching.size());

    }
}