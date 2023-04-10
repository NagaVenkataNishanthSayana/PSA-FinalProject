package org.TSP.util;
import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import java.util.*;
import org.TSP.TSPsteps.GreedyMatching;

public class GraphUtils {

    public static Set<Vertex> findVerticesWithOddDegree(HashMap<Vertex, List<Edge>> mst) {
        Set<Vertex> oddVertices = new HashSet<>();
        // Iterate through all vertices in the MST and add the odd vertices in a Set
        for (Vertex v : mst.keySet()) {
            if(v.getDegree()%2!=0){
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
