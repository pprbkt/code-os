# Java Conditionals

## Overview

Conditions and if statements control the flow of a program — deciding which code runs and which is skipped. Every condition evaluates to `true` or `false`, making if statements work hand-in-hand with boolean values and comparison operators.

Java provides four conditional constructs:

- `if` — runs a block if a condition is true
- `else` — runs a block if the condition is false
- `else if` — tests a new condition when the previous one is false
- `switch` — selects one of many blocks to execute (covered separately)

---

## The if Statement

```java
if (condition) {
    // runs if condition is true
}
```

The condition can be a comparison expression or a boolean variable:

```java
int x = 20;
int y = 18;

if (x > y) {
    System.out.println("x is greater than y"); // Prints: x is greater than y
}
```

```java
boolean isLightOn = true;

if (isLightOn) {
    System.out.println("The light is on."); // Prints: The light is on.
}
```

Writing `if (isLightOn)` is equivalent to `if (isLightOn == true)` — the shorter form is preferred.

**Always use curly braces `{}`**, even for single-line bodies. Without them, only the first line after `if` belongs to the block — any following lines run unconditionally, which is a common source of bugs.

---

## The else Statement

`else` runs when the `if` condition is false. It has no condition of its own.

```java
if (condition) {
    // runs if true
} else {
    // runs if false
}
```

```java
int time = 20;

if (time < 18) {
    System.out.println("Good day.");
} else {
    System.out.println("Good evening."); // Prints: Good evening.
}
```

**Note:** Do not place a semicolon directly after `if (condition)`. That ends the statement early and causes `else` to behave unexpectedly.

---

## The else if Statement

Use `else if` to test additional conditions when the previous one is false.

```java
if (condition1) {
    // runs if condition1 is true
} else if (condition2) {
    // runs if condition1 is false and condition2 is true
} else {
    // runs if all conditions are false
}
```

```java
int time = 16;

if (time < 12) {
    System.out.println("Good morning.");
} else if (time < 18) {
    System.out.println("Good day.");    // Prints: Good day.
} else {
    System.out.println("Good evening.");
}
```

Java evaluates conditions top to bottom and stops at the first one that is true. Only one block runs per if-else chain.

---

## Nested If

An `if` statement can be placed inside another `if`. The inner condition is only checked if the outer one is true.

```java
int age = 20;
boolean isCitizen = true;

if (age >= 18) {
    System.out.println("Old enough to vote.");

    if (isCitizen) {
        System.out.println("And you are a citizen, so you can vote!"); // Prints this
    } else {
        System.out.println("But you must be a citizen to vote.");
    }
} else {
    System.out.println("Not old enough to vote.");
}
```

Avoid nesting too deeply — it quickly becomes hard to read. Logical operators can often flatten nested conditions into a single `if`.

---

## Logical Operators in Conditions

Logical operators combine or reverse conditions within an `if` statement.

| Operator | Name | Behaviour |
|----------|------|-----------|
| `&&` | AND | Both conditions must be true |
| `\|\|` | OR | At least one condition must be true |
| `!` | NOT | Reverses a condition |

```java
int a = 200, b = 33, c = 500;

if (a > b && c > a) {
    System.out.println("Both conditions are true"); // Prints this
}

if (a > b || a > c) {
    System.out.println("At least one condition is true"); // Prints this
}

if (!(a > c)) {
    System.out.println("a is NOT greater than c"); // Prints this
}
```

Use parentheses to make the intended grouping explicit when combining operators:

```java
boolean isLoggedIn = true;
boolean isAdmin = false;
int securityLevel = 1;

if (isLoggedIn && (isAdmin || securityLevel <= 2)) {
    System.out.println("Access granted"); // Prints: Access granted
} else {
    System.out.println("Access denied");
}
```

---

## Ternary Operator

The ternary operator is a shorthand for a simple `if...else` that returns a value. It takes three operands: a condition, a value if true, and a value if false.

```java
variable = (condition) ? valueIfTrue : valueIfFalse;
```

```java
int time = 20;
String result = (time < 18) ? "Good day." : "Good evening.";
System.out.println(result); // Prints: Good evening.
```

For more than two outcomes, ternary operators can be nested, but this hurts readability. Use a regular `if...else if` chain instead for anything beyond a simple binary choice.

```java
// Readable in this case, but avoid deeper nesting
String message = (time < 12) ? "Good morning."
               : (time < 18) ? "Good afternoon."
               : "Good evening.";
```

---

## Quick Reference

| Construct | When to use |
|-----------|-------------|
| `if` | Run code only when a condition is true |
| `else` | Provide a fallback when `if` is false |
| `else if` | Test multiple mutually exclusive conditions |
| Nested `if` | Check a condition that depends on another |
| `&&` / `\|\|` / `!` | Combine or reverse conditions in a single check |
| Ternary `?:` | Assign a value based on a simple true/false condition |

---

## Switch Statement

The `switch` statement is an alternative to a long `if...else if` chain when you need to compare a single value against multiple fixed options.

```java
switch (expression) {
    case x:
        // runs if expression == x
        break;
    case y:
        // runs if expression == y
        break;
    default:
        // runs if no case matches
}
```

The expression is evaluated once, then compared against each `case` value in order. When a match is found, that block runs. `break` exits the switch — without it, execution continues into the next case (see fallthrough below). `default` runs when no case matches and is optional.

```java
int day = 4;

switch (day) {
    case 1: System.out.println("Monday");    break;
    case 2: System.out.println("Tuesday");   break;
    case 3: System.out.println("Wednesday"); break;
    case 4: System.out.println("Thursday");  break; // Prints: Thursday
    case 5: System.out.println("Friday");    break;
    case 6: System.out.println("Saturday");  break;
    case 7: System.out.println("Sunday");    break;
}
```

```java
int day = 4;

switch (day) {
    case 6:
        System.out.println("Today is Saturday");
        break;
    case 7:
        System.out.println("Today is Sunday");
        break;
    default:
        System.out.println("Looking forward to the Weekend"); // Prints this
}
```

### switch vs if...else if

| | `switch` | `if...else if` |
|--|----------|----------------|
| Best for | One value compared to many fixed options | Range checks, complex boolean conditions |
| Condition type | Equality only (`==`) | Any boolean expression |
| Readability | Cleaner with many fixed cases | Better for ranges or mixed conditions |

Use `switch` when you have a single variable being compared to several known values. Use `if...else if` when conditions involve ranges, multiple variables, or logical operators.