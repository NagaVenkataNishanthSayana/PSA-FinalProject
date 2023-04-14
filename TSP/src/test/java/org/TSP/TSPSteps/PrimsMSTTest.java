package org.TSP.TSPSteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.PrimsMST;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimsMSTTest {

    @Test
    public void testPrim() {
        // Create vertices
        Vertex v1 = new Vertex("London", 51.5074, -0.1278);
        Vertex v2 = new Vertex("Paris", 48.8566, 2.3522);
        Vertex v3 = new Vertex("Bangalore", 77.5946, 12.9716);
        Vertex v4 = new Vertex("New York", 40.7128, -74.0060);
        // Create edges
        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        Edge e3 = new Edge(v3, v4);
        Edge e4 = new Edge(v4, v1);
        Edge e5 = new Edge(v1, v3);
        Edge e6 = new Edge(v2, v4);

        // Create graph
        HashMap<Vertex, List<Edge>> graph = new HashMap<>();
        graph.put(v1, new ArrayList<>(List.of(e1, e4, e5)));
        graph.put(v2, new ArrayList<>(List.of(e1, e2, e6)));
        graph.put(v3, new ArrayList<>(List.of(e2, e3, e5)));
        graph.put(v4, new ArrayList<>(List.of(e3, e4, e6)));

        // Run Prim's algorithm
        HashMap<Vertex, List<Edge>> mst = PrimsMST.prim(graph, v1);

        // Verify MST edges
        List<Edge> expectedEdges = new ArrayList<>();
        expectedEdges.add(e1);
        expectedEdges.add(e5);
        expectedEdges.add(e4);

        List<Edge> actualEdges = mst.get(v1);
        Assert.assertEquals(expectedEdges.size(), actualEdges.size());
        for (Edge edge : expectedEdges) {
            Assert.assertTrue(actualEdges.contains(edge));
        }

        actualEdges = mst.get(v2);
        Assert.assertEquals(1, actualEdges.size());
        Assert.assertTrue(actualEdges.contains(e1));

        actualEdges = mst.get(v3);
        Assert.assertEquals(1, actualEdges.size());
        Assert.assertTrue(actualEdges.contains(e2));

        actualEdges = mst.get(v4);
        Assert.assertEquals(1, actualEdges.size());
        Assert.assertTrue(actualEdges.contains(e4));
    }
}
