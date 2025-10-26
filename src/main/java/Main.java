

import algorithms.KruskalMST;
import algorithms.PrimMST;
import utils.GraphGenerator;
import GraphIOModels.Edge;
import GraphIOModels.GraphData;
import utils.JsonUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting MST comparison...");

        // Generate or load graphs
        List<GraphData> datasets = GraphGenerator.generateGraphs();
        List<Map<String, Object>> summary = new ArrayList<>();

        for (GraphData graphData : datasets) {
            System.out.println("\nProcessing graph: " + graphData.getId() +
                    " (" + graphData.getNodeCount() + " nodes)");

            // Run Prim
            PrimMST prim = new PrimMST(graphData);
            prim.run();
            double primCost = prim.getTotalWeight();
            long primOps = prim.getOperationCount();
            long primTime = prim.getExecutionTime();

            // Run Kruskal
            KruskalMST kruskal = new KruskalMST(graphData);
            kruskal.run();
            double kruskalCost = kruskal.getTotalWeight();
            long kruskalOps = kruskal.getOperationCount();
            long kruskalTime = kruskal.getExecutionTime();

            // Check correctness
            boolean sameCost = (Math.abs(primCost - kruskalCost) < 1e-6);

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("graph_id", graphData.getId());
            result.put("vertices", graphData.getNodeCount());
            result.put("edges", graphData.getEdges().size());
            result.put("prim_cost", primCost);
            result.put("kruskal_cost", kruskalCost);
            result.put("same_cost", sameCost);
            result.put("prim_ops", primOps);
            result.put("kruskal_ops", kruskalOps);
            result.put("prim_time_ms", primTime);
            result.put("kruskal_time_ms", kruskalTime);

            summary.add(result);
        }

        // Write summary.json
        JsonUtils.writeGraphsToJson(datasets, "assign_3_input.json");
        writeSummaryCsv(summary, "summary.csv");

        System.out.println("\n✅ All done! Results saved to output files.");
    }

    private static void writeSummaryCsv(List<Map<String, Object>> summary, String filename) {
        try (FileWriter csv = new FileWriter(filename)) {
            csv.write("Graph,Vertices,Edges,PrimCost,KruskalCost,SameCost,PrimOps,KruskalOps,PrimTime(ms),KruskalTime(ms)\n");
            for (Map<String, Object> s : summary) {
                csv.write(String.format("%s,%s,%s,%.2f,%.2f,%s,%s,%s,%s,%s\n",
                        s.get("graph_id"), s.get("vertices"), s.get("edges"),
                        s.get("prim_cost"), s.get("kruskal_cost"),
                        s.get("same_cost"), s.get("prim_ops"),
                        s.get("kruskal_ops"), s.get("prim_time_ms"),
                        s.get("kruskal_time_ms")));
            }
            System.out.println("✅ Saved " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
