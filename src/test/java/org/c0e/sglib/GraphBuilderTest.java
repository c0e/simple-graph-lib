package org.c0e.sglib;

import com.sun.deploy.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GraphBuilderTest {
    @Test
    public void pathCalculationTest() throws GraphException {
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";
        String f = "f";

        Graph graph = GraphBuilder.create(GraphBuilder.Type.DIRECTED)
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

        path = graph.getPath(f, a);

        Assert.assertTrue(path.isEmpty());
    }

    @Test
    public void directionTest() throws GraphException {
        String a = "a";
        String b = "b";

        Graph graph = GraphBuilder.create(GraphBuilder.Type.DIRECTED)
                .addVertex(a)
                .addVertex(b)
                .addEdge(a, b)
                .build();

        Assert.assertEquals(graph.getPath(a, b).size(), 2);
        Assert.assertEquals(graph.getPath(b, a).size(), 0);

        graph = GraphBuilder.create(GraphBuilder.Type.UNDIRECTED)
                .addVertex(a)
                .addVertex(b)
                .addEdge(a, b)
                .build();

        Assert.assertEquals(graph.getPath(a, b).size(), 2);
        Assert.assertEquals(graph.getPath(b, a).size(), 2);
    }

    @Test
    public void wrongPathTest() throws GraphException {
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";

        Graph graph = GraphBuilder.<String>create(GraphBuilder.Type.DIRECTED)
                .addVertex(a)
                .addVertex(b)
                .addVertex(c)
                .addVertex(d)
                .addEdge(a, b)
                .addEdge(d, c)
                .build();
        Assert.assertTrue(graph.getPath(a, c).isEmpty());
    }

    @Test(expected = GraphException.class)
    public void doubleVertexTest() throws GraphException {
        String a = "a";
        String b = "b";
        Graph build = GraphBuilder.<String>create(GraphBuilder.Type.DIRECTED)
                .addVertex(a)
                .addVertex(b)
                .addVertex(a)
                .addEdge(a, b)
                .build();
        build.getPath(a, b);
    }
}