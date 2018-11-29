// Note: you do not need to read this code!

import Lab3Help.*;
import java.io.*;
import java.util.*;

public class GUIMain {
    public static void main(String[] args) {
        Lab3File file = new Lab3File();
        if (args.length != 2) {
            System.err.println("Expected two arguments: stops file and lines file");
            return;
        }
        try {
            List<BStop> stops = file.readStops(args[0]);
            List<BLineTable> lines = file.readLines(args[1]);
            Graph graph = new Graph();
            for (BStop stop: stops) graph.addVertex(stop.getName());
            for (BLineTable line: lines) {
                BLineStop[] linestops = line.getStops();
                for (int i = 0; i < linestops.length - 1; i++)
                    graph.addEdge(linestops[i].getName(), linestops[i+1].getName(), linestops[i+1].getTime());
            }
            new GUI(stops, lines, new GraphPath(graph));
        } catch (MalformedData | IOException e) {
            e.printStackTrace();
        }
    }
}

class GraphPath implements Path<String> {
    Graph graph;
    Graph.Path path = null;
    public GraphPath(Graph graph) { this.graph = graph; }
    public void computePath(String from, String to) {
        path = graph.shortestPath(from, to);
    }
    public int getPathLength() {
        return path.totalDist;
    }
    public Iterator<String> getPath() {
        return path.vertices.iterator();
    }
}