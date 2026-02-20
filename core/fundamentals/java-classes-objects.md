# Java Classes and Objects

## Overview

Everything in Java is associated with classes and objects, along with their attributes and methods. A class is like a blueprint for creating objects. In real life, a car is an object with attributes (such as weight and color) and methods (such as drive and brake).

## Table of Contents

- [Classes and Objects Concept](#classes-and-objects-concept)
- [Creating Classes](#creating-classes)
- [Creating Objects](#creating-objects)
- [Multiple Objects](#multiple-objects)
- [Multiple Classes](#multiple-classes)
- [Class Attributes](#class-attributes)
- [Accessing Attributes](#accessing-attributes)
- [Modifying Attributes](#modifying-attributes)
- [Class Methods](#class-methods)
- [Accessing Methods with Objects](#accessing-methods-with-objects)

## Classes and Objects Concept

A **class** is a blueprint or template for creating objects. It defines what attributes (variables) and methods (functions) an object will have.

An **object** is an instance of a class. You create objects from classes using the `new` keyword.

Think of it this way:
- **Class**: A blueprint for a car
- **Object**: An actual car built from that blueprint

## Creating Classes

To create a class, use the `class` keyword:

```java
public class Main {
  int x = 5;
}
```

The class `Main` has one attribute: `x` with an initial value of 5.

## Creating Objects

To create an object from a class, use the `new` keyword and specify the class name:

```java
public class Main {
  int x = 5;

  public static void main(String[] args) {
    Main myObj = new Main();  // Create an object
    System.out.println(myObj.x);
  }
}
```

**Output:**
```
5
```

### How It Works

- `Main myObj = new Main();` creates a new object called `myObj` from the `Main` class
- `myObj.x` accesses the attribute `x` from the object using dot notation (`.`)

## Multiple Objects

You can create multiple objects from the same class. Each object is independent:

```java
public class Main {
  int x = 5;

  public static void main(String[] args) {
    Main myObj1 = new Main();  // Object 1
    Main myObj2 = new Main();  // Object 2
    System.out.println(myObj1.x);
    System.out.println(myObj2.x);
  }
}
```

**Output:**
```
5
5
```

Each object has its own copy of the attributes defined in the class.

## Multiple Classes

You can organize your code by creating multiple classes in separate files. This is useful for better code organization: one class holds the attributes and methods, while another holds the `main()` method.

### File Structure

**Main.java**
```java
public class Main {
  int x = 5;
}
```

**Second.java**
```java
class Second {
  public static void main(String[] args) {
    Main myObj = new Main();
    System.out.println(myObj.x);
  }
}
```

### Compilation and Execution

```bash
javac Main.java
javac Second.java
java Second
```

**Output:**
```
5
```

**Note:** The file name must match the public class name. In this example, `Main.java` contains the `Main` class, and `Second.java` contains the `Second` class.

## Class Attributes

Variables declared inside a class are called **attributes** (also known as fields). Attributes belong to the class and are part of every object created from that class.

### Example

```java
public class Main {
  int x = 5;
  int y = 3;
  String name = "John";
}
```

You can declare as many attributes as you need with different data types.

## Accessing Attributes

Access attributes using dot notation (`.`) on an object:

```java
public class Main {
  int x = 5;

  public static void main(String[] args) {
    Main myObj = new Main();
    System.out.println(myObj.x);  // Print the value of x
  }
}
```

**Output:**
```
5
```

### Multiple Attributes Example

```java
public class Main {
  String fname = "John";
  String lname = "Doe";
  int age = 24;

  public static void main(String[] args) {
    Main myObj = new Main();
    System.out.println("Name: " + myObj.fname + " " + myObj.lname);
    System.out.println("Age: " + myObj.age);
  }
}
```

**Output:**
```
Name: John Doe
Age: 24
```

## Modifying Attributes

You can modify attribute values after creating an object:

### Set a New Value

```java
public class Main {
  int x;

  public static void main(String[] args) {
    Main myObj = new Main();
    myObj.x = 40;
    System.out.println(myObj.x);
  }
}
```

**Output:**
```
40
```

### Override Existing Values

```java
public class Main {
  int x = 10;

  public static void main(String[] args) {
    Main myObj = new Main();
    myObj.x = 25;  // Override the initial value
    System.out.println(myObj.x);
  }
}
```

**Output:**
```
25
```

### Making Attributes Immutable with `final`

To prevent modification, use the `final` keyword:

```java
public class Main {
  final int x = 10;

  public static void main(String[] args) {
    Main myObj = new Main();
    myObj.x = 25;  // ❌ Error: cannot assign a value to a final variable
    System.out.println(myObj.x);
  }
}
```

### Independent Object Attributes

When you modify an attribute in one object, it doesn't affect other objects:

```java
public class Main {
  int x = 5;

  public static void main(String[] args) {
    Main myObj1 = new Main();  // Object 1
    Main myObj2 = new Main();  // Object 2
    myObj2.x = 25;
    System.out.println(myObj1.x);  // Outputs 5 (unchanged)
    System.out.println(myObj2.x);  // Outputs 25 (modified)
  }
}
```

**Output:**
```
5
25
```

## Class Methods

Methods are blocks of code that perform certain actions. Methods in a class are declared inside that class:

### Creating a Method

```java
public class Main {
  static void myMethod() {
    System.out.println("Hello World!");
  }
}
```

### Calling a Method

```java
public class Main {
  static void myMethod() {
    System.out.println("Hello World!");
  }

  public static void main(String[] args) {
    myMethod();  // Call the method
  }
}
```

**Output:**
```
Hello World!
```

## Accessing Methods with Objects

You can call methods on objects using dot notation. Methods can have parameters and return values:

### Example

```java
public class Main {
  
  // Method without parameters
  public void fullThrottle() {
    System.out.println("The car is going as fast as it can!");
  }

  // Method with a parameter
  public void speed(int maxSpeed) {
    System.out.println("Max speed is: " + maxSpeed);
  }

  public static void main(String[] args) {
    Main myCar = new Main();      // Create an object
    myCar.fullThrottle();         // Call method without parameters
    myCar.speed(200);             // Call method with parameter
  }
}
```

**Output:**
```
The car is going as fast as it can!
Max speed is: 200
```

### Example Explained

1. We created a `Main` class with two methods: `fullThrottle()` and `speed()`
2. `fullThrottle()` has no parameters and prints a message
3. `speed()` accepts an `int` parameter called `maxSpeed`
4. We created an object called `myCar` from the `Main` class using the `new` keyword
5. We called both methods on the `myCar` object using dot notation
6. The `speed()` method was called with an argument of `200`, which is passed to the `maxSpeed` parameter

### Key Points

- Use `public` instead of `static` to access methods through objects
- Methods can have zero, one, or multiple parameters
- Call methods on objects using the syntax: `objectName.methodName(arguments)`
- Parameters are passed inside the parentheses

## Summary: Classes vs Objects vs Attributes vs Methods

| Concept | Description |
|---------|-------------|
| **Class** | Blueprint for creating objects |
| **Object** | Instance of a class; created with `new` keyword |
| **Attribute** | Variable inside a class; belongs to objects |
| **Method** | Function inside a class; performs actions |
| **`new` Keyword** | Creates a new object from a class |
| **Dot Notation (`.`)** | Accesses attributes and methods of an object |
| **`static`** | Method/attribute belongs to the class, not objects |
| **`public`** | Accessible from outside the class |
| **`final`** | Makes an attribute immutable (cannot be changed) |