package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.util.GraphUtils;

import java.util.*;

public class TSPSolver {
    public static List<Vertex> solve(HashMap<Vertex, List<Edge>> graph, HashMap<Vertex, List<Edge>> multiGraph) {
        // Step 1: Generate an Eulerian tour
        List<Vertex> tspPath=null;
        int minTSPTour=Integer.MAX_VALUE;
        for(Vertex start:multiGraph.keySet()){
            List<Vertex> eulerianTour = EulerianTour.generate(multiGraph,start);
            if (eulerianTour == null) {
                return null; // The input graph is not suitable for TSP
            }
            List<Vertex> hamiltonianCircuit = obtainHamiltonianCircuit(eulerianTour);
            int tspTourCost= GraphUtils.calculateTotalDistance(hamiltonianCircuit,graph);
            if(tspTourCost<minTSPTour){
                minTSPTour=tspTourCost;
                tspPath=hamiltonianCircuit;
            }
        }
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
