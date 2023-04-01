package org.TSP.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    HashMap<Vertex, List<Edge>> graph=new HashMap<>();

    public void addVertex(Vertex v){
        if(!graph.containsKey(v)){
            graph.put(v,new ArrayList<Edge>());
        }
    }

    public void addEdge(Vertex source, Vertex destination){

        if(!graph.containsKey(source)){
            graph.put(source,new ArrayList<Edge>());
        }
        if(!graph.containsKey(destination)){
            graph.put(destination,new ArrayList<Edge>());
        }
        graph.get(source).add(new Edge(source,destination));

        graph.get(destination).add(new Edge(destination,source));

    }

    public List<Edge> getAdjVertices(Vertex v){
        return graph.get(v);
    }


    public HashMap<Vertex, List<Edge>> getGraph(){
        return graph;
    }

}
