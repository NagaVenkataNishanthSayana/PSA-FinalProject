package org.TSP.TSPSteps;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Vertex;
import org.TSP.TSPsteps.MultiGraph;
import org.TSP.TSPsteps.PrimsMST;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

class MultiGraphTest {
    @Test
    public void testFormMultiGraph() {
        // Create a sample MST
        HashMap<Vertex, List<Edge>> mst = new HashMap<>();
        Vertex v1 = new Vertex("London", 51.5074, -0.1278);
        Vertex v2 = new Vertex("Paris", 48.8566, 2.3522);
        Vertex v3 = new Vertex("Bangalore", 77.5946, 12.9716);
        mst.put(v1, new ArrayList<>());
        mst.put(v2, new ArrayList<>());
        mst.put(v3, new ArrayList<>());
        mst.get(v1).add(new Edge(v1, v2));
        mst.get(v1).add(new Edge(v1, v3));
        mst.get(v2).add(new Edge(v2, v1));
        mst.get(v2).add(new Edge(v2, v3));
        mst.get(v3).add(new Edge(v3, v1));
        mst.get(v3).add(new Edge(v3, v2));

        // Create a sample perfect matching
        List<Edge> perfectMatchedEdges = new ArrayList<>();
        perfectMatchedEdges.add(new Edge(v1, v2));
        perfectMatchedEdges.add(new Edge(v3, v2));

        // Form the multi-graph
        HashMap<Vertex, List<Edge>> multiGraph = MultiGraph.formMultiGraph(mst, perfectMatchedEdges);

        assertEquals(3, multiGraph.get(v1).size()); // London
        assertEquals(4, multiGraph.get(v2).size()); // Paris
        assertEquals(3, multiGraph.get(v3).size()); // Bangalore

        assertEquals(1, v1.getDegree()); // London
        assertEquals(2, v2.getDegree()); // Paris
        assertEquals(1, v3.getDegree()); // Bangalore
    }
}
