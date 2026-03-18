# BFS with Queue

## Overview

Breadth-First Search (BFS) explores a tree or graph level by level — all nodes at depth 1 before depth 2, all nodes at depth 2 before depth 3, and so on. A queue enforces this order naturally: nodes are processed in the same order they are discovered (FIFO).

BFS is the standard approach whenever a problem asks about **levels**, **shortest paths**, or **nearest nodes**.

```java
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) { this.val = val; }
}
```

---

## Core Pattern

```java
import java.util.ArrayDeque;
import java.util.Deque;

void bfs(TreeNode root) {
    if (root == null) return;

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        System.out.print(node.val + " ");

        if (node.left  != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```

`offer()` adds to the back of the queue. `poll()` removes from the front. Every node is enqueued exactly once and dequeued exactly once.

---

## Level-by-Level Processing

Most BFS problems require knowing which level a node belongs to. Snapshot the queue size at the start of each iteration — that count is the number of nodes in the current level.

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size(); // number of nodes at this level
        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);

            if (node.left  != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        result.add(level);
    }

    return result;
}
```

```
Tree:           Level order result:
      1         [[1], [2, 3], [4, 5, 6]]
     / \
    2   3
   / \ /
  4  5 6
```

---

## Application 1 — Maximum Depth

Find the height of the tree by counting how many levels BFS processes.

```java
int maxDepth(TreeNode root) {
    if (root == null) return 0;

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    int depth = 0;

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        depth++;

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            if (node.left  != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    return depth;
}
```

---

## Application 2 — Right Side View

Return the value of the last node at each level — what you would see looking at the tree from the right.

```java
List<Integer> rightSideView(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size();

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();

            if (i == levelSize - 1) result.add(node.val); // last node in level

            if (node.left  != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    return result;
}

// On the tree above: [1, 3, 6]
```

---

## Application 3 — Minimum Depth

Find the shallowest level that contains a leaf node. BFS guarantees the first leaf encountered is at the minimum depth.

```java
int minDepth(TreeNode root) {
    if (root == null) return 0;

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    int depth = 0;

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        depth++;

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();

            if (node.left == null && node.right == null) return depth; // first leaf

            if (node.left  != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    return depth;
}
```

---

## Step-by-step Trace — Level Order on the example tree

```
Initial queue: [1]

--- Level 1 (size = 1) ---
poll 1 → enqueue 2, 3
level: [1]
queue: [2, 3]

--- Level 2 (size = 2) ---
poll 2 → enqueue 4, 5
poll 3 → enqueue 6
level: [2, 3]
queue: [4, 5, 6]

--- Level 3 (size = 3) ---
poll 4 → no children
poll 5 → no children
poll 6 → no children
level: [4, 5, 6]
queue: []

Result: [[1], [2, 3], [4, 5, 6]]
```

---

## BFS vs DFS

| | BFS | DFS |
|--|-----|-----|
| Data structure | Queue | Stack / recursion |
| Explores | Level by level | Branch by branch |
| Shortest path | Yes | No |
| Memory | O(w) — max width of tree | O(h) — height of tree |
| Best for | Level-order, shortest path, nearest node | Path existence, connected regions, tree shape |

For wide, shallow trees, DFS uses less memory. For deep, narrow trees, BFS uses less memory. In the worst case both are O(n).

---

## Complexity

| | Value |
|--|-------|
| Time | O(n) — every node is enqueued and dequeued exactly once |
| Space | O(w) — queue holds at most one full level; w is the maximum width |