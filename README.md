#Simple Graph Lib

##Description

This tiny library supports both directed and undirected graphs with ability 
to use synchronized realizations of them.

Vertexes of the graph have user defined type.

##Usage

You should use GraphBuilder class for building you graph data.

    String a = "a";
    String b = "b";
    String c = "c";
    String d = "d";
    
    Graph graph = GraphBuilder.create(Builder.Type.DIRECTED)
        .addVertex(a)
        .addVertex(b)
        .addVertex(c)
        .addEdge(a, b)
        .addEdge(b, c)
        .addEdge(c, d)
        .build();
    
    List<String> path = graph.getPath(a, f);


**addVertex** - adds vertex to the graph

**addEdge** - adds edge to the graph

**build** - builds specific graph implementation.

**getPath** - returns a list of edges between 2 vertices

