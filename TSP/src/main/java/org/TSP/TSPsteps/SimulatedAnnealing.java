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

        Random random = new Random();

        for (int i = 0; i < maxIterations; i++) {
            int index1 = random.nextInt(tour.size());
            int index2 = random.nextInt(tour.size());

            List<Vertex> newTour = TwoOpt.reverseSubtour(tour, Math.min(index1, index2), Math.max(index1, index2));
            double newDistance = GraphUtils.calculateTotalDistance(newTour, graph);

            if (shouldAcceptMove(bestDistance, newDistance, temperature)) {
                tour = newTour;
                bestDistance = newDistance;
                bestTour = new ArrayList<>(newTour);
            }

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
}
