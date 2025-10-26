package com.example.mst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalMST_Simple {

    public static MSTResult run(Graph g, Metrics metrics) {
        long t0 = System.nanoTime();


        List<Edge> edges = new ArrayList<>(g.edges);


        Comparator<Edge> cmp = new Comparator<Edge>() {
            public int compare(Edge a, Edge b) {
                metrics.comparisons++;
                return Double.compare(a.weight, b.weight);
            }
        };
        Collections.sort(edges, cmp);

        UF uf = new UF(g.V);
        List<Edge> mst = new ArrayList<>();
        double total = 0.0;

        for (Edge e : edges) {
            metrics.edgeChecks++;
            int u = e.u;
            int v = e.v;
            int fu = uf.find(u); metrics.finds++;
            int fv = uf.find(v); metrics.finds++;
            if (fu != fv) {
                uf.union(fu, fv); metrics.unions++;
                mst.add(e);
                total += e.weight;
                if (mst.size() == g.V - 1) break;
            }
        }

        metrics.timeMs = (System.nanoTime() - t0) / 1_000_000;
        MSTResult res = new MSTResult();
        res.mstEdges = mst;
        res.totalCost = total;
        res.metrics = metrics;
        return res;
    }
}
