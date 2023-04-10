package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.MultiGraph;
import org.TSP.TSPsteps.PrimsMST;
import org.TSP.TSPsteps.BolssomsAlgorithim;
import org.TSP.util.FileIO;
import org.TSP.TSPsteps.TSPSolver;
import org.TSP.util.GraphUtils;
import org.TSP.TSPsteps.EulerianTour;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileIO fileIO=new FileIO();


        Graph graph=fileIO.getConnectedGraph();
        HashMap<Vertex, List<Edge>> map=graph.getGraph();

        Vertex start=map.keySet().iterator().next();
        System.out.println("MST Start point:"+start.getId());
        HashMap<Vertex,List<Edge>> minSpanTree= PrimsMST.prim(map,start);

        Set<Vertex> oddVertices= GraphUtils.findVerticesWithOddDegree(minSpanTree);
        System.out.println("odd vertices:" + oddVertices.size());

        List<Edge> perfectMatchedEdges=GraphUtils.findPerfectMatching(map,oddVertices);
        System.out.println("perfect matched edges:" + perfectMatchedEdges.size());

        HashMap<Vertex,List<Edge>> multiGraph= MultiGraph.formMultiGraph(minSpanTree,perfectMatchedEdges);
        System.out.println("multi graph:" + multiGraph.size());

        int tspSolution = TSPSolver.solve(multiGraph);
        System.out.println("Total Weight:" + tspSolution);

    }
}