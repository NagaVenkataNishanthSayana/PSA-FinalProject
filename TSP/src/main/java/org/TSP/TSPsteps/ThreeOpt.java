package org.TSP.TSPsteps;

import org.TSP.Graph.Vertex;
import org.TSP.Graph.Edge;
import java.util.*;

public class ThreeOpt {

    public static List<Vertex> threeOpt(List<Vertex> tour) {
        while (true) {
            int delta = 0;
            for (int[] segment : allSegments(tour.size())) {
                delta += reverseSegmentIfBetter(tour, segment[0], segment[1], segment[2]);
            }
            if (delta >= 0) {
                break;
            }
        }
        return tour;
    }

    private static int[][] allSegments(int n) {
        List<int[]> segments = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 2; j < n-2; j++) {
                for (int k = j + 2; k < n-4; k++) {
                    segments.add(new int[]{i, j, k});
                }
            }
        }
        return segments.toArray(new int[0][0]);
    }

    private static double reverseSegmentIfBetter(List<Vertex> tour, int i, int j, int k) {
        Vertex A = tour.get(i-1), B = tour.get(i), C = tour.get(j-1), D = tour.get(j),
                E = tour.get(k-1), F = tour.get(k % tour.size());
        double d0 = getEdgeWeight(A, B) + getEdgeWeight(C, D) + getEdgeWeight(E, F);
        double d1 = getEdgeWeight(A, C) + getEdgeWeight(B, D) + getEdgeWeight(E, F);
        double d2 = getEdgeWeight(A, B) + getEdgeWeight(C, E) + getEdgeWeight(D, F);
        double d3 = getEdgeWeight(A, D) + getEdgeWeight(E, B) + getEdgeWeight(C, F);
        double d4 = getEdgeWeight(F, B) + getEdgeWeight(C, D) + getEdgeWeight(E, A);

        if (d0 > d1) {
            Collections.reverse(tour.subList(i, j));
            return -d0 + d1;
        } else if (d0 > d2) {
            Collections.reverse(tour.subList(j, k));
            return -d0 + d2;
        } else if (d0 > d4) {
            Collections.reverse(tour.subList(i, k));
            return -d0 + d4;
        } else if (d0 > d3) {
            List<Vertex> tmp = new ArrayList<>(tour.subList(j, k));
            tmp.addAll(tour.subList(i, j));
            tour.subList(i, k).clear();
            tour.addAll(i, tmp);
            return -d0 + d3;
        }
        return 0;
    }

    private static double getEdgeWeight(Vertex v1, Vertex v2) {
        // calculate the distance between two vertices using the Haversine formula
        return org.apache.lucene.util.SloppyMath.haversinMeters(v1.getLatitude(), v1.getLongitude(), v2.getLatitude(), v2.getLongitude());
    }
}
