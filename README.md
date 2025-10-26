# 🧩 Assignment 3 — Minimum Spanning Tree (Prim & Kruskal Algorithms)

This project implements and compares two algorithms for finding the **Minimum Spanning Tree (MST)** in a weighted graph:
- **Prim's Algorithm**
- **Kruskal's Algorithm**

The project reads graph data from a JSON file (`input.json`), runs both algorithms, and generates the results in `output_generated.json`.  
Additionally, the project can generate visual PNG images of graphs with MST edges highlighted.

---

## 📁 Project Structure
```
project_root/
├── src/
│ ├── main/
│ │ ├── java/com/example/mst/
│ │ │ ├── Edge.java
│ │ │ ├── Graph.java
│ │ │ ├── Metrics.java
│ │ │ ├── UF.java
│ │ │ ├── PrimMST.java
│ │ │ ├── KruskalMST_Simple.java
│ │ │ ├── MSTResult.java
│ │ │ ├── JSONUtils.java
│ │ │ ├── GraphDrawer.java
│ │ │ └── Runner.java
│ │ └── resources/data/
│ │ ├── input.json
│ │ └── output_generated.json
│ └── test/java/com/example/mst/
│ ├── PrimMSTTest.java
│ └── KruskalMSTTest.java
├── pom.xml
└── README.md
```

---

## ⚙️ How to Run

### ▶️ Run in IntelliJ IDEA
1. Open **Run → Edit Configurations...**
2. Create a new **Application** configuration:
   - **Main class:** `com.example.mst.Runner`
   - **Program arguments:**  
     ```
     src/main/resources/data/input.json src/main/resources/data/output_generated.json
     ```
   - **Working directory:** `${PROJECT_DIR}`
3. Click **Run ▶**

---

### 🧰 Run with Maven (Terminal)

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.mst.Runner" -Dexec.args="src/main/resources/data/input.json src/main/resources/data/output_generated.json"
```
To run tests:
```
mvn test
```

📊 Example Output (generated)
File: output_generated.json
```
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": { "vertices": 5, "edges": 7 },
      "prim": {
        "total_cost": 16.0,
        "operations_count": 67,
        "execution_time_ms": 0.0
      },
      "kruskal": {
        "total_cost": 16.0,
        "operations_count": 31,
        "execution_time_ms": 5.0
      }
    },
    {
      "graph_id": 2,
      "input_stats": { "vertices": 4, "edges": 5 },
      "prim": {
        "total_cost": 6.0,
        "operations_count": 41,
        "execution_time_ms": 0.0
      },
      "kruskal": {
        "total_cost": 6.0,
        "operations_count": 20,
        "execution_time_ms": 0.0
      }
    }
  ]
}

```
| Graph ID | Vertices | Edges | Prim Cost | Kruskal Cost | Prim Ops | Kruskal Ops | Prim Time (ms) | Kruskal Time (ms) |
| -------- | -------- | ----- | --------- | ------------ | -------- | ----------- | -------------- | ----------------- |
| 1        | 5        | 7     | 16.0      | 16.0         | 67       | 31          | 0.0            | 5.0               |
| 2        | 4        | 5     | 6.0       | 6.0          | 41       | 20          | 0.0            | 0.0               |

🧮 Algorithms Used
```
Prim’s Algorithm

Builds the MST by starting from one vertex and expanding the tree by adding the lowest-weight edge connecting the tree to a new vertex.

Works efficiently on dense graphs.

Kruskal’s Algorithm

Sorts all edges and adds them one by one to the MST if they do not form a cycle (using Union-Find structure).

Works efficiently on sparse graphs.
```
🧠 Implementation Details
```
Language: Java (JDK 17+)

Build tool: Maven

Libraries:

com.google.code.gson — for JSON input/output

org.junit.jupiter — for testing

Visualization: GraphDrawer uses java.awt and ImageIO to render .png graphs.
```
🧪 Testing

Test classes:

PrimMSTTest.java

KruskalMSTTest.java
Run:
```
mvn test

```
Both test small graphs to confirm:

MST has V - 1 edges

Total cost is correct

Prim and Kruskal produce identical costs

