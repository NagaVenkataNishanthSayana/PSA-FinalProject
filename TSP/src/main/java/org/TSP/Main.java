package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.*;
import org.TSP.util.BenchmarkTimer;
import org.TSP.util.FileIO;
import org.TSP.util.GraphUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Read file
        FileIO fileIO=new FileIO();
        //Generate Graph
        Graph graph=fileIO.getConnectedGraph();
        HashMap<Vertex, List<Edge>> graphMap=graph.getGraph();

        // Start point
        Vertex start=graphMap.keySet().iterator().next();

        // Prims MST
        HashMap<Vertex,List<Edge>> minSpanTree= PrimsMST.prim(graphMap,start);

        // Find odd vertices
        Set<Vertex> oddVertices= GraphUtils.findVerticesWithOddDegree(minSpanTree);

        // Greedy Matching
        List<Edge> perfectMatchedEdges=GraphUtils.findPerfectMatching(graphMap,oddVertices);

        // MultiGraph
        HashMap<Vertex,List<Edge>> multiGraph= MultiGraph.formMultiGraph(minSpanTree,perfectMatchedEdges);

        // TSP
        System.out.println();
        List<Vertex>hamiltonCircuit=TSPSolver.solve(graphMap,multiGraph);
        System.out.println("TSP Cost:" +GraphUtils.calculateTotalDistance(hamiltonCircuit,graphMap));
        GraphUtils.printTour(hamiltonCircuit);
        System.out.println();

        // TwoOpt
        System.out.println();
        System.out.println("Two Opt Optimization started running...");
        System.out.println("Started TwoOpt - It takes about 1 second to run");
        BenchmarkTimer benchmarkTimerTwoOpt=new BenchmarkTimer();
        benchmarkTimerTwoOpt.start();
        List<Vertex> twoOptPath= TwoOpt.twoOpt(hamiltonCircuit);
        benchmarkTimerTwoOpt.end();
        System.out.println("TwoOpt Cost:"+GraphUtils.calculateTotalDistance(twoOptPath,graphMap));
        System.out.println("Time taken by TwoOpt method to execute (in seconds): " + benchmarkTimerTwoOpt.getElapsedTime());
        GraphUtils.printTour(twoOptPath);
        System.out.println();

        // ThreeOpt
        System.out.println();
        System.out.println("Three Opt Optimization started running...");
        System.out.println("Started ThreeOpt - It takes about 100 seconds to run");
        BenchmarkTimer benchmarkTimerThreeOpt=new BenchmarkTimer();
        benchmarkTimerThreeOpt.start();
        List<Vertex> threeOptPath= ThreeOpt.threeOpt(hamiltonCircuit);
        System.out.println("ThreeOpt Cost:"+GraphUtils.calculateTotalDistance(threeOptPath,graphMap));
        benchmarkTimerThreeOpt.end();
        System.out.println("Time taken by ThreeOpt method to execute (in seconds): " + benchmarkTimerThreeOpt.getElapsedTime());
        GraphUtils.printTour(threeOptPath);
        System.out.println();

        // Simulated Annealing
        System.out.println();
        System.out.println("Simulated Annealing started running...");
        System.out.println("Started Simulated Annealing - It takes about 350 seconds to run");
        BenchmarkTimer benchmarkTimerSmtA=new BenchmarkTimer();
        benchmarkTimerSmtA.start();
        List<Vertex> annealedTour = SimulatedAnnealing.simulatedAnnealing(new ArrayList<>(hamiltonCircuit), graphMap, 1000, 0.001, 500000);
        benchmarkTimerSmtA.end();
        System.out.println("Simulated Annealing Cost: " + GraphUtils.calculateTotalDistance(annealedTour, graphMap));
        System.out.println("Time taken by Simulated Annealing method to execute (in seconds): " + benchmarkTimerSmtA.getElapsedTime());
        GraphUtils.printTour(annealedTour);
        System.out.println();

        //Ant Colony
        System.out.println();
        System.out.println("Ant Colony Optimization started running...");
        System.out.println("Ant Colony Optimization - It takes about 1000 seconds to run");
        BenchmarkTimer benchmarkTimerAnt=new BenchmarkTimer();
        benchmarkTimerAnt.start();
        List<Vertex> antColony=new AntColonyOptimization(hamiltonCircuit).solve();
        benchmarkTimerAnt.end();
        System.out.println("Ant Colony Cost:"+GraphUtils.calculateTotalDistance(antColony,graphMap));
        System.out.println("Time taken by Ant Colony method to execute (in seconds):"+benchmarkTimerAnt.getElapsedTime());
        GraphUtils.printTour(antColony);
        System.out.println();

    }
}