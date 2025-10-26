import GraphIOModels.GraphData;
import algorithms.KruskalMST;
import algorithms.PrimMST;
import utils.GraphGenerator;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Generating graphs...");
            List<GraphData> graphs = GraphGenerator.generateGraphs(5, 5, 10, 20);
            System.out.println("Generated " + graphs.size() + " graphs.\n");

            for (GraphData graph : graphs) {
                System.out.println("Processing Graph ID: " + graph.getId() +
                        " | Nodes: " + graph.getNodeCount() +
                        " | Edges: " + graph.getEdgeCount());

                // --- Run Prim ---
                PrimMST prim = new PrimMST(graph);
                prim.run();
                System.out.println("Prim total weight: " + prim.getTotalWeight() +
                        " | Operations: " + prim.getOperationCount() +
                        " | Time: " + prim.getExecutionTime() + " ms");

                // --- Run Kruskal ---
                KruskalMST kruskal = new KruskalMST(graph);
                kruskal.run();
                System.out.println("Kruskal total weight: " + kruskal.getTotalWeight() +
                        " | Operations: " + kruskal.getOperationCount() +
                        " | Time: " + kruskal.getExecutionTime() + " ms");

                System.out.println("------------------------------");
            }

            System.out.println("\nAll graphs processed successfully!");
        } catch (IOException e) {
            System.err.println("Error generating graphs: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
