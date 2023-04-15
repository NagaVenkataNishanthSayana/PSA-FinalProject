package org.TSP.util;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GraphUtilsTest {

        @Test
        public void testFindVerticesWithOddDegree() {
            // Create a sample MST with three vertices and two edges
            HashMap<Vertex, List<Edge>> mst = new HashMap<>();
            Vertex v1 = new Vertex("London", 51.5074, -0.1278);
            Vertex v2 = new Vertex("Paris", 48.8566, 2.3522);
            Vertex v3 = new Vertex("Bangalore", 77.5946, 12.9716);
            mst.put(v1, new ArrayList<>());
            mst.put(v2, new ArrayList<>());
            mst.put(v3, new ArrayList<>());
            mst.get(v1).add(new Edge(v1, v2));
            mst.get(v2).add(new Edge(v2, v3));

            // Find vertices with odd degree
            Set<Vertex> oddVertices = GraphUtils.findVerticesWithOddDegree(mst);

            // Check that the correct vertices are returned
            assertEquals(0, oddVertices.size());
        }
    }