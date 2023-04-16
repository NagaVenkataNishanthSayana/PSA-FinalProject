package org.TSP.TSPOptimizations.Tactical;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.util.GraphUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoOpt {
    public static List<Vertex> twoOpt(List<Vertex> tour, HashMap<Vertex, List<Edge>> graph, int maxIterations, long timeoutMillis) {
        boolean improved = true;
        int size = tour.size();
        long startTime = System.currentTimeMillis();
        int iteration = 0;

        while (improved && iteration < maxIterations && System.currentTimeMillis() - startTime < timeoutMillis) {
            improved = false;

            for (int i = 0; i < size - 2; i++) {
                for (int j = i + 1; j < size - 1; j++) {
                    double delta = calculateDelta(tour, i, j, graph);

                    if (delta < 0) {
                        tour = reverseSublist(tour, i + 1, j);
                        improved = true;
                    }
                }
            }

            iteration++;
        }

        return tour;
    }

    private static double calculateDelta(List<Vertex> tour, int i, int j, HashMap<Vertex, List<Edge>> graph) {
        Vertex a = tour.get(i);
        Vertex b = tour.get(i + 1);
        Vertex c = tour.get(j);
        Vertex d = tour.get(j + 1);

        double ab = findEdgeWeight(a, b, graph);
        double cd = findEdgeWeight(c, d, graph);
        double ac = findEdgeWeight(a, c, graph);
        double bd = findEdgeWeight(b, d, graph);

        return ac + bd - ab - cd;
    }

    private static double findEdgeWeight(Vertex source, Vertex destination, HashMap<Vertex, List<Edge>> graph) {
        List<Edge> edges = graph.get(source);

        for (Edge edge : edges) {
            if (edge.getDestination().equals(destination)) {
                return edge.getWeight();
            }
        }

        return Double.MAX_VALUE; // If there's no edge found, return the maximum double value
    }

    public static List<Vertex> reverseSublist(List<Vertex> list, int start, int end) {
        List<Vertex> reversedList = new ArrayList<>(list.subList(0, start));
        for (int i = end; i >= start; i--) {
            reversedList.add(list.get(i));
        }
        reversedList.addAll(list.subList(end + 1, list.size()));
        return reversedList;
    }
}
