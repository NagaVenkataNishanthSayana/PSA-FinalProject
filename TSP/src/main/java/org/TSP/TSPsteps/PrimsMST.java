package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.*;

public class PrimsMST {

    public static HashMap<Vertex, List<Edge>> prim(HashMap<Vertex, List<Edge>> graph, Vertex start){
        HashMap<Vertex, List<Edge>> mst = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();
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
            Vertex destination = minEdge.getDestinantion();

            //Skip Edge if the Destination already exists
            if (visited.contains(destination)) {
                continue;
            }

            //Add Destination vertex to the visited set
            visited.add(destination);

            //Add Source and Destination vertices into the MST MAP
            if(!mst.containsKey(source)){
                mst.put(source,new ArrayList<Edge>());
            }
            if(!mst.containsKey(destination)){
                mst.put(destination,new ArrayList<Edge>());
            }



            //Add edges to Source and Destination vertices
            if(!mst.get(source).contains(minEdge)){
                mst.get(source).add(minEdge);
            }

            if(!mst.get(destination).contains(minEdge)){
                mst.get(destination).add(minEdge);
            }

            //Calculating MST cost
            mstWeight+=minEdge.getWeight();

            //Increment Source and destination Degrees
            source.incrementDegree();
            destination.incrementDegree();

            //Put all edges starting from Destination into the Heap
            for (Edge edge : graph.get(destination)) {
                if (!visited.contains(edge.getDestinantion())) {
                    heap.add(edge);
                }
            }
        }
        System.out.println("MST Weight:"+mstWeight);
        return mst;
    }
}
