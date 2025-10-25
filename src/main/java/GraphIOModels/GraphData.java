package GraphIOModels;

import java.util.List;

public class GraphData {
    private int id;
    private List<String> nodes;
    private List<Edge> edges;

    // Constructor
    public GraphData(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
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

    // Optional setters (useful if you modify graphs dynamically)
    public void setId(int id) {
        this.id = id;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
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
