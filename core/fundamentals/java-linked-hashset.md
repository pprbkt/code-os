# Java LinkedHashSet

## Overview

`LinkedHashSet` is a `Set` implementation that stores unique elements and preserves the order in which they were added. It behaves like `HashSet` but with predictable iteration order.

```java
import java.util.LinkedHashSet;

LinkedHashSet<String> cars = new LinkedHashSet<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");
cars.add("BMW");   // duplicate — ignored
cars.add("Mazda");

System.out.println(cars); // Prints: [Volvo, BMW, Ford, Mazda]
```

## Common Methods

| Method | Description |
|--------|-------------|
| `add(element)` | Adds the element if not already present; insertion order is maintained |
| `remove(element)` | Removes the specified element |
| `contains(element)` | Returns `true` if the element is in the set |
| `size()` | Returns the number of unique elements |
| `clear()` | Removes all elements |

```java
LinkedHashSet<String> cars = new LinkedHashSet<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

System.out.println(cars.contains("BMW")); // Prints: true
System.out.println(cars.size());          // Prints: 3

cars.remove("Ford");
System.out.println(cars);                 // Prints: [Volvo, BMW]

cars.clear();
System.out.println(cars.size());          // Prints: 0
```

## Looping Through a LinkedHashSet

Elements are always visited in insertion order:

```java
LinkedHashSet<String> cars = new LinkedHashSet<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

for (String car : cars) {
    System.out.println(car);
}
// Prints:
// Volvo
// BMW
// Ford
```

## HashSet vs LinkedHashSet

| | HashSet | LinkedHashSet |
|--|---------|---------------|
| Order | None | Insertion order preserved |
| Duplicates | Not allowed | Not allowed |
| Performance | O(1) average | O(1) average, slightly more memory |
| Best for | Fast membership checks, order irrelevant | Unique elements where insertion order matters |

Use `HashSet` when you only need uniqueness and want the fastest performance. Use `LinkedHashSet` when you also need to preserve the order elements were added.

## Declaring with the Set Interface

```java
import java.util.Set;
import java.util.LinkedHashSet;

Set<String> cars = new LinkedHashSet<>();
```

Declaring the variable as `Set` keeps the code flexible — the implementation can be swapped later without changing any code that uses `cars`. The trade-off is that any `LinkedHashSet`-specific behaviour is not accessible through a `Set` reference.

## The var Keyword

From Java 10, `var` lets you avoid writing the type twice:

```java
// Without var
LinkedHashSet<String> cars = new LinkedHashSet<String>();

// With var
var cars = new LinkedHashSet<String>();
```

Both are equivalent. `var` is a style choice — many developers still prefer the explicit type for readability.