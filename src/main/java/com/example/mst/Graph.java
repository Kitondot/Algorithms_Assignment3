package com.example.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Graph {
    public final int V;
    public final List<Edge> edges;
    public final Map<String, Integer> nameToIndex;
    public final String[] indexToName;

    public Graph(String[] nodeNames) {
        this.V = nodeNames.length;
        this.edges = new ArrayList<>();
        this.nameToIndex = new HashMap<>();
        this.indexToName = new String[V];
        for (int i = 0; i < V; i++) {
            nameToIndex.put(nodeNames[i], i);
            indexToName[i] = nodeNames[i];
        }
    }

    public void addEdgeByNames(String from, String to, double w) {
        Integer iu = nameToIndex.get(from);
        Integer iv = nameToIndex.get(to);
        if (iu == null || iv == null) throw new IllegalArgumentException("Unknown node name");
        addEdge(iu, iv, w);
    }

    public void addEdge(int u, int v, double w) {
        edges.add(new Edge(u, v, w));
    }
}
