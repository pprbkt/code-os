# Java ArrayList

## Overview

An `ArrayList` is a resizable array implementation in Java that provides dynamic storage for objects. Unlike built-in Java arrays, which have a fixed size, an `ArrayList` allows you to add and remove elements at runtime without creating a new array.

`ArrayList` is part of the `java.util` package and implements the `List` interface, making it one of the most commonly used data structures in Java.

## Key Differences: ArrayList vs Array

| Feature | Array | ArrayList |
|---------|-------|-----------|
| Size | Fixed, set at creation | Dynamic, resizable |
| Type Safety | Type-safe | Type-safe with Generics |
| Performance | Slightly faster | Minimal overhead |
| Memory | Efficient | Extra capacity for growth |
| Flexibility | Limited | Add/remove elements freely |

## Getting Started

### Import

To use `ArrayList`, import it from `java.util`:

```java
import java.util.ArrayList;
```

### Create an ArrayList

```java
// Create an ArrayList to store Strings
ArrayList<String> cars = new ArrayList<String>();

// Modern syntax (Java 7+) with diamond operator
ArrayList<String> cars = new ArrayList<>();

// Using the List interface (recommended)
import java.util.List;
List<String> cars = new ArrayList<>();

// Using var keyword (Java 10+)
var cars = new ArrayList<String>();
```

## Common Operations

### Add Elements

```java
// Add to the end of the list
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

// Add at a specific index (shifts other elements)
cars.add(0, "Mazda");  // Insert at the beginning
cars.add(1, "Tesla");  // Insert at index 1

System.out.println(cars);  // [Mazda, Tesla, Volvo, BMW, Ford]
```

**Performance Note:** Adding at index 0 is O(n) because all elements must shift. Adding at the end is O(1) amortized.

### Access Elements

```java
// Get element at index
String first = cars.get(0);  // "Mazda"
String second = cars.get(1); // "Tesla"

// Check if element exists
boolean hasVolvo = cars.contains("Volvo");  // true

// Find index of element
int index = cars.indexOf("BMW");  // 3
```

### Modify Elements

```java
// Change an element at a specific index
cars.set(0, "Opel");  // Replace "Mazda" with "Opel"

System.out.println(cars);  // [Opel, Tesla, Volvo, BMW, Ford]
```

### Remove Elements

```java
// Remove element at index
cars.remove(0);  // Removes "Opel"

// Remove specific element by value
cars.remove("Tesla");  // Removes "Tesla"

// Remove all elements
cars.clear();

// Check size
System.out.println(cars.size());  // 0
```

## Iteration

### Traditional For Loop

```java
ArrayList<String> cars = new ArrayList<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");

for (int i = 0; i < cars.size(); i++) {
    System.out.println(cars.get(i));
}
```

### Enhanced For Loop (For-Each)

```java
for (String car : cars) {
    System.out.println(car);
}
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
```

## Working with Different Types

### Strings

```java
ArrayList<String> fruits = new ArrayList<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Orange");
```

### Integers

Use the wrapper class `Integer` instead of primitive `int`:

```java
ArrayList<Integer> numbers = new ArrayList<Integer>();
numbers.add(10);
numbers.add(15);
numbers.add(20);
numbers.add(25);

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
ArrayList<Double> decimals = new ArrayList<>();
decimals.add(3.14);
decimals.add(2.71);
decimals.add(1.41);
```

### Custom Objects

```java
class Person {
    String name;
    int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

ArrayList<Person> people = new ArrayList<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));

for (Person p : people) {
    System.out.println(p.name + " is " + p.age);
}
```

## Sorting

### Sort Strings

Use `Collections.sort()` for alphabetical sorting:

```java
import java.util.Collections;

ArrayList<String> cars = new ArrayList<>();
cars.add("Volvo");
cars.add("BMW");
cars.add("Ford");
cars.add("Mazda");

Collections.sort(cars);

for (String car : cars) {
    System.out.println(car);
}
// Output: BMW, Ford, Mazda, Volvo
```

### Sort Numbers

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(33);
numbers.add(15);
numbers.add(20);
numbers.add(34);
numbers.add(8);
numbers.add(12);

Collections.sort(numbers);

for (int num : numbers) {
    System.out.println(num);
}
// Output: 8, 12, 15, 20, 33, 34
```

### Reverse Sort

```java
Collections.sort(numbers, Collections.reverseOrder());

for (int num : numbers) {
    System.out.println(num);
}
// Output: 34, 33, 20, 15, 12, 8
```

### Sort Custom Objects

```java
class Person implements Comparable<Person> {
    String name;
    int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
}

ArrayList<Person> people = new ArrayList<>();
people.add(new Person("Alice", 30));
people.add(new Person("Bob", 25));
people.add(new Person("Charlie", 35));

Collections.sort(people);  // Sorts by age
```

## Complete Reference

### Essential Methods

```java
// Add
list.add(element);           // Add at end
list.add(index, element);    // Add at index

// Access
list.get(index);             // Get element
list.contains(element);      // Check existence
list.indexOf(element);       // Find index

// Modify
list.set(index, element);    // Replace element

// Remove
list.remove(index);          // Remove by index
list.remove(element);        // Remove by value
list.clear();                // Remove all

// Query
list.size();                 // Get size
list.isEmpty();              // Check if empty

// Iteration
list.iterator();             // Get iterator
list.forEach(consumer);      // Java 8+

// Sort
Collections.sort(list);      // Sort ascending
Collections.sort(list, Collections.reverseOrder()); // Sort descending
```

## Performance Characteristics

| Operation | Time Complexity |
|-----------|-----------------|
| Add (end) | O(1) amortized |
| Add (middle) | O(n) |
| Get | O(1) |
| Remove (end) | O(1) |
| Remove (middle) | O(n) |
| Contains | O(n) |
| Sort | O(n log n) |

**Note:** ArrayList automatically increases its capacity when needed, typically by 50% (implementation-dependent).

## Best Practices

### 1. Use the List Interface

```java
// Good - flexible and follows best practices
List<String> cars = new ArrayList<>();

// Also works but less flexible
ArrayList<String> cars = new ArrayList<>();
```

### 2. Specify Type with Generics

```java
// Good - type-safe
ArrayList<String> names = new ArrayList<>();

// Avoid - causes warnings
ArrayList names = new ArrayList();
```

### 3. Use Enhanced For Loop When Possible

```java
// Good - cleaner and avoids index errors
for (String car : cars) {
    System.out.println(car);
}

// Acceptable but more verbose
for (int i = 0; i < cars.size(); i++) {
    System.out.println(cars.get(i));
}
```

### 4. Check Size Before Accessing

```java
// Good
if (!list.isEmpty()) {
    String first = list.get(0);
}

// Better
if (list.size() > 0) {
    String first = list.get(0);
}
```

### 5. Use contains() Instead of Manual Search

```java
// Good
if (list.contains("BMW")) {
    // Found
}

// Avoid
boolean found = false;
for (String car : list) {
    if (car.equals("BMW")) {
        found = true;
        break;
    }
}
```

## Common Pitfalls

### Modifying While Iterating

```java
// WRONG - causes ConcurrentModificationException
for (String car : cars) {
    if (car.equals("BMW")) {
        cars.remove(car);  // Don't do this!
    }
}

// CORRECT - use iterator
Iterator<String> it = cars.iterator();
while (it.hasNext()) {
    if (it.next().equals("BMW")) {
        it.remove();  // Safe to remove
    }
}
```

### Index Out of Bounds

```java
// WRONG
String car = cars.get(100);  // May throw IndexOutOfBoundsException

// CORRECT
if (index < cars.size()) {
    String car = cars.get(index);
}
```

### Null Elements

```java
// ArrayList allows null, but be careful
ArrayList<String> list = new ArrayList<>();
list.add(null);
list.add("Test");

for (String item : list) {
    System.out.println(item.length());  // NullPointerException on null!
}
```

## Real-World Examples

### Building a Shopping List

```java
List<String> shoppingList = new ArrayList<>();

// Add items
shoppingList.add("Milk");
shoppingList.add("Eggs");
shoppingList.add("Bread");

// Display
System.out.println("Shopping List: " + shoppingList);

// Mark as done
shoppingList.remove("Milk");

// Check size
System.out.println("Items remaining: " + shoppingList.size());
```

### Student Grade Tracking

```java
List<Double> grades = new ArrayList<>();
grades.add(85.5);
grades.add(92.0);
grades.add(78.5);
grades.add(95.0);

Collections.sort(grades);

System.out.println("Lowest: " + grades.get(0));
System.out.println("Highest: " + grades.get(grades.size() - 1));
```

### Filtering Elements

```java
List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
List<Integer> evenNumbers = new ArrayList<>();

for (Integer num : numbers) {
    if (num % 2 == 0) {
        evenNumbers.add(num);
    }
}

System.out.println(evenNumbers);  // [2, 4]
```

## When to Use ArrayList

- **✓** Need dynamic sizing
- **✓** Frequent random access by index
- **✓** Mostly adding/removing from the end
- **✓** Working with a collection of objects

## When to Use Alternatives

- **LinkedList**: Frequent insertions/deletions at the beginning
- **Array**: Fixed size, need maximum performance
- **HashSet**: Need unique elements, don't need ordering
- **HashMap**: Need key-value pairs
- **Queue/Deque**: Need FIFO or LIFO behavior

## Java Version Notes

### Java 7+ (Diamond Operator)
```java
ArrayList<String> cars = new ArrayList<>();  // Type inferred
```

### Java 10+ (var Keyword)
```java
var cars = new ArrayList<String>();
```

### Java 8+ (forEach and Streams)
```java
cars.forEach(System.out::println);

// Using streams
cars.stream()
    .filter(car -> car.startsWith("B"))
    .forEach(System.out::println);
```

## Conclusion

`ArrayList` is a fundamental data structure in Java that combines the convenience of dynamic arrays with the power of object-oriented programming. It's ideal for most collection needs where you don't have specific performance requirements that demand specialized data structures.

For most Java applications, `ArrayList` should be your go-to choice when you need an ordered, resizable collection.