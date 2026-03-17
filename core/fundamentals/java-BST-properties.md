# BST Properties

## Overview

A Binary Search Tree (BST) is a binary tree where every node satisfies the BST property:

- All values in the **left** subtree are **strictly less than** the node's value
- All values in the **right** subtree are **strictly greater than** the node's value
- Both subtrees are themselves valid BSTs

```
        5
       / \
      3   7
     / \ / \
    2  4 6  9
```

This ordering makes search, insertion, and deletion efficient — you eliminate half the remaining tree at each step.

```java
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) { this.val = val; }
}
```

---

## Key Properties

| Property | Description |
|----------|-------------|
| **Ordering** | left < root < right at every node |
| **Inorder traversal** | Produces elements in sorted ascending order |
| **No duplicates** | Standard BSTs do not allow duplicate values |
| **Search** | O(h) — halves the search space at each node |
| **Height** | O(log n) balanced, O(n) worst case (skewed tree) |

---

## Inorder Traversal Produces Sorted Output

Because of the BST property, an inorder traversal (left → root → right) visits every node in ascending order. This is the most direct way to verify that a tree is a valid BST.

```java
void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    System.out.print(node.val + " ");
    inorder(node.right);
}

// On the tree above: 2 3 4 5 6 7 9
```

---

## Search

To find a value, compare it against the current node. If smaller, go left. If greater, go right. If equal, the value is found.

```java
boolean search(TreeNode root, int target) {
    if (root == null) return false;
    if (target == root.val) return true;
    if (target < root.val) return search(root.left, target);
    return search(root.right, target);
}
```

Each comparison eliminates an entire subtree, giving O(h) time where h is the height.

---

## Insertion

Insert by searching for the correct position, then placing the new node where the search falls off the tree.

```java
TreeNode insert(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);
    if (val < root.val) root.left  = insert(root.left, val);
    else if (val > root.val) root.right = insert(root.right, val);
    // val == root.val: duplicate, do nothing
    return root;
}
```

---

## Deletion

Deletion has three cases:

1. **Node has no children** — remove it directly
2. **Node has one child** — replace the node with its child
3. **Node has two children** — replace the node's value with its inorder successor (smallest value in the right subtree), then delete the successor

```java
TreeNode delete(TreeNode root, int val) {
    if (root == null) return null;

    if (val < root.val) {
        root.left = delete(root.left, val);
    } else if (val > root.val) {
        root.right = delete(root.right, val);
    } else {
        // Case 1 & 2: zero or one child
        if (root.left == null)  return root.right;
        if (root.right == null) return root.left;

        // Case 3: two children — find inorder successor
        TreeNode successor = root.right;
        while (successor.left != null) successor = successor.left;

        root.val = successor.val;                      // copy successor value
        root.right = delete(root.right, successor.val); // delete successor
    }

    return root;
}
```

---

## Validate a BST

Checking `left.val < root.val < right.val` at each node is not sufficient — a node deeper in the left subtree could be greater than an ancestor. The correct approach passes a valid range down the recursion.

```
Invalid tree that fools local checks:
      5
     / \
    1   6
         \
          4   ← 4 < 5, violates BST property for the root
```

```java
boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

boolean validate(TreeNode node, long min, long max) {
    if (node == null) return true;
    if (node.val <= min || node.val >= max) return false;

    return validate(node.left,  min,      node.val)
        && validate(node.right, node.val, max);
}
```

`Long` is used instead of `int` to safely handle nodes with values equal to `Integer.MIN_VALUE` or `Integer.MAX_VALUE`.

---

## Find Min and Max

In a BST, the minimum value is always the leftmost node and the maximum is always the rightmost.

```java
int findMin(TreeNode root) {
    while (root.left != null) root = root.left;
    return root.val;
}

int findMax(TreeNode root) {
    while (root.right != null) root = root.right;
    return root.val;
}
```

---

## Lowest Common Ancestor (LCA)

The LCA of two nodes `p` and `q` is the deepest node that has both as descendants. In a BST, the BST property makes this straightforward — the split point where `p` and `q` diverge to different sides is the LCA.

```java
TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val < root.val && q.val < root.val) return lca(root.left, p, q);
    if (p.val > root.val && q.val > root.val) return lca(root.right, p, q);
    return root; // split point — this is the LCA
}
```

---

## Complexity Summary

| Operation | Average (balanced) | Worst case (skewed) |
|-----------|-------------------|---------------------|
| Search | O(log n) | O(n) |
| Insert | O(log n) | O(n) |
| Delete | O(log n) | O(n) |
| Min / Max | O(log n) | O(n) |
| Inorder traversal | O(n) | O(n) |

A skewed BST (all nodes inserted in sorted order) degrades to a linked list with O(n) operations. Self-balancing trees like AVL or Red-Black trees maintain O(log n) by rebalancing after insertions and deletions.