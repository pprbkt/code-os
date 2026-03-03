# Java TreeMap

## Overview

`TreeMap` is a `Map` implementation that stores key/value pairs sorted by key in natural ascending order. It behaves like `HashMap` but with predictable, sorted iteration.

```java
import java.util.TreeMap;

TreeMap<String, String> capitalCities = new TreeMap<>();
capitalCities.put("England", "London");
capitalCities.put("India", "New Delhi");
capitalCities.put("Austria", "Wien");
capitalCities.put("Norway", "Oslo");
capitalCities.put("USA", "Washington DC");

System.out.println(capitalCities);
// Prints: {Austria=Wien, England=London, India=New Delhi, Norway=Oslo, USA=Washington DC}
```

Duplicate keys are not allowed — adding the same key again replaces the existing value.

## Common Methods

| Method | Description |
|--------|-------------|
| `put(key, value)` | Adds or updates a key/value pair |
| `get(key)` | Returns the value associated with the key |
| `remove(key)` | Removes the key/value pair for the given key |
| `containsKey(key)` | Returns `true` if the key exists |
| `size()` | Returns the number of key/value pairs |
| `clear()` | Removes all entries |

```java
TreeMap<String, String> capitalCities = new TreeMap<>();
capitalCities.put("England", "London");
capitalCities.put("Austria", "Wien");
capitalCities.put("Norway", "Oslo");

System.out.println(capitalCities.get("England"));      // Prints: London
System.out.println(capitalCities.containsKey("India")); // Prints: false
System.out.println(capitalCities.size());               // Prints: 3

capitalCities.remove("Austria");
System.out.println(capitalCities);                      // Prints: {England=London, Norway=Oslo}
```

## Looping Through a TreeMap

Use `keySet()` for keys, `values()` for values, or combine them for both. Keys are always visited in sorted order.

```java
// Keys only
for (String key : capitalCities.keySet()) {
    System.out.println(key);
}

// Values only
for (String value : capitalCities.values()) {
    System.out.println(value);
}

// Keys and values
for (String key : capitalCities.keySet()) {
    System.out.println(key + " → " + capitalCities.get(key));
}
```

## HashMap vs TreeMap

| | HashMap | TreeMap |
|--|---------|---------|
| Order | None | Sorted by key (natural order) |
| Null keys | One allowed | Not allowed |
| Performance | O(1) average | O(log n) |
| Best for | Fast lookups, order irrelevant | Sorted key/value pairs |

Use `HashMap` when you only need fast lookups and order does not matter. Use `TreeMap` when you need keys kept in sorted order.

## Declaring with the Map Interface

```java
import java.util.Map;
import java.util.TreeMap;

Map<String, String> capitalCities = new TreeMap<>();
```

Declaring the variable as `Map` keeps the code flexible — the implementation can be swapped later without changing any code that uses `capitalCities`. The trade-off is that `TreeMap`-specific methods are not accessible through a `Map` reference.

## The var Keyword

From Java 10, `var` lets you avoid writing the type twice:

```java
// Without var
TreeMap<String, String> capitalCities = new TreeMap<String, String>();

// With var
var capitalCities = new TreeMap<String, String>();
```

Both are equivalent. `var` is a style choice — many developers still prefer the explicit type for readability.