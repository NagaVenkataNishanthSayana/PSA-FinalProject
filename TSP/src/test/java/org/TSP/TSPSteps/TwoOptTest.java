package org.TSP.TSPSteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.TwoOpt;
import org.TSP.util.GraphUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class TwoOptTest {

    @Test
    public void testTwoOpt() {
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

        // Create an initial tour that visits the cities in order
        List<Vertex> tour = new ArrayList<>();
        tour.add(v1);
        tour.add(v2);
        tour.add(v3);
        tour.add(v1);

        // Apply 2-opt to the tour
        List<Vertex> newTour = TwoOpt.twoOpt(tour, graph, 1000, 60000);

        // Check that the new tour is shorter than the original tour
        int oldDistance = GraphUtils.calculateTotalDistance(tour, graph);
        int newDistance = GraphUtils.calculateTotalDistance(newTour, graph);
        assertTrue(newDistance <= oldDistance);
    }

}
