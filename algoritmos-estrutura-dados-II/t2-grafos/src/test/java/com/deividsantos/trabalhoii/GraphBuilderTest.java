package com.deividsantos.trabalhoii;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphBuilderTest {

    @Test
    public void buildGraphWithCastlesTest() {
        List<String> castleGraphLines = Arrays.asList(("10 4 6\n" +
                "1 2\n" +
                "2 5\n" +
                "3 4\n" +
                "4 9\n" +
                "0 1\n" +
                "0 2\n" +
                "0 3\n" +
                "1 2\n" +
                "2 3\n" +
                "3 4").split("\n"));
        assertEquals("graph {\n" +
                "rankdir = LR;\n" +
                "node [shape = circle];\n" +
                "0 -- 3;\n" +
                "0 -- 2;\n" +
                "0 -- 1;\n" +
                "1 -- 2;\n" +
                "2 -- 3;\n" +
                "3 -- 4;\n" +
                "}", GraphBuilder.createGraph(castleGraphLines, 4).toDot());
    }
}