package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class twoOpt {
    public static List<Vertex> twoOpt(List<Vertex> tour, HashMap<Vertex, List<Edge>> multiGraph) {
        int improve = 0;
        int iteration = 0;
        int size = tour.size();
        while (improve < 20 && iteration < 100) {
            int bestDistance = calculateTotalDistance(tour, multiGraph);
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    List<Vertex> newTour = reverseSublist(tour, i, j);
                    int newDistance = calculateTotalDistance(newTour, multiGraph);
                    if (newDistance < bestDistance) {
                        tour = newTour;
                        improve = 0;
                        break;
                    }
                }
            }
            iteration++;
            improve++;
        }
        return tour;
    }

    public static int calculateTotalDistance(List<Vertex> tour, HashMap<Vertex, List<Edge>> multiGraph) {
        int totalDistance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            Vertex source = tour.get(i);
            Vertex destination = tour.get(i + 1);
            List<Edge> edges = multiGraph.get(source);
            for (Edge edge : edges) {
                if (edge.getDestination().equals(destination)) {
                    totalDistance += edge.getWeight();
                    break;
                }
            }
        }
        // Add the distance of the last edge connecting the last vertex to the first vertex
        Vertex firstVertex = tour.get(0);
        Vertex lastVertex = tour.get(tour.size() - 1);
        List<Edge> edges = multiGraph.get(lastVertex);
        for (Edge edge : edges) {
            if (edge.getDestination().equals(firstVertex)) {
                totalDistance += edge.getWeight();
                break;
            }
        }
        return totalDistance;
    }

    public static List<Vertex> reverseSublist(List<Vertex> list, int start, int end) {
        List<Vertex> reversedList = new ArrayList<>(list.subList(0, start));
        for (int i = end; i >= start; i--) {
            reversedList.add(list.get(i));
        }
        reversedList.addAll(list.subList(end + 1, list.size()));
        return reversedList;
    }

}
