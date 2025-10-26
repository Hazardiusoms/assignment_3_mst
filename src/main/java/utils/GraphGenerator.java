package utils;

import GraphIOModels.GraphData;
import GraphIOModels.Edge;
import utils.JsonUtils;

import java.util.*;

public class GraphGenerator {

    public static List<GraphData> generateGraphs() {
        List<GraphData> graphs = new ArrayList<>();

        // 5 small (30 nodes)
        for (int i = 0; i < 5; i++) graphs.add(generateGraph(30, "small_" + i));

        // 10 medium (300 nodes)
        for (int i = 0; i < 10; i++) graphs.add(generateGraph(300, "medium_" + i));

        // 10 large (1000 nodes)
        for (int i = 0; i < 10; i++) graphs.add(generateGraph(1000, "large_" + i));

        // 3 extra large (1300, 1600, 2000 nodes)
        graphs.add(generateGraph(1300, "xlarge_1300"));
        graphs.add(generateGraph(1600, "xlarge_1600"));
        graphs.add(generateGraph(2000, "xlarge_2000"));

        // Save to JSON
        JsonUtils.writeGraphsToJson(graphs, "assign_3_input.json");

        return graphs;
    }

    private static GraphData generateGraph(int numNodes, String id) {
        Random rand = new Random();
        List<String> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        // Create nodes
        for (int i = 0; i < numNodes; i++) {
            nodes.add("N" + i);
        }

        // Connect randomly (sparse)
        for (int i = 0; i < numNodes * 2; i++) {
            int src = rand.nextInt(numNodes);
            int dest = rand.nextInt(numNodes);
            int weight = rand.nextInt(100) + 1;
            if (src != dest) edges.add(new Edge(src, dest, weight));
        }

        return new GraphData(id, numNodes, nodes, edges);
    }
}
