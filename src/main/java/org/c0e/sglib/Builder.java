package org.c0e.sglib;

public class Builder<T> {
    public enum Type {
        DIRECTED, UNDIRECTED
    }

    private Graph graph;

    public static <T> Builder create(Type type) throws GraphException {
        Builder<T> builder = new Builder<T>();

        if (type == Type.DIRECTED) {
            builder.graph = new DirectedGraph<T>();
            return builder;
        } else if (type == Type.UNDIRECTED) {
            builder.graph = new UnDirectedGraph<T>();
            return builder;
        }
        throw new GraphException("Graph type " + type.name() + " isn't implemented");

    }

    public Builder addVertex(T vertex) throws GraphException {
        graph.addVertex(vertex);
        return this;
    }

    public Builder addEdge(T begin, T end) throws GraphException {
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