package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.*;

public class EulerianTour {

    public static List<Edge> generate(HashMap<Vertex, List<Edge>> graph) {
        // Check if the multi graph is connected and has only even-degree vertices
        if (!isConnected(graph) || !hasEvenDegreeVertices(graph)) {
            return null;
        }
        HashMap<Vertex, List<Edge>> multiGraph=new HashMap<>();
        for (Vertex v : graph.keySet()) {
            multiGraph.put(v, new ArrayList<>(graph.get(v)));
        }

        // Initialize an empty list to store the edges in the Eulerian tour
        List<Edge> eulerianTour = new ArrayList<>();

        // Choose any vertex in the multi graph as the starting vertex
        Vertex startVertex = multiGraph.keySet().iterator().next();

        // Initialize a stack to keep track of the current path
        Stack<Vertex> path = new Stack<>();
        path.push(startVertex);

        // Traverse the multi graph using a depth-first search
        while (!path.isEmpty()) {
            Vertex vertex = path.peek();
            List<Edge> edges = multiGraph.get(vertex);

            if (!edges.isEmpty()) {
                // Choose any unvisited edge and traverse it
                Edge edge = edges.get(0);
                edges.remove(0);
                multiGraph.get(edge.getDestinantion()).removeIf(e -> e.getSource().equals(vertex) && e.getDestinantion().equals(edge.getDestinantion()));
                eulerianTour.add(edge);
                path.push(edge.getDestinantion());
            } else {
                // Backtrack to the previous vertex that has unvisited edges
                path.pop();
            }
        }

        return eulerianTour;
    }

    private static boolean isConnected(HashMap<Vertex, List<Edge>> multiGraph) {
        // Perform a depth-first search from any vertex to check if all vertices are reachable
        Vertex startVertex = multiGraph.keySet().iterator().next();
        Set<Vertex> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            visited.add(vertex);

            for (Edge edge : multiGraph.get(vertex)) {
                Vertex adjacentVertex = edge.getDestinantion();

                if (!visited.contains(adjacentVertex)) {
                    stack.push(adjacentVertex);
                }
            }
        }

        return visited.size() == multiGraph.size();
    }

    private static boolean hasEvenDegreeVertices(HashMap<Vertex, List<Edge>> multiGraph) {
        // Check if all vertices have even degree
        for (List<Edge> edges : multiGraph.values()) {
            if (edges.size() % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}
