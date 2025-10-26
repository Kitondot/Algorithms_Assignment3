package com.example.mst;

public class Edge {
    public final int u;        // индекс вершины u
    public final int v;        // индекс вершины v
    public final double weight;

    public Edge(int u, int v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }


    public int other(int x) {
        return (x == u) ? v : u;
    }

    @Override
    public String toString() {
        return u + "-" + v + " " + weight;
    }
}
