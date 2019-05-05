package org.c0e.sglib;

import java.util.*;

abstract class AbstractGraph<T> implements Graph<T> {
    Set<T> vertexes = new HashSet<T>();

    Map<T, Integer> vertexesToIndexes = new HashMap<T, Integer>();
    Map<Integer, T> indexesToVertexes = new HashMap<Integer, T>();

    ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<ArrayList<Integer>>();

    public void addVertex(T vertex) throws GraphException {
        if (vertexes.add(vertex)) {
            int size = vertexesToIndexes.size();

            ArrayList<Integer> emptyVertexData = new ArrayList<Integer>();
            emptyVertexData.addAll(Collections.nCopies(size, 0));
            adjacencyMatrix.add(emptyVertexData);

            vertexesToIndexes.put(vertex, size);
            indexesToVertexes.put(size, vertex);

            for (ArrayList<Integer> integers : adjacencyMatrix) {
                integers.add(0);
            }
        } else {
            throw new GraphException("Vertex already exist");
        }
    }

    public void addEdge(T begin, T end) throws GraphException {
        throw new GraphException("Method addEdge isn't implemented");
    }

    public List getPath(T from, T to) throws GraphException {
        Set<T> path = new LinkedHashSet<>();
        path.add(from);

        T closest;

        while ((closest = getClosestElement(from, path)) != null) {
            path.add(closest);
            from = closest;
            if (closest.equals(to)) {
                return Arrays.asList(path.toArray());
            }
        }

        return Collections.EMPTY_LIST;
    }

    private T getClosestElement(T from, Set<T> path) {
        ArrayList<Integer> adjList = adjacencyMatrix.get(vertexesToIndexes.get(from));

        Integer minIndex = null;
        Integer minValue = null;

        for (int index = 0; index < adjList.size(); index++) {
            Integer value = adjList.get(index);
            T element = indexesToVertexes.get(index);
            if (!path.contains(element)
                    && !element.equals(from)
                    && value != 0 && (minValue == null || value < minValue)) {
                minIndex = index;
                minValue = value;
            }
        }

        return indexesToVertexes.get(minIndex);
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
