package org.c0e.sglib;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BuilderTest {

    @Test
    public void builderTest() throws Exception {
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";
        String f = "f";

        Graph graph = Builder.create(Builder.Type.DIRECTED)
                .addVertex(a)
                .addVertex(b)
                .addVertex(c)
                .addVertex(d)
                .addVertex(e)
                .addVertex(f)
                .addEdge(a, b)
                .addEdge(a, c)
                .addEdge(b, c)
                .addEdge(b, d)
                .addEdge(c, e)
                .addEdge(e, d)
                .addEdge(d, f)
                .build();

        List<String> path = graph.getPath(a, f);

        assertNotNull(path);
        assertEquals("abdf", StringUtils.join(path, ""));
    }
}