package org.c0e.sglib;

import java.util.*;

public class DirectedGraph<T> extends AbstractGraph<T> {

    public void addEdge(T begin, T end) throws GraphException {
        Integer weight = 1;

        if (!vertexes.contains(begin) && begin != null) {
            throw new GraphException("Begin vertex does not exist");
        }

        if (!vertexes.contains(end) && end != null) {
            throw new GraphException("End vertex does not exist");
        }

        Integer beginIndex = indexes.get(begin);
        Integer endIndex = indexes.get(end);

        matrix.get(beginIndex).set(endIndex, weight);
    }

    public List getPath(T from, T to) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (T vertex : vertexes) {
            builder.append(vertex.toString());
        }
        return builder.toString();
    }
}
