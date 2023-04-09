package org.TSP.util;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.GreedyMatching;

import java.util.*;

public class GraphUtils {

    public static Set<Vertex> findVerticesWithOddDegree(HashMap<Vertex, Edge> mst) {
        Set<Vertex> oddVertices = new HashSet<>();
        Map<Vertex, Integer> degreeMap = new HashMap<>();

        // First, count the degree of each vertex in the MST
        for (Edge e : mst.values()) {
            Vertex u = e.getSource();
            Vertex v = e.getDestinantion();
            degreeMap.put(u, degreeMap.getOrDefault(u, 0) + 1);
            degreeMap.put(v, degreeMap.getOrDefault(v, 0) + 1);
        }

        // Then, add all vertices with odd degree to the set
        for (Vertex v : degreeMap.keySet()) {
            if (degreeMap.get(v) % 2 == 1) {
                oddVertices.add(v);
            }
        }

        return oddVertices;
    }

    public static HashMap<Vertex, List<Edge>> createSubgraph(HashMap<Vertex, List<Edge>> graph, Set<Vertex> vertices) {
        HashMap<Vertex, List<Edge>> subgraph = new HashMap<>();

        for (Vertex v : vertices) {
            subgraph.put(v, graph.get(v));
        }

        return subgraph;
    }

    public static List<Edge> findPerfectMatching(HashMap<Vertex, List<Edge>> graph, Set<Vertex> vertices) {
        return GreedyMatching.greedyMatch(createSubgraph(graph, vertices), new ArrayList<>(vertices));
    }


}
