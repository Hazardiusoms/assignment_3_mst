package tests;

import algorithms.KruskalMST;
import algorithms.PrimMST;
import GraphIOModels.Edge;
import GraphIOModels.GraphData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MSTAlgorithmsTest {

    @Test
    public void testSmallGraphMST() {
        GraphData g = new GraphData("test_graph", 4, Arrays.asList(
                new Edge(0, 1, 10),
                new Edge(0, 2, 6),
                new Edge(0, 3, 5),
                new Edge(1, 3, 15),
                new Edge(2, 3, 4)
        ));

        PrimMST prim = new PrimMST(g);
        KruskalMST kruskal = new KruskalMST(g);
        prim.run();
        kruskal.run();

        assertEquals(15.0, prim.getTotalWeight(), 0.001);
        assertEquals(15.0, kruskal.getTotalWeight(), 0.001);
        assertEquals(3, prim.getMSTEdges().size());
        assertEquals(3, kruskal.getMSTEdges().size());
    }

    @Test
    public void testPerformanceTracking() {
        GraphData g = new GraphData("perf_graph", 6, Arrays.asList(
                new Edge(0, 1, 4),
                new Edge(0, 2, 3),
                new Edge(1, 2, 1),
                new Edge(1, 3, 2),
                new Edge(2, 3, 4),
                new Edge(3, 4, 2),
                new Edge(4, 5, 6)
        ));

        PrimMST prim = new PrimMST(g);
        prim.run();

        assertTrue(prim.getExecutionTime() >= 0);
        assertTrue(prim.getOperationCount() > 0);
        assertEquals(5, prim.getMSTEdges().size());
    }
}
