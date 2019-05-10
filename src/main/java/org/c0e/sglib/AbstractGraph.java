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

        LinkedHashSet<Integer> visitedVertexes = new LinkedHashSet<>();
        Integer[] costs = new Integer[vertexes.size()];
        Integer[] raletives = new Integer[vertexes.size()];

        costs[vertexesToIndexes.get(from)] = 0;

        Integer index;
        while ((index = nextElement(costs, visitedVertexes)) != null) {
            updateClosest(costs, raletives, index, adjacencyMatrix.get(index));
        }

        return buildPath(raletives, vertexesToIndexes.get(from), vertexesToIndexes.get(to));
    }

    private Integer nextElement(Integer[] costs, Set<Integer> visitedVertexes) {
        Integer minValue = null;
        Integer minIndex = null;

        for (Integer elementIndex = 0; elementIndex < costs.length; elementIndex++) {
            Integer cost = costs[elementIndex];
            if (cost != null && !visitedVertexes.contains(elementIndex)
                    && (minValue == null || minValue > cost)) {
                minIndex = elementIndex;
                minValue = cost;
            }
        }

        if (minIndex != null) {
            visitedVertexes.add(minIndex);
        }

        return minIndex;
    }


    protected void updateClosest(Integer[] costs, Integer[] relatives, Integer beginElement, ArrayList<Integer> adjacencyList) {
        for (int endElement = 0; endElement < adjacencyList.size(); endElement++) {
            Integer weightOfTheAngle = adjacencyList.get(endElement);
            if (!weightOfTheAngle.equals(0)) {
                Integer weightOfPath = weightOfTheAngle + costs[beginElement];
                if (costs[endElement] == null || weightOfPath < costs[endElement]) {
                    costs[endElement] = weightOfPath;
                    relatives[endElement] = beginElement;
                }
            }
        }
    }

    protected List buildPath(Integer[] vertexes, Integer from, Integer to) {
        ArrayList<T> result = new ArrayList<>(vertexes.length);
        do {
            result.add(indexesToVertexes.get(to));
            Integer next = vertexes[to];
            to = next;
        } while (to != null);

        Collections.reverse(result);

        if (result.get(0).equals(indexesToVertexes.get(from))) {
            return result;
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
