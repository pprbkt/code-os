# Java Type Casting

## Overview

Type casting converts a value from one data type to another. Java has two forms:

- **Widening casting** — converting a smaller type to a larger type. Done automatically by Java.
- **Narrowing casting** — converting a larger type to a smaller type. Must be done manually and may result in data loss.

The full widening order is:

```
byte → short → char → int → long → float → double
```

Narrowing goes in the opposite direction.

## Widening Casting

Widening casting happens automatically because there is no risk of losing data — the target type can always hold the original value.

```java
int myInt = 9;
double myDouble = myInt; // automatic: int to double

System.out.println(myInt);    // Prints: 9
System.out.println(myDouble); // Prints: 9.0
```

## Narrowing Casting

Narrowing casting must be done manually by placing the target type in parentheses in front of the value. Java requires this explicitly because data loss is possible — for example, decimal digits are dropped when converting a `double` to an `int`.

```java
double myDouble = 9.78;
int myInt = (int) myDouble; // manual: double to int

System.out.println(myDouble); // Prints: 9.78
System.out.println(myInt);    // Prints: 9
```

## Widening vs Narrowing

| | Widening | Narrowing |
|--|----------|-----------|
| Direction | smaller → larger | larger → smaller |
| Done | Automatically | Manually, with `(type)` syntax |
| Data loss | No | Possible |
| Example | `int` → `double` | `double` → `int` |

## Practical Example

When dividing two integers in Java, the result is also an integer — the decimal is truncated. Casting one operand to `double` before the division forces floating-point arithmetic:

```java
int maxScore = 500;
int userScore = 423;

double percentage = (double) userScore / maxScore * 100.0;

System.out.println("User's percentage is " + percentage); // Prints: User's percentage is 84.6
```

Without the cast, `userScore / maxScore` would evaluate to `0` (integer division), and the percentage would be `0.0`.