package org.TSP.TSPsteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.util.GraphUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class twoOpt {
    public static List<Vertex> twoOpt(List<Vertex> tour, HashMap<Vertex, List<Edge>> graph) {
        int improve = 0;
        int iteration = 0;
        int size = tour.size();
        while (improve < 20 && iteration < 100) {
            int bestDistance = GraphUtils.calculateTotalDistance(tour, graph);
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    List<Vertex> newTour = reverseSublist(tour, i, j);
                    int newDistance = GraphUtils.calculateTotalDistance(newTour, graph);
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

    public static List<Vertex> reverseSublist(List<Vertex> list, int start, int end) {
        List<Vertex> reversedList = new ArrayList<>(list.subList(0, start));
        for (int i = end; i >= start; i--) {
            reversedList.add(list.get(i));
        }
        reversedList.addAll(list.subList(end + 1, list.size()));
        return reversedList;
    }

}
