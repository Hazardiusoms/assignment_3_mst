package algorithms;

import GraphIOModels.Edge;
import GraphIOModels.GraphData;

import java.util.*;

public class PrimMST {

    private final GraphData graphData;
    private final List<Edge> mstEdges = new ArrayList<>();
    private int totalCost = 0;
    private long operationCount = 0;
    private double executionTimeMs = 0.0;

    public PrimMST(GraphData graphData) {
        this.graphData = graphData;
    }

    public void run() {
        long startTime = System.nanoTime();

        List<String> nodes = graphData.getNodes();
        Map<String, List<Edge>> adj = buildAdjacencyList(graphData.getEdges());

        if (nodes.isEmpty()) return;

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        String startNode = nodes.get(0);
        visited.add(startNode);

        // Add all edges from the starting node
        for (Edge e : adj.getOrDefault(startNode, new ArrayList<>())) {
            pq.offer(e);
            operationCount++;
        }

        while (!pq.isEmpty() && visited.size() < nodes.size()) {
            Edge edge = pq.poll();
            operationCount++;

            String nextNode = !visited.contains(edge.getFrom()) ? edge.getFrom() : edge.getTo();

            if (visited.contains(nextNode)) continue;

            visited.add(nextNode);
            mstEdges.add(edge);
            totalCost += edge.getWeight();

            for (Edge e : adj.getOrDefault(nextNode, new ArrayList<>())) {
                String other = e.getFrom().equals(nextNode) ? e.getTo() : e.getFrom();
                if (!visited.contains(other)) {
                    pq.offer(e);
                    operationCount++;
                }
            }
        }

        long endTime = System.nanoTime();
        executionTimeMs = (endTime - startTime) / 1_000_000.0;
    }

    private Map<String, List<Edge>> buildAdjacencyList(List<Edge> edges) {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (Edge e : edges) {
            adj.computeIfAbsent(e.getFrom(), k -> new ArrayList<>()).add(e);
            adj.computeIfAbsent(e.getTo(), k -> new ArrayList<>()).add(e);
        }
        return adj;
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
