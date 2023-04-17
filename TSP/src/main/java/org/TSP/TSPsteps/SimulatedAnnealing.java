package org.TSP.TSPsteps;

import org.TSP.Graph.Vertex;
import org.TSP.Graph.Edge;
import org.TSP.util.GraphUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {

    public static List<Vertex> simulatedAnnealing(List<Vertex> tour, HashMap<Vertex, List<Edge>> graph, double initialTemp, double coolingRate, int maxIterations) {
        double temperature = initialTemp;
        double bestDistance = GraphUtils.calculateTotalDistance(tour, graph);
        List<Vertex> bestTour = new ArrayList<>(tour);

        for (int i = 0; i < maxIterations; i++) {

            List<Vertex> newTour = TwoOpt.twoOpt(tour);

            double newDistance = GraphUtils.calculateTotalDistance(newTour, graph);

            if (shouldAcceptMove(bestDistance, newDistance, temperature)) {
                tour = newTour;
                bestDistance = newDistance;
                bestTour = new ArrayList<>(newTour);
            }
            if(temperature<1) break;
            temperature *= (1 - coolingRate);
        }

        return bestTour;
    }

    private static boolean shouldAcceptMove(double currentDistance, double newDistance, double temperature) {
        if (newDistance < currentDistance) {
            return true;
        } else {
            double probability = Math.exp((currentDistance - newDistance) / temperature);
            return Math.random() < probability;
        }
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