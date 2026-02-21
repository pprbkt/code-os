# Java HashSet

## Overview

A `HashSet` is a collection of unique elements where duplicates are automatically ignored. It is part of the `java.util` package and implements the `Set` interface.

`HashSet` uses a hash table internally, providing fast average-case performance for common operations like add, remove, and contains checks. It does not maintain any specific order of elements.

## Key Characteristics

| Feature | Description |
|---------|-------------|
| Uniqueness | No duplicate elements allowed |
| Order | No guaranteed order (unordered) |
| Null values | Allows one null element |
| Performance | O(1) average for add, remove, contains |
| Thread Safety | Not synchronized (use `Collections.synchronizedSet()`) |
| Implements | Set interface |

## When to Use HashSet

**Use HashSet when you need:**
- To store unique elements
- Fast membership checks (`contains()`)
- To remove duplicates from a collection
- A set-based data structure (union, intersection, difference)
- Unordered collection

**Don't use HashSet when you need:**
- Specific ordering (use `TreeSet` or `LinkedHashSet`)
- Key-value pairs (use `HashMap` or `HashTable`)
- Sequential access (use `ArrayList`)

## Getting Started

### Import

```java
import java.util.HashSet;
```

### Create a HashSet

```java
// Create a HashSet to store Strings
HashSet<String> cars = new HashSet<String>();

// Modern syntax (Java 7+) with diamond operator
HashSet<String> cars = new HashSet<>();

// Using the Set interface (recommended)
import java.util.Set;
Set<String> cars = new HashSet<>();

// Using var keyword (Java 10+)
var cars = new HashSet<String>();
```

## Core Operations

### Add Elements

```java
HashSet<String> cars = new HashSet<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");
cars.add("Mazda");
cars.add("BMW");  // Duplicate - ignored

System.out.println(cars);  // [Volvo, BMW, Ford, Mazda] (order may vary)
System.out.println(cars.size());  // 4 (BMW counted once)
```

**Key Point:** When you add a duplicate element, the set ignores it. No error is thrown; it simply doesn't add the duplicate.

### Check If Element Exists

```java
Set<String> fruits = new HashSet<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Orange");

// Check existence
boolean hasApple = fruits.contains("Apple");     // true
boolean hasGrape = fruits.contains("Grape");     // false

if (fruits.contains("Banana")) {
    System.out.println("Banana is in the set");
}
```

**Performance:** O(1) average time - very fast!

### Remove Elements

```java
// Remove a specific element
fruits.remove("Apple");
System.out.println(fruits);  // [Banana, Orange]

// Remove all elements
fruits.clear();
System.out.println(fruits.size());  // 0
```

### Get Set Size

```java
Set<String> cars = new HashSet<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

System.out.println(cars.size());  // 3
```

**Note:** Size only counts unique elements. Duplicate attempts don't increase the size.

## Iteration

### Enhanced For Loop (For-Each)

```java
Set<String> cars = new HashSet<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

for (String car : cars) {
    System.out.println(car);
}
// Output order is not guaranteed
```

### Iterator

```java
import java.util.Iterator;

Iterator<String> it = cars.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

### forEach Method (Java 8+)

```java
cars.forEach(car -> System.out.println(car));

// With method reference
cars.forEach(System.out::println);
```

### Stream API (Java 8+)

```java
cars.stream()
    .forEach(System.out::println);

// Filter and print
cars.stream()
    .filter(car -> car.startsWith("B"))
    .forEach(System.out::println);
```

## Working with Different Types

### Strings

```java
Set<String> colors = new HashSet<>();
colors.add("Red");
colors.add("Green");
colors.add("Blue");
colors.add("Red");  // Duplicate ignored

System.out.println(colors.size());  // 3
```

### Integers

Use the wrapper class `Integer` instead of primitive `int`:

```java
Set<Integer> numbers = new HashSet<>();
numbers.add(4);
numbers.add(7);
numbers.add(8);
numbers.add(4);  // Duplicate ignored

for (int num : numbers) {
    System.out.println(num);
}
```

### Other Primitive Wrappers

- `Boolean` for boolean
- `Double` for double
- `Float` for float
- `Long` for long
- `Character` for char
- `Short` for short

```java
Set<Double> decimals = new HashSet<>();
decimals.add(3.14);
decimals.add(2.71);
decimals.add(3.14);  // Duplicate

System.out.println(decimals.size());  // 2
```

### Custom Objects

For custom objects to work properly in a `HashSet`, override `hashCode()` and `equals()`:

```java
class Person {
    String name;
    int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return this.name.equals(other.name) && this.age == other.age;
    }
}

Set<Person> people = new HashSet<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));
people.add(new Person("Alice", 30));  // Duplicate ignored

System.out.println(people.size());  // 2
```

## Real-World Examples

### Remove Duplicates from a List

```java
List<String> namesWithDuplicates = Arrays.asList(
    "Alice", "Bob", "Alice", "Charlie", "Bob", "David"
);

Set<String> uniqueNames = new HashSet<>(namesWithDuplicates);

System.out.println(uniqueNames);  // [Alice, Bob, Charlie, David]
System.out.println(uniqueNames.size());  // 4
```

### Check for Duplicate Elements

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 3, 6, 2, 7);
Set<Integer> seen = new HashSet<>();

for (int num : numbers) {
    if (seen.contains(num)) {
        System.out.println(num + " is a duplicate");
    } else {
        seen.add(num);
    }
}
// Output: 3 is a duplicate, 2 is a duplicate
```

### Find Common Elements (Intersection)

```java
Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
Set<String> set2 = new HashSet<>(Arrays.asList("C", "D", "E", "F"));

set1.retainAll(set2);  // Keep only common elements

System.out.println(set1);  // [C, D]
```

### Find All Unique Elements (Union)

```java
Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C"));
Set<String> set2 = new HashSet<>(Arrays.asList("C", "D", "E"));

set1.addAll(set2);  // Add all from set2

System.out.println(set1);  // [A, B, C, D, E]
System.out.println(set1.size());  // 5
```

### Find Unique Elements in First Set (Difference)

```java
Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
Set<String> set2 = new HashSet<>(Arrays.asList("C", "D", "E"));

set1.removeAll(set2);  // Remove all from set2

System.out.println(set1);  // [A, B]
```

### Track Visited Nodes in Graph

```java
Set<Integer> visited = new HashSet<>();
Queue<Integer> queue = new LinkedList<>();

void bfs(int start, List<List<Integer>> graph) {
    queue.add(start);
    visited.add(start);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.println(node);
        
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.add(neighbor);
            }
        }
    }
}
```

## Set Operations

### Union

```java
Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5));

Set<Integer> union = new HashSet<>(set1);
union.addAll(set2);

System.out.println(union);  // [1, 2, 3, 4, 5]
```

### Intersection

```java
Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6));

Set<Integer> intersection = new HashSet<>(set1);
intersection.retainAll(set2);

System.out.println(intersection);  // [3, 4]
```

### Difference

```java
Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6));

Set<Integer> difference = new HashSet<>(set1);
difference.removeAll(set2);

System.out.println(difference);  // [1, 2]
```

### Subset Check

```java
Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

boolean isSubset = set2.containsAll(set1);
System.out.println(isSubset);  // true
```

## Complete Reference

### Essential Methods

```java
// Add
set.add(element);              // Add element, returns true if successful

// Check
set.contains(element);         // Check if element exists
set.isEmpty();                 // Check if set is empty
set.size();                    // Get number of unique elements

// Remove
set.remove(element);           // Remove specific element
set.clear();                   // Remove all elements

// Set operations
set.addAll(collection);        // Union
set.retainAll(collection);     // Intersection
set.removeAll(collection);     // Difference

// Iteration
set.iterator();                // Get iterator
set.forEach(consumer);         // Java 8+ lambda

// Conversion
new ArrayList<>(set);          // Convert to list
new TreeSet<>(set);            // Convert to sorted set
```

## Performance Characteristics

| Operation | Time Complexity |
|-----------|-----------------|
| add() | O(1) average, O(n) worst |
| remove() | O(1) average, O(n) worst |
| contains() | O(1) average, O(n) worst |
| size() | O(1) |
| clear() | O(n) |
| addAll() | O(m + n) where m and n are sizes |
| retainAll() | O(n) |
| removeAll() | O(n) |

## Best Practices

### 1. Use the Set Interface

```java
// Good - flexible and follows best practices
Set<String> cars = new HashSet<>();

// Also works but less flexible
HashSet<String> cars = new HashSet<>();
```

### 2. Use Generics

```java
// Good - type-safe
Set<String> names = new HashSet<>();

// Avoid - causes warnings
Set names = new HashSet();
```

### 3. Check Before Operations

```java
// Good
if (!set.isEmpty()) {
    // Operate on set
}

// Safe removal
set.removeIf(item -> item.equals("remove_me"));
```

### 4. Override hashCode() and equals()

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

### 5. Use addAll() for Bulk Operations

```java
Set<Integer> set = new HashSet<>();

// Good - one operation
set.addAll(Arrays.asList(1, 2, 3, 4, 5));

// Avoid - multiple operations
set.add(1);
set.add(2);
set.add(3);
set.add(4);
set.add(5);
```

## Common Pitfalls

### Modifying While Iterating

```java
// WRONG - throws ConcurrentModificationException
for (String item : set) {
    if (item.equals("remove_me")) {
        set.remove(item);  // Don't do this!
    }
}

// CORRECT - use iterator
Iterator<String> it = set.iterator();
while (it.hasNext()) {
    if (it.next().equals("remove_me")) {
        it.remove();  // Safe
    }
}

// Also correct - use removeIf
set.removeIf(item -> item.equals("remove_me"));
```

### Forgetting Null Handling

```java
// HashSet allows one null, but be careful
Set<String> set = new HashSet<>();
set.add(null);
set.add("test");

for (String item : set) {
    System.out.println(item.length());  // NullPointerException!
}

// Better
for (String item : set) {
    if (item != null) {
        System.out.println(item.length());
    }
}
```

### Not Overriding hashCode()

```java
// WRONG - custom objects may not behave correctly
class Person {
    String name;
}

Set<Person> set = new HashSet<>();
set.add(new Person("Alice"));
set.add(new Person("Alice"));  // Duplicates allowed incorrectly!

// CORRECT - override both methods
class Person {
    String name;
    
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;
        return this.name.equals(((Person) obj).name);
    }
}
```

### Assuming Order

```java
// WRONG - don't assume any specific order
Set<Integer> set = new HashSet<>(Arrays.asList(3, 1, 2));
System.out.println(set.get(0));  // No get() method!
// Order is undefined anyway

// Use iteration instead
for (Integer num : set) {
    System.out.println(num);
}
```

## HashSet vs Other Collections

| Collection | Uniqueness | Order | Performance | Use Case |
|-----------|-----------|-------|-------------|----------|
| HashSet | Yes | No | O(1) | Fast uniqueness checks |
| LinkedHashSet | Yes | Insertion | O(1) | Unique elements in order |
| TreeSet | Yes | Sorted | O(log n) | Sorted unique elements |
| HashMap | Keys only | No | O(1) | Key-value pairs |
| ArrayList | No | Yes | O(1) access | Ordered collection |

## Null Handling

```java
Set<String> set = new HashSet<>();

// HashSet allows one null
set.add(null);
set.add("test");
System.out.println(set.size());  // 2

// Check for null
if (set.contains(null)) {
    System.out.println("Set contains null");
}

// Remove null
set.remove(null);
System.out.println(set.size());  // 1
```

## Thread Safety

`HashSet` is **not thread-safe**. For multi-threaded environments:

```java
// Create synchronized set
Set<String> syncSet = Collections.synchronizedSet(new HashSet<>());

// Or use ConcurrentHashMap's keySet()
import java.util.concurrent.ConcurrentHashMap;
Set<String> concurrentSet = ConcurrentHashMap.newKeySet();
```

## Java Version Notes

### Java 7+ (Diamond Operator)
```java
Set<String> cars = new HashSet<>();  // Type inferred
```

### Java 10+ (var Keyword)
```java
var cars = new HashSet<String>();
```

### Java 8+ (forEach and Streams)
```java
set.forEach(System.out::println);

// Using streams
set.stream()
    .filter(item -> item.startsWith("A"))
    .forEach(System.out::println);
```

## Conclusion

`HashSet` is an essential data structure for managing collections of unique elements. Its O(1) average performance for add, remove, and contains operations makes it ideal for:

- Removing duplicates
- Membership checks
- Set operations (union, intersection, difference)
- Tracking visited elements
- Implementing algorithms requiring unique element sets

For most use cases requiring unique elements and fast lookups, `HashSet` is the perfect choice. If you need ordering, consider `LinkedHashSet` or `TreeSet` instead.