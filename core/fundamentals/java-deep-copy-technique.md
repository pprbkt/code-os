# Deep Copy Technique

Deep copy (also called deep clone) is the process of creating a complete duplicate of a data structure, including all nested objects and references, rather than just copying references to the original objects.

## Shallow Copy vs Deep Copy

**Shallow Copy:**

Copies the object structure but references point to the same nested objects.

```java
// Shallow copy - both share the same nested objects
Node original = new Node(1);
original.next = new Node(2);

Node copy = original; // Just copies reference
copy.next.val = 99;   // Modifies original too!
```

**Deep Copy:**

Creates completely independent copy with new objects for all nested structures.

```java
// Deep copy - completely independent
Node original = new Node(1);
original.next = new Node(2);

Node copy = deepCopy(original);
copy.next.val = 99; // Original unchanged
```

## Key Characteristics

- **Goal**: Create independent copy with no shared references
- **Challenge**: Handle circular references and complex pointer structures
- **Common Approaches**: HashMap, recursion, iteration
- **Time Complexity**: O(n) where n is number of nodes
- **Space Complexity**: O(n) for storing copies

## Pattern 1: Deep Copy Linked List

Simple linked list without random pointers.

```java
public class DeepCopyLinkedList {
    
    public static ListNode copyList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        Map<ListNode, ListNode> map = new HashMap<>();
        
        // First pass: create all nodes
        ListNode curr = head;
        while (curr != null) {
            map.put(curr, new ListNode(curr.val));
            curr = curr.next;
        }
        
        // Second pass: connect nodes
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            curr = curr.next;
        }
        
        return map.get(head);
    }
}
```

## Pattern 2: Deep Copy with Random Pointer

Copy linked list where each node has a random pointer.

```java
class Node {
    int val;
    Node next;
    Node random;
    
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyRandomList {
    
    // Approach 1: Using HashMap
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        Map<Node, Node> map = new HashMap<>();
        
        // First pass: create all nodes
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        
        // Second pass: assign next and random pointers
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        
        return map.get(head);
    }
    
    // Approach 2: O(1) space using interweaving
    public static Node copyRandomListOptimized(Node head) {
        if (head == null) {
            return null;
        }
        
        // Step 1: Create copy nodes interleaved with original
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }
        
        // Step 2: Assign random pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        
        // Step 3: Separate the lists
        curr = head;
        Node copyHead = head.next;
        Node copyCurr = copyHead;
        
        while (curr != null) {
            curr.next = curr.next.next;
            if (copyCurr.next != null) {
                copyCurr.next = copyCurr.next.next;
            }
            curr = curr.next;
            copyCurr = copyCurr.next;
        }
        
        return copyHead;
    }
}
```

**Interweaving Visualization:**

```
Original: 1 → 2 → 3 → null

After Step 1 (interweave):
1 → 1' → 2 → 2' → 3 → 3' → null

After Step 3 (separate):
Original: 1 → 2 → 3 → null
Copy:     1' → 2' → 3' → null
```

## Pattern 3: Deep Copy Graph (Undirected)

Clone an undirected graph where each node contains a list of neighbors.

```java
class GraphNode {
    int val;
    List<GraphNode> neighbors;
    
    public GraphNode(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }
}

public class CloneGraph {
    
    // DFS approach
    public static GraphNode cloneGraph(GraphNode node) {
        if (node == null) {
            return null;
        }
        
        Map<GraphNode, GraphNode> map = new HashMap<>();
        return cloneDFS(node, map);
    }
    
    private static GraphNode cloneDFS(GraphNode node, 
                                      Map<GraphNode, GraphNode> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        // Create copy of current node
        GraphNode copy = new GraphNode(node.val);
        map.put(node, copy);
        
        // Recursively clone neighbors
        for (GraphNode neighbor : node.neighbors) {
            copy.neighbors.add(cloneDFS(neighbor, map));
        }
        
        return copy;
    }
    
    // BFS approach
    public static GraphNode cloneGraphBFS(GraphNode node) {
        if (node == null) {
            return null;
        }
        
        Map<GraphNode, GraphNode> map = new HashMap<>();
        Queue<GraphNode> queue = new LinkedList<>();
        
        // Create copy of first node
        map.put(node, new GraphNode(node.val));
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            GraphNode curr = queue.poll();
            
            for (GraphNode neighbor : curr.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new GraphNode(neighbor.val));
                    queue.offer(neighbor);
                }
                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }
        
        return map.get(node);
    }
}
```

## Pattern 4: Deep Copy Binary Tree

Clone a binary tree structure.

```java
public class CloneBinaryTree {
    
    public static TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode copy = new TreeNode(root.val);
        copy.left = cloneTree(root.left);
        copy.right = cloneTree(root.right);
        
        return copy;
    }
    
    // With parent pointers (requires HashMap)
    public static TreeNode cloneTreeWithParent(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        Map<TreeNode, TreeNode> map = new HashMap<>();
        return cloneWithParent(root, null, map);
    }
    
    private static TreeNode cloneWithParent(TreeNode node, 
                                            TreeNode parent,
                                            Map<TreeNode, TreeNode> map) {
        if (node == null) {
            return null;
        }
        
        if (map.containsKey(node)) {
            return map.get(node);
        }
        
        TreeNode copy = new TreeNode(node.val);
        copy.parent = parent;
        map.put(node, copy);
        
        copy.left = cloneWithParent(node.left, copy, map);
        copy.right = cloneWithParent(node.right, copy, map);
        
        return copy;
    }
}
```

## Pattern 5: Deep Copy N-ary Tree

Clone an N-ary tree with arbitrary number of children.

```java
class NaryNode {
    int val;
    List<NaryNode> children;
    
    public NaryNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

public class CloneNaryTree {
    
    public static NaryNode cloneTree(NaryNode root) {
        if (root == null) {
            return null;
        }
        
        NaryNode copy = new NaryNode(root.val);
        
        for (NaryNode child : root.children) {
            copy.children.add(cloneTree(child));
        }
        
        return copy;
    }
    
    // Iterative approach
    public static NaryNode cloneTreeIterative(NaryNode root) {
        if (root == null) {
            return null;
        }
        
        Map<NaryNode, NaryNode> map = new HashMap<>();
        Queue<NaryNode> queue = new LinkedList<>();
        
        NaryNode copy = new NaryNode(root.val);
        map.put(root, copy);
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            NaryNode curr = queue.poll();
            
            for (NaryNode child : curr.children) {
                if (!map.containsKey(child)) {
                    map.put(child, new NaryNode(child.val));
                    queue.offer(child);
                }
                map.get(curr).children.add(map.get(child));
            }
        }
        
        return copy;
    }
}
```

## Pattern 6: Deep Copy 2D Array

Clone a 2D array or matrix.

```java
public class Clone2DArray {
    
    public static int[][] clone2DArray(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        
        int rows = matrix.length;
        int[][] copy = new int[rows][];
        
        for (int i = 0; i < rows; i++) {
            copy[i] = matrix[i].clone();
        }
        
        return copy;
    }
    
    // For objects (deep copy required)
    public static Object[][] deepClone2DArray(Object[][] matrix) {
        if (matrix == null) {
            return null;
        }
        
        int rows = matrix.length;
        Object[][] copy = new Object[rows][];
        
        for (int i = 0; i < rows; i++) {
            if (matrix[i] != null) {
                copy[i] = new Object[matrix[i].length];
                for (int j = 0; j < matrix[i].length; j++) {
                    // Assuming objects implement Cloneable
                    copy[i][j] = deepCopyObject(matrix[i][j]);
                }
            }
        }
        
        return copy;
    }
    
    private static Object deepCopyObject(Object obj) {
        // Implementation depends on object type
        // May require reflection or custom copy logic
        return obj;
    }
}
```

## Common Templates

### Template 1: HashMap Approach (Two Pass)

```java
public Node deepCopy(Node head) {
    if (head == null) return null;
    
    Map<Node, Node> map = new HashMap<>();
    
    // Pass 1: Create all nodes
    Node curr = head;
    while (curr != null) {
        map.put(curr, new Node(curr.val));
        curr = curr.next;
    }
    
    // Pass 2: Connect pointers
    curr = head;
    while (curr != null) {
        map.get(curr).next = map.get(curr.next);
        map.get(curr).random = map.get(curr.random);
        curr = curr.next;
    }
    
    return map.get(head);
}
```

### Template 2: Recursive DFS

```java
public Node deepCopy(Node node, Map<Node, Node> map) {
    if (node == null) return null;
    
    if (map.containsKey(node)) {
        return map.get(node);
    }
    
    Node copy = new Node(node.val);
    map.put(node, copy);
    
    copy.next = deepCopy(node.next, map);
    copy.random = deepCopy(node.random, map);
    
    return copy;
}
```

### Template 3: Iterative BFS

```java
public Node deepCopy(Node head) {
    if (head == null) return null;
    
    Map<Node, Node> map = new HashMap<>();
    Queue<Node> queue = new LinkedList<>();
    
    map.put(head, new Node(head.val));
    queue.offer(head);
    
    while (!queue.isEmpty()) {
        Node curr = queue.poll();
        
        if (curr.next != null && !map.containsKey(curr.next)) {
            map.put(curr.next, new Node(curr.next.val));
            queue.offer(curr.next);
        }
        map.get(curr).next = map.get(curr.next);
        
        // Handle other pointers similarly
    }
    
    return map.get(head);
}
```

## Serialization and Deserialization

Alternative approach using serialization.

```java
public class SerializeDeserialize {
    
    // For simple trees
    public static TreeNode cloneViaSerialize(TreeNode root) {
        String serialized = serialize(root);
        return deserialize(serialized);
    }
    
    private static String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }
    
    private static TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(nodes);
    }
    
    private static TreeNode deserializeHelper(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals("null")) {
            return null;
        }
        
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(nodes);
        node.right = deserializeHelper(nodes);
        
        return node;
    }
}
```

## Common Pitfalls

**Shallow Copy Instead of Deep Copy**

Using assignment or clone() without proper deep copying.

```java
// Wrong - shallow copy
Node copy = original;

// Wrong - only copies first level
ArrayList<Integer> copy = new ArrayList<>(original);

// Correct - deep copy
Node copy = deepCopy(original, new HashMap<>());
```

**Not Handling Null References**

Always check for null before accessing or copying.

**Missing Circular Reference Handling**

Use HashMap to track already-copied nodes to avoid infinite loops.

**Forgetting to Copy All Pointers**

Ensure all fields (next, random, parent, etc.) are properly copied.

**Modifying Original During Copy**

The interweaving technique modifies the original temporarily but must restore it.

