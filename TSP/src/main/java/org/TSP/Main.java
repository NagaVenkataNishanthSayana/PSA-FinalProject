package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.TSPOptimizations.Strategic.SimulatedAnnealing;
import org.TSP.TSPOptimizations.Tactical.ThreeOpt;
import org.TSP.TSPOptimizations.Tactical.TwoOpt;
import org.TSP.TSPsteps.*;
import org.TSP.util.FileIO;
import org.TSP.util.GraphUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileIO fileIO=new FileIO();


        Graph graph=fileIO.getConnectedGraph();
        HashMap<Vertex, List<Edge>> graphMap=graph.getGraph();

        Vertex start=graphMap.keySet().iterator().next();
        System.out.println("MST Start point:"+start.getId());
        HashMap<Vertex,List<Edge>> minSpanTree= PrimsMST.prim(graphMap,start);

        Set<Vertex> oddVertices= GraphUtils.findVerticesWithOddDegree(minSpanTree);
        System.out.println("odd vertices:" + oddVertices.size());

        List<Edge> perfectMatchedEdges=GraphUtils.findPerfectMatching(graphMap,oddVertices);
        System.out.println("perfect matched edges:" + perfectMatchedEdges.size());

        HashMap<Vertex,List<Edge>> multiGraph= MultiGraph.formMultiGraph(minSpanTree,perfectMatchedEdges);
        System.out.println("multi graph:" + multiGraph.size());

        List<Vertex>hamiltonCircuit=TSPSolver.solve(graphMap,multiGraph);
        System.out.println("TSP Weight:" +GraphUtils.calculateTotalDistance(hamiltonCircuit,graphMap));

//        List<Vertex> twoOptPath= TwoOpt.twoOpt(hamiltonCircuit,graphMap,100,100);
//        System.out.println("TwoOpt:"+GraphUtils.calculateTotalDistance(twoOptPath,graphMap));

        List<Vertex> threeOptPath= ThreeOpt.threeOpt(hamiltonCircuit,graphMap,1,1000);
        System.out.println("ThreeOpt:"+GraphUtils.calculateTotalDistance(threeOptPath,graphMap));

        List<Vertex> smTour= SimulatedAnnealing.optimize(hamiltonCircuit,graphMap);
        System.out.println("smTour:"+GraphUtils.calculateTotalDistance(smTour,graphMap));
    }
}