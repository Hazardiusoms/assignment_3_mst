// tests/MSTTest.java
package tests;

import org.junit.jupiter.api.*;
import GraphIOModels.*;
import utils.*;
import algorithms.*;

import static org.junit.jupiter.api.Assertions.*;

public class MSTTest {

    @Test
    public void correctnessPrimKruskalEqualCostSmall() {
        GraphData g = GraphGenerator.generateRandomGraph(30);
        PrimMST p = new PrimMST(g); p.run();
        KruskalMST k = new KruskalMST(g); k.run();
        assertEquals(p.getTotalCost(), k.getTotalCost(), "Prim and Kruskal costs must match");
        assertEquals(g.numVertices - 1, p.getMstEdges().size());
        assertEquals(g.numVertices - 1, k.getMstEdges().size());
        assertTrue(GraphUtils.isAcyclic(g.numVertices, p.getMstEdges()));
        assertTrue(GraphUtils.isAcyclic(g.numVertices, k.getMstEdges()));
        assertTrue(GraphUtils.connectsAllVertices(g.numVertices, p.getMstEdges()));
    }

    @Test
    public void disconnectedHandledGracefully() {
        // Create a disconnected graph manually
        GraphData g = new GraphData(5);
        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        // nodes 3 and 4 disconnected between components
        PrimMST p = new PrimMST(g); p.run();
        KruskalMST k = new KruskalMST(g); k.run();
        assertTrue(p.getMstEdges().size() < g.numVertices - 1 || k.getMstEdges().size() < g.numVertices - 1);
    }

    @Test
    public void performanceAndConsistency() {
        GraphData g = GraphGenerator.generateRandomGraph(300);
        PrimMST p1 = new PrimMST(g); p1.run();
        PrimMST p2 = new PrimMST(g); p2.run();
        assertTrue(p1.getExecutionTimeMs() >= 0);
        assertTrue(p1.getOperationsCount() >= 0);
        assertEquals(p1.getTotalCost(), p2.getTotalCost());
    }
}
