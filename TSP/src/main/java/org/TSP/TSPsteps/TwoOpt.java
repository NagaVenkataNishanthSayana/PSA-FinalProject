package org.TSP.TSPsteps;

import org.TSP.Graph.Vertex;
import java.util.*;

public class TwoOpt {

    public static List<Vertex> twoOpt(List<Vertex> tour) {
        boolean improved = true;

        while (improved) {
            improved = false;

            for (int i = 0; i < tour.size() - 1; i++) {
                for (int j = i + 1; j < tour.size(); j++) {
                    double delta = calculateDelta(tour, i, j);
                    if (delta < 0) {
                        tour = reverseSubtour(tour, i + 1, j);
                        improved = true;
                    }
                }
            }
        }

        return tour;
    }

    private static double calculateDelta(List<Vertex> tour, int i, int j) {
        int n = tour.size();
        Vertex a = tour.get(i);
        Vertex b = tour.get((i + 1) % n);
        Vertex c = tour.get(j);
        Vertex d = tour.get((j + 1) % n);

        double currentDistance = getEdgeWeight(a, b) + getEdgeWeight(c, d);
        double newDistance = getEdgeWeight(a, c) + getEdgeWeight(b, d);

        return newDistance - currentDistance;
    }

    static List<Vertex> reverseSubtour(List<Vertex> tour, int i, int j) {
        List<Vertex> newTour = new ArrayList<>(tour.subList(0, i));
        List<Vertex> segment1 = tour.subList(i, j + 1);
        Collections.reverse(segment1);
        newTour.addAll(segment1);
        newTour.addAll(tour.subList(j + 1, tour.size()));
        return newTour;
    }

    private static double getEdgeWeight(Vertex v1, Vertex v2) {
        return org.apache.lucene.util.SloppyMath.haversinMeters(v1.getLatitude(), v1.getLongitude(), v2.getLatitude(), v2.getLongitude());
    }
}
