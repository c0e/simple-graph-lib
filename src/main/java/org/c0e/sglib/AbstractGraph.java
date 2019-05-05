package org.c0e.sglib;

import java.util.*;

abstract class AbstractGraph<T> implements Graph<T>{
    Set<T> vertexes = new HashSet<T>();
    Map<T, Integer> indexes = new HashMap<T, Integer>();
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

    public void addVertex(T vertex) throws GraphException {
        if(vertexes.add(vertex)){
            int size = indexes.size();

            ArrayList<Integer> emptyVertexData = new ArrayList<Integer>();
            emptyVertexData.addAll(Collections.nCopies(size, 0));
            matrix.add(emptyVertexData);

            indexes.put(vertex, size);

            for (ArrayList<Integer> integers : matrix) {
                integers.add(0);
            }
        }else{
            throw new GraphException("Vertex already exist");
        }
    }

    public void addEdge(T begin, T end) throws GraphException {
        throw new GraphException("Method addEdge isn't implemented");
    }

    public List getPath(T from, T to) throws GraphException {
        throw new GraphException("Method getPath isn't implemented");
    }
}
