package utils;

import GraphIOModels.Edge;
import GraphIOModels.GraphData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {

    private static final Random random = new Random();

    public static List<GraphData> generateGraphs() {
        List<GraphData> graphs = new ArrayList<>();

        // Small (30 nodes, 5 graphs)
        for (int i = 1; i <= 5; i++) {
            graphs.add(generateGraph(i, 30, "small"));
        }

        // Medium (300 nodes, 10 graphs)
        for (int i = 6; i <= 15; i++) {
            graphs.add(generateGraph(i, 300, "medium"));
        }

        // Large (1000 nodes, 10 graphs)
        for (int i = 16; i <= 25; i++) {
            graphs.add(generateGraph(i, 1000, "large"));
        }

        // Extra Large (1300, 1600, 2000)
        graphs.add(generateGraph(26, 1300, "extra_large"));
        graphs.add(generateGraph(27, 1600, "extra_large"));
        graphs.add(generateGraph(28, 2000, "extra_large"));

        return graphs;
    }

    private static GraphData generateGraph(int id, int nodeCount, String type) {
        List<String> nodeLabels = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            nodeLabels.add("N" + i);
        }

        List<Edge> edges = new ArrayList<>();
        int maxEdges = (nodeCount * (nodeCount - 1)) / 4; // 25% of full connection for density

        for (int i = 0; i < maxEdges; i++) {
            int src = random.nextInt(nodeCount);
            int dest = random.nextInt(nodeCount);
            int weight = random.nextInt(90) + 10; // weights 10–99
            if (src != dest) {
                edges.add(new Edge(src, dest, weight));
            }
        }

        System.out.println("Generated " + type + " graph #" + id + " with " + nodeCount + " nodes and " + edges.size() + " edges.");

        return new GraphData(id, nodeLabels, edges);
    }
}
