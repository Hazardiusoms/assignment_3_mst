package utils;

import GraphIOModels.Edge;
import GraphIOModels.GraphData;

import java.util.*;

public class GraphGenerator {

    private static final Random random = new Random();

    public static List<GraphData> generateGraphs() {
        List<GraphData> graphs = new ArrayList<>();

        // 5 small graphs (≈30 nodes)
        for (int i = 1; i <= 5; i++) {
            graphs.add(generateGraph(i, 30, 0.3));
        }

        // 10 medium graphs (≈300 nodes)
        for (int i = 6; i <= 15; i++) {
            graphs.add(generateGraph(i, 300, 0.2));
        }

        // 10 large graphs (≈1000 nodes)
        for (int i = 16; i <= 25; i++) {
            graphs.add(generateGraph(i, 1000, 0.05));
        }

        // 3 extra-large graphs
        graphs.add(generateGraph(26, 1300, 0.04));
        graphs.add(generateGraph(27, 1600, 0.03));
        graphs.add(generateGraph(28, 2000, 0.02));

        return graphs;
    }

    /**
     * Generate a connected random graph.
     * @param id Graph ID
     * @param nodeCount number of vertices
     * @param density probability of edge creation (0 < density ≤ 1)
     */
    private static GraphData generateGraph(int id, int nodeCount, double density) {
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            nodes.add("N" + i);
        }

        List<Edge> edges = new ArrayList<>();

        // Ensure connectivity with a spanning backbone
        for (int i = 1; i < nodeCount; i++) {
            String from = nodes.get(random.nextInt(i));
            String to = nodes.get(i);
            int weight = random.nextInt(90) + 10; // weight between 10–99
            edges.add(new Edge(from, to, weight));
        }

        // Add additional random edges based on density
        for (int i = 0; i < nodeCount; i++) {
            for (int j = i + 1; j < nodeCount; j++) {
                if (random.nextDouble() < density) {
                    int weight = random.nextInt(90) + 10;
                    edges.add(new Edge(nodes.get(i), nodes.get(j), weight));
                }
            }
        }

        System.out.println("Generated graph ID " + id + " with " +
                nodeCount + " nodes and " + edges.size() + " edges.");

        return new GraphData(id, nodes, edges);
    }
}
