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
//        System.out.println("Started ThreeOpt - It took about 1 second to run");
        BenchmarkTimer benchmarkTimerTwoOpt=new BenchmarkTimer();
        benchmarkTimerTwoOpt.start();
        List<Vertex> twoOptPath= TwoOpt.twoOpt(hamiltonCircuit);
        benchmarkTimerTwoOpt.end();
        System.out.println("TwoOpt:"+GraphUtils.calculateTotalDistance(twoOptPath,graphMap));
        System.out.println("Time taken by TwoOpt method to execute (in seconds): " + benchmarkTimerTwoOpt.getElapsedTime());

        // ThreeOpt
//        System.out.println("Started ThreeOpt - It took about 100 seconds to run");
        BenchmarkTimer benchmarkTimerThreeOpt=new BenchmarkTimer();
        benchmarkTimerThreeOpt.start();
        List<Vertex> threeOptPath= ThreeOpt.threeOpt(hamiltonCircuit);
        System.out.println("ThreeOpt:"+GraphUtils.calculateTotalDistance(threeOptPath,graphMap));
        benchmarkTimerThreeOpt.end();
        System.out.println("Time taken by ThreeOpt method to execute (in seconds): " + benchmarkTimerThreeOpt.getElapsedTime());

        // Simulated Annealing
//        System.out.println("Started Simulated Annealing - It took about 350 seconds to run");
        BenchmarkTimer benchmarkTimerSmtA=new BenchmarkTimer();
        benchmarkTimerSmtA.start();
        List<Vertex> annealedTour = SimulatedAnnealing.simulatedAnnealing(hamiltonCircuit, graphMap, 500, 0.001, 500000);
        benchmarkTimerSmtA.end();
        System.out.println("Simulated Annealing: " + GraphUtils.calculateTotalDistance(annealedTour, graphMap));
        System.out.println("Time taken by Simulated Annealing method to execute (in seconds): " + benchmarkTimerSmtA.getElapsedTime());

        //Ant Colony
        BenchmarkTimer benchmarkTimerAnt=new BenchmarkTimer();
        benchmarkTimerAnt.start();
        List<Vertex> antColony=new AntColonyOptimization(hamiltonCircuit).solve();
        benchmarkTimerAnt.end();
        System.out.println("Ant Colony:"+GraphUtils.calculateTotalDistance(antColony,graphMap));
        System.out.println("Ant Colony ran in:"+benchmarkTimerAnt.getElapsedTime());

    }
}