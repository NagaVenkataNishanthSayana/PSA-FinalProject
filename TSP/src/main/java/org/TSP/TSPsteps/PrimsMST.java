package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.*;

public class PrimsMST {

    public static HashMap<Vertex, List<Edge>> prim(HashMap<Vertex, List<Edge>> graph, Vertex start){
        HashMap<Vertex, List<Edge>> mst = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();
        List<Edge> mstEdges=new ArrayList<>();
        PriorityQueue<Edge> heap = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return Double.compare(e1.getWeight(), e2.getWeight());
            }
        });

        for (Edge edge : graph.get(start)) {
            heap.add(edge);
        }
        visited.add(start);
        int mstWeight=0;
        while (!heap.isEmpty()) {

            //Pop out the Min weighted Edge
            Edge minEdge = heap.poll();
            Vertex source=minEdge.getSource();
            Vertex destination = minEdge.getDestination();

            //Skip Edge if the Destination already exists
            if (visited.contains(destination)) {
                continue;
            }
            mstEdges.add(minEdge);

            //Add Destination vertex to the visited set
            visited.add(destination);

            //Add Source and Destination vertices into the MST MAP


            mst.putIfAbsent(source,new ArrayList<Edge>());
            mst.putIfAbsent(destination,new ArrayList<Edge>());

            mst.get(destination).add(new Edge(destination,source));
            mst.get(source).add(minEdge);
            //Add edges to Source and Destination vertices

            //Calculating MST cost
            mstWeight+=minEdge.getWeight();

            //Increment Source and destination Degrees
            source.incrementDegree();
            destination.incrementDegree();

            //Put all edges starting from Destination into the Heap
            for (Edge edge : graph.get(destination)) {
                if (!visited.contains(edge.getDestination())) {
                    heap.add(edge);
                }
            }
        }
        for(Edge edge:mstEdges){

        }
        System.out.println("MST edges:"+mstEdges.size());
        System.out.println("MST Weight:"+mstWeight);

        return mst;
    }
}
