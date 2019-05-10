package org.c0e.sglib;

public class GraphBuilder<T> {

    public enum Type {
        DIRECTED, UNDIRECTED, SYNC_DIRECTED, SYNC_UNDIRECTED
    }

    private Graph graph;

    public static <T> GraphBuilder create(Type type) throws GraphException {
        GraphBuilder<T> builder = new GraphBuilder<T>();
        switch (type) {
            case UNDIRECTED:
                builder.graph = new UnDirectedGraph<T>();
                return builder;
            case DIRECTED:
                builder.graph = new DirectedGraph<T>();
                return builder;
            case SYNC_DIRECTED:
                builder.graph = new SynchronizedGraph(new DirectedGraph<T>());
                return builder;
            case SYNC_UNDIRECTED:
                builder.graph = new SynchronizedGraph(new UnDirectedGraph<T>());
                return builder;
        }
        throw new GraphException("Graph type " + type.name() + " isn't implemented");
    }

    public GraphBuilder addVertex(T vertex) throws GraphException {
        graph.addVertex(vertex);
        return this;
    }

    public GraphBuilder addEdge(T begin, T end) throws GraphException {
        graph.addEdge(begin, end);
        return this;
    }

    public Graph build() {
        return graph;
    }

    @Override
    public String toString() {
        return "Graph lib builder";
    }
}
