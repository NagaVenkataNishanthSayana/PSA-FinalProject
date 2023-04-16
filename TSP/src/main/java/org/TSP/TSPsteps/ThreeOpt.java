package org.TSP.TSPsteps;

import org.TSP.Graph.Vertex;
import org.TSP.Graph.Edge;
import java.util.*;

public class ThreeOpt {

    public static List<Vertex> threeOpt(List<Vertex> tour, HashMap<Vertex, List<Edge>> graph) {
        boolean improved = true;

        while (improved) {
            improved = false;

            for (int i = 0; i < tour.size() - 2; i++) {
                for (int j = i + 1; j < tour.size() - 1; j++) {
                    for (int k = j + 1; k < tour.size(); k++) {
                        double delta = calculateDelta(tour, i, j, k);
                        if (delta < 0) {
                            tour = reverseSubtour(tour, i + 1, j, k);
                            improved = true;
                        }
                    }
                }
            }
        }
        System.out.println("Size of 3-opt tour" + tour.size());
        return tour;
    }

    private static double calculateDelta(List<Vertex> tour, int i, int j, int k) {
        int n = tour.size();
        Vertex a = tour.get(i);
        Vertex b = tour.get((i + 1) % n);
        Vertex c = tour.get(j);
        Vertex d = tour.get((j + 1) % n);
        Vertex e = tour.get(k);
        Vertex f = tour.get((k + 1) % n);

        double currentDistance = getEdgeWeight(a, b) + getEdgeWeight(c, d) + getEdgeWeight(e, f);
        double newDistance = getEdgeWeight(a, c) + getEdgeWeight(b, e) + getEdgeWeight(d, f);

        return newDistance - currentDistance;
    }

    private static List<Vertex> reverseSubtour(List<Vertex> tour, int i, int j, int k) {
        List<Vertex> newTour = new ArrayList<>(tour.subList(0, i));
        List<Vertex> segment1 = tour.subList(i, j + 1);
        List<Vertex> segment2 = tour.subList(j + 1, k + 1);
        Collections.reverse(segment1);
        newTour.addAll(segment1);
        newTour.addAll(segment2);
        newTour.addAll(tour.subList(k + 1, tour.size()));
        return newTour;
    }

    private static double getEdgeWeight(Vertex v1, Vertex v2) {
        return org.apache.lucene.util.SloppyMath.haversinMeters(v1.getLatitude(), v1.getLongitude(), v2.getLatitude(), v2.getLongitude());
    }
}
