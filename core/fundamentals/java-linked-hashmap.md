# Java LinkedHashMap

## Overview

`LinkedHashMap` is a `Map` implementation that stores key/value pairs and preserves the order in which they were added. It behaves like `HashMap` but with predictable insertion-order iteration.

```java
import java.util.LinkedHashMap;

LinkedHashMap<String, String> capitalCities = new LinkedHashMap<>();
capitalCities.put("England", "London");
capitalCities.put("India", "New Delhi");
capitalCities.put("Austria", "Wien");
capitalCities.put("Norway", "Oslo");
capitalCities.put("USA", "Washington DC");

System.out.println(capitalCities);
// Prints: {England=London, India=New Delhi, Austria=Wien, Norway=Oslo, USA=Washington DC}
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
LinkedHashMap<String, String> capitalCities = new LinkedHashMap<>();
capitalCities.put("England", "London");
capitalCities.put("Austria", "Wien");
capitalCities.put("Norway", "Oslo");

System.out.println(capitalCities.get("England"));       // Prints: London
System.out.println(capitalCities.containsKey("India")); // Prints: false
System.out.println(capitalCities.size());               // Prints: 3

capitalCities.remove("Austria");
System.out.println(capitalCities); // Prints: {England=London, Norway=Oslo}
```

## Looping Through a LinkedHashMap

Use `keySet()` for keys, `values()` for values, or combine them for both. Entries are always visited in insertion order.

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

## HashMap vs LinkedHashMap

| | HashMap | LinkedHashMap |
|--|---------|---------------|
| Order | None | Insertion order preserved |
| Duplicate keys | Not allowed | Not allowed |
| Null keys | One allowed | One allowed |
| Performance | O(1) average | O(1) average, slightly more memory |
| Best for | Fast lookups, order irrelevant | Fast lookups where insertion order matters |

Use `HashMap` when order does not matter and you want the lowest memory overhead. Use `LinkedHashMap` when you need the map to remember the order entries were added.

## Declaring with the Map Interface

```java
import java.util.Map;
import java.util.LinkedHashMap;

Map<String, String> capitalCities = new LinkedHashMap<>();
```

Declaring the variable as `Map` keeps the code flexible — the implementation can be swapped later without changing any code that uses `capitalCities`. The trade-off is that any `LinkedHashMap`-specific behaviour is not accessible through a `Map` reference.

## The var Keyword

From Java 10, `var` lets you avoid writing the type twice:

```java
// Without var
LinkedHashMap<String, String> capitalCities = new LinkedHashMap<String, String>();

// With var
var capitalCities = new LinkedHashMap<String, String>();
```

Both are equivalent. `var` is a style choice — many developers still prefer the explicit type for readability.