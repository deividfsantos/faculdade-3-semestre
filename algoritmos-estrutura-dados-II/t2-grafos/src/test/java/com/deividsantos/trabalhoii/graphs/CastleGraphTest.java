package com.deividsantos.trabalhoii.graphs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CastleGraphTest {

    @Test
    public void createCastleGraphTest() {
        Graph g = new Graph(8 + 1);
        g.addEdge(5, 8);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(0, 8);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 4);
        g.addEdge(1, 7);
        g.addEdge(1, 8);
        g.addEdge(1, 3);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        g.addEdge(2, 7);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(3, 6);
        g.addEdge(3, 7);
        g.addEdge(3, 8);
        assertEquals("graph {\n" +
                "rankdir = LR;\n" +
                "node [shape = circle];\n" +
                "0 -- 3;\n" +
                "0 -- 2;\n" +
                "0 -- 1;\n" +
                "0 -- 8;\n" +
                "0 -- 6;\n" +
                "0 -- 5;\n" +
                "1 -- 3;\n" +
                "1 -- 8;\n" +
                "1 -- 7;\n" +
                "1 -- 4;\n" +
                "2 -- 3;\n" +
                "2 -- 7;\n" +
                "2 -- 6;\n" +
                "2 -- 5;\n" +
                "3 -- 8;\n" +
                "3 -- 7;\n" +
                "3 -- 6;\n" +
                "3 -- 5;\n" +
                "3 -- 4;\n" +
                "5 -- 8;\n" +
                "}", g.toDot());
    }
}