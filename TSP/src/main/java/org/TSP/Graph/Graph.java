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

    public void addEdge(Edge e){
        if(!graph.containsKey(e.getVertex1())){
            graph.put(e.getVertex1(),new ArrayList<Edge>());
        }
        if(!graph.containsKey(e.getVertex2())){
            graph.put(e.getVertex2(),new ArrayList<Edge>());
        }

        graph.get(e.getVertex1()).add(e);
        graph.get(e.getVertex2()).add(e);
    }

    public Map<Vertex, List<Edge>> getGraph(){
        return graph;
    }

}
