package org.c0e.sglib;

import org.junit.Test;

public class BuilderTest {
    @Test
    public void builderTest() throws Exception {
        String v1 = "V1";
        String v2 = "V2";
        String v3 = "V3";
        String v4 = "V4";
        String v5 = "V5";

        Graph graph = Builder.create(Builder.Type.DIRECTED)
                .addVertex(v1)
                .addVertex(v2)
                .addVertex(v3)
                .addVertex(v4)
                .addVertex(v5)
                .addEdge(v1, v2)
                .addEdge(v2, v3)
                .addEdge(v4, v5)
                .addEdge(v5, v1)
                .build();

         System.out.print(graph.toString());
    }
}