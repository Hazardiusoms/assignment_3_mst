

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import GraphIOModels.GraphData;
import GraphIOModels.Edge;
import algorithms.KruskalMST;
import algorithms.PrimMST;
import utils.GraphGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Generating graphs...");
        List<GraphData> graphs = GraphGenerator.generateGraphs();

        List<Map<String, Object>> resultsList = new ArrayList<>();

        for (GraphData graphData : graphs) {
            System.out.println("Processing graph ID: " + graphData.getId());

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("graph_id", graphData.getId());

            Map<String, Object> inputStats = new LinkedHashMap<>();
            inputStats.put("vertices", graphData.getNodes().size());
            inputStats.put("edges", graphData.getEdges().size());
            result.put("input_stats", inputStats);

            // Run Prim’s algorithm
            PrimMST prim = new PrimMST(graphData);
            prim.run();

            Map<String, Object> primResult = new LinkedHashMap<>();
            primResult.put("mst_edges", prim.getMstEdges());
            primResult.put("total_cost", prim.getTotalCost());
            primResult.put("operations_count", prim.getOperationCount());
            primResult.put("execution_time_ms", prim.getExecutionTimeMs());
            result.put("prim", primResult);

            // Run Kruskal’s algorithm
            KruskalMST kruskal = new KruskalMST(graphData);
            kruskal.run();

            Map<String, Object> kruskalResult = new LinkedHashMap<>();
            kruskalResult.put("mst_edges", kruskal.getMstEdges());
            kruskalResult.put("total_cost", kruskal.getTotalCost());
            kruskalResult.put("operations_count", kruskal.getOperationCount());
            kruskalResult.put("execution_time_ms", kruskal.getExecutionTimeMs());
            result.put("kruskal", kruskalResult);

            resultsList.add(result);
        }

        Map<String, Object> output = new LinkedHashMap<>();
        output.put("results", resultsList);

        // Save results as JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("mst_results.json")) {
            gson.toJson(output, writer);
            System.out.println("Results saved to mst_results.json");
        } catch (IOException e) {
            System.err.println("Error saving results: " + e.getMessage());
        }

        System.out.println("✅ All graphs processed successfully.");
    }
}
