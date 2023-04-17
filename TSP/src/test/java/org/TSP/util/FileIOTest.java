package org.TSP.util;

import org.junit.jupiter.api.Test;
import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.TSP.util.FileIO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileIOTest {

    @Test
    public void testFileIO() {
        FileIO fileIO = new FileIO();
        Graph graph = fileIO.getConnectedGraph();

        assertNotNull(graph);
        assertTrue(graph.getGraph().size() > 0);
    }

    @Test
    public void testGraph() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex("1", 0.0, 0.0);
        Vertex v2 = new Vertex("2", 1.0, 1.0);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2);

        List<Vertex> adjVertices = graph.getAdjVertices(v1);
        assertTrue(adjVertices.size() == 1);
        assertEquals(v2, adjVertices.get(0));
    }
}
