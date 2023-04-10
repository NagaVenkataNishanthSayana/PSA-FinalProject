package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.twoOpt;
import java.util.*;

public class TSPSolver {
    public static int solve(HashMap<Vertex, List<Edge>> multiGraph) {
        // Step 1: Generate an Eulerian tour
        List<Vertex> eulerianTour = EulerianTour.generate(multiGraph);
        System.out.println("Eulerian Tour:"+eulerianTour.size());
        if (eulerianTour == null) {
            return -1; // The input graph is not suitable for TSP
        }

        // Step 2: Remove duplicates to obtain a Hamiltonian circuit
        List<Vertex> hamiltonianCircuit = obtainHamiltonianCircuit(eulerianTour);
        System.out.println("Hamilton Circuit size:"+hamiltonianCircuit.size());
        System.out.print("Hamilton Circuit tour: [");
        for (Vertex vertex : hamiltonianCircuit) {
            System.out.print(vertex.getId() + " ");
        }
        System.out.println("]");

        // Step 3: Calculate the total weight of the Hamiltonian circuit
        int totalWeight = 0;
        for (int i = 0; i < hamiltonianCircuit.size() - 1; i++) {
            Vertex source = hamiltonianCircuit.get(i);
//            System.out.print(source.getId()+", ");
            Vertex destination = hamiltonianCircuit.get(i + 1);
            List<Edge> edges = multiGraph.get(source);
            for (Edge edge : edges) {
                if (edge.getDestination().equals(destination)) {
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
            if (edge.getDestination().equals(firstVertex)) {
                totalWeight += edge.getWeight();
                break;
            }
        }

        // use two opt

        twoOptTour(hamiltonianCircuit, multiGraph);
        // Step 4: Return the total weight as the TSP solution
        return totalWeight;
    }

    public static List<Vertex> obtainHamiltonianCircuit(List<Vertex> eulerianTour) {
        List<Vertex> hamiltonianCircuit = new ArrayList<>();
        Set<Vertex> visitedVertices = new HashSet<>();
        for (Vertex vertex : eulerianTour) {
            if (!visitedVertices.contains(vertex)) {
                hamiltonianCircuit.add(vertex);
                visitedVertices.add(vertex);
            }
        }
        return hamiltonianCircuit;
    }

    public static void twoOptTour(List<Vertex> tour, HashMap<Vertex, List<Edge>> multiGraph) {
        List<Vertex> t = twoOpt.twoOpt(tour, multiGraph);
        System.out.println("twoOpt size:" + t.size());
        System.out.print("two opt tour: [");
        for (Vertex vertex : t) {
            System.out.print(vertex.getId() + " ");
        }
        System.out.println("]");

        int tw = 0;
        Vertex prev = t.get(0);
        for (int i = 1; i < t.size(); i++) {
            Vertex current = t.get(i);
            List<Edge> edges = multiGraph.get(prev);
            for (Edge edge : edges) {
                if (edge.getDestination().equals(current)) {
                    tw += edge.getWeight();
                    break;
                }
            }
            prev = current;
        }

        Vertex first = t.get(0);
        Vertex last = t.get(t.size() - 1);
        List<Edge> edges = multiGraph.get(last);
        for (Edge edge : edges) {
            if (edge.getDestination().equals(first)) {
                tw += edge.getWeight();
                break;
            }
        }

        System.out.println("twoOpt weight:" + tw);
    }
}
