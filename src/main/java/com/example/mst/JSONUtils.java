package com.example.mst;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    static class InputRoot {
        List<GraphSpec> graphs;
    }
    static class GraphSpec {
        int id;
        String[] nodes;
        List<EdgeSpec> edges;
    }
    static class EdgeSpec {
        String from;
        String to;
        double weight;
    }

    public static List<GraphWithId> readInput(String filename) throws IOException {
        Reader r = new FileReader(filename);
        InputRoot root = GSON.fromJson(r, InputRoot.class);
        r.close();
        List<GraphWithId> list = new ArrayList<>();
        if (root == null || root.graphs == null) return list;
        for (GraphSpec gs : root.graphs) {
            Graph graph = new Graph(gs.nodes);
            for (EdgeSpec es : gs.edges) graph.addEdgeByNames(es.from, es.to, es.weight);
            list.add(new GraphWithId(gs.id, graph));
        }
        return list;
    }


    public static class GraphWithId {
        public final int id;
        public final Graph graph;
        public GraphWithId(int id, Graph graph) { this.id = id; this.graph = graph; }
    }


    static class OutputRoot {
        List<ResultItem> results = new ArrayList<>();
    }
    static class ResultItem {
        @SerializedName("graph_id")
        int graph_id;
        @SerializedName("input_stats")
        InputStats input_stats;
        MSTBlock prim;
        MSTBlock kruskal;
    }
    static class InputStats { int vertices; int edges; }
    static class MSTBlock {
        @SerializedName("mst_edges")
        List<EdgeOut> mst_edges;
        @SerializedName("total_cost")
        double total_cost;
        @SerializedName("operations_count")
        long operations_count;
        @SerializedName("execution_time_ms")
        double execution_time_ms;
    }
    static class EdgeOut {
        String from;
        String to;
        double weight;
        EdgeOut(String f, String t, double w){ from=f; to=t; weight=w; }
    }

    public static void writeOutput(String filename, List<ResultItem> results) throws IOException {
        OutputRoot root = new OutputRoot();
        root.results.addAll(results);
        Writer w = new FileWriter(filename);
        GSON.toJson(root, w);
        w.close();
    }

    public static EdgeOut toEdgeOut(Edge e, Graph g) {
        String a = g.indexToName[e.u];
        String b = g.indexToName[e.v];
        return new EdgeOut(a, b, e.weight);
    }
}
