package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultiGraph {

    public static HashMap<Vertex, List<Edge>> formMultiGraph(HashMap<Vertex,List<Edge>> mst,List<Edge> perfectMatchedEdges){

        // Combine the MST and the perfect matching to form a new graph that contains only even-degree vertices

        //Create a perfect copy of the existing MST
        HashMap<Vertex, List<Edge>> evenDegreeGraph = new HashMap<>();
        for (Vertex vertex : mst.keySet()) {
            evenDegreeGraph.put(vertex, new ArrayList<Edge>(mst.get(vertex)));
        }


        // Reverse perfect matching edges and add them to the new graph
        for (Edge edge : perfectMatchedEdges) {
            Vertex source = edge.getSource();
            Vertex destination = edge.getDestination();
            evenDegreeGraph.get(source).add(edge);
            evenDegreeGraph.get(destination).add(new Edge(destination, source));
        }

        return evenDegreeGraph;
    }

}
