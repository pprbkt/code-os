# Java Modifiers Guide

## Overview

Modifiers are keywords used to set the access level and functionality for classes, attributes, methods, and constructors. They control who can access and use your code.

The `public` keyword that appears frequently in Java code is an access modifier. Java divides modifiers into two main categories:

- **Access Modifiers**: Control the access level for code
- **Non-Access Modifiers**: Provide additional functionality without controlling access

## Table of Contents

- [Access Modifiers for Classes](#access-modifiers-for-classes)
- [Access Modifiers for Attributes, Methods, and Constructors](#access-modifiers-for-attributes-methods-and-constructors)
- [Public vs. Private](#public-vs-private)
- [Protected and Default](#protected-and-default)
- [Non-Access Modifiers](#non-access-modifiers)
- [Modifier Summary](#modifier-summary)

## Access Modifiers for Classes

For classes, you can use either `public` or `default` (no modifier specified):

| Modifier | Description | Accessibility |
|----------|-------------|----------------|
| **`public`** | The class is accessible by any other class | Anywhere |
| **default** | The class is only accessible by classes in the same package | Same package only |

### Example: Public Class

```java
public class Main {
  public static void main(String[] args) {
    System.out.println("This class can be accessed from anywhere");
  }
}
```

### Example: Default Class

```java
class MyClass {
  public static void main(String[] args) {
    System.out.println("This class can only be accessed in the same package");
  }
}
```

**Note:** When you don't specify an access modifier, the class defaults to package-private (default access).

## Access Modifiers for Attributes, Methods, and Constructors

For attributes, methods, and constructors, you can use one of four access modifiers:

| Modifier | Description | Same Class | Same Package | Subclasses | Other Packages |
|----------|-------------|:----------:|:------------:|:----------:|:-------------:|
| **`public`** | Accessible everywhere | ✓ | ✓ | ✓ | ✓ |
| **`protected`** | Accessible in same package and subclasses | ✓ | ✓ | ✓ | ✗ |
| **default** | Accessible in same package only | ✓ | ✓ | ✗ | ✗ |
| **`private`** | Accessible only within the class | ✓ | ✗ | ✗ | ✗ |

### Public Access

```java
public class Main {
  public int x = 5;
  
  public void myMethod() {
    System.out.println("This can be accessed from anywhere");
  }
}
```

The `public` attribute and method can be accessed from any other class.

### Private Access

```java
public class Main {
  private int x = 5;
  
  private void myMethod() {
    System.out.println("This can only be accessed within this class");
  }
}
```

The `private` attribute and method can only be used inside the `Main` class.

### Default Access (Package-Private)

```java
class MyClass {
  int x = 5;  // No modifier = default access
  
  void myMethod() {
    System.out.println("This can be accessed in the same package");
  }
}
```

When no modifier is specified, the default access level is package-private.

### Protected Access

```java
public class Parent {
  protected int x = 5;
  
  protected void myMethod() {
    System.out.println("Accessible in subclasses");
  }
}

public class Child extends Parent {
  public static void main(String[] args) {
    Parent p = new Parent();
    System.out.println(p.x);  // ✓ Accessible in subclass
    p.myMethod();             // ✓ Accessible in subclass
  }
}
```

Protected members are accessible in the same package and in subclasses.

## Public vs. Private

### Real-World Analogy

Think of access modifiers like this:

- **`public`**: A public park — everyone can enter and use it
- **`private`**: Your house key — only you can use it
- **`protected`**: Your family's property — family members and some guests can access it
- **default**: Your neighborhood — only residents of the neighborhood can access it

### Practical Example

```java
class Person {
  public String name = "John";     // Public - accessible everywhere
  private int age = 30;            // Private - only accessible inside this class
  protected String email = "john@example.com";  // Protected - accessible in subclasses
  String phone = "555-1234";       // Default - accessible in same package
}

public class Main {
  public static void main(String[] args) {
    Person p = new Person();
    
    System.out.println(p.name);    // ✓ Works - public attribute
    System.out.println(p.email);   // ✓ Works - protected attribute (same package)
    System.out.println(p.phone);   // ✓ Works - default attribute (same package)
    System.out.println(p.age);     // ✗ Error: age has private access in Person
  }
}
```

**Output:**
```
John
john@example.com
555-1234
```

### Why Use Private?

Using `private` for sensitive data provides encapsulation and data protection:

```java
class BankAccount {
  private double balance = 1000.0;  // Keep balance private
  
  // Public method to safely access balance
  public double getBalance() {
    return balance;
  }
  
  // Public method to safely modify balance
  public void withdraw(double amount) {
    if (amount <= balance) {
      balance -= amount;
    } else {
      System.out.println("Insufficient funds");
    }
  }
}

public class Main {
  public static void main(String[] args) {
    BankAccount account = new BankAccount();
    System.out.println(account.getBalance());  // ✓ Allowed
    account.withdraw(200);                     // ✓ Allowed
    // account.balance = -500;                 // ✗ Error - cannot access private field
  }
}
```

**Output:**
```
1000.0
```

## Protected and Default

### Protected Access Example

Protected members are accessible in subclasses:

```java
public class Animal {
  protected String name = "Animal";
  
  protected void eat() {
    System.out.println(name + " is eating");
  }
}

public class Dog extends Animal {
  public static void main(String[] args) {
    Dog dog = new Dog();
    System.out.println(dog.name);  // ✓ Accessible - protected in subclass
    dog.eat();                     // ✓ Accessible - protected in subclass
  }
}
```

**Output:**
```
Animal
Animal is eating
```

### Default Access Example

Default access is package-private. Members are accessible in the same package but not from other packages:

**File: com/example/ClassA.java**
```java
package com.example;

class ClassA {
  int value = 42;  // Default access
}
```

**File: com/example/ClassB.java**
```java
package com.example;

public class ClassB {
  public static void main(String[] args) {
    ClassA a = new ClassA();
    System.out.println(a.value);  // ✓ Allowed - same package
  }
}
```

**File: com/other/ClassC.java**
```java
package com.other;
import com.example.ClassA;

public class ClassC {
  public static void main(String[] args) {
    ClassA a = new ClassA();
    System.out.println(a.value);  // ✗ Error - different package, default access
  }
}
```

## Non-Access Modifiers

Non-access modifiers provide additional functionality without controlling access levels:

| Modifier | Used For | Description |
|----------|----------|-------------|
| **`static`** | Classes, Attributes, Methods | Belongs to the class, not to instances |
| **`final`** | Classes, Attributes, Methods | Cannot be modified, extended, or overridden |
| **`abstract`** | Classes, Methods | Must be implemented by subclasses |
| **`synchronized`** | Methods | Used for thread safety |
| **`volatile`** | Attributes | Value is not cached; always read from memory |

### Static Modifier

```java
public class Counter {
  static int count = 0;  // Shared by all instances
  
  static void increment() {
    count++;
  }
}

public class Main {
  public static void main(String[] args) {
    Counter.increment();   // Call without creating object
    System.out.println(Counter.count);  // Prints: 1
  }
}
```

### Final Modifier

```java
public class Config {
  final String API_KEY = "secret123";  // Cannot be changed
  
  final void criticalMethod() {
    System.out.println("This method cannot be overridden");
  }
}

public final class ImmutableClass {
  // This class cannot be extended
}
```

### Abstract Modifier

```java
public abstract class Animal {
  abstract void makeSound();  // Must be implemented by subclasses
}

public class Dog extends Animal {
  @Override
  void makeSound() {
    System.out.println("Woof!");
  }
}
```

## Modifier Summary

### For Classes

```
public class MyClass { }      // ✓ Public class
class MyClass { }             // ✓ Default class
final class MyClass { }       // ✓ Final class (cannot be extended)
abstract class MyClass { }    // ✓ Abstract class (cannot be instantiated)
```

### For Attributes and Methods

```
public int x;                 // Accessible everywhere
private int x;                // Accessible only in this class
protected int x;              // Accessible in same package and subclasses
int x;                        // Default - accessible in same package

public static void method();  // Static method - called on class
public final void method();   // Final method - cannot be overridden
public abstract void method();// Abstract method - must be implemented
```

## Best Practices

1. **Use `private` by default**: Start with private and only increase visibility when needed
2. **Encapsulation**: Keep data private and provide public getter/setter methods
3. **Use `final` for constants**: Makes intent clear and prevents accidental modification
4. **Document access levels**: Comments explaining why certain access levels were chosen
5. **Use `public` sparingly**: Only expose what external code actually needs to use

### Example: Following Best Practices

```java
public class Student {
  private String name;
  private int age;
  private double gpa;
  
  // Public getter for name
  public String getName() {
    return name;
  }
  
  // Public setter for name
  public void setName(String name) {
    if (name != null && !name.isEmpty()) {
      this.name = name;
    }
  }
  
  // Public getter for GPA
  public double getGpa() {
    return gpa;
  }
  
  // GPA should only be modified through this validated method
  public void setGpa(double gpa) {
    if (gpa >= 0.0 && gpa <= 4.0) {
      this.gpa = gpa;
    } else {
      System.out.println("Invalid GPA");
    }
  }
}
```

This approach provides control and validation over data changes while keeping the implementation details hidden.