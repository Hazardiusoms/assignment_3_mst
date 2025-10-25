import java.util.*;

public class PrimMST {

    static class Node implements Comparable<Node> {
        int vertex, weight;
        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public List<Edge> primMST(Graph graph) {
        int V = graph.getVertices();
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        for (Edge e : graph.getEdges()) {
            adj.get(e.src).add(e);
            adj.get(e.dest).add(new Edge(e.dest, e.src, e.weight));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        key[0] = 0;
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;
            inMST[u] = true;

            for (Edge e : adj.get(u)) {
                int v = e.dest;
                if (!inMST[v] && e.weight < key[v]) {
                    key[v] = e.weight;
                    pq.add(new Node(v, key[v]));
                    parent[v] = u;
                }
            }
        }

        List<Edge> mst = new ArrayList<>();
        for (int i = 1; i < V; i++) {
            if (parent[i] != -1)
                mst.add(new Edge(parent[i], i, key[i]));
        }
        return mst;
    }
}