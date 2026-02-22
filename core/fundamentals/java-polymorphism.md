# Java Polymorphism

## Overview

Polymorphism means "many forms". It occurs when multiple classes are related through inheritance and each provides its own implementation of the same method. This allows a single action to behave differently depending on the object that performs it.

## Example

Consider a superclass `Animal` with a method `animalSound()`. Each subclass overrides that method with its own behaviour:

```java
class Animal {
    public void animalSound() {
        System.out.println("The animal makes a sound");
    }
}

class Pig extends Animal {
    public void animalSound() {
        System.out.println("The pig says: wee wee");
    }
}

class Dog extends Animal {
    public void animalSound() {
        System.out.println("The dog says: bow wow");
    }
}
```

Each subclass can be referenced as its superclass type. When `animalSound()` is called, Java runs the version defined in the actual object's class:

```java
class Main {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myPig = new Pig();
        Animal myDog = new Dog();

        myAnimal.animalSound(); // Prints: The animal makes a sound
        myPig.animalSound();    // Prints: The pig says: wee wee
        myDog.animalSound();    // Prints: The dog says: bow wow
    }
}
```

## How it Works

Polymorphism builds directly on inheritance. The `extends` keyword gives subclasses access to the superclass's attributes and methods. When a subclass defines a method with the same name and signature as one in the superclass, it **overrides** that method. At runtime, Java determines which version to call based on the actual type of the object, not the declared type of the variable.

## Why Use Polymorphism?

Polymorphism promotes code reusability. You can write code that operates on a superclass type and it will work correctly for any subclass — present or future — without modification. This makes it easier to extend a program without changing existing logic.