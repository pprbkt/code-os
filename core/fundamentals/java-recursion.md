# Java Recursion

## Overview

Recursion is the technique of making a method call itself. It breaks a complicated problem down into smaller, repeated subproblems until a simple base case is reached.

Every recursive method needs two things:

- **Recursive case** — the method calls itself with a modified argument, moving toward the base case
- **Base case (halting condition)** — the condition where the method stops calling itself and returns a value directly

Without a base case, the method calls itself indefinitely, eventually causing a `StackOverflowError`.

---

## Example — Sum of a Range

### Sum from 1 to n

```java
public static int sum(int k) {
    if (k > 0) {
        return k + sum(k - 1); // recursive case
    } else {
        return 0;              // base case
    }
}

// sum(10) → 55
```

### How it unfolds

```
sum(10)
= 10 + sum(9)
= 10 + (9 + sum(8))
= 10 + (9 + (8 + ... + sum(0)))
= 10 + 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 + 0
= 55
```

When `k` reaches `0`, the method returns `0` without calling itself again. The call stack then unwinds, adding each value back up.

### Sum between two values

```java
public static int sum(int start, int end) {
    if (end > start) {
        return end + sum(start, end - 1);
    } else {
        return end; // base case: end == start
    }
}

// sum(5, 10) → 5 + 6 + 7 + 8 + 9 + 10 = 45
```

---

## Example — Countdown

```java
static void countdown(int n) {
    if (n > 0) {
        System.out.print(n + " ");
        countdown(n - 1);
    }
    // base case: n == 0, method returns without printing
}

// countdown(5) → prints: 5 4 3 2 1
```

---

## Example — Factorial

The factorial of `n` (written `n!`) is the product of all positive integers from 1 to n. By definition, `0!` and `1!` are both `1`.

```
5! = 5 × 4 × 3 × 2 × 1 = 120
```

```java
static int factorial(int n) {
    if (n > 1) {
        return n * factorial(n - 1); // recursive case
    } else {
        return 1;                    // base case: 0! and 1! = 1
    }
}

// factorial(5) → 120
```

### Step-by-step trace

| Call | Returns |
|------|---------|
| `factorial(5)` | `5 × factorial(4)` |
| `factorial(4)` | `4 × factorial(3)` |
| `factorial(3)` | `3 × factorial(2)` |
| `factorial(2)` | `2 × factorial(1)` |
| `factorial(1)` | `1` (base case) |
| Unwind | `2 × 1 = 2` → `3 × 2 = 6` → `4 × 6 = 24` → `5 × 24 = 120` |

---

## Recursion vs Iteration

Most recursive solutions can also be written as a loop. The choice is usually about readability.

| | Recursion | Iteration |
|--|-----------|-----------|
| Code length | Often shorter | Often more explicit |
| Memory | Uses call stack (risk of overflow on large inputs) | Constant stack usage |
| Best for | Tree/graph traversal, divide-and-conquer, problems with natural substructure | Simple counting, accumulation |

For small inputs and problems with a natural recursive structure (like trees or factorials), recursion is clean and readable. For large inputs or performance-critical code, an iterative approach is safer.