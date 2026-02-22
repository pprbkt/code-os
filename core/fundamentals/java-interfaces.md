# Java Interfaces

## Overview

An interface is a completely abstract class used to group related methods with empty bodies. It is another way to achieve abstraction in Java — hiding implementation details and only exposing what a class must do, not how it does it.

To use an interface, another class must "implement" it using the `implements` keyword and provide a body for each of its methods.

## Syntax

```java
interface Animal {
    public void animalSound(); // no body
    public void sleep();       // no body
}
```

### Example

```java
interface Animal {
    public void animalSound();
    public void sleep();
}

class Pig implements Animal {
    public void animalSound() {
        System.out.println("The pig says: wee wee");
    }
    public void sleep() {
        System.out.println("Zzz");
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

## Interface Rules

| Rule | Description |
|------|-------------|
| **No objects** | An interface cannot be instantiated directly |
| **No method bodies** | Method bodies are provided by the implementing class |
| **Must override all methods** | The implementing class must define every method in the interface |
| **Methods** | Are `abstract` and `public` by default |
| **Attributes** | Are `public`, `static`, and `final` by default |
| **No constructor** | Interfaces cannot have constructors |

## Multiple Interfaces

Java does not support multiple inheritance through classes — a class can only extend one superclass. However, a class can implement multiple interfaces by separating them with a comma.

```java
interface FirstInterface {
    public void myMethod();
}

interface SecondInterface {
    public void myOtherMethod();
}

class DemoClass implements FirstInterface, SecondInterface {
    public void myMethod() {
        System.out.println("Some text..");
    }
    public void myOtherMethod() {
        System.out.println("Some other text...");
    }
}

class Main {
    public static void main(String[] args) {
        DemoClass myObj = new DemoClass();
        myObj.myMethod();      // Prints: Some text..
        myObj.myOtherMethod(); // Prints: Some other text...
    }
}
```

## Why Use Interfaces?

Interfaces are useful in two main situations. First, for security — they let you expose only what a class can do while keeping the implementation details hidden. Second, to work around Java's lack of multiple inheritance — since a class can only extend one superclass, implementing multiple interfaces is the standard way to inherit behaviour from more than one source.