package org.TSP.TSPOptimizations.Tactical;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.util.GraphUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoOpt {
    public static List<Vertex> twoOpt(List<Vertex> tour, HashMap<Vertex, List<Edge>> graph,int maxIterations, long timeoutMillis) {
        boolean improved = true;
        int size = tour.size();
        long startTime = System.currentTimeMillis();
        int iteration=0;
        while (improved && iteration < maxIterations && System.currentTimeMillis() - startTime < timeoutMillis) {
            improved = false;
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    List<Vertex> newTour = reverseSublist(tour, i, j);
                    double newDistance = GraphUtils.calculateTotalDistance(newTour, graph);
                    double oldDistance = GraphUtils.calculateTotalDistance(tour, graph);
                    if (newDistance < oldDistance) {
                        tour = newTour;
                        improved = true;
                    }
                }
            }
            iteration++;

        }
        return tour;
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
