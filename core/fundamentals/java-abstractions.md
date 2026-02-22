# Java Abstraction

## Overview

Abstraction is the process of hiding certain details and only exposing the essential information to the user. In Java, abstraction is achieved using the `abstract` keyword applied to classes and methods.

- **Abstract class** - a restricted class that cannot be instantiated. It must be inherited by another class to be used.
- **Abstract method** - a method with no body, declared inside an abstract class. The body is provided by the subclass.

An abstract class can contain both abstract methods and regular methods.

## Syntax

```java
abstract class Animal {
    public abstract void animalSound(); // no body
    public void sleep() {               // regular method
        System.out.println("Zzz");
    }
}
```

Attempting to instantiate an abstract class directly causes a compile-time error:

```java
Animal myObj = new Animal(); // error: Animal is abstract; cannot be instantiated
```

## Example

To use an abstract class, inherit from it with `extends` and provide a body for each abstract method.

```java
abstract class Animal {
    public abstract void animalSound();

    public void sleep() {
        System.out.println("Zzz");
    }
}

class Pig extends Animal {
    public void animalSound() {
        System.out.println("The pig says: wee wee");
    }
}

class Main {
    public static void main(String[] args) {
        Pig myPig = new Pig();
        myPig.animalSound(); // Prints: The pig says: wee wee
        myPig.sleep();       // Prints: Zzz
    }
}
```

## Abstract Classes vs Regular Classes

| Feature | Abstract Class | Regular Class |
|---------|---------------|---------------|
| Can be instantiated | No | Yes |
| Can have abstract methods | Yes | No |
| Can have regular methods | Yes | Yes |
| Must be inherited to use | Yes | No |

## Why Use Abstraction?

Abstraction is useful for security and design — it lets you enforce a contract on subclasses (every subclass must implement the abstract methods) while hiding implementation details from the outside. If multiple classes share common behaviour but differ in specifics, an abstract class lets you define the shared structure in one place.

**Note:** Abstraction can also be achieved with interfaces, which allow a class to implement multiple abstract contracts simultaneously. See the [Java Interfaces](../interfaces/README.md) chapter for details.