package com.example.mst;

public class UF {
    private final int[] parent;
    private final int[] rank;
    public long finds = 0;
    public long unions = 0;

    public UF(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        finds++;
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public void union(int a, int b) {
        unions++;
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return;
        if (rank[ra] < rank[rb]) parent[ra] = rb;
        else if (rank[rb] < rank[ra]) parent[rb] = ra;
        else {
            parent[rb] = ra;
            rank[ra]++;
        }
    }
}
