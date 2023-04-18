package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.*;

public class EulerianTour {

    public static List<Vertex> generate(HashMap<Vertex, List<Edge>> multiGraph,Vertex start) {
        // Check if the multi graph is connected and has only even-degree vertices
        if (!isConnected(multiGraph) || !hasEvenDegreeVertices(multiGraph)) {
            return null;
        }

        // Initialize an empty list to store the edges in the Eulerian tour
        List<Vertex> eulerianPath = new ArrayList<>();

        // Choose any vertex in the multi graph as the starting vertex
        Vertex startVertex = start;

        Set<Edge> visitedEdge=new HashSet<>();
        Stack<Vertex> st = new Stack<>();
        st.push(startVertex);
        eulerianPath.add(startVertex);

        while(!st.isEmpty()){
            Vertex source=st.pop();
            eulerianPath.add(source);
            List<Edge> list=multiGraph.get(source);
           for(int i=0;i<list.size();i++){
               Vertex destination=list.get(i).getDestination();
               Edge edge=list.get(i);
               if(!visitedEdge.contains(edge)){
                   st.push(destination);
                   visitedEdge.add(edge);
               }
           }

        }

        return eulerianPath;
    }

    private static boolean isConnected(HashMap<Vertex, List<Edge>> multiGraph) {
        // Perform a depth-first search from any vertex to check if all vertices are reachable
        Vertex startVertex = multiGraph.keySet().iterator().next();
        Set<Vertex> visited = new HashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            visited.add(vertex);

            for (Edge edge : multiGraph.get(vertex)) {
                Vertex adjacentVertex = edge.getDestination();

                if (!visited.contains(adjacentVertex)) {
                    stack.push(adjacentVertex);
                }
            }
        }

        return visited.size() == multiGraph.size();
    }

    private static boolean hasEvenDegreeVertices(HashMap<Vertex, List<Edge>> multiGraph) {
        // Check if all vertices have even degree
        for(Vertex vertex: multiGraph.keySet()){
            if(vertex.getDegree()%2!=0) return false;
        }

        return true;
    }
}
