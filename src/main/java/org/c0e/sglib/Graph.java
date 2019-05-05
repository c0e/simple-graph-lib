package org.c0e.sglib;

import java.util.List;

public interface Graph<T> {
    void addVertex(T vertex) throws GraphException;
    void addEdge(T begin, T end) throws GraphException;
    List getPath(T from, T to) throws GraphException;
}
