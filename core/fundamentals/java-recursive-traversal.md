# Recursive Tree Traversal

## Overview

Tree traversal means visiting every node in a tree in a specific order. Recursion is the natural fit for trees because a tree is itself a recursive structure — every node is the root of its own subtree.

The three standard depth-first traversal orders differ only in **when the current node is processed** relative to its children.

```java
class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}
```

---

## Traversal Orders

### Inorder — Left, Root, Right

The left subtree is visited first, then the current node, then the right subtree. For a Binary Search Tree, inorder traversal produces elements in sorted ascending order.

```java
void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    System.out.print(node.val + " ");
    inorder(node.right);
}
```

### Preorder — Root, Left, Right

The current node is processed first, then the left subtree, then the right. Useful for copying a tree or serialising its structure.

```java
void preorder(TreeNode node) {
    if (node == null) return;
    System.out.print(node.val + " ");
    preorder(node.left);
    preorder(node.right);
}
```

### Postorder — Left, Right, Root

Both subtrees are visited before the current node. Useful for deleting a tree or evaluating expression trees (children before parent).

```java
void postorder(TreeNode node) {
    if (node == null) return;
    postorder(node.left);
    postorder(node.right);
    System.out.print(node.val + " ");
}
```

---

## Example Tree

```
        1
       / \
      2   3
     / \
    4   5
```

| Traversal | Order visited | Output |
|-----------|--------------|--------|
| Inorder | Left → Root → Right | `4 2 5 1 3` |
| Preorder | Root → Left → Right | `1 2 4 5 3` |
| Postorder | Left → Right → Root | `4 5 2 3 1` |

```java
public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left        = new TreeNode(2);
        root.right       = new TreeNode(3);
        root.left.left   = new TreeNode(4);
        root.left.right  = new TreeNode(5);

        System.out.print("Inorder:   "); inorder(root);   System.out.println();
        System.out.print("Preorder:  "); preorder(root);  System.out.println();
        System.out.print("Postorder: "); postorder(root); System.out.println();
    }
    // Inorder:   4 2 5 1 3
    // Preorder:  1 2 4 5 3
    // Postorder: 4 5 2 3 1
}
```

---

## Step-by-step Trace — Inorder on the example tree

| Call | Action |
|------|--------|
| `inorder(1)` | go left |
| `inorder(2)` | go left |
| `inorder(4)` | go left |
| `inorder(null)` | base case, return |
| back at 4 | print `4` |
| `inorder(null)` | base case, return |
| back at 2 | print `2` |
| `inorder(5)` | go left |
| `inorder(null)` | base case, return |
| back at 5 | print `5` |
| `inorder(null)` | base case, return |
| back at 1 | print `1` |
| `inorder(3)` | go left |
| `inorder(null)` | base case, return |
| back at 3 | print `3` |
| `inorder(null)` | base case, return |

Output: `4 2 5 1 3`

---

## Collecting Results into a List

In practice, traversal results are usually collected into a list rather than printed directly:

```java
import java.util.ArrayList;
import java.util.List;

List<Integer> result = new ArrayList<>();

void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    result.add(node.val);
    inorder(node.right);
}
```

The same pattern applies to preorder and postorder — just move `result.add(node.val)` to before or after the recursive calls.

---

## Common Problems Using Recursive Traversal

| Problem | Traversal used | Why |
|---------|---------------|-----|
| Validate a BST | Inorder | Sorted order reveals invalid values |
| Tree height / depth | Postorder | Children computed before parent |
| Path sum | Preorder | Decision made top-down before going deeper |
| Serialize / deserialize tree | Preorder | Root recorded before children |
| Delete a tree | Postorder | Children freed before parent |
| Mirror / invert a tree | Preorder or postorder | Either works — swap left and right at each node |

---

## Complexity

| | Value |
|--|-------|
| Time | O(n) — every node is visited exactly once |
| Space | O(h) — call stack depth equals tree height h; O(log n) for balanced trees, O(n) worst case for skewed trees |