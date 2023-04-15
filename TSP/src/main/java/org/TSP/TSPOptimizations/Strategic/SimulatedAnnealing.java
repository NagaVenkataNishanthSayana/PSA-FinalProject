package org.TSP.TSPOptimizations.Strategic;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.util.GraphUtils;

import java.util.*;

public class SimulatedAnnealing {

    public static List<Vertex> optimize(List<Vertex> tour, HashMap<Vertex,List<Edge>> graph){
        double temperature = 100000;
        double coolingFactor = 0.003;
        List<Vertex> optimizedTour=new ArrayList<>(tour);
        for (double t = temperature; t > 1; t *=1- coolingFactor) {
            List<Vertex> currentTour= new ArrayList<>(tour);
            int index1 = new Random().nextInt(currentTour.size());
            int index2 = new Random().nextInt(currentTour.size());
            if(index2==index1) continue;
            Collections.swap(currentTour, index1, index2);

            double tourLength = GraphUtils.calculateTotalDistance(tour,graph);
            double currentTourLength = GraphUtils.calculateTotalDistance(currentTour,graph);

            if (Math.random() < GraphUtils.probability(tourLength, currentTourLength, t)) {
                tour = new ArrayList<>(currentTour);
            }

            if (GraphUtils.calculateTotalDistance(tour,graph) < GraphUtils.calculateTotalDistance(optimizedTour,graph)) {
                optimizedTour = new ArrayList<>(tour);
            }
        }

        return optimizedTour;
    }
}
