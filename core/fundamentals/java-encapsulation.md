# Java Encapsulation

## Overview

Encapsulation is the practice of hiding sensitive data from direct access. It ensures class variables are only read or modified through controlled methods.

To achieve encapsulation in Java:

- Declare class variables as `private`
- Provide `public` getter and setter methods to access and update those variables

## Get and Set Methods

`private` variables cannot be accessed from outside their class. Getter and setter methods provide a controlled way to interact with them.

| Method | Purpose | Naming Convention |
|--------|---------|-------------------|
| **Getter** | Returns the value of a variable | `get` + VariableName |
| **Setter** | Updates the value of a variable | `set` + VariableName |

### Syntax

```java
public class Person {
    private String name;

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String newName) {
        this.name = newName;
    }
}
```

The `get` method returns the value of `name`. The `set` method takes a parameter and assigns it to `name`. The `this` keyword refers to the current object instance.

## Accessing Private Variables

Attempting to access a `private` variable directly from outside the class causes a compile-time error.

```java
public class Main {
    public static void main(String[] args) {
        Person myObj = new Person();
        myObj.name = "John";            // error: name has private access in Person
        System.out.println(myObj.name); // error: name has private access in Person
    }
}
```

Instead, use the getter and setter methods:

```java
public class Main {
    public static void main(String[] args) {
        Person myObj = new Person();
        myObj.setName("John");
        System.out.println(myObj.getName()); // Prints: John
    }
}
```

## Why Use Encapsulation?

| Benefit | Description |
|---------|-------------|
| **Data security** | Variables are protected from unintended external modification |
| **Controlled access** | You decide exactly how variables are read or written |
| **Read/write control** | Expose only a getter (read-only) or only a setter (write-only) as needed |
| **Flexibility** | Internal implementation can change without affecting other parts of the code |