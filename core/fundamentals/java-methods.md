# Java Methods

## Overview

A method is a block of code that only runs when it is called. Methods are used to perform certain actions and are also known as functions. You can pass data into methods using parameters, and methods can return values back to the caller.

## Table of Contents

- [What is a Method?](#what-is-a-method)
- [Creating Methods](#creating-methods)
- [Calling Methods](#calling-methods)
- [Method Parameters](#method-parameters)
- [Return Values](#return-values)
- [Method Overloading](#method-overloading)

## What is a Method?

Methods allow you to write reusable code that performs specific actions. They help organize code, reduce duplication, and make programs easier to maintain.

Java provides pre-defined methods like `System.out.println()`, but you can also create your own custom methods.

## Creating Methods

A method must be declared within a class. The basic syntax is:

```java
public class Main {
  static void myMethod() {
    // code to be executed
  }
}
```

### Method Components

- **`static`**: Indicates the method belongs to the class itself, not to an object of the class
- **`void`**: The return type; `void` means the method does not return a value
- **`myMethod()`**: The name of the method followed by parentheses
- **`{}`**: The method body containing the code to execute

### Example

```java
public class Main {
  static void myMethod() {
    System.out.println("This method performs an action!");
  }
}
```

## Calling Methods

To call a method, write the method name followed by parentheses `()` and a semicolon `;`

### Example

```java
public class Main {
  static void myMethod() {
    System.out.println("I just got executed!");
  }

  public static void main(String[] args) {
    myMethod(); // Calling the method
  }
}
```

**Output:**
```
I just got executed!
```

## Method Parameters

Parameters allow you to pass data into methods. Parameters act as variables inside the method.

### Single Parameter

Specify parameters inside the parentheses after the method name:

```java
public class Main {
  static void myMethod(String fname) {
    System.out.println(fname + " Refsnes");
  }

  public static void main(String[] args) {
    myMethod("Liam");
    myMethod("Jenny");
    myMethod("Anja");
  }
}
```

**Output:**
```
Liam Refsnes
Jenny Refsnes
Anja Refsnes
```

### Multiple Parameters

Add multiple parameters separated by commas:

```java
public class Main {
  static void myMethod(String fname, int age) {
    System.out.println(fname + " is " + age);
  }

  public static void main(String[] args) {
    myMethod("Liam", 5);
    myMethod("Jenny", 8);
    myMethod("Anja", 31);
  }
}
```

**Output:**
```
Liam is 5
Jenny is 8
Anja is 31
```

**Note:** When calling a method with multiple parameters, ensure the arguments match the parameter types and order.

## Return Values

The `return` keyword allows a method to return a value to the caller. Instead of using `void`, specify the return type (such as `int`, `String`, `double`, etc.).

### Simple Return Example

```java
public class Main {
  static int myMethod(int x) {
    return 5 + x;
  }

  public static void main(String[] args) {
    System.out.println(myMethod(3));
  }
}
```

**Output:**
```
8
```

### Storing the Return Value

It's recommended to store the return value in a variable for better readability and maintainability:

```java
public class Main {
  static int myMethod(int x, int y) {
    return x + y;
  }

  public static void main(String[] args) {
    int z = myMethod(5, 3);
    System.out.println(z);
  }
}
```

**Output:**
```
8
```

## Method Overloading

Method overloading allows multiple methods to have the same name, as long as they have different parameters. This is useful when you want to perform the same action with different data types.

### Without Overloading

```java
static int plusMethodInt(int x, int y) {
  return x + y;
}

static double plusMethodDouble(double x, double y) {
  return x + y;
}

public static void main(String[] args) {
  int myNum1 = plusMethodInt(8, 5);
  double myNum2 = plusMethodDouble(4.3, 6.26);
  System.out.println("int: " + myNum1);
  System.out.println("double: " + myNum2);
}
```

### With Overloading (Better Approach)

```java
static int plusMethod(int x, int y) {
  return x + y;
}

static double plusMethod(double x, double y) {
  return x + y;
}

public static void main(String[] args) {
  int myNum1 = plusMethod(8, 5);
  double myNum2 = plusMethod(4.3, 6.26);
  System.out.println("int: " + myNum1);
  System.out.println("double: " + myNum2);
}
```

**Output:**
```
int: 13
double: 10.56
```

### How Method Overloading Works

The Java compiler determines which method to call based on:
- The number of parameters
- The types of parameters
- The order of parameters

This makes the code cleaner and more intuitive.

## Method Summary

| Aspect | Description |
|--------|-------------|
| **Return Type** | `void` (no return) or a data type (`int`, `String`, etc.) |
| **Parameters** | Optional; can have zero, one, or multiple parameters |
| **Naming** | Method names should be descriptive and follow camelCase convention |
| **Overloading** | Same name allowed with different parameter signatures |
| **Calling** | Use method name followed by parentheses and arguments |
| **Return Keyword** | Used to send a value back to the caller (non-void methods only) |