# HashMap vs HashSet

## Key Differences

| Feature | HashMap | HashSet |
|---------|---------|---------|
| Stores | Key-value pairs | Unique values only |
| Implements | Map interface | Set interface |
| Allows null | One null key, many null values | One null value |
| Use case | Lookups by key | Checking membership/uniqueness |
| Internal | Uses hash table | Built on HashMap |
| Iteration order | Undefined | Undefined |

## Performance

Both have average O(1) for common operations:
- `put()` / `add()` - O(1)
- `get()` / `contains()` - O(1)
- `remove()` - O(1)
- `size()` - O(1)

Worst case O(n) if many hash collisions occur.

## When to Use

**HashMap**
- Need to associate values with keys
- Quick lookups by key
- Counting frequencies: `map.put(key, map.getOrDefault(key, 0) + 1)`
- Storing configuration data
- Word to definition mapping
- Caching/memoization

**HashSet**
- Need to store unique values
- Check if element exists: `set.contains(element)`
- Remove duplicates from collection
- Track visited nodes in graph algorithms
- Implementing sets of allowed values
- Union, intersection, difference operations

## Example Usage

```java
// HashMap - for key-value associations
Map<String, Integer> map = new HashMap<>();
map.put("apple", 5);
map.put("banana", 3);
map.put("apple", 6);  // Updates previous value

System.out.println(map.get("apple"));      // 6
System.out.println(map.get("orange"));     // null
System.out.println(map.containsKey("apple")); // true

// HashSet - for unique values
Set<String> set = new HashSet<>();
set.add("apple");
set.add("banana");
set.add("apple");  // Second add is ignored

System.out.println(set.size());             // 2
System.out.println(set.contains("apple"));  // true
System.out.println(set.contains("orange")); // false
```

## Common HashMap Methods

```java
map.put(key, value);              // Add/update entry
map.get(key);                     // Retrieve (null if absent)
map.getOrDefault(key, default);   // Retrieve with fallback
map.remove(key);                  // Remove entry
map.containsKey(key);             // Check key existence
map.containsValue(value);         // Check value existence
map.keySet();                     // Get all keys
map.values();                     // Get all values
map.entrySet();                   // Get key-value pairs
map.putIfAbsent(key, value);      // Put only if key absent
map.size();                       // Get number of entries
map.clear();                      // Remove all entries
```

## Common HashSet Methods

```java
set.add(element);                 // Add element
set.remove(element);              // Remove element
set.contains(element);            // Check membership
set.size();                       // Get size
set.isEmpty();                    // Check if empty
set.clear();                      // Remove all elements
set.addAll(collection);           // Add multiple elements
set.removeAll(otherSet);          // Set difference
set.retainAll(otherSet);          // Set intersection
```

## Practical Examples

### Frequency Counter (HashMap)
```java
String text = "hello world";
Map<Character, Integer> freq = new HashMap<>();

for (char c : text.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}

System.out.println(freq); // {h=1, e=1, l=3, o=2, ...}
```

### Remove Duplicates (HashSet)
```java
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3);
Set<Integer> unique = new HashSet<>(numbers);
System.out.println(unique); // [1, 2, 3]
```

### Graph Visited Tracking (HashSet)
```java
Set<Integer> visited = new HashSet<>();

void dfs(int node) {
    if (visited.contains(node)) return;
    visited.add(node);
    // Process node...
}
```

### Word to Definition (HashMap)
```java
Map<String, String> dictionary = new HashMap<>();
dictionary.put("java", "A programming language");
dictionary.put("algorithm", "Step-by-step procedure");

String definition = dictionary.get("java");
```

## Memory Considerations

Both use hash tables internally with similar memory overhead.

**HashMap**
- Memory per entry: key + value + overhead
- Entry object stores both key and value

**HashSet**
- Memory per entry: value + overhead
- Generally less memory than HashMap for same number of elements

## Thread Safety

- Both are **not thread-safe**
- Use `Collections.synchronizedMap()` / `Collections.synchronizedSet()`
- Or use `ConcurrentHashMap` for better concurrent performance
- `Collections.synchronizedSet(new HashSet<>())` for thread-safe set

## Hash Code and Equals

For custom objects in HashMap/HashSet, override:
```java
@Override
public int hashCode() {
    return Objects.hash(field1, field2);
}

@Override
public boolean equals(Object obj) {
    if (!(obj instanceof MyClass)) return false;
    MyClass other = (MyClass) obj;
    return this.field1.equals(other.field1) && 
           this.field2.equals(other.field2);
}
```

## Variants

- **LinkedHashMap**: Maintains insertion order
- **LinkedHashSet**: Maintains insertion order
- **TreeMap**: Keys sorted, O(log n) operations
- **TreeSet**: Values sorted, O(log n) operations
- **Hashtable**: Legacy, synchronized (avoid in new code)