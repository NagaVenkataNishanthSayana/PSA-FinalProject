package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.*;

public class PrimsMST {

    public static HashMap<Vertex, Edge> prim(HashMap<Vertex, List<Edge>> graph, Vertex start){
        HashMap<Vertex, Edge> mst = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Edge> heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return (int) (e1.getWeight()-e2.getWeight());
            }
        });


        for (Edge edge : graph.get(start)) {
            heap.add(edge);
        }

        while (!heap.isEmpty()) {
            Edge minEdge = heap.poll();
            Vertex vertex = minEdge.getDestinantion();

            if (visited.contains(vertex)) {
                continue;
            }
            minEdge.getSource().incrementDegree();
            minEdge.getDestinantion().incrementDegree();

            visited.add(vertex);
            mst.put(vertex, minEdge);

            for (Edge edge : graph.get(vertex)) {
                if (!visited.contains(edge.getDestinantion())) {
                    heap.add(edge);
                }
            }
        }

        return mst;
    }
}
