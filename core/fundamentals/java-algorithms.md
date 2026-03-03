# Java Algorithms

## Overview

Algorithms are procedures for solving problems — sorting, searching, and manipulating data. Java provides many common algorithms as static methods in the `Collections` class (`java.util`), so you rarely need to implement them from scratch.

---

## Sorting

`Collections.sort()` sorts a list in ascending natural order:

```java
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(5);
numbers.add(1);
numbers.add(7);
numbers.add(3);
numbers.add(9);

Collections.sort(numbers);
System.out.println(numbers); // Prints: [1, 3, 5, 7, 9]
```

To sort in descending order, pass `Collections.reverseOrder()` as a second argument:

```java
Collections.sort(numbers, Collections.reverseOrder());
System.out.println(numbers); // Prints: [9, 7, 5, 3, 1]
```

---

## Searching

`Collections.binarySearch()` finds the index of an element in a sorted list. The list **must be sorted** before calling this method:

```java
ArrayList<String> names = new ArrayList<>();
names.add("Liam");
names.add("Jenny");
names.add("Kasper");
names.add("Angie");

Collections.sort(names);
int index = Collections.binarySearch(names, "Angie");
System.out.println("Angie is at index: " + index); // Prints: Angie is at index: 0
```

Binary search runs in O(log n). If you call it on an unsorted list, the result is undefined.

---

## Iterating

Two common approaches for looping through a list:

```java
ArrayList<String> colors = new ArrayList<>();
colors.add("Red");
colors.add("Green");
colors.add("Blue");

// For-each loop
for (String c : colors) {
    System.out.println(c);
}

// Iterator
Iterator<String> it = colors.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

Both produce the same output. The `Iterator` approach is useful when you need to remove elements during iteration — calling `it.remove()` is safe, whereas modifying a list inside a for-each loop throws a `ConcurrentModificationException`.

---

## Other Useful Methods

| Method | Description |
|--------|-------------|
| `Collections.max(list)` | Returns the largest element |
| `Collections.min(list)` | Returns the smallest element |
| `Collections.shuffle(list)` | Randomly shuffles elements |
| `Collections.frequency(list, element)` | Returns how many times an element appears |
| `Collections.swap(list, i, j)` | Swaps two elements by index |

```java
ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 1, 7, 3, 9));

System.out.println(Collections.max(numbers));       // Prints: 9
System.out.println(Collections.min(numbers));       // Prints: 1
System.out.println(Collections.frequency(numbers, 7)); // Prints: 1

Collections.swap(numbers, 0, 4);
System.out.println(numbers); // Prints: [9, 1, 7, 3, 5]

Collections.shuffle(numbers);
System.out.println(numbers); // Prints: random order
```

---

## Complexity Reference

| Method | Time Complexity |
|--------|----------------|
| `Collections.sort()` | O(n log n) |
| `Collections.binarySearch()` | O(log n) — requires sorted list |
| `Collections.max()` / `min()` | O(n) |
| `Collections.frequency()` | O(n) |
| `Collections.swap()` | O(1) |
| `Collections.shuffle()` | O(n) |