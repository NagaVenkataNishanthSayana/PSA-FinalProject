package org.TSP.TSPSteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.EulerianTour;
import org.TSP.TSPsteps.TSPSolver;
import org.TSP.util.GraphUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class TSPSolverTest {

    @Test
    public void testSolve() {
        // Create a sample graph with three cities and corresponding edges
        HashMap<Vertex, List<Edge>> graph = new HashMap<>();
        Vertex v1 = new Vertex("London", 51.5074, -0.1278);
        Vertex v2 = new Vertex("Paris", 48.8566, 2.3522);
        Vertex v3 = new Vertex("Bangalore", 77.5946, 12.9716);
        graph.put(v1, new ArrayList<>());
        graph.put(v2, new ArrayList<>());
        graph.put(v3, new ArrayList<>());
        graph.get(v1).add(new Edge(v1, v2));
        graph.get(v2).add(new Edge(v2, v3));
        graph.get(v3).add(new Edge(v3, v1));

        // Create a multigraph with multiple edges between each pair of cities
        HashMap<Vertex, List<Edge>> multiGraph = new HashMap<>();
        multiGraph.put(v1, new ArrayList<>());
        multiGraph.put(v2, new ArrayList<>());
        multiGraph.put(v3, new ArrayList<>());
        multiGraph.get(v1).add(new Edge(v1, v2));
        multiGraph.get(v1).add(new Edge(v1, v2));
        multiGraph.get(v2).add(new Edge(v2, v3));
        multiGraph.get(v2).add(new Edge(v2, v3));
        multiGraph.get(v3).add(new Edge(v3, v1));
        multiGraph.get(v3).add(new Edge(v3, v1));

        // Solve the TSP using the graph and multigraph
        List<Vertex> tspPath = TSPSolver.solve(graph, multiGraph);

        // Check that the TSP path visits all cities exactly once
        assertEquals(3, tspPath.size());
        assertTrue(tspPath.contains(v1));
        assertTrue(tspPath.contains(v2));
        assertTrue(tspPath.contains(v3));

        // Check that the TSP path has a lower cost than the original multigraph
        int tspTourCost = GraphUtils.calculateTotalDistance(tspPath, graph);
        int multiGraphCost = GraphUtils.calculateTotalDistance(EulerianTour.generate(multiGraph, v1), graph);
        assertTrue(tspTourCost <= multiGraphCost);
    }
}
