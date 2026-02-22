# Java Enums

## Overview

An `enum` is a special class that represents a group of constants — unchangeable variables, similar to `final` variables. Use the `enum` keyword to declare one, list the constants separated by commas, and write them in uppercase.

```java
enum Level {
    LOW,
    MEDIUM,
    HIGH
}
```

Access enum constants with the dot syntax:

```java
Level myVar = Level.MEDIUM;
```

## Enum inside a Class

An `enum` can be declared inside a class:

```java
public class Main {
    enum Level {
        LOW,
        MEDIUM,
        HIGH
    }

    public static void main(String[] args) {
        Level myVar = Level.MEDIUM;
        System.out.println(myVar); // Prints: MEDIUM
    }
}
```

## Enum in a Switch Statement

Enums are commonly used in `switch` statements to check for corresponding values:

```java
enum Level {
    LOW,
    MEDIUM,
    HIGH
}

public class Main {
    public static void main(String[] args) {
        Level myVar = Level.MEDIUM;

        switch(myVar) {
            case LOW:
                System.out.println("Low level");
                break;
            case MEDIUM:
                System.out.println("Medium level");
                break;
            case HIGH:
                System.out.println("High level");
                break;
        }
    }
}

// Prints: Medium level
```

## Looping Through an Enum

The `values()` method returns an array of all constants in the enum, which can be used to iterate over them:

```java
for (Level myVar : Level.values()) {
    System.out.println(myVar);
}

// Prints:
// LOW
// MEDIUM
// HIGH
```

## Enum Constructor

An `enum` can have a constructor, just like a class. The constructor runs automatically when each constant is created — you cannot call it manually. It must be `private` (Java enforces this automatically if omitted).

This is useful for attaching additional data to each constant:

```java
enum Level {
    LOW("Low level"),
    MEDIUM("Medium level"),
    HIGH("High level");

    private String description;

    private Level(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

public class Main {
    public static void main(String[] args) {
        Level myVar = Level.MEDIUM;
        System.out.println(myVar.getDescription()); // Prints: Medium level
    }
}
```

You can combine `values()` with the getter to loop through all constants and their associated data:

```java
for (Level myVar : Level.values()) {
    System.out.println(myVar + ": " + myVar.getDescription());
}

// Prints:
// LOW: Low level
// MEDIUM: Medium level
// HIGH: High level
```

## Enums vs Classes

| Feature | Enum | Class |
|---------|------|-------|
| Can have attributes and methods | Yes | Yes |
| Can have a constructor | Yes | Yes |
| Constants are `public`, `static`, `final` | Yes | No |
| Can create objects | No | Yes |
| Can extend other classes | No | Yes |
| Can implement interfaces | Yes | Yes |

## Why Use Enums?

Use enums when you have a fixed set of values that will never change — for example, days of the week, months of the year, compass directions, or status levels. They are safer than plain constants because the type system prevents invalid values from being assigned.