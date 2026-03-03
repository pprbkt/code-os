# Java LinkedList

## Overview

`LinkedList` is a class in the Java Collections Framework that implements the `List` interface. It stores elements in nodes, where each node holds a value and a reference to the next (and previous) node in the chain.

```java
import java.util.LinkedList;

LinkedList<String> cars = new LinkedList<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");
cars.add("Mazda");

System.out.println(cars); // Prints: [Volvo, BMW, Ford, Mazda]
```

## ArrayList vs LinkedList

Both implement `List` and share the same general methods, but they are built differently and perform differently depending on what you do with them.

| | ArrayList | LinkedList |
|--|-----------|------------|
| Internal structure | Resizable array | Chain of linked nodes |
| Random access by index | Fast — O(1) | Slow — O(n), must traverse the chain |
| Insert / remove at start or middle | Slow — elements must shift | Fast — only node links change |
| Memory overhead | Lower | Higher — each node stores two references |
| Best for | Reading and accessing data | Frequent insertions and removals |

Use `ArrayList` when you primarily read or access elements by index. Use `LinkedList` when you frequently add or remove elements at the beginning or middle of the list.

## LinkedList-Specific Methods

In addition to the standard `List` methods, `LinkedList` provides methods for working directly with the first and last elements:

| Method | Description |
|--------|-------------|
| `addFirst(element)` | Adds an element to the beginning of the list |
| `addLast(element)` | Adds an element to the end of the list |
| `removeFirst()` | Removes and returns the first element |
| `removeLast()` | Removes and returns the last element |
| `getFirst()` | Returns the first element without removing it |
| `getLast()` | Returns the last element without removing it |

```java
LinkedList<String> cars = new LinkedList<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

cars.addFirst("Tesla");
cars.addLast("Mazda");

System.out.println(cars.getFirst()); // Prints: Tesla
System.out.println(cars.getLast());  // Prints: Mazda

cars.removeFirst();
System.out.println(cars);            // Prints: [Volvo, BMW, Ford, Mazda]
```

## Declaring with the List Interface

You will often see `LinkedList` declared against the `List` interface rather than the concrete type:

```java
import java.util.List;
import java.util.LinkedList;

List<String> cars = new LinkedList<>();
```

This works because `LinkedList` implements `List`. Declaring the variable as `List` is a common pattern — it keeps the code flexible, so the implementation can be swapped to `ArrayList` later without changing any other code that uses `cars`.

The trade-off is that `LinkedList`-specific methods like `addFirst()` and `getFirst()` are not accessible through a `List` reference. If you need those, declare the variable as `LinkedList` directly.

## The var Keyword

From Java 10, `var` can be used to avoid writing the type twice. The compiler infers the type from the assigned value:

```java
// Without var
LinkedList<String> cars = new LinkedList<String>();

// With var
var cars = new LinkedList<String>();
```

Both are equivalent. `var` is a matter of style — many developers still prefer the explicit type for readability.