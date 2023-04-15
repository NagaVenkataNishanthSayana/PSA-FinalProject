package org.TSP.TSPOptimizations.Tactical;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.util.GraphUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThreeOpt {

    public static List<Vertex> threeOpt(List<Vertex> tour, HashMap<Vertex, List<Edge>> graph, int maxIterations, long timeoutMillis) {
        boolean improved = true;
        int size = tour.size();
        long startTime = System.currentTimeMillis();
        int iteration=0;
        while (System.currentTimeMillis() - startTime < timeoutMillis && improved && iteration < maxIterations) {
            improved = false;
            for (int i = 0; i < size - 2; i++) {
                if(System.currentTimeMillis() - startTime < timeoutMillis) break;
                for (int j = i + 1; j < size-1; j++) {
                    if(System.currentTimeMillis() - startTime < timeoutMillis) break;
                    for (int k = j + 1; k < size; k++){
                        if(System.currentTimeMillis() - startTime < timeoutMillis) break;
                        List<Vertex> newTour = reverseSublist(tour, i, j);
                        newTour = reverseSublist(newTour, j+1, k);
                        double newDistance = GraphUtils.calculateTotalDistance(newTour, graph);
                        double oldDistance = GraphUtils.calculateTotalDistance(tour, graph);
                        if (newDistance < oldDistance) {
                            tour = newTour;
                            improved = true;
                        }
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
