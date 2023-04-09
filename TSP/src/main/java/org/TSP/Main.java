package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.MultiGraph;
import org.TSP.TSPsteps.PrimsMST;
import org.TSP.TSPsteps.BolssomsAlgorithim;
import org.TSP.util.FileIO;
import org.TSP.util.FormGraph;
import org.TSP.util.GraphUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileIO fileIO=new FileIO();


        Graph graph=fileIO.getConnectedGraph();
        HashMap<Vertex, List<Edge>> map=graph.getGraph();

        Vertex start=map.keySet().iterator().next();

        HashMap<Vertex,Edge> minSpanTree= PrimsMST.prim(map,start);
        int count=0;
        for(Vertex v:minSpanTree.keySet()){
            if(v.getDegree()%2!=0) count++;
        }
        System.out.println(count);

        Set<Vertex> oddVertices= GraphUtils.findVerticesWithOddDegree(minSpanTree);
        System.out.println(oddVertices.size());

        List<Edge> perfectMatchedEdges=GraphUtils.findPerfectMatching(map,oddVertices);
        System.out.println(perfectMatchedEdges.size());

        HashMap<Vertex,List<Edge>> multiGraph= MultiGraph.formMultiGraph(minSpanTree,perfectMatchedEdges);
        System.out.println(multiGraph.size());

    }
}