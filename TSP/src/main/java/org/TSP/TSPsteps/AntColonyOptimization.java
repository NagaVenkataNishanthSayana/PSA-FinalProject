package org.TSP.TSPsteps;

import org.TSP.Graph.Vertex;
import org.TSP.util.GraphUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColonyOptimization {

    private static final int NUM_ANTS = 50;
    private static final int NUM_ITERATIONS = 1000;
    private static final double ALPHA = 1.0;
    private static final double BETA = 2.0;
    private static final double RHO = 0.5;
    private static final double Q = 100.0;
    private static final double INIT_PHEROMONE = 1.0;

    private List<Vertex> vertices;
    private double[][] distanceMatrix;
    private double[][] pheromoneMatrix;
    private Random random;

    public AntColonyOptimization(List<Vertex> vertices) {
        this.vertices = vertices;
        this.distanceMatrix = calculateDistanceMatrix(vertices);
        this.pheromoneMatrix = initializePheromoneMatrix(vertices.size());
        this.random = new Random();
    }

    public List<Vertex> solve() {
        List<List<Vertex>> bestSolutions = new ArrayList<>();
        double bestTourLength = Double.MAX_VALUE;

        for (int iteration = 0; iteration < NUM_ITERATIONS; iteration++) {
            List<List<Vertex>> solutions = new ArrayList<>();
            for (int ant = 0; ant < NUM_ANTS; ant++) {
                List<Vertex> tour = buildTour();
                solutions.add(tour);
            }

            for (List<Vertex> tour : solutions) {
                double tourLength = calculateTourLength(tour);
                if (tourLength < bestTourLength) {
                    bestTourLength = tourLength;
                    bestSolutions.clear();
                    bestSolutions.add(new ArrayList<>(tour));
                } else if (tourLength == bestTourLength) {
                    bestSolutions.add(new ArrayList<>(tour));
                }
            }

            updatePheromoneMatrix(solutions);
        }

        return bestSolutions.get(random.nextInt(bestSolutions.size()));
    }

    private List<Vertex> buildTour() {
        List<Vertex> unvisited = new ArrayList<>(vertices);
        List<Vertex> tour = new ArrayList<>();

        Vertex start = unvisited.remove(random.nextInt(unvisited.size()));
        tour.add(start);

        while (!unvisited.isEmpty()) {
            Vertex last = tour.get(tour.size() - 1);
            Vertex next = selectNextVertex(last, unvisited);
            tour.add(next);
            unvisited.remove(next);
        }

        return tour;
    }

    private Vertex selectNextVertex(Vertex current, List<Vertex> unvisited) {
        double sumProb = 0.0;
        for (Vertex vertex : unvisited) {
            int i = vertices.indexOf(current);
            int j = vertices.indexOf(vertex);
            sumProb += Math.pow(pheromoneMatrix[i][j], ALPHA) * Math.pow(1.0 / distanceMatrix[i][j], BETA);
        }

        double randomProb = random.nextDouble() * sumProb;
        double cumulativeProb = 0.0;

        for (Vertex vertex : unvisited) {
            int i = vertices.indexOf(current);
            int j = vertices.indexOf(vertex);
            cumulativeProb += Math.pow(pheromoneMatrix[i][j], ALPHA) * Math.pow(1.0 / distanceMatrix[i][j], BETA);

            if (cumulativeProb >= randomProb) {
                return vertex;
            }
        }

        return unvisited.get(unvisited.size() - 1);
    }

    private double[][] calculateDistanceMatrix(List<Vertex> vertices) {
        int n = vertices.size();
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = GraphUtils.getWeight(vertices.get(i),vertices.get(j));
            }
        }

        return matrix;
    }

    private double[][] initializePheromoneMatrix(int n) {
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = INIT_PHEROMONE;
            }
        }

        return matrix;
    }

    private double calculateTourLength(List<Vertex> tour) {
        double length = 0.0;

        for (int i = 0; i < tour.size(); i++) {
            Vertex current = tour.get(i);
            Vertex next = tour.get((i + 1) % tour.size());
            length += GraphUtils.getWeight(current,next);
        }

        return length;
    }

    private void updatePheromoneMatrix(List<List<Vertex>> solutions) {
        int n = vertices.size();
        double[][] newPheromoneMatrix = new double[n][n];

        for (List<Vertex> tour : solutions) {
            double tourLength = calculateTourLength(tour);
            double deltaPheromone = Q / tourLength;

            for (int i = 0; i < tour.size(); i++) {
                int current = vertices.indexOf(tour.get(i));
                int next = vertices.indexOf(tour.get((i + 1) % tour.size()));

                newPheromoneMatrix[current][next] += deltaPheromone;
                newPheromoneMatrix[next][current] += deltaPheromone;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pheromoneMatrix[i][j] = (1 - RHO) * pheromoneMatrix[i][j] + newPheromoneMatrix[i][j];
            }
        }
    }
}
