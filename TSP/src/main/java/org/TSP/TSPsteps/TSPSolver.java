package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.*;

public class TSPSolver {
    public static int solve(HashMap<Vertex, List<Edge>> multiGraph) {
        // Step 1: Generate an Eulerian tour
        List<Edge> eulerianTour = EulerianTour.generate(multiGraph);
        System.out.println("Eulerian Tour:"+eulerianTour.size());
        if (eulerianTour == null) {
            return -1; // The input graph is not suitable for TSP
        }

        // Step 2: Remove duplicates to obtain a Hamiltonian circuit
        List<Vertex> hamiltonianCircuit = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        for (Edge edge : eulerianTour) {
            if (!visited.contains(edge.getSource())) {
                hamiltonianCircuit.add(edge.getSource());
                visited.add(edge.getSource());
            }
            if (!visited.contains(edge.getDestinantion())) {
                hamiltonianCircuit.add(edge.getDestinantion());
                visited.add(edge.getDestinantion());
            }
        }
        System.out.println("Hamilton Circuit:"+hamiltonianCircuit.size());

        // Step 3: Calculate the total weight of the Hamiltonian circuit
        int totalWeight = 0;
        for (int i = 0; i < hamiltonianCircuit.size() - 1; i++) {
            Vertex source = hamiltonianCircuit.get(i);
//            System.out.print(source.getId()+", ");
            Vertex destination = hamiltonianCircuit.get(i + 1);
            List<Edge> edges = multiGraph.get(source);
            for (Edge edge : edges) {
                if (edge.getDestinantion().equals(destination)) {
                    totalWeight += edge.getWeight();
                    break;
                }
            }
        }
        // Add the weight of the last edge connecting the last vertex to the first vertex
        Vertex firstVertex = hamiltonianCircuit.get(0);
        Vertex lastVertex = hamiltonianCircuit.get(hamiltonianCircuit.size() - 1);
        List<Edge> edges = multiGraph.get(lastVertex);
        for (Edge edge : edges) {
            if (edge.getDestinantion().equals(firstVertex)) {
                totalWeight += edge.getWeight();
                break;
            }
        }

        // Step 4: Return the total weight as the TSP solution
        return totalWeight;
    }
}
