package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.*;
import org.TSP.util.FileIO;
import org.TSP.util.GraphUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileIO fileIO=new FileIO();

        // Read file
        Graph graph=fileIO.getConnectedGraph();
        HashMap<Vertex, List<Edge>> graphMap=graph.getGraph();
        // Start point
        Vertex start=graphMap.keySet().iterator().next();
        System.out.println("MST Start point:"+start.getId());
        // Prims MST
        HashMap<Vertex,List<Edge>> minSpanTree= PrimsMST.prim(graphMap,start);
        // Find odd vertices
        Set<Vertex> oddVertices= GraphUtils.findVerticesWithOddDegree(minSpanTree);
        System.out.println("odd vertices:" + oddVertices.size());
        // Greedy Matching
        List<Edge> perfectMatchedEdges=GraphUtils.findPerfectMatching(graphMap,oddVertices);
        System.out.println("perfect matched edges:" + perfectMatchedEdges.size());
        // MultiGraph
        HashMap<Vertex,List<Edge>> multiGraph= MultiGraph.formMultiGraph(minSpanTree,perfectMatchedEdges);
        System.out.println("multi graph:" + multiGraph.size());
        for (Vertex vertex : multiGraph.keySet()) {
            List<Edge> edges = multiGraph.get(vertex);
            for (Edge edge : edges) {
                System.out.println(edge.getDestination().getId());
            }
        }
        // TSP
        List<Vertex>hamiltonCircuit=TSPSolver.solve(graphMap,multiGraph);
        System.out.println("TSP Weight:" +GraphUtils.calculateTotalDistance(hamiltonCircuit,graphMap));
        // TwoOpt
        System.out.println("Started ThreeOpt - It took about 1 second to run");
        long startTime1 = System.nanoTime();
        List<Vertex> twoOptPath= TwoOpt.twoOpt(hamiltonCircuit);
        System.out.println("TwoOpt:"+GraphUtils.calculateTotalDistance(twoOptPath,graphMap));
        long endTime1 = System.nanoTime();
        long timeElapsed1 = endTime1 - startTime1;
        double secondsElapsed1 = (double) timeElapsed1 / 1_000_000_000.0;
        System.out.println("Time taken by TwoOpt method to execute (in seconds): " + secondsElapsed1);
        // ThreeOpt
        System.out.println("Started ThreeOpt - It took about 100 seconds to run");
        long startTime2 = System.nanoTime();
        List<Vertex> threeOptPath= ThreeOpt.threeOpt(hamiltonCircuit,graphMap);
        System.out.println("ThreeOpt:"+GraphUtils.calculateTotalDistance(threeOptPath,graphMap));
        long endTime2 = System.nanoTime();
        long timeElapsed2 = endTime2 - startTime2;
        double secondsElapsed2 = (double) timeElapsed2 / 1_000_000_000.0;
        System.out.println("Time taken by ThreeOpt method to execute (in seconds): " + secondsElapsed2);
        // Simulated Annealing
        System.out.println("Started Simulated Annealing - It took about 350 seconds to run");
        long startTime3 = System.nanoTime();
        List<Vertex> annealedTour = SimulatedAnnealing.simulatedAnnealing(hamiltonCircuit, graphMap, 500, 0.001, 500000);
        System.out.println("Simulated Annealing: " + GraphUtils.calculateTotalDistance(annealedTour, graphMap));
        long endTime3 = System.nanoTime();
        long timeElapsed3 = endTime3 - startTime3;
        double secondsElapsed3 = (double) timeElapsed3 / 1_000_000_000.0;
        System.out.println("Time taken by Simulated Annealing method to execute (in seconds): " + secondsElapsed3);
    }}