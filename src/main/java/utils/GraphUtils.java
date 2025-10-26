// utils/GraphUtils.java
package utils;

import GraphIOModels.Edge;
import java.util.*;

public class GraphUtils {
    public static boolean isAcyclic(int n, List<Edge> edges) {
        // Use Union-Find to detect cycle: if union finds same set -> cycle
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (Edge e : edges) {
            int x = find(parent, e.src);
            int y = find(parent, e.dest);
            if (x == y) return false; // cycle found
            parent[x] = y;
        }
        return true;
    }
    private static int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    public static boolean connectsAllVertices(int n, List<Edge> edges) {
        // Build adjacency and BFS from 0
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i=0;i<n;i++) adj.add(new ArrayList<>());
        for (Edge e: edges) {
            adj.get(e.src).add(e.dest);
            adj.get(e.dest).add(e.src);
        }
        boolean[] seen = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0); seen[0]=true;
        int count=1;
        while (!q.isEmpty()) {
            int u=q.poll();
            for (int v: adj.get(u)) if (!seen[v]) { seen[v]=true; q.add(v); count++; }
        }
        return count==n;
    }
}
