package org.TSP.util;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {

    private static final String TEST_FILE = "src/test/java/sample.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a sample CSV file with three vertices
        try (PrintWriter writer = new PrintWriter(new File(TEST_FILE))) {
            writer.println("ID,LONGITUDE,LATITUDE");
            writer.println("0,-0.1278,51.5074");
            writer.println("1,2.3522,48.8566");
            writer.println("2,12.9716,77.5946");
        }
    }

    @Test
    void getConnectedGraph() {
        FileIO fileIO = new FileIO(TEST_FILE);
        Graph graph = fileIO.getConnectedGraph();
        Map<Vertex, List<Edge>> connectedGraph = graph.getGraph();

        assertEquals(3, connectedGraph.size());

        Vertex v0 = new Vertex("0", -0.1278, 51.5074);
        Vertex v1 = new Vertex("1", 2.3522, 48.8566);
        Vertex v2 = new Vertex("2", 12.9716, 77.5946);

        // Check if all vertices are present in the graph
        assertTrue(connectedGraph.keySet().stream().anyMatch(v0::equals));
        assertTrue(connectedGraph.keySet().stream().anyMatch(v1::equals));
        assertTrue(connectedGraph.keySet().stream().anyMatch(v2::equals));
    }

    @Test
    void testFileNotFoundException() {
        // Test with a non-existent file
        String nonExistentFile = "non_existent_file.csv";
        FileIO fileIO = new FileIO(nonExistentFile);

        assertThrows(RuntimeException.class, fileIO::getConnectedGraph);
    }
    @Test
    void testIOException() throws IOException {
        // Test with a directory path instead of a file
        String directoryPath = "src/test/tempDir/sample.csv";
        FileIO fileIO = new FileIO(directoryPath);

        assertThrows(RuntimeException.class, fileIO::getConnectedGraph);
    }

}
