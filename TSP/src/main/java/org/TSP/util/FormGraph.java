package org.TSP.util;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FormGraph {
    public static Graph getGraph(Set<Vertex> oddVertices) {

        Graph graph=new Graph();

        oddVertices.forEach((v) ->graph.addVertex(v));

        HashMap<Vertex, List<Edge>> disconnectedGraph = graph.getGraph();

        for (Vertex source : disconnectedGraph.keySet()) {
            for (Vertex destination : disconnectedGraph.keySet()) {
                if (source != destination) {
                    disconnectedGraph.get(source).add(new Edge(source,destination));
                }
            }
        }
        return graph;
    }
}
