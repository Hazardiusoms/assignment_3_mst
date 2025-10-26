package algorithms;

import GraphIOModels.Edge;
import GraphIOModels.GraphData;
import java.util.*;

public class KruskalMST {
    private GraphData graph;
    private List<Edge> mstEdges;
    private double totalWeight;
    private long operationCount;
    private long executionTime;

    public KruskalMST(GraphData graph) {
        this.graph = graph;
        this.mstEdges = new ArrayList<>();
        this.totalWeight = 0;
        this.operationCount = 0;
    }

    public void run() {
        long start = System.currentTimeMillis();

        int V = graph.getNodeCount();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getWeight));

        // Create subsets
        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset(v, 0);
        }

        int e = 0;
        int i = 0;
        while (e < V - 1 && i < edges.size()) {
            operationCount++;
            Edge next = edges.get(i++);
            int x = find(subsets, next.src);
            int y = find(subsets, next.dest);

            if (x != y) {
                mstEdges.add(next);
                totalWeight += next.weight;
                union(subsets, x, y);
                e++;
            }
        }

        long end = System.currentTimeMillis();
        executionTime = end - start;
    }

    private int find(Subset[] subsets, int i) {
        operationCount++;
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    private void union(Subset[] subsets, int x, int y) {
        operationCount++;
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
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

    static class Subset {
        int parent, rank;

        Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
}
