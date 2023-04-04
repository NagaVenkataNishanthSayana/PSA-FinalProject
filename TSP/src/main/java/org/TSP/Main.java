package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.MST.PrimsMST;
import org.TSP.MWPM.BolssomsAlgorithim;
import org.TSP.util.FileIO;
import org.TSP.util.FormGraph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileIO fileIO=new FileIO();


        Graph graph=fileIO.getConnectedGraph();
        HashMap<Vertex, List<Edge>> map=graph.getGraph();

        Vertex start=map.keySet().iterator().next();

        HashMap<Vertex,Edge> minSpanTree= PrimsMST.prim(map,start);

//        double mstPathCost=0;
//        List<String> mstPath=new ArrayList<>();

        //Isolating Odd vertices
        Set<Vertex> oddVertices=new HashSet<>();
        for(Vertex v:minSpanTree.keySet()){
//            mstPath.add(v.getId());
            if(v.getDegree()%2!=0) oddVertices.add(v);
//            mstPathCost+=minSpanTree.get(v).getWeight();
        }

        //Forming a graph from Odd Vertices
        Graph oddVerticesGraph= FormGraph.getGraph(oddVertices);

        List<Edge> minWeightPM= BolssomsAlgorithim.findMWPM(oddVerticesGraph);
    }
}