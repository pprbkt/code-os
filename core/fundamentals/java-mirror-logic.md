# Mirror Logic (Tree Symmetry)

## Overview

Mirror logic refers to problems where the relationship between two nodes is checked symmetrically — not just within a single subtree, but between a left subtree and its corresponding right subtree. The core idea is that two trees (or two subtrees) are mirrors of each other if:

- Their root values are equal
- The left subtree of one is a mirror of the right subtree of the other

This leads to a recursive pattern where pairs of nodes are compared simultaneously, moving inward from opposite sides.

```java
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) { this.val = val; }
}
```

---

## Application 1 — Check if a Tree is Symmetric

A binary tree is symmetric if it is a mirror of itself around its root.

```
Symmetric:          Not Symmetric:
      1                   1
     / \                 / \
    2   2               2   2
   / \ / \               \   \
  3  4 4  3               3   3
```

**Approach:** Recursively compare the left and right subtrees. At each step, compare the outer pair (left.left vs right.right) and the inner pair (left.right vs right.left).

```java
public boolean isSymmetric(TreeNode root) {
    return isMirror(root, root);
}

private boolean isMirror(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null) return false;

    return (left.val == right.val)
        && isMirror(left.left, right.right)   // outer pair
        && isMirror(left.right, right.left);  // inner pair
}
```

```java
public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left        = new TreeNode(2);
    root.right       = new TreeNode(2);
    root.left.left   = new TreeNode(3);
    root.left.right  = new TreeNode(4);
    root.right.left  = new TreeNode(4);
    root.right.right = new TreeNode(3);

    System.out.println(isSymmetric(root)); // Prints: true
}
```

### Step-by-step trace

```
isMirror(1, 1)
├── values match: 1 == 1
├── isMirror(left.left=2, right.right=2)
│   ├── values match: 2 == 2
│   ├── isMirror(3, 3) → true
│   └── isMirror(4, 4) → true
└── isMirror(left.right=2, right.left=2)
    ├── values match: 2 == 2
    ├── isMirror(4, 4) → true
    └── isMirror(3, 3) → true

Result: true
```

---

## Application 2 — Invert a Binary Tree

Inverting a tree means swapping the left and right children at every node, producing the mirror image of the original tree.

```
Original:       Inverted:
    4               4
   / \             / \
  2   7           7   2
 / \ / \         / \ / \
1  3 6  9       9  6 3  1
```

**Approach:** At each node, swap the left and right children, then recursively invert both subtrees.

```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;

    TreeNode temp   = root.left;
    root.left       = root.right;
    root.right      = temp;

    invertTree(root.left);
    invertTree(root.right);

    return root;
}
```

The swap happens at the current node first (preorder), then the children are recursively inverted. Postorder (invert children first, then swap) also produces the correct result.

---

## Application 3 — Check if Two Trees are Mirrors of Each Other

Given two separate trees, determine if one is the mirror image of the other.

```java
public boolean areMirrors(TreeNode t1, TreeNode t2) {
    if (t1 == null && t2 == null) return true;
    if (t1 == null || t2 == null) return false;

    return (t1.val == t2.val)
        && areMirrors(t1.left, t2.right)
        && areMirrors(t1.right, t2.left);
}
```

This is the same as `isMirror` from Application 1, extracted as a standalone utility. The symmetric tree check is simply `areMirrors(root.left, root.right)`.

---

## The Mirror Recursion Pattern

All three applications share the same underlying call structure:

```
compare(nodeA, nodeB):
    if both null       → true
    if one null        → false
    if values differ   → false
    recurse on:
        (nodeA.left,  nodeB.right)   ← outer pair
        (nodeA.right, nodeB.left)    ← inner pair
```

When you see a problem asking whether a tree or two trees have symmetric structure, this two-pointer recursive pattern is the solution.

---

## Complexity

| | Value |
|--|-------|
| Time | O(n) — each node is visited once |
| Space | O(h) — call stack depth equals tree height; O(log n) balanced, O(n) worst case |