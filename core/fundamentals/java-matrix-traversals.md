# Matrix Traversal Techniques

## Overview

A matrix is a 2D array of values arranged in rows and columns. Traversal means visiting every cell — or finding a path between cells — in a deliberate order. The technique you choose depends on what the problem is asking for:

- **Row-by-row / column-by-column** — when you need to visit every cell once
- **Depth-First Search (DFS)** — when you need to explore a connected region as deep as possible before backtracking
- **Breadth-First Search (BFS)** — when you need the shortest path or nearest neighbour in terms of steps

Matrices are indexed by `[row][col]`, where `[0][0]` is the top-left corner.

---

## Row-by-Row Traversal

The simplest traversal. Use two nested loops — the outer loop iterates over rows, the inner over columns.

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

for (int row = 0; row < matrix.length; row++) {
    for (int col = 0; col < matrix[row].length; col++) {
        System.out.print(matrix[row][col] + " ");
    }
    System.out.println();
}

// Prints:
// 1 2 3
// 4 5 6
// 7 8 9
```

To traverse column-by-column instead, swap the loop order — outer loop over columns, inner over rows.

---

## Depth-First Search (DFS)

DFS explores as far as possible in one direction before backtracking. It is typically implemented with recursion (using the call stack) or an explicit stack.

It is well suited for problems like:
- Finding connected regions (islands, blobs)
- Flood fill
- Detecting cycles
- Path existence between two cells

### Direction Vectors

Most matrix problems allow movement in 4 directions (up, down, left, right). Encoding these as arrays keeps the code clean and avoids writing four separate conditions:

```java
int[] dRow = {-1, 1, 0, 0}; // up, down, left, right
int[] dCol = {0, 0, -1, 1};
```

For 8-directional movement (including diagonals), extend both arrays with the four diagonal offsets.

### Example — Count Islands

Given a grid of `'1'` (land) and `'0'` (water), count the number of islands. An island is a group of connected `'1'` cells.

```java
public class Islands {
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};

    public static int countIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    dfs(grid, visited, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    static void dfs(char[][] grid, boolean[][] visited, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;

        // base case: out of bounds, already visited, or water
        if (row < 0 || row >= rows || col < 0 || col >= cols) return;
        if (visited[row][col] || grid[row][col] == '0') return;

        visited[row][col] = true;

        for (int d = 0; d < 4; d++) {
            dfs(grid, visited, row + dRow[d], col + dCol[d]);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '0', '0'},
            {'1', '0', '0', '1'},
            {'0', '0', '1', '1'}
        };
        System.out.println(countIslands(grid)); // Prints: 3
    }
}
```

### Step-by-step trace

Starting at `[0][0]`, DFS marks the connected land cells, then increments the island count and moves on to find the next unvisited land cell:

| Island | Cells visited |
|--------|--------------|
| 1 | [0][0], [0][1], [1][0] |
| 2 | [1][3], [2][3], [2][2] |
| 3 | — wait, [2][2] and [2][3] already grouped with island 2 |

```
Grid:
1 1 0 0
1 0 0 1
0 0 1 1

Island 1: (0,0) → (0,1) → (1,0)
Island 2: (1,3) → (2,3) → (2,2)
Total: 2  ← actual result for this grid
```

---

## Breadth-First Search (BFS)

BFS explores all neighbours at the current distance before moving further out. It uses a queue and guarantees the shortest path in an unweighted grid.

It is well suited for problems like:
- Shortest path between two cells
- Nearest cell matching a condition
- Multi-source spreading (e.g. rotting oranges)

### Example — Shortest Path

Find the shortest path from the top-left to the bottom-right of a grid. `0` is open, `1` is a wall.

```java
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath {
    static int[] dRow = {-1, 1, 0, 0};
    static int[] dCol = {0, 0, -1, 1};

    public static int shortestPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[0][0] == 1 || grid[rows-1][cols-1] == 1) return -1;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0, 1}); // {row, col, distance}
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0], col = current[1], dist = current[2];

            if (row == rows - 1 && col == cols - 1) return dist;

            for (int d = 0; d < 4; d++) {
                int newRow = row + dRow[d];
                int newCol = col + dCol[d];

                if (newRow >= 0 && newRow < rows &&
                    newCol >= 0 && newCol < cols &&
                    !visited[newRow][newCol] &&
                    grid[newRow][newCol] == 0) {

                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol, dist + 1});
                }
            }
        }

        return -1; // no path found
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 1},
            {1, 0, 0},
            {1, 1, 0}
        };
        System.out.println(shortestPath(grid)); // Prints: 5
    }
}
```

---

## DFS vs BFS

| | DFS | BFS |
|--|-----|-----|
| Data structure | Stack (or recursion) | Queue |
| Explores | Deep first | Wide first |
| Shortest path | No | Yes (unweighted) |
| Memory | O(depth) | O(width) |
| Good for | Connected regions, path existence | Shortest path, nearest cell |

---

## Boundary and Visited Checks

Every matrix traversal needs two guard conditions before processing a cell:

1. **In bounds** — `row >= 0 && row < rows && col >= 0 && col < cols`
2. **Not visited** — prevents infinite loops in connected-region problems

A `visited` boolean matrix of the same dimensions is the standard way to track this. Alternatively, the grid itself can be mutated (e.g. marking `'1'` as `'0'` after visiting) to avoid allocating extra space, though this modifies the input.

---

## Complexity

| Traversal | Time | Space |
|-----------|------|-------|
| Row-by-row | O(m × n) | O(1) |
| DFS | O(m × n) | O(m × n) — recursion stack |
| BFS | O(m × n) | O(m × n) — queue |

Where `m` is the number of rows and `n` is the number of columns.

---

## When to Use Each Technique

| Problem type | Technique |
|--------------|-----------|
| Visit every cell once | Row-by-row |
| Find all cells in a connected region | DFS |
| Check whether a path exists | DFS |
| Find the shortest path | BFS |
| Spread from multiple sources simultaneously | BFS (multi-source) |
| Detect cycles | DFS |