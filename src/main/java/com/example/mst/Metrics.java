package com.example.mst;

public class Metrics {
    public long comparisons = 0;   // сравнения весов / сравнений в сортировке/очереди
    public long unions = 0;        // union операций (для UF)
    public long finds = 0;         // find операций (для UF)
    public long edgeChecks = 0;    // сколько ребер проверили
    public long timeMs = 0;        // время выполнения в миллисекундах
}
