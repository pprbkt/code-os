# Java List

## Overview

The `List` interface is part of the Java Collections Framework and represents an ordered collection of elements. Key properties:

- Elements are accessible by index
- Duplicates are allowed
- Insertion order is maintained

Since `List` is an interface, you cannot instantiate it directly. Instead, you use a class that implements it.

## Implementations

| Class | Description |
|-------|-------------|
| `ArrayList` | Backed by a resizable array. Fast random access by index. |
| `LinkedList` | Backed by a doubly-linked list. Fast insertions and removals at any position. |

```java
List<String> arrayList  = new ArrayList<>();
List<String> linkedList = new LinkedList<>();
```

## Common Methods

| Method | Description |
|--------|-------------|
| `add(element)` | Adds an element to the end of the list |
| `get(index)` | Returns the element at the specified index |
| `set(index, element)` | Replaces the element at the specified index |
| `remove(index)` | Removes the element at the specified index |
| `size()` | Returns the number of elements in the list |

```java
List<String> names = new ArrayList<>();

names.add("Alice");
names.add("Bob");
names.add("Alice");     // duplicates allowed

System.out.println(names.get(0));     // Prints: Alice
System.out.println(names.size());     // Prints: 3

names.set(1, "Charlie");
System.out.println(names.get(1));     // Prints: Charlie

names.remove(0);
System.out.println(names.size());     // Prints: 2
```

## List vs Array

| | Array | List |
|--|-------|------|
| Size | Fixed at creation | Dynamic |
| Duplicates | Allowed | Allowed |
| Access by index | Yes | Yes |
| Part of Collections Framework | No | Yes |
| Built-in methods | None | `add()`, `remove()`, `size()`, etc. |

Use an array when the size is known and fixed and raw performance matters. Use a `List` when you need to add or remove elements dynamically or want access to the Collections utility methods.
