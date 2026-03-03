# Java Set

## Overview

The `Set` interface is part of the Java Collections Framework and stores a collection of unique elements. If you try to add a duplicate, the set silently ignores it.

Unlike `List`, a `Set` does not provide index-based access and does not guarantee order — unless you choose an implementation that does.

## Implementations

| Class | Order | Description |
|-------|-------|-------------|
| `HashSet` | None | Fastest general-purpose set. No guaranteed order. |
| `LinkedHashSet` | Insertion order | Maintains the order elements were added. |
| `TreeSet` | Sorted (natural or custom) | Elements are kept in ascending order. Slower than `HashSet`. |

```java
import java.util.HashSet;

Set<String> names = new HashSet<>();
names.add("Alice");
names.add("Bob");
names.add("Alice"); // duplicate — ignored

System.out.println(names.size()); // Prints: 2
System.out.println(names);        // Prints: [Bob, Alice] (order not guaranteed)
```

## Common Methods

| Method | Description |
|--------|-------------|
| `add(element)` | Adds the element if it is not already present |
| `remove(element)` | Removes the specified element |
| `contains(element)` | Returns `true` if the element is in the set |
| `size()` | Returns the number of elements |
| `clear()` | Removes all elements |

```java
Set<String> fruits = new HashSet<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Cherry");

System.out.println(fruits.contains("Banana")); // Prints: true

fruits.remove("Banana");
System.out.println(fruits.size());             // Prints: 2
```

## Set vs List

| | List | Set |
|--|------|-----|
| Duplicates | Allowed | Not allowed |
| Order | Maintained (insertion order) | Not guaranteed (depends on implementation) |
| Access by index | Yes — `get(index)` | No |
| Best for | Ordered sequences, positional access | Uniqueness checks, membership testing |

Use a `List` when order and positional access matter, or when duplicates are valid. Use a `Set` when you only care about whether a value is present, and each value must appear at most once.

## Choosing an Implementation

```java
Set<String> hashSet       = new HashSet<>();       // fastest, no order
Set<String> linkedHashSet = new LinkedHashSet<>();  // insertion order preserved
Set<String> treeSet       = new TreeSet<>();        // always sorted
```

If order does not matter, prefer `HashSet` — it has O(1) average time for `add`, `remove`, and `contains`. Use `TreeSet` only when you need elements sorted, as its operations are O(log n).