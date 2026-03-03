# Java Math

## Overview

The `Math` class provides a set of static methods for performing common mathematical operations. No import is needed — it is part of `java.lang` and available by default.

## Common Methods

| Method | Description | Example | Result |
|--------|-------------|---------|--------|
| `Math.max(x, y)` | Returns the larger of two values | `Math.max(5, 10)` | `10` |
| `Math.min(x, y)` | Returns the smaller of two values | `Math.min(5, 10)` | `5` |
| `Math.abs(x)` | Returns the absolute (positive) value | `Math.abs(-4.7)` | `4.7` |
| `Math.sqrt(x)` | Returns the square root | `Math.sqrt(64)` | `8.0` |
| `Math.pow(x, y)` | Returns x raised to the power of y | `Math.pow(2, 8)` | `256.0` |

```java
System.out.println(Math.max(5, 10));   // Prints: 10
System.out.println(Math.min(5, 10));   // Prints: 5
System.out.println(Math.abs(-4.7));    // Prints: 4.7
System.out.println(Math.sqrt(64));     // Prints: 8.0
System.out.println(Math.pow(2, 8));    // Prints: 256.0
```

**Note:** `Math.pow()` always returns a `double`, even when the result is a whole number — `Math.pow(2, 8)` returns `256.0`, not `256`.

## Rounding Methods

| Method | Behaviour | Example | Result |
|--------|-----------|---------|--------|
| `Math.round(x)` | Rounds to the nearest integer | `Math.round(4.6)` | `5` |
| `Math.ceil(x)` | Always rounds up | `Math.ceil(4.1)` | `5.0` |
| `Math.floor(x)` | Always rounds down | `Math.floor(4.9)` | `4.0` |

```java
System.out.println(Math.round(4.6)); // Prints: 5
System.out.println(Math.ceil(4.1));  // Prints: 5.0
System.out.println(Math.floor(4.9)); // Prints: 4.0
```

## Random Numbers

`Math.random()` returns a random `double` between `0.0` (inclusive) and `1.0` (exclusive).

```java
System.out.println(Math.random()); // Prints: e.g. 0.7234918273
```

To generate a random integer within a specific range, multiply by the range size and cast to `int`:

```java
int randomNum = (int)(Math.random() * 101); // 0 to 100
```

The formula for any range is:

```java
int randomNum = (int)(Math.random() * (max - min + 1)) + min;

// Example: random number between 10 and 50
int result = (int)(Math.random() * 41) + 10;
```