# Java Variables

## Overview

Variables are containers for storing data values. In Java, variables have specific types that determine what kind of data they can hold.

## Data Types

Java supports several fundamental data types:

| Type | Purpose | Examples |
|------|---------|----------|
| **String** | Store text | `"hello"`, `"hi"` |
| **int** | Store integers | `123`, `-123`, `49946` |
| **float** | Store floating point numbers | `-99.69`, `45.90`, `0.36` |
| **char** | Store single characters | `'a'`, `'x'`, `'@'` |
| **boolean** | Store true/false values | `true`, `false` |

## Variable Declaration

### Syntax

```java
type variableName = value;
```

### Example

```java
int myNum = 69;
```

## Constants

Constants are variables that, once declared, cannot be changed. Use the `final` keyword to declare a constant.

```java
final int myNum = 36;
myNum = 44; // This will generate an error
```

## Printing Variables

To combine text with variables or multiple variables together, use the `+` operator.

### Examples

**String Concatenation:**
```java
String firstName = "John ";
String lastName = "Doe";
String fullName = firstName + lastName;
System.out.println(fullName); // Prints: John Doe
```

**Arithmetic with Variables:**
```java
int x = 5;
int y = 6;

System.out.println("The sum is " + x + y);   // Prints: The sum is 56
System.out.println("The sum is " + (x + y)); // Prints: The sum is 11
```

**Important Note:** When concatenating with `+`, the operation is performed left to right. In the first example above, `x` and `y` are treated as strings and concatenated rather than added. In the second example, parentheses force the arithmetic operation first, then the result is converted to a string for concatenation.