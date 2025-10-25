package GraphIOModels;

import java.util.*;

public class Graph {
    private List<String> nodes;
    private List<GraphIOModels.Edge> edges;

    public Graph(List<String> nodes) {
        this.nodes = new ArrayList<>(nodes);
        this.edges = new ArrayList<>();
    }

    public void addEdge(String from, String to, int weight) {
        edges.add(new GraphIOModels.Edge(from, to, weight));
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<GraphIOModels.Edge> getEdges() {
        return edges;
    }

    public int getVerticesCount() {
        return nodes.size();
    }

    public int getEdgesCount() {
        return edges.size();
    }

    @Override
    public String toString() {
        return "GraphIOModels.Graph{nodes=" + nodes + ", edges=" + edges + "}";
    }
}
