package GraphIOModels;

import java.util.ArrayList;
import java.util.List;

public class GraphData {
    private int id;
    private List<String> nodes; // original node labels (A, B, C, etc.)
    private List<Edge> edges;   // list of all edges
    private List<List<Edge>> adjacencyList; // adjacency list for Prim's algorithm

    public GraphData(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
        buildAdjacencyList();
    }

    // Build adjacency list based on edges
    private void buildAdjacencyList() {
        int V = nodes.size();
        adjacencyList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Add edges to adjacency list (undirected)
        for (Edge e : edges) {
            if (e.getSrc() < V && e.getDest() < V) {
                adjacencyList.get(e.getSrc()).add(new Edge(e.getSrc(), e.getDest(), e.getWeight()));
                adjacencyList.get(e.getDest()).add(new Edge(e.getDest(), e.getSrc(), e.getWeight()));
            }
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    @Override
    public String toString() {
        return "GraphData{" +
                "id=" + id +
                ", nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
