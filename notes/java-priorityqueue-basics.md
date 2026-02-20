# Priority Queue Basics

## Overview

A Priority Queue is a data structure where elements are retrieved based on priority, not insertion order. Internally uses a **binary min-heap** by default.

## Declaration

```java
// Min-heap (smallest element has highest priority)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-heap (largest element has highest priority)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
// Or:
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// With initial capacity
PriorityQueue<Integer> pq = new PriorityQueue<>(20);
```

## Core Operations

| Operation | Time | Description |
|-----------|------|-------------|
| `add(E)` | O(log n) | Insert element |
| `offer(E)` | O(log n) | Insert element (same as add) |
| `poll()` | O(log n) | Remove and return highest priority |
| `remove()` | O(log n) | Remove by reference (don't use) |
| `peek()` | O(1) | View highest priority (no removal) |
| `element()` | O(1) | View highest priority (throws exception if empty) |
| `size()` | O(1) | Get number of elements |
| `isEmpty()` | O(1) | Check if empty |

## Basic Example

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.add(5);
pq.add(1);
pq.add(3);

System.out.println(pq.peek());   // 1 (smallest)
System.out.println(pq.poll());   // 1, removes it
System.out.println(pq.poll());   // 3
System.out.println(pq.poll());   // 5
System.out.println(pq.poll());   // null (or throws exception)
```

## Min-Heap vs Max-Heap

```java
// Min-heap (default) - poll() returns smallest
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.add(5);
minHeap.add(1);
minHeap.add(3);
System.out.println(minHeap.poll()); // 1

// Max-heap - poll() returns largest
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
maxHeap.add(5);
maxHeap.add(1);
maxHeap.add(3);
System.out.println(maxHeap.poll()); // 5
```

## Custom Objects - Comparable Interface

```java
class Task implements Comparable<Task> {
    int priority;
    String name;
    
    public Task(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }
    
    @Override
    public int compareTo(Task other) {
        // Lower number = higher priority
        return Integer.compare(this.priority, other.priority);
    }
    
    @Override
    public String toString() {
        return name + " (priority: " + priority + ")";
    }
}

PriorityQueue<Task> tasks = new PriorityQueue<>();
tasks.add(new Task(3, "Low"));
tasks.add(new Task(1, "High"));
tasks.add(new Task(2, "Medium"));

System.out.println(tasks.poll()); // High (priority: 1)
System.out.println(tasks.poll()); // Medium (priority: 2)
System.out.println(tasks.poll()); // Low (priority: 3)
```

## Custom Objects - Comparator

```java
// Using Comparator in constructor
PriorityQueue<Task> tasks = new PriorityQueue<>(
    (t1, t2) -> Integer.compare(t1.priority, t2.priority)
);

// For reverse order (max-heap behavior)
PriorityQueue<Task> tasks = new PriorityQueue<>(
    (t1, t2) -> Integer.compare(t2.priority, t1.priority)
);

// By multiple fields
PriorityQueue<Task> tasks = new PriorityQueue<>(
    (t1, t2) -> {
        if (t1.priority != t2.priority) {
            return Integer.compare(t1.priority, t2.priority);
        }
        return t1.name.compareTo(t2.name);
    }
);
```

## Common Use Cases

### 1. K-th Largest Element
```java
int[] nums = {3, 2, 1, 5, 6, 4};
int k = 2;

// Min-heap of size k
PriorityQueue<Integer> pq = new PriorityQueue<>();
for (int num : nums) {
    pq.add(num);
    if (pq.size() > k) {
        pq.poll();
    }
}
System.out.println(pq.peek()); // k-th largest
```

### 2. Dijkstra's Algorithm
```java
class Node implements Comparable<Node> {
    int id, distance;
    public Node(int id, int dist) { this.id = id; this.distance = dist; }
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }
}

PriorityQueue<Node> pq = new PriorityQueue<>();
pq.add(new Node(0, 0));

while (!pq.isEmpty()) {
    Node current = pq.poll();
    // Process current node with minimum distance
}
```

### 3. Task Scheduler
```java
class Task implements Comparable<Task> {
    String name;
    int priority;
    public Task(String name, int priority) { this.name = name; this.priority = priority; }
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
}

PriorityQueue<Task> scheduler = new PriorityQueue<>();
scheduler.add(new Task("Email", 2));
scheduler.add(new Task("Deploy", 1));
scheduler.add(new Task("Code review", 3));

while (!scheduler.isEmpty()) {
    Task next = scheduler.poll();
    System.out.println("Processing: " + next.name);
}
```

### 4. Median Finder
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

void addNum(int num) {
    maxHeap.add(num);
    if (maxHeap.peek() > minHeap.peek()) {
        minHeap.add(maxHeap.poll());
    }
    if (maxHeap.size() < minHeap.size()) {
        maxHeap.add(minHeap.poll());
    }
}

double findMedian() {
    if (maxHeap.size() > minHeap.size()) {
        return maxHeap.peek();
    }
    return (maxHeap.peek() + minHeap.peek()) / 2.0;
}
```

### 5. Huffman Coding
```java
class Node implements Comparable<Node> {
    int freq;
    Node left, right;
    public int compareTo(Node other) {
        return Integer.compare(this.freq, other.freq);
    }
}

PriorityQueue<Node> pq = new PriorityQueue<>();
// Add all nodes
while (pq.size() > 1) {
    Node first = pq.poll();
    Node second = pq.poll();
    // Combine them
}
```

## Important Notes

### Thread Safety
- **Not thread-safe** by default
- Use `PriorityBlockingQueue` for multi-threaded scenarios
```java
PriorityBlockingQueue<Integer> pq = new PriorityBlockingQueue<>();
```

### Heap Structure
- Internally uses array-based binary heap
- Not a sorted structure - only root is guaranteed to be smallest/largest
- `peek()` is O(1), iteration would need to poll all elements

### Null Values
- Cannot contain null elements
- Will throw `NullPointerException` if null added

### Modifying Elements
- If you modify an element after adding it, the heap becomes invalid
- Remove and re-add if you need to update priority

### Equals vs Comparator
- Even if elements are equal by `compareTo()`, they're still in the queue
- `contains()`, `remove()` use `equals()`, not `compareTo()`

## Common Mistakes

```java
// Wrong - no guarantee of order beyond root
for (int val : pq) {
    System.out.println(val); // Not in priority order
}

// Right - use poll() to extract in order
while (!pq.isEmpty()) {
    System.out.println(pq.poll()); // In priority order
}

// Wrong - modifying element doesn't update position
Task task = tasks.peek();
task.priority = 1;  // Breaks heap structure

// Right - remove and re-add
Task task = tasks.poll();
task.priority = 1;
tasks.add(task);
```

## Performance Summary

- **Space**: O(n)
- **Insert/Delete**: O(log n)
- **Access min/max**: O(1)
- **Heapify**: O(n)
- **Extract all in order**: O(n log n)