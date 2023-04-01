package org.TSP.MST;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.*;

public class PrimMST {

    public static HashMap<Vertex, Edge> prim(HashMap<Vertex, List<Edge>> graph, Vertex start){
        HashMap<Vertex, Edge> tree = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();
        PriorityQueue<Edge> heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                double difference= o1.getWeight()-o2.getWeight();

                if (difference == 0) {

                    // Both are equal
                    return 0;
                }
                else if (difference < 0) {

                    // obj1 < obj2
                    return -1;
                }
                else {

                    // obj1 > obj2
                    return 1;
                }

            }
        });

        visited.add(start);
        for (Edge edge : graph.get(start)) {
            heap.add(edge);
        }

        while (!heap.isEmpty()) {
            Edge minEdge = heap.poll();
            Vertex vertex = minEdge.getDestinantion();

            if (visited.contains(vertex)) {
                continue;
            }

            visited.add(vertex);
            tree.put(vertex, minEdge);

            for (Edge edge : graph.get(vertex)) {
                if (!visited.contains(edge.getDestinantion())) {
                    heap.offer(edge);
                }
            }
        }

        return tree;
    }
}
