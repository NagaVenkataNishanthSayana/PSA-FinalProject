package org.TSP.util;
import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import java.util.*;
import org.TSP.TSPsteps.GreedyMatching;
import org.apache.lucene.util.SloppyMath;

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

    public static double calculateTotalDistance(List<Vertex> tour, HashMap<Vertex, List<Edge>> graph) {
        double totalDistance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            Vertex source = tour.get(i);
            Vertex destination = tour.get(i + 1);
            List<Edge> edges = graph.get(source);
            for (Edge edge : edges) {
                if (edge.getDestination().equals(destination)) {
                    totalDistance += edge.getWeight();
                    break;
                }
            }
        }
        // Add the distance of the last edge connecting the last vertex to the first vertex
        Vertex firstVertex = tour.get(0);
        Vertex lastVertex = tour.get(tour.size() - 1);
        List<Edge> edges = graph.get(lastVertex);
        for (Edge edge : edges) {
            if (edge.getDestination().equals(firstVertex)) {
                totalDistance += edge.getWeight();
                break;
            }
        }
        return totalDistance;
    }

    public static double probability(double f1, double f2, double temp) {
        if (f2 < f1) return 1;
        return Math.exp((f1 - f2) / temp);
    }

    public static double getWeight(Vertex source, Vertex destination) {
        return SloppyMath.haversinMeters(source.getLatitude(), source.getLongitude(), destination.getLatitude(), destination.getLongitude());
    }

}
