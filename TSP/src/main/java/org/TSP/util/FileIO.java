package org.TSP.util;

import org.TSP.Graph.Edge;
import org.TSP.Graph.Graph;
import org.TSP.Graph.Vertex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileIO {

        public Graph getConnectedGraph() {
            Graph graph=new Graph();
            try {
                int count = 0;
                String line = "";
                String splitBy = ",";
                BufferedReader br;
                br = new BufferedReader(new FileReader("F:\\Projects\\PSA\\PSA-FinalProject\\TSP\\src\\main\\resources\\crimeSample.csv"));
                br.readLine();
                while ((line = br.readLine()) != null)   //returns a Boolean value
                {
//                    System.out.println(line);
                    String[] vertexDetails = line.split(splitBy);
                    graph.addVertex(new Vertex(String.valueOf(count), Double.parseDouble(vertexDetails[1]), Double.parseDouble(vertexDetails[2])));
                    count++;

                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            HashMap<Vertex, List<Edge>> disconnectedGraph = graph.getGraph();
            for (Vertex source : disconnectedGraph.keySet()) {
                disconnectedGraph.put(source,new ArrayList<Edge>());
                for (Vertex destination : disconnectedGraph.keySet()) {
                    if (source != destination) {
                        disconnectedGraph.get(source).add(new Edge(source,destination));
                    }
                }
            }
            return graph;
        }

}


