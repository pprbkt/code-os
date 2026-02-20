# ArrayList vs LinkedList

## Performance Comparison

| Operation | ArrayList | LinkedList |
|-----------|-----------|------------|
| Access by index | O(1) | O(n) |
| Insert/Delete at start | O(n) | O(1) |
| Insert/Delete at end | O(1) amortized | O(1) |
| Insert/Delete in middle | O(n) | O(n)* |
| Search | O(n) | O(n) |

*LinkedList is O(1) if you already have the node reference

## When to Use

**ArrayList**
- Default choice for most cases
- Frequent random access by index
- Few insertions/deletions in the middle
- Memory efficient (no extra pointers)

**LinkedList**
- Frequent insertions/deletions at the beginning
- Don't need random access
- Implementing stacks or queues
- Large dataset with many middle insertions

## Internal Structure

**ArrayList**
- Backed by dynamic array
- Resizes when capacity exceeded (usually 1.5x)
- Contiguous memory
- Cache-friendly

**LinkedList**
- Doubly-linked list of nodes
- Each node stores: previous, value, next
- Non-contiguous memory
- Extra memory overhead per node

## Example Usage

```java
// ArrayList - better for random access
List<String> list = new ArrayList<>();
list.add("apple");           // O(1)
list.add("banana");          // O(1)
String first = list.get(0);  // O(1) - fast
list.add(0, "orange");       // O(n) - slow

// LinkedList - better for frequent additions at start
Deque<String> deque = new LinkedList<>();
deque.addFirst("apple");     // O(1) - fast
deque.addLast("banana");     // O(1) - fast
deque.removeFirst();         // O(1) - fast
```

## Key Methods

### ArrayList
```java
list.add(element);           // Add at end
list.add(index, element);    // Insert at index
list.remove(index);          // Remove by index
list.get(index);             // Access by index
list.set(index, element);    // Update element
list.indexOf(element);       // Find position
list.size();                 // Get size
```

### LinkedList
```java
list.addFirst(element);      // Add at beginning - O(1)
list.addLast(element);       // Add at end - O(1)
list.removeFirst();          // Remove first - O(1)
list.removeLast();           // Remove last - O(1)
list.getFirst();             // Get first
list.getLast();              // Get last
list.get(index);             // Random access - O(n)
```

## Memory Overhead

**ArrayList**
- Storage for elements only
- Some wasted space due to array resizing

**LinkedList**
- 2 extra references (prev, next) per node
- ~2-3x more memory per element vs ArrayList

## Thread Safety

- Both are **not thread-safe**
- Use `Collections.synchronizedList()` for thread-safe version
- Or use `CopyOnWriteArrayList` for concurrent reads

## Quick Decision Guide

Use **ArrayList** if:
- You access elements by index frequently
- You mostly add/remove from the end
- Memory efficiency matters

Use **LinkedList** if:
- You frequently add/remove from the beginning
- You need to implement Queue/Deque behavior
- Random access is rare