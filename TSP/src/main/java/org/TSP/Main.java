package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.MST.PrimMST;
import org.TSP.util.FileIO;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileIO fileIO=new FileIO();


        Graph graph=fileIO.getConnectedGraph();
        HashMap<Vertex, List<Edge>> map=graph.getGraph();

        Vertex start=map.keySet().iterator().next();

        HashMap<Vertex,Edge> minSpanTree=PrimMST.prim(map,start);

        double sum=0;
        for(Vertex v:minSpanTree.keySet()){
            sum+=minSpanTree.get(v).getWeight();
        }
        System.out.println(sum);
    }
}