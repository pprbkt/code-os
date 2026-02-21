# Java HashMap

## Overview

A `HashMap` stores items in key/value pairs, where each key maps to a specific value. It is part of the `java.util` package and implements the `Map` interface.

Unlike `ArrayList` which uses numeric indices, `HashMap` uses keys to retrieve values. This makes it ideal for scenarios where you need to look up values by a meaningful identifier rather than position.

## Key Characteristics

| Feature | Description |
|---------|-------------|
| Key-Value Pairs | Stores associated key-value entries |
| Unique Keys | Each key can map to only one value |
| No Guaranteed Order | Unordered collection |
| Null Keys/Values | Allows one null key and multiple null values |
| Performance | O(1) average for get, put, remove |
| Thread Safety | Not synchronized (use `Collections.synchronizedMap()`) |
| Implements | Map interface |

## When to Use HashMap

**Use HashMap when you need:**
- To associate values with meaningful keys
- Fast lookups by key (O(1))
- Storing configuration data
- Counting frequencies
- Caching/memoization
- Building indices

**Don't use HashMap when you need:**
- Sorted order (use `TreeMap`)
- Insertion order (use `LinkedHashMap`)
- Only unique values without keys (use `HashSet`)
- Ordered list access (use `ArrayList`)

## Getting Started

### Import

```java
import java.util.HashMap;
```

### Create a HashMap

```java
// Create a HashMap with String keys and String values
HashMap<String, String> capitalCities = new HashMap<>();

// With explicit types (Java 6 style)
HashMap<String, String> capitalCities = new HashMap<String, String>();

// Using the Map interface (recommended)
import java.util.Map;
Map<String, String> capitalCities = new HashMap<>();

// Using var keyword (Java 10+)
var capitalCities = new HashMap<String, String>();
```

## Core Operations

### Add Items (put)

```java
HashMap<String, String> capitalCities = new HashMap<>();

// Add key-value pairs
capitalCities.put("England", "London");
capitalCities.put("India", "New Delhi");
capitalCities.put("Austria", "Vienna");
capitalCities.put("Norway", "Oslo");

System.out.println(capitalCities);
// {Norway=Oslo, Austria=Vienna, India=New Delhi, England=London}
```

**Important:** If you add the same key twice, the new value overwrites the old one:

```java
capitalCities.put("England", "London");
capitalCities.put("England", "Manchester");  // Overwrites

capitalCities.get("England");  // Returns "Manchester"
```

### Access Items (get)

```java
// Get value by key
String capital = capitalCities.get("England");  // "London"

// Get with default value if key doesn't exist
String capital = capitalCities.getOrDefault("France", "Unknown");  // "Unknown"

// Check if key exists
if (capitalCities.containsKey("England")) {
    System.out.println("England is in the map");
}

// Check if value exists
if (capitalCities.containsValue("London")) {
    System.out.println("London is a capital");
}
```

### Remove Items

```java
// Remove specific key-value pair
capitalCities.remove("England");

// Remove if key matches condition
capitalCities.remove("Austria", "Vienna");  // Only removes if value matches

// Remove all items
capitalCities.clear();

System.out.println(capitalCities.isEmpty());  // true
```

### Get HashMap Size

```java
HashMap<String, String> map = new HashMap<>();
map.put("A", "1");
map.put("B", "2");
map.put("C", "3");

System.out.println(map.size());  // 3
```

**Note:** Size counts unique keys only. Adding a value to an existing key doesn't increase size.

## Iteration

### Iterate Over Keys

```java
HashMap<String, String> capitalCities = new HashMap<>();
capitalCities.put("England", "London");
capitalCities.put("India", "New Delhi");
capitalCities.put("Austria", "Vienna");

// Using keySet()
for (String key : capitalCities.keySet()) {
    System.out.println(key);
}
// Output: England, India, Austria (order not guaranteed)
```

### Iterate Over Values

```java
// Using values()
for (String value : capitalCities.values()) {
    System.out.println(value);
}
// Output: London, New Delhi, Vienna (order not guaranteed)
```

### Iterate Over Key-Value Pairs

```java
// Using entrySet() - Most efficient
for (Map.Entry<String, String> entry : capitalCities.entrySet()) {
    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
}

// Using keySet with get()
for (String key : capitalCities.keySet()) {
    System.out.println("Key: " + key + ", Value: " + capitalCities.get(key));
}
```

### Using Iterator

```java
import java.util.Iterator;

Iterator<Map.Entry<String, String>> iterator = capitalCities.entrySet().iterator();
while (iterator.hasNext()) {
    Map.Entry<String, String> entry = iterator.next();
    System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
}
```

### forEach Method (Java 8+)

```java
capitalCities.forEach((key, value) -> 
    System.out.println("Key: " + key + ", Value: " + value)
);
```

### Stream API (Java 8+)

```java
capitalCities.entrySet().stream()
    .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

// Filter and print
capitalCities.entrySet().stream()
    .filter(entry -> entry.getValue().startsWith("L"))
    .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
```

## Working with Different Types

### String Keys and String Values

```java
Map<String, String> config = new HashMap<>();
config.put("database", "postgres");
config.put("port", "5432");
config.put("host", "localhost");
```

### String Keys and Integer Values

```java
Map<String, Integer> ages = new HashMap<>();
ages.put("John", 32);
ages.put("Steve", 30);
ages.put("Angie", 33);

for (String name : ages.keySet()) {
    System.out.println(name + " is " + ages.get(name));
}
```

### Other Primitive Wrappers

Use wrapper classes for primitives:
- `Integer` for int
- `Double` for double
- `Boolean` for boolean
- `Long` for long
- `Character` for char
- `Float` for float

```java
// String keys, Double values (prices)
Map<String, Double> prices = new HashMap<>();
prices.put("Apple", 0.99);
prices.put("Banana", 0.59);
prices.put("Orange", 1.29);

// Integer keys, String values
Map<Integer, String> idToName = new HashMap<>();
idToName.put(1, "Alice");
idToName.put(2, "Bob");
idToName.put(3, "Charlie");
```

### Custom Objects as Keys

For custom objects as keys, override `hashCode()` and `equals()`:

```java
class Person {
    String id;
    String name;
    
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return this.id.equals(other.id);
    }
}

Map<Person, String> personRole = new HashMap<>();
personRole.put(new Person("1", "John"), "Manager");
personRole.put(new Person("2", "Jane"), "Developer");
```

### Nested HashMap

```java
// HashMap of HashMap - maps within maps
Map<String, Map<String, String>> userSettings = new HashMap<>();

Map<String, String> johnSettings = new HashMap<>();
johnSettings.put("theme", "dark");
johnSettings.put("language", "English");

userSettings.put("john", johnSettings);

System.out.println(userSettings.get("john").get("theme"));  // "dark"
```

## Real-World Examples

### Frequency Counter

```java
String text = "hello world";
Map<Character, Integer> freq = new HashMap<>();

for (char c : text.toCharArray()) {
    if (c != ' ') {
        freq.put(c, freq.getOrDefault(c, 0) + 1);
    }
}

System.out.println(freq);
// {h=1, e=1, l=3, o=2, w=1, r=1, d=1}
```

### Word Index

```java
String[] words = {"java", "is", "fun", "and", "java", "is", "powerful"};
Map<String, Integer> wordIndex = new HashMap<>();

for (int i = 0; i < words.length; i++) {
    if (!wordIndex.containsKey(words[i])) {
        wordIndex.put(words[i], i);
    }
}

System.out.println(wordIndex);
// {java=0, is=1, fun=2, and=3, powerful=6}
```

### Phone Book

```java
Map<String, String> phoneBook = new HashMap<>();
phoneBook.put("Alice", "555-1234");
phoneBook.put("Bob", "555-5678");
phoneBook.put("Charlie", "555-9012");

// Look up phone number
String number = phoneBook.getOrDefault("David", "Number not found");
System.out.println("Alice's number: " + phoneBook.get("Alice"));
```

### Configuration Management

```java
Map<String, Object> config = new HashMap<>();
config.put("port", 8080);
config.put("hostname", "localhost");
config.put("debug", true);
config.put("maxConnections", 100);

int port = (int) config.get("port");
String hostname = (String) config.get("hostname");
boolean debug = (boolean) config.get("debug");
```

### Cache/Memoization

```java
Map<Integer, Integer> cache = new HashMap<>();

int fibonacci(int n) {
    if (n <= 1) return n;
    
    // Check cache first
    if (cache.containsKey(n)) {
        return cache.get(n);
    }
    
    // Calculate and store in cache
    int result = fibonacci(n - 1) + fibonacci(n - 2);
    cache.put(n, result);
    
    return result;
}
```

### Grouping Elements

```java
List<String> words = Arrays.asList("apple", "apricot", "banana", "blueberry", "cherry");
Map<Character, List<String>> grouped = new HashMap<>();

for (String word : words) {
    char first = word.charAt(0);
    grouped.computeIfAbsent(first, k -> new ArrayList<>()).add(word);
}

System.out.println(grouped);
// {a=[apple, apricot], b=[banana, blueberry], c=[cherry]}
```

## Advanced Operations

### putIfAbsent

```java
Map<String, String> map = new HashMap<>();
map.put("key1", "value1");

// Only puts if key doesn't exist
map.putIfAbsent("key1", "newValue");  // Doesn't change anything
map.putIfAbsent("key2", "value2");    // Adds new entry

System.out.println(map.get("key1"));  // "value1"
System.out.println(map.get("key2"));  // "value2"
```

### getOrDefault

```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 85);

// Returns value if key exists, default otherwise
int aliceScore = scores.getOrDefault("Alice", 0);  // 85
int bobScore = scores.getOrDefault("Bob", 0);      // 0
```

### computeIfAbsent

```java
Map<String, List<Integer>> map = new HashMap<>();

// If key absent, compute and put value
map.computeIfAbsent("list1", k -> new ArrayList<>()).add(10);
map.computeIfAbsent("list1", k -> new ArrayList<>()).add(20);

System.out.println(map.get("list1"));  // [10, 20]
```

### computeIfPresent

```java
Map<String, Integer> counts = new HashMap<>();
counts.put("a", 5);

// Only compute if key exists
counts.computeIfPresent("a", (key, value) -> value + 1);
counts.computeIfPresent("b", (key, value) -> value + 1);  // Doesn't execute

System.out.println(counts.get("a"));  // 6
```

### merge

```java
Map<String, Integer> map1 = new HashMap<>();
Map<String, Integer> map2 = new HashMap<>();

map1.put("a", 1);
map1.put("b", 2);
map2.put("b", 3);
map2.put("c", 4);

// Merge map2 into map1
map2.forEach((key, value) ->
    map1.merge(key, value, Integer::sum)
);

System.out.println(map1);  // {a=1, b=5, c=4}
```

## Complete Reference

### Essential Methods

```java
// Add/Update
map.put(key, value);                    // Add or overwrite
map.putIfAbsent(key, value);           // Add only if absent
map.putAll(otherMap);                  // Add all from another map

// Get
map.get(key);                          // Get value (null if absent)
map.getOrDefault(key, defaultValue);   // Get with fallback

// Check
map.containsKey(key);                  // Check key existence
map.containsValue(value);              // Check value existence
map.isEmpty();                         // Check if empty

// Remove
map.remove(key);                       // Remove by key
map.remove(key, value);                // Remove if value matches
map.clear();                           // Remove all

// Query
map.size();                            // Get number of entries
map.keySet();                          // Get all keys
map.values();                          // Get all values
map.entrySet();                        // Get key-value pairs

// Compute
map.compute(key, (k,v) -> ...);       // Compute new value
map.computeIfAbsent(key, k -> ...);   // Compute if absent
map.computeIfPresent(key, (k,v) -> ...); // Compute if present
map.merge(key, value, (old, new) -> ...); // Merge values

// Iteration
map.forEach((key, value) -> ...);     // Java 8+
map.entrySet().stream();               // Stream operations
```

## Performance Characteristics

| Operation | Time Complexity |
|-----------|-----------------|
| get() | O(1) average, O(n) worst |
| put() | O(1) average, O(n) worst |
| remove() | O(1) average, O(n) worst |
| containsKey() | O(1) average, O(n) worst |
| size() | O(1) |
| clear() | O(n) |
| entrySet() | O(n) |
| keySet() | O(n) |
| values() | O(n) |

## Best Practices

### 1. Use the Map Interface

```java
// Good - flexible and follows best practices
Map<String, String> map = new HashMap<>();

// Also works but less flexible
HashMap<String, String> map = new HashMap<>();
```

### 2. Use Generics for Type Safety

```java
// Good - type-safe
Map<String, Integer> ages = new HashMap<>();

// Avoid - causes warnings
Map ages = new HashMap();
```

### 3. Use getOrDefault to Avoid Null Checks

```java
// Good
int count = map.getOrDefault("key", 0);

// Verbose
int count = map.containsKey("key") ? map.get("key") : 0;
```

### 4. Use entrySet() for Iteration

```java
// Good - most efficient
for (Map.Entry<String, String> entry : map.entrySet()) {
    String key = entry.getKey();
    String value = entry.getValue();
}

// Less efficient - two lookups per iteration
for (String key : map.keySet()) {
    String value = map.get(key);  // Extra lookup
}
```

### 5. Override hashCode() and equals() for Custom Keys

```java
@Override
public int hashCode() {
    return Objects.hash(field1, field2);
}

@Override
public boolean equals(Object obj) {
    if (!(obj instanceof MyClass)) return false;
    MyClass other = (MyClass) obj;
    return this.field1.equals(other.field1);
}
```

## Common Pitfalls

### Forgetting Null Checks

```java
// WRONG - NullPointerException possible
Map<String, String> map = new HashMap<>();
String value = map.get("key");
System.out.println(value.length());  // Null pointer!

// CORRECT
String value = map.getOrDefault("key", "");
if (value != null && !value.isEmpty()) {
    System.out.println(value.length());
}
```

### Modifying While Iterating

```java
// WRONG - throws ConcurrentModificationException
for (String key : map.keySet()) {
    if (key.equals("remove_me")) {
        map.remove(key);  // Don't do this!
    }
}

// CORRECT - use iterator
Iterator<String> it = map.keySet().iterator();
while (it.hasNext()) {
    String key = it.next();
    if (key.equals("remove_me")) {
        it.remove();
    }
}

// Also correct - use removeIf
map.keySet().removeIf(key -> key.equals("remove_me"));
```

### Not Overriding hashCode()

```java
// WRONG - custom objects won't work correctly as keys
class Person {
    String id;
}

Map<Person, String> map = new HashMap<>();
Person p1 = new Person("1");
map.put(p1, "John");
map.get(new Person("1"));  // null - different object!

// CORRECT - override both methods
class Person {
    String id;
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        return this.id.equals(((Person) obj).id);
    }
}
```

### Type Casting Issues

```java
// WRONG - ClassCastException possible
Map<String, Object> map = new HashMap<>();
map.put("age", 25);

String age = (String) map.get("age");  // ClassCastException!

// CORRECT - check type first
Object value = map.get("age");
if (value instanceof Integer) {
    int age = (int) value;
}
```

## HashMap vs Other Collections

| Collection | Keys | Values | Order | Performance |
|-----------|------|--------|-------|-------------|
| HashMap | Unique | Any | Unordered | O(1) |
| LinkedHashMap | Unique | Any | Insertion | O(1) |
| TreeMap | Unique | Any | Sorted | O(log n) |
| Hashtable | Unique | Any | Unordered | O(1) (legacy) |
| HashSet | N/A | Unique | Unordered | O(1) |
| ArrayList | Index | Any | Ordered | O(1) access |

## Null Handling

```java
Map<String, String> map = new HashMap<>();

// HashMap allows one null key
map.put(null, "nullKey");

// And multiple null values
map.put("key1", null);
map.put("key2", null);

System.out.println(map.get(null));      // "nullKey"
System.out.println(map.get("key1"));    // null
System.out.println(map.containsKey(null));   // true
```

## Thread Safety

`HashMap` is **not thread-safe**. For multi-threaded environments:

```java
// Create synchronized map
Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());

// Or use ConcurrentHashMap for better performance
import java.util.concurrent.ConcurrentHashMap;
Map<String, String> concurrentMap = new ConcurrentHashMap<>();
```

## Java Version Notes

### Java 7+ (Diamond Operator)
```java
Map<String, String> map = new HashMap<>();  // Type inferred
```

### Java 10+ (var Keyword)
```java
var map = new HashMap<String, String>();
```

### Java 8+ (forEach and Streams)
```java
map.forEach((key, value) -> System.out.println(key + " -> " + value));

// Using streams
map.entrySet().stream()
    .filter(e -> e.getValue() > 10)
    .forEach(e -> System.out.println(e.getKey()));
```

## Conclusion

`HashMap` is a fundamental data structure in Java for storing key-value pairs with fast O(1) average lookups. It's perfect for:

- Associating values with meaningful keys
- Quick membership checks
- Building indices
- Caching data
- Counting frequencies
- Grouping elements

For most use cases requiring key-value storage, `HashMap` is the go-to choice. If you need ordering, consider `LinkedHashMap` (insertion order) or `TreeMap` (sorted order).