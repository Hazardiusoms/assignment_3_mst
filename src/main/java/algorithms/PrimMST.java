package algorithms;

import GraphIOModels.Edge;
import GraphIOModels.GraphData;
import java.util.*;

public class PrimMST {
    private GraphData graph;
    private List<Edge> mstEdges;
    private double totalWeight;
    private long operationCount;
    private long executionTime;

    public PrimMST(GraphData graph) {
        this.graph = graph;
        this.mstEdges = new ArrayList<>();
        this.totalWeight = 0;
        this.operationCount = 0;
    }

    public void run() {
        long start = System.currentTimeMillis();

        int V = graph.getNodeCount();
        List<List<Edge>> adj = graph.getAdjacencyList();

        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        visited[0] = true;
        for (Edge e : adj.get(0)) {
            pq.add(e);
        }

        while (!pq.isEmpty() && mstEdges.size() < V - 1) {
            operationCount++;
            Edge edge = pq.poll();

            if (visited[edge.getDest()]) continue;

            mstEdges.add(edge);
            totalWeight += edge.getWeight();
            visited[edge.getDest()] = true;

            for (Edge next : adj.get(edge.getDest())) {
                if (!visited[next.getDest()]) {
                    pq.add(next);
                }
            }
        }

        long end = System.currentTimeMillis();
        executionTime = end - start;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public List<Edge> getMstEdges() {
        return mstEdges;
    }

    public long getOperationCount() {
        return operationCount;
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
