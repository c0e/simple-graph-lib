package org.c0e.sglib;

public class DirectedGraph<T> extends AbstractGraph<T> {
    public void addEdge(T begin, T end) throws GraphException {
        Integer weight = 1;

        if (!vertexes.contains(begin) && begin != null) {
            throw new GraphException("Begin vertex does not exist");
        }

        if (!vertexes.contains(end) && end != null) {
            throw new GraphException("End vertex does not exist");
        }

        Integer beginIndex = vertexesToIndexes.get(begin);
        Integer endIndex = vertexesToIndexes.get(end);

        adjacencyMatrix.get(beginIndex).set(endIndex, weight);
    }
}
