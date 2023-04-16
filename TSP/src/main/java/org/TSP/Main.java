package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.TSPOptimizations.Strategic.SimulatedAnnealing;
import org.TSP.TSPOptimizations.Tactical.ThreeOpt;
import org.TSP.TSPsteps.*;
import org.TSP.TSPOptimizations.Tactical.TwoOpt;
import org.TSP.util.BenchmarkTimer;
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

        // TSP
        List<Vertex>hamiltonCircuit=TSPSolver.solve(graphMap,multiGraph);
        System.out.println("TSP Weight:" +GraphUtils.calculateTotalDistance(hamiltonCircuit,graphMap));

        // TwoOpt
        BenchmarkTimer benchmarkTimer1=new BenchmarkTimer();
        benchmarkTimer1.start();
//        List<Vertex> twoOptPath= TwoOpt.twoOpt(hamiltonCircuit,graphMap,100,1000);
        benchmarkTimer1.end();
//        System.out.println("TwoOpt:"+GraphUtils.calculateTotalDistance(twoOptPath,graphMap));
//        System.out.println("Time taken by TwoOpt method to execute (in seconds): " + benchmarkTimer1.totalTimeElapsed());

        // ThreeOpt
        BenchmarkTimer benchmarkTimer2=new BenchmarkTimer();
        benchmarkTimer2.start();
        List<Vertex> threeOptPath= ThreeOpt.threeOpt(hamiltonCircuit,graphMap);
        benchmarkTimer2.end();
        System.out.println("ThreeOpt:"+GraphUtils.calculateTotalDistance(threeOptPath,graphMap));
        System.out.println("Time taken by ThreeOpt method to execute (in seconds): " + benchmarkTimer2.totalTimeElapsed());

        // Simulated Annealing
        BenchmarkTimer benchmarkTimer3=new BenchmarkTimer();
        benchmarkTimer3.start();
        List<Vertex> annealedTour = SimulatedAnnealing.simulatedAnnealing(hamiltonCircuit, graphMap, 500, 0.001, 500000);
        benchmarkTimer3.end();
        System.out.println("Simulated Annealing: " + GraphUtils.calculateTotalDistance(annealedTour, graphMap));
        System.out.println("Time taken by Simulated Annealing method to execute (in seconds): " + benchmarkTimer3.totalTimeElapsed());

    }
}