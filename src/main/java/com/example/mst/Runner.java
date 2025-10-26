package com.example.mst;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws Exception {
        String inputFile = (args.length > 0) ? args[0] : "data/input.json"; // путь к твоему input
        String outputFile = (args.length > 1) ? args[1] : "data/output_generated.json";

        List<JSONUtils.GraphWithId> list = JSONUtils.readInput(inputFile);
        List<JSONUtils.ResultItem> outResults = new ArrayList<>();

        for (JSONUtils.GraphWithId gi : list) {
            Graph g = gi.graph;

            // Kruskal
            Metrics km = new Metrics();
            MSTResult kr = KruskalMST_Simple.run(g, km);

            // Prim
            Metrics pm = new Metrics();
            MSTResult pr = PrimMST.run(g, pm);

            // Заполняем ResultItem
            JSONUtils.ResultItem ri = new JSONUtils.ResultItem();
            ri.graph_id = gi.id;
            JSONUtils.InputStats stats = new JSONUtils.InputStats();
            stats.vertices = g.V;
            stats.edges = g.edges.size();
            ri.input_stats = stats;

            // prim block
            JSONUtils.MSTBlock primBlock = new JSONUtils.MSTBlock();
            primBlock.mst_edges = new ArrayList<>();
            for (Edge e : pr.mstEdges) primBlock.mst_edges.add(JSONUtils.toEdgeOut(e, g));
            primBlock.total_cost = pr.totalCost;
            primBlock.operations_count = pr.metrics.comparisons + pr.metrics.edgeChecks + pr.metrics.finds + pr.metrics.unions;
            primBlock.execution_time_ms = pr.metrics.timeMs;
            ri.prim = primBlock;

            // kruskal block
            JSONUtils.MSTBlock krBlock = new JSONUtils.MSTBlock();
            krBlock.mst_edges = new ArrayList<>();
            for (Edge e : kr.mstEdges) krBlock.mst_edges.add(JSONUtils.toEdgeOut(e, g));
            krBlock.total_cost = kr.totalCost;
            krBlock.operations_count = kr.metrics.comparisons + kr.metrics.edgeChecks + kr.metrics.finds + kr.metrics.unions;
            krBlock.execution_time_ms = kr.metrics.timeMs;
            ri.kruskal = krBlock;

            outResults.add(ri);
        }

        JSONUtils.writeOutput(outputFile, outResults);
        System.out.println("Done. Output written to " + outputFile);
    }
}
