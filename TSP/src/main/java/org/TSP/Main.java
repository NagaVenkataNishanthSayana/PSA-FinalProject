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
        for (Vertex vertex : multiGraph.keySet()) {
            List<Edge> edges = multiGraph.get(vertex);
            for (Edge edge : edges) {
                System.out.println(edge.getDestination().getId());
            }
        }

        List<Vertex>hamiltonCircuit=TSPSolver.solve(graphMap,multiGraph);
        System.out.println("TSP Weight:" +GraphUtils.calculateTotalDistance(hamiltonCircuit,graphMap));

        long startTime = System.nanoTime();

        List<Vertex> threeOptPath= ThreeOpt.threeOpt(hamiltonCircuit,graphMap);
        System.out.println("ThreeOpt:"+GraphUtils.calculateTotalDistance(threeOptPath,graphMap));

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        double secondsElapsed = (double) timeElapsed / 1_000_000_000.0;
        System.out.println("Time taken by ThreeOpt method to execute (in seconds): " + secondsElapsed);
}}