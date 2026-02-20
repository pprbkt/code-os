# Java Loops

## Overview

Loops are used to execute a block of code repeatedly as long as a specified condition is true. Java provides several types of loops to handle different scenarios.

## Table of Contents

- [While Loop](#while-loop)
- [Do/While Loop](#dowhile-loop)
- [For Loop](#for-loop)
- [For-Each Loop](#for-each-loop)
- [Nested Loops](#nested-loops)
- [Loop Control Statements](#loop-control-statements)

## While Loop

The while loop repeats a block of code as long as the specified condition is true.

### Syntax

```java
while (condition) {
  // code block to be executed
}
```

### Example

```java
int i = 0;
while (i < 5) {
  System.out.println(i);
  i++;
}
```

**Output:** Prints 0, 1, 2, 3, 4

## Do/While Loop

The do/while loop executes the code block once before checking if the condition is true. Then it repeats the loop as long as the condition remains true.

### Syntax

```java
do {
  // code block to be executed
}
while (condition);
```

### Example

```java
int i = 0;
do {
  System.out.println(i);
  i++;
}
while (i < 5);
```

**Output:** Prints 0, 1, 2, 3, 4

**Note:** The do/while loop is useful when you need to execute the code block at least once, regardless of the condition.

## For Loop

Use the for loop when you know exactly how many times you want to loop through a block of code.

### Syntax

```java
for (statement 1; statement 2; statement 3) {
  // code block to be executed
}
```

Where:
- **Statement 1:** Initializes the loop variable (executed once)
- **Statement 2:** Defines the condition for the loop to run
- **Statement 3:** Increments/decrements the loop variable

### Example

```java
for (int i = 0; i < 5; i++) {
  System.out.println(i);
}
```

**Output:** Prints 0, 1, 2, 3, 4

## For-Each Loop

The for-each loop is used exclusively to loop through elements in an array or other data structures. It's simpler and more readable than traditional for loops when iterating over collections.

### Syntax

```java
for (type variableName : arrayName) {
  // code block to be executed
}
```

### Example

```java
int[] numbers = {10, 20, 30, 40};

for (int num : numbers) {
  System.out.println(num);
}
```

**Output:** Prints 10, 20, 30, 40

## Nested Loops

A loop can be placed inside another loop. This is called a nested loop. The inner loop executes completely for each iteration of the outer loop.

### Example

```java
// Outer loop
for (int i = 1; i <= 2; i++) {
  System.out.println("Outer: " + i); // Executes 2 times
  
  // Inner loop
  for (int j = 1; j <= 3; j++) {
    System.out.println(" Inner: " + j); // Executes 6 times (2 × 3)
  }
}
```

**Output:**
```
Outer: 1
 Inner: 1
 Inner: 2
 Inner: 3
Outer: 2
 Inner: 1
 Inner: 2
 Inner: 3
```

## Loop Control Statements

### Break Statement

The `break` statement jumps out of a loop immediately, terminating the loop even if the condition is still true.

#### Example

```java
for (int i = 0; i < 10; i++) {
  if (i == 4) {
    break;
  }
  System.out.println(i);
}
```

**Output:** Prints 0, 1, 2, 3 (stops when i equals 4)

### Continue Statement

The `continue` statement skips the current iteration of the loop and continues with the next iteration.

#### Example

```java
for (int i = 0; i < 10; i++) {
  if (i == 4) {
    continue;
  }
  System.out.println(i);
}
```

**Output:** Prints 0, 1, 2, 3, 5, 6, 7, 8, 9 (skips 4)

## Loop Comparison

| Loop Type | Best Used For | Condition Checked |
|-----------|---------------|-------------------|
| **While** | Unknown number of iterations | Before execution |
| **Do/While** | At least one execution needed | After execution |
| **For** | Known number of iterations | Before execution |
| **For-Each** | Iterating through collections | Each element |