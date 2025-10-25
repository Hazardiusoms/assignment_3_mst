import java.util.*;

public class KruskalMST {

    static class Subset {
        int parent, rank;
    }

    private int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    private void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    public List<Edge> kruskalMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);

        int V = graph.getVertices();
        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        int e = 0, i = 0;
        while (e < V - 1 && i < edges.size()) {
            Edge next = edges.get(i++);
            int x = find(subsets, next.src);
            int y = find(subsets, next.dest);

            if (x != y) {
                result.add(next);
                union(subsets, x, y);
                e++;
            }
        }
        return result;
    }
}
