package GraphIOModels;

import java.util.List;

public class GraphData {
    private String id;
    private int nodeCount;
    private List<String> nodes;
    private List<Edge> edges;

    public GraphData(String id, int nodeCount, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodeCount = nodeCount;
        this.nodes = nodes;
        this.edges = edges;
    }

    public String getId() { return id; }
    public int getNodeCount() { return nodeCount; }
    public List<String> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }
}

