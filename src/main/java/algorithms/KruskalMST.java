package algorithms;

import GraphIOModels.Edge;
import GraphIOModels.GraphData;

import java.util.*;

public class KruskalMST {

    private final GraphData graphData;
    private final List<Edge> mstEdges = new ArrayList<>();
    private int totalCost = 0;
    private long operationCount = 0;
    private double executionTimeMs = 0.0;

    public KruskalMST(GraphData graphData) {
        this.graphData = graphData;
    }

    // Disjoint Set (Union-Find) helper class
    private static class Subset {
        String parent;
        int rank;
    }

    public void run() {
        long startTime = System.nanoTime();

        // Sort edges by weight
        List<Edge> edges = new ArrayList<>(graphData.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getWeight));
        operationCount += edges.size();

        // Initialize disjoint sets for each vertex
        Map<String, Subset> subsets = new HashMap<>();
        for (String node : graphData.getNodes()) {
            Subset subset = new Subset();
            subset.parent = node;
            subset.rank = 0;
            subsets.put(node, subset);
        }

        int edgeCount = 0;
        int vertexCount = graphData.getNodes().size();
        int i = 0;

        while (edgeCount < vertexCount - 1 && i < edges.size()) {
            Edge nextEdge = edges.get(i++);
            operationCount++;

            String root1 = find(subsets, nextEdge.getFrom());
            String root2 = find(subsets, nextEdge.getTo());

            if (!root1.equals(root2)) {
                mstEdges.add(nextEdge);
                totalCost += nextEdge.getWeight();
                union(subsets, root1, root2);
                edgeCount++;
            }
        }

        long endTime = System.nanoTime();
        executionTimeMs = (endTime - startTime) / 1_000_000.0;
    }

    // Union-Find helpers
    private String find(Map<String, Subset> subsets, String node) {
        operationCount++;
        if (!subsets.get(node).parent.equals(node)) {
            subsets.get(node).parent = find(subsets, subsets.get(node).parent);
        }
        return subsets.get(node).parent;
    }

    private void union(Map<String, Subset> subsets, String root1, String root2) {
        operationCount++;
        Subset s1 = subsets.get(root1);
        Subset s2 = subsets.get(root2);

        if (s1.rank < s2.rank) {
            s1.parent = root2;
        } else if (s1.rank > s2.rank) {
            s2.parent = root1;
        } else {
            s2.parent = root1;
            s1.rank++;
        }
    }

    // ✅ Getters for Main.java
    public List<Edge> getMstEdges() {
        return mstEdges;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public long getOperationCount() {
        return operationCount;
    }

    public double getExecutionTimeMs() {
        return executionTimeMs;
    }
}
