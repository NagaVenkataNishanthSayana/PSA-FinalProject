package org.TSP;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.MST.PrimsMST;
import org.TSP.util.FileIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileIO fileIO=new FileIO();


        Graph graph=fileIO.getConnectedGraph();
        HashMap<Vertex, List<Edge>> map=graph.getGraph();

        Vertex start=map.keySet().iterator().next();

        HashMap<Vertex,Edge> minSpanTree= PrimsMST.prim(map,start);

        double sum=0;
        List<String> path=new ArrayList<>();
        for(Vertex v:minSpanTree.keySet()){
            path.add(v.getId());
            sum+=minSpanTree.get(v).getWeight();
        }
        System.out.println(sum);
        System.out.println(path);
    }
}