package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.util.GraphUtils;

import java.util.*;

public class TSPSolver {
    public static List<Vertex> solve(HashMap<Vertex, List<Edge>> graph, HashMap<Vertex, List<Edge>> multiGraph) {
        // Step 1: Generate an Eulerian tour
        List<Vertex> tspPath=null;
        double minTSPTour=Double.MAX_VALUE;
        Vertex minVertex=null;
        for(Vertex start:multiGraph.keySet()){
            List<Vertex> eulerianTour = EulerianTour.generate(multiGraph,start);
            if (eulerianTour == null) {
                return null; // The input graph is not suitable for TSP
            }
            List<Vertex> hamiltonianCircuit = obtainHamiltonianCircuit(eulerianTour);
            double tspTourCost= GraphUtils.calculateTotalDistance(hamiltonianCircuit,graph);
            if(tspTourCost<minTSPTour){
                minVertex=start;
                minTSPTour=tspTourCost;
                tspPath=hamiltonianCircuit;
            }
        }
        System.out.println("Min Vertex:"+minVertex.getId());
        return tspPath;
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
}
