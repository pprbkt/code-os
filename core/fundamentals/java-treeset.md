# Java TreeSet

## Overview

`TreeSet` is a `Set` implementation that stores unique elements in sorted order. Elements are automatically sorted in their natural order (alphabetical for strings, ascending for numbers) whenever they are added.

```java
import java.util.TreeSet;

TreeSet<String> cars = new TreeSet<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");
cars.add("BMW");   // duplicate — ignored
cars.add("Mazda");

System.out.println(cars); // Prints: [BMW, Ford, Mazda, Volvo]
```

## Common Methods

| Method | Description |
|--------|-------------|
| `add(element)` | Adds the element if not already present; maintains sorted order |
| `remove(element)` | Removes the specified element |
| `contains(element)` | Returns `true` if the element is in the set |
| `size()` | Returns the number of unique elements |
| `clear()` | Removes all elements |

```java
TreeSet<String> cars = new TreeSet<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

System.out.println(cars.contains("BMW")); // Prints: true
System.out.println(cars.size());          // Prints: 3

cars.remove("Ford");
System.out.println(cars);                 // Prints: [BMW, Volvo]

cars.clear();
System.out.println(cars.size());          // Prints: 0
```

## Looping Through a TreeSet

Use a for-each loop to iterate. Elements are always visited in sorted order:

```java
TreeSet<Integer> numbers = new TreeSet<>();
numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(20);

for (int n : numbers) {
    System.out.println(n);
}
// Prints: 10 20 30 40
```

## HashSet vs TreeSet

| | HashSet | TreeSet |
|--|---------|---------|
| Order | None | Sorted (natural order) |
| Duplicates | Not allowed | Not allowed |
| Performance | O(1) average | O(log n) |
| Best for | Fast membership checks | Sorted unique elements |

Use `HashSet` when order does not matter and you want the fastest lookups. Use `TreeSet` when you need elements kept in sorted order.

## Declaring with the Set Interface

You will often see `TreeSet` declared against the `Set` interface:

```java
import java.util.Set;
import java.util.TreeSet;

Set<String> cars = new TreeSet<>();
```

This works because `TreeSet` implements `Set`. Declaring the variable as `Set` keeps the code flexible — you can swap the implementation later without changing any code that uses `cars`. The trade-off is that `TreeSet`-specific methods are not accessible through a `Set` reference.

## The var Keyword

From Java 10, `var` lets you avoid writing the type twice:

```java
// Without var
TreeSet<String> cars = new TreeSet<String>();

// With var
var cars = new TreeSet<String>();
```

Both are equivalent. `var` is a style choice — many developers still prefer the explicit type for readability.