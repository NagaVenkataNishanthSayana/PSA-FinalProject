package org.TSP;

import java.util.*;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

public class GreedyMatching {

    public static List<Edge> greedyMatch(HashMap<Vertex, List<Edge>> graph, List<Vertex> vertices) {
        List<Edge> matching = new ArrayList<>();
        Set<Vertex> unmatchedVertices = new HashSet<>(vertices);

        // Sort the edges by weight
        List<Edge> edges = new ArrayList<>();
        for (List<Edge> e : graph.values()) {
            edges.addAll(e);
        }
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge e1, Edge e2) {
                return Double.compare(e1.getWeight(), e2.getWeight());
            }
        });

        // Iteratively add the lowest-weight edge to the matching
        for (Edge e : edges) {
            Vertex u = e.getSource();
            Vertex v = e.getDestinantion();
            if (unmatchedVertices.contains(u) && unmatchedVertices.contains(v)) {
                matching.add(e);
                unmatchedVertices.remove(u);
                unmatchedVertices.remove(v);
            }
        }

        return matching;
    }
}
