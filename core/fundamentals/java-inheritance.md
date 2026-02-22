# Java Inheritance

## Overview

Inheritance allows a class to acquire the attributes and methods of another class. Java groups this into two categories:

- **Subclass (child)** - the class that inherits from another class
- **Superclass (parent)** - the class being inherited from

To inherit from a class, use the `extends` keyword.

## Syntax

```java
class Subclass extends Superclass {
    // additional attributes and methods
}
```

### Example

```java
class Vehicle {
    protected String brand = "Ford";

    public void honk() {
        System.out.println("Tuut, tuut!");
    }
}

class Car extends Vehicle {
    private String modelName = "Mustang";

    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.honk();
        System.out.println(myCar.brand + " " + myCar.modelName); // Prints: Ford Mustang
    }
}
```

`Car` inherits both the `brand` attribute and the `honk()` method from `Vehicle` without redefining them.

## Access Modifiers and Inheritance

The access modifier on a superclass attribute determines whether a subclass can access it.

| Modifier | Accessible in Subclass |
|----------|----------------------|
| `public` | Yes |
| `protected` | Yes |
| `private` | No |

In the example above, `brand` is declared `protected` so that `Car` can access it. If it were `private`, the subclass would have no access and the code would fail to compile.

## The final Keyword

To prevent a class from being inherited, declare it with the `final` keyword.

```java
final class Vehicle {
    ...
}

class Car extends Vehicle { // error: cannot inherit from final Vehicle
    ...
}
```

Attempting to extend a `final` class produces a compile-time error:

```
Main.java:9: error: cannot inherit from final Vehicle
class Car extends Vehicle {
                ^
1 error
```

## Why Use Inheritance?

Inheritance promotes code reusability. Instead of duplicating attributes and methods across multiple classes, you define them once in a superclass and let subclasses inherit them.