package org.TSP.TSPSteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.PrimsMST;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.PrimsMST;
import org.junit.Before;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PrimsMSTTest {
    private HashMap<Vertex, List<Edge>> graph;
    private Vertex v1;
    private Vertex v2;
    private Vertex v3;

    @Before
    public void setUp() {
        // Create the vertices
        v1 = new Vertex("London", 51.5074, -0.1278);
        v2 = new Vertex("Paris", 48.8566, 2.3522);
        v3 = new Vertex("Bangalore", 77.5946, 12.9716);

        // Create the edges
        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v1, v3);
        Edge e3 = new Edge(v2, v3);

        // Add the edges to the vertices
        graph = new HashMap<>();
        graph.put(v1, new ArrayList<>());
        graph.put(v2, new ArrayList<>());
        graph.put(v3, new ArrayList<>());
        graph.get(v1).add(e1);
        graph.get(v1).add(e2);
        graph.get(v2).add(e1);
        graph.get(v2).add(e3);
        graph.get(v3).add(e2);
        graph.get(v3).add(e3);
    }

    @Test
    public void testPrim() {
        v1 = new Vertex("London", 51.5074, -0.1278);
        v2 = new Vertex("Paris", 48.8566, 2.3522);
        v3 = new Vertex("Bangalore", 77.5946, 12.9716);

        // Create the edges
        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v1, v3);
        Edge e3 = new Edge(v2, v3);

        HashMap<Vertex, List<Edge>> graph = new HashMap<>();
        graph.put(v1, new ArrayList<>());
        graph.put(v2, new ArrayList<>());
        graph.put(v3, new ArrayList<>());
        graph.get(v1).add(new Edge(v1, v2));
        graph.get(v1).add(new Edge(v1, v3));
        graph.get(v2).add(new Edge(v2, v1));
        graph.get(v2).add(new Edge(v2, v3));
        graph.get(v3).add(new Edge(v3, v1));
        graph.get(v3).add(new Edge(v3, v2));
        HashMap<Vertex, List<Edge>> mst = PrimsMST.prim(graph, v1);

        // Check that the MST contains all three vertices
        assertEquals(3, mst.size());
        assertTrue(mst.containsKey(v1));
        assertTrue(mst.containsKey(v2));
        assertTrue(mst.containsKey(v3));

        // Check that the MST contains two edges
        List<Edge> mstEdges = new ArrayList<>();
        for (List<Edge> edges : mst.values()) {
            mstEdges.addAll(edges);
        }
        assertEquals(4, mstEdges.size());

        // Check that the MST weight is correct
        double expectedWeight = 2* (new Edge(v1, v2).getWeight() + new Edge(v1, v3).getWeight());
        double actualWeight = 0;
        for (Edge edge : mstEdges) {
            actualWeight += edge.getWeight();
        }
        assertEquals(expectedWeight, actualWeight, 0.0001);
    }
}
