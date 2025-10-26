package com.example.mst;

import java.util.ArrayList;
import java.util.List;

public class PrimMST {


    public static MSTResult run(Graph g, Metrics metrics) {
        long t0 = System.nanoTime();

        int V = g.V;
        boolean[] used = new boolean[V];
        double[] minEdge = new double[V];
        int[] selEdgeFrom = new int[V];
        for (int i = 0; i < V; i++) {
            minEdge[i] = Double.POSITIVE_INFINITY;
            selEdgeFrom[i] = -1;
        }

        List<Edge> mstEdges = new ArrayList<>();

        minEdge[0] = 0.0;

        for (int it = 0; it < V; it++) {

            int v = -1;
            double val = Double.POSITIVE_INFINITY;
            for (int i = 0; i < V; i++) {
                metrics.comparisons++;
                if (!used[i] && minEdge[i] < val) {
                    val = minEdge[i];
                    v = i;
                }
            }
            if (v == -1) break;
            used[v] = true;

            if (selEdgeFrom[v] != -1) {
                mstEdges.add(new Edge(selEdgeFrom[v], v, minEdge[v]));
            }

            for (Edge e : g.edges) {
                metrics.edgeChecks++;
                int a = e.u;
                int b = e.v;
                double w = e.weight;
                if (a == v && !used[b]) {
                    metrics.comparisons++;
                    if (w < minEdge[b]) {
                        minEdge[b] = w;
                        selEdgeFrom[b] = a;
                    }
                } else if (b == v && !used[a]) {
                    metrics.comparisons++;
                    if (w < minEdge[a]) {
                        minEdge[a] = w;
                        selEdgeFrom[a] = b;
                    }
                }
            }
        }

        double total = 0;
        for (Edge e : mstEdges) total += e.weight;
        metrics.timeMs = (System.nanoTime() - t0) / 1_000_000;
        MSTResult res = new MSTResult();
        res.mstEdges = mstEdges;
        res.totalCost = total;
        res.metrics = metrics;
        return res;
    }
}
