package org.c0e.sglib;

import java.util.List;

public class SynchronizedGraph<T> implements Graph<T> {

    Graph graph;

    private SynchronizedGraph() {
    }

    public SynchronizedGraph(Graph<T> graph) {
        this.graph = graph;
    }

    synchronized public void addVertex(T vertex) throws GraphException {
        graph.addVertex(vertex);
    }

    synchronized public void addEdge(T begin, T end) throws GraphException {
        graph.addEdge(begin, end);
    }

    synchronized public List getPath(T from, T to) throws GraphException {
        return graph.getPath(from, to);
    }
}
