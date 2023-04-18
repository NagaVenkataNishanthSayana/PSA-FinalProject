package org.TSP.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    HashMap<Vertex, List<Edge>> graph=new HashMap<>();

    public void addVertex(Vertex v){
        if(!this.graph.containsKey(v)){
            this.graph.put(v,new ArrayList<Edge>());
        }
    }

    public void addEdge(Vertex source, Vertex destination){
        if(!this.graph.get(source).contains(destination)){
            this.graph.get(source).add(new Edge(source,destination));
        }

        if(!this.graph.get(destination).contains(source)){
            this.graph.get(destination).add(new Edge(destination,source));
        }

    }

    public List<Vertex> getAdjVertices(Vertex v){

        List<Vertex> adj=new ArrayList<>();
        for(Edge e:this.graph.get(v)){
            adj.add(e.getDestination());
        }

        return adj;
    }


    public HashMap<Vertex, List<Edge>> getGraph(){
        return this.graph;
    }

}
