# Java Operators

## Overview

Operators perform operations on variables and values. Java groups them into five categories:

- Arithmetic
- Assignment
- Comparison
- Logical
- Bitwise

---

## Arithmetic Operators

Used to perform standard mathematical operations.

| Operator | Name | Description | Example |
|----------|------|-------------|---------|
| `+` | Addition | Adds two values | `x + y` |
| `-` | Subtraction | Subtracts one value from another | `x - y` |
| `*` | Multiplication | Multiplies two values | `x * y` |
| `/` | Division | Divides one value by another | `x / y` |
| `%` | Modulus | Returns the division remainder | `x % y` |
| `++` | Increment | Increases a variable by 1 | `++x` |
| `--` | Decrement | Decreases a variable by 1 | `--x` |

```java
int x = 10;
int y = 3;

System.out.println(x + y); // Prints: 13
System.out.println(x - y); // Prints: 7
System.out.println(x * y); // Prints: 30
System.out.println(x / y); // Prints: 3
System.out.println(x % y); // Prints: 1
```

**Note:** Dividing two integers produces an integer — decimals are truncated. Use `double` values for decimal results:

```java
System.out.println(10 / 3);       // Prints: 3
System.out.println(10.0 / 3.0);   // Prints: 3.3333333333333335
```

### Increment and Decrement

`++` and `--` are shorthand for adding or subtracting 1. They are commonly used with counters and loops:

```java
int peopleInRoom = 0;

peopleInRoom++; // someone enters
peopleInRoom++; // someone enters
peopleInRoom++; // someone enters
System.out.println(peopleInRoom); // Prints: 3

peopleInRoom--; // someone leaves
System.out.println(peopleInRoom); // Prints: 2
```

---

## Assignment Operators

Used to assign values to variables. Most are shorthand for combining an arithmetic operation with assignment.

| Operator | Example | Same As |
|----------|---------|---------|
| `=` | `x = 5` | `x = 5` |
| `+=` | `x += 3` | `x = x + 3` |
| `-=` | `x -= 3` | `x = x - 3` |
| `*=` | `x *= 3` | `x = x * 3` |
| `/=` | `x /= 3` | `x = x / 3` |
| `%=` | `x %= 3` | `x = x % 3` |
| `&=` | `x &= 3` | `x = x & 3` |
| `\|=` | `x \|= 3` | `x = x \| 3` |
| `^=` | `x ^= 3` | `x = x ^ 3` |
| `>>=` | `x >>= 3` | `x = x >> 3` |
| `<<=` | `x <<= 3` | `x = x << 3` |

```java
int savings = 100;
savings += 50; // same as: savings = savings + 50
System.out.println(savings); // Prints: 150
```

---

## Comparison Operators

Compare two values and return `true` or `false`.

| Operator | Name | Example |
|----------|------|---------|
| `==` | Equal to | `x == y` |
| `!=` | Not equal | `x != y` |
| `>` | Greater than | `x > y` |
| `<` | Less than | `x < y` |
| `>=` | Greater than or equal to | `x >= y` |
| `<=` | Less than or equal to | `x <= y` |

```java
int age = 18;
System.out.println(age >= 18); // Prints: true
System.out.println(age < 18);  // Prints: false

int passwordLength = 5;
System.out.println(passwordLength >= 8); // Prints: false
```

---

## Logical Operators

Combine multiple conditions and return `true` or `false`.

| Operator | Name | Description | Example |
|----------|------|-------------|---------|
| `&&` | Logical AND | Returns `true` if both conditions are true | `x > 0 && x < 10` |
| `\|\|` | Logical OR | Returns `true` if at least one condition is true | `x < 0 \|\| x > 10` |
| `!` | Logical NOT | Reverses the result | `!(x > 0)` |

```java
boolean isLoggedIn = true;
boolean isAdmin = false;

System.out.println(isLoggedIn && !isAdmin); // Prints: true  (logged in, not admin)
System.out.println(isLoggedIn || isAdmin);  // Prints: true  (at least one is true)
System.out.println(!isLoggedIn);            // Prints: false (reverses true)
```

---

## Operator Precedence

When an expression contains multiple operators, Java evaluates them in a defined order. Higher precedence operators are evaluated first.

| Precedence | Operators |
|------------|-----------|
| Highest | `()` parentheses |
| | `++`, `--` |
| | `*`, `/`, `%` |
| | `+`, `-` |
| | `>`, `<`, `>=`, `<=` |
| | `==`, `!=` |
| | `&&` |
| | `\|\|` |
| Lowest | `=`, `+=`, `-=`, etc. |

```java
int result1 = 2 + 3 * 4;   // 3*4 first → 2 + 12 = 14
int result2 = (2 + 3) * 4; // parentheses first → 5 * 4 = 20

System.out.println(result1); // Prints: 14
System.out.println(result2); // Prints: 20
```

Operators at the same precedence level evaluate left to right:

```java
int result1 = 10 - 2 + 5;    // (10 - 2) + 5 = 13
int result2 = 10 - (2 + 5);  // 10 - 7 = 3

System.out.println(result1); // Prints: 13
System.out.println(result2); // Prints: 3
```

When in doubt, use parentheses. They make the intended order explicit and improve readability.