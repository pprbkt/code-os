# Java Priority Queue

## Overview

A Priority Queue is a special data structure where elements are processed based on their priority rather than the order they were added. Elements with higher priority are retrieved first, making it useful for task scheduling, finding minimum/maximum values, and implementing algorithms like Dijkstra's shortest path.

## Key Concepts

A Priority Queue maintains a **heap structure** internally, which allows fast access to the highest priority element. By default, it's a **min-heap** (smallest element has highest priority).

| Concept | Description | Example |
|---------|-------------|---------|
| **Min-Heap** | Smallest element has highest priority | `1, 2, 3, 5, 7` retrieved as `1 → 2 → 3...` |
| **Max-Heap** | Largest element has highest priority | `1, 2, 3, 5, 7` retrieved as `7 → 5 → 3...` |
| **Add** | Insert element maintaining heap order | `pq.add(5)` - O(log n) |
| **Remove** | Extract highest priority element | `pq.poll()` - O(log n) |
| **Peek** | View highest priority without removing | `pq.peek()` - O(1) |

## Declaration

### Syntax

```java
PriorityQueue<Type> pqName = new PriorityQueue<>();
```

### Examples

**Min-Heap (Default - Smallest First):**
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
```

**Max-Heap (Largest First):**
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

**Using Interface:**
```java
Queue<Integer> pq = new PriorityQueue<>();
```

## Adding Elements

### Syntax

```java
pq.add(value);      // Throws exception if fails
pq.offer(value);    // Returns false if fails (safer)
```

### Example

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.add(5);
pq.add(1);
pq.add(3);
pq.add(7);
pq.add(2);

System.out.println(pq.size());  // 5
System.out.println(pq.peek());  // 1 (smallest)
```

## Removing Elements

### Syntax

```java
pq.poll();     // Remove and return top, null if empty
pq.remove();   // Remove and return top, exception if empty
```

### Example

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.addAll(java.util.Arrays.asList(5, 1, 3, 7, 2));

System.out.println(pq.poll());  // 1 (removes it)
System.out.println(pq.poll());  // 2 (removes it)
System.out.println(pq.size());  // 3 remaining

// Remove all elements
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
// Output: 3, 5, 7
```

## Iterating Through Priority Queue

### Best Practice: Use poll() for Priority Order

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.addAll(java.util.Arrays.asList(5, 1, 3, 7, 2));

System.out.println("In priority order:");
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}

// Output:
// 1
// 2
// 3
// 5
// 7
```

**Important Note:** Using `poll()` removes elements as you iterate. If you need to keep elements, use `for-each` loop (but order is not guaranteed).

### Alternative: For-Each Loop (Order Not Guaranteed)

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.addAll(java.util.Arrays.asList(5, 1, 3, 7, 2));

System.out.println("Order NOT guaranteed:");
for (Integer num : pq) {
    System.out.println(num);
}
// Elements remain in queue, but order may vary
```

## Custom Objects with Comparable

### Syntax

```java
class ClassName implements Comparable<ClassName> {
    // Fields
    
    @Override
    public int compareTo(ClassName other) {
        return Integer.compare(this.priority, other.priority);
    }
}
```

### Example

```java
class Task implements Comparable<Task> {
    String name;
    int priority;  // 1 = high, 5 = low
    
    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
    
    @Override
    public String toString() {
        return name + " (priority: " + priority + ")";
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        PriorityQueue<Task> queue = new PriorityQueue<>();
        
        queue.add(new Task("Email", 3));
        queue.add(new Task("Meeting", 1));
        queue.add(new Task("Lunch", 5));
        queue.add(new Task("Report", 2));
        
        System.out.println("Tasks in priority order:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}

// Output:
// Tasks in priority order:
// Meeting (priority: 1)
// Report (priority: 2)
// Email (priority: 3)
// Lunch (priority: 5)
```

## Custom Comparator

### Syntax

```java
PriorityQueue<Type> pq = new PriorityQueue<>(
    (obj1, obj2) -> Integer.compare(obj1.field, obj2.field)
);
```

### Example: Max-Heap (Largest First)

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
    (a, b) -> Integer.compare(b, a)
);

maxHeap.addAll(java.util.Arrays.asList(5, 1, 3, 7, 2));

System.out.println("Largest first:");
while (!maxHeap.isEmpty()) {
    System.out.println(maxHeap.poll());
}

// Output:
// 7
// 5
// 3
// 2
// 1
```

### Example: Custom Class with Comparator

```java
class Patient {
    String name;
    int severity;
    
    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }
    
    @Override
    public String toString() {
        return name + " (severity: " + severity + ")";
    }
}

public class HospitalQueue {
    public static void main(String[] args) {
        // Higher severity = treated first
        PriorityQueue<Patient> queue = new PriorityQueue<>(
            (p1, p2) -> Integer.compare(p2.severity, p1.severity)
        );
        
        queue.add(new Patient("John (bruise)", 2));
        queue.add(new Patient("Alice (broken leg)", 4));
        queue.add(new Patient("Bob (headache)", 1));
        
        System.out.println("Patients treated by severity:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}

// Output:
// Patients treated by severity:
// Alice (severity: 4)
// John (severity: 2)
// Bob (severity: 1)
```

## Common Methods

| Method | Purpose | Returns | Removes? |
|--------|---------|---------|----------|
| `add(E)` | Insert element | - | No |
| `offer(E)` | Insert element | boolean | No |
| `peek()` | View highest priority | element or null | No |
| `poll()` | Remove highest priority | element or null | **Yes** |
| `remove()` | Remove highest priority | element | **Yes** |
| `isEmpty()` | Check if empty | boolean | No |
| `size()` | Get number of elements | int | No |
| `clear()` | Remove all elements | - | **Yes** |

## Real-World Example: K-th Largest Element

```java
public class KthLargestExample {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        
        // Min-heap of size k to track k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();  // Remove smallest
            }
        }
        
        System.out.println(k + "-th largest: " + minHeap.peek());
    }
}

// Output:
// 2-th largest: 5
```

## Important Notes

**Min-Heap vs Max-Heap:**
By default, Priority Queue is a min-heap. To create a max-heap, use a comparator that reverses the order.

```java
// Min-heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-heap (with comparator)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

**Iteration vs Removal:**
When using `poll()` in a loop, elements are removed and processed in priority order. If you use `for-each` or `iterator()`, elements remain but order is not guaranteed.

**Null Values:**
Priority Queue does not allow null elements and will throw `NullPointerException` if you try to add one.

```java
PriorityQueue<String> pq = new PriorityQueue<>();
pq.add(null);  // ERROR - NullPointerException
```

**Thread Safety:**
Priority Queue is not thread-safe. For multi-threaded use, use `PriorityBlockingQueue`.

```java
import java.util.concurrent.PriorityBlockingQueue;

PriorityBlockingQueue<Integer> pq = new PriorityBlockingQueue<>();
```

## Performance Summary

| Operation | Time Complexity |
|-----------|-----------------|
| add() / offer() | O(log n) |
| peek() | O(1) |
| poll() / remove() | O(log n) |
| contains() | O(n) |
| size() | O(1) |

