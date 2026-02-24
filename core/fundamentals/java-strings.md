# Java Strings

## Overview

A `String` is used to store text. In Java, strings are objects — they come with built-in methods for performing operations on text. A string value is written in double quotes:

```java
String greeting = "Hello";
```

## Immutability

Strings in Java are **immutable** — once a `String` object is created, its value cannot be changed. Methods like `toUpperCase()` or `replace()` do not modify the original string; they return a new one.

```java
String txt = "hello";
String upper = txt.toUpperCase();

System.out.println(txt);   // Prints: hello  (unchanged)
System.out.println(upper); // Prints: HELLO  (new string)
```

This makes strings safe to share across a program, but inefficient when you need to build or modify strings repeatedly in a loop. See [StringBuilder](#stringbuilder) for that use case.

## Common String Methods

### Length

```java
String txt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
System.out.println(txt.length()); // Prints: 26
```

### Case Conversion

```java
String txt = "Hello World";
System.out.println(txt.toUpperCase()); // Prints: HELLO WORLD
System.out.println(txt.toLowerCase()); // Prints: hello world
```

### Finding Characters and Substrings

`indexOf()` returns the position of the first occurrence of a specified character or substring. Positions are zero-based.

```java
String txt = "Please locate where 'locate' occurs!";
System.out.println(txt.indexOf("locate")); // Prints: 7
```

`charAt()` returns the character at a specific index:

```java
String txt = "Hello";
System.out.println(txt.charAt(0)); // Prints: H
System.out.println(txt.charAt(4)); // Prints: o
```

### Comparing Strings

Use `equals()` to compare string values. Do not use `==`, which compares object references, not content.

```java
String txt1 = "Hello";
String txt2 = "Hello";
String txt3 = "Greetings";

System.out.println(txt1.equals(txt2)); // Prints: true
System.out.println(txt1.equals(txt3)); // Prints: false
```

### Removing Whitespace

```java
String txt = "   Hello World   ";
System.out.println(txt.trim()); // Prints: Hello World
```

## String Concatenation

Use the `+` operator to combine strings:

```java
String firstName = "John";
String lastName = "Doe";
System.out.println(firstName + " " + lastName); // Prints: John Doe
```

You can also use the `concat()` method, though `+` is preferred for readability:

```java
String a = "Java ";
String b = "is ";
String c = "fun!";
System.out.println(a.concat(b).concat(c)); // Prints: Java is fun!
```

### Adding Numbers and Strings

The `+` operator behaves differently depending on the types involved. Numbers are added; strings are concatenated. When a number and a string are combined, the result is always a string.

```java
int x = 10;
int y = 20;
System.out.println(x + y);          // Prints: 30  (addition)

String a = "10";
String b = "20";
System.out.println(a + b);          // Prints: 1020 (concatenation)

String s = "10";
int n = 20;
System.out.println(s + n);          // Prints: 1020 (concatenation)
```

Evaluation runs left to right, so parentheses matter:

```java
System.out.println("Sum: " + 5 + 10);   // Prints: Sum: 510
System.out.println("Sum: " + (5 + 10)); // Prints: Sum: 15
```

## Special Characters

Because strings are wrapped in double quotes, including a literal quote requires an escape sequence. Use the backslash (`\`) to escape special characters:

| Escape Sequence | Result |
|-----------------|--------|
| `\"` | Double quote |
| `\'` | Single quote |
| `\\` | Backslash |
| `\n` | New line |
| `\t` | Tab |

```java
String txt = "We are the so-called \"Vikings\" from the north.";
System.out.println(txt); // Prints: We are the so-called "Vikings" from the north.
```

## Char Arrays

A `String` is built on top of a `char[]` (character array) internally. You can convert between the two using `toCharArray()` and the `String(char[])` constructor:

```java
String txt = "Hello";
char[] chars = txt.toCharArray();

for (char c : chars) {
    System.out.print(c + " "); // Prints: H e l l o
}

// Convert back to a String
String restored = new String(chars);
System.out.println(restored); // Prints: Hello
```

Char arrays are useful when you need to manipulate individual characters, as strings themselves are immutable.

## StringBuilder

Because `String` objects are immutable, concatenating strings in a loop creates a new object on every iteration, which is inefficient. `StringBuilder` provides a mutable alternative — it builds a string in place without creating intermediate objects.

```java
StringBuilder sb = new StringBuilder();

sb.append("Java");
sb.append(" is");
sb.append(" fun!");

System.out.println(sb.toString()); // Prints: Java is fun!
```

Common `StringBuilder` methods:

| Method | Description |
|--------|-------------|
| `append(value)` | Adds a value to the end |
| `insert(index, value)` | Inserts a value at a specific position |
| `delete(start, end)` | Removes characters in the given range |
| `reverse()` | Reverses the character sequence |
| `toString()` | Converts the result to a `String` |

```java
StringBuilder sb = new StringBuilder("Hello World");

sb.insert(5, ",");       // "Hello, World"
sb.delete(7, 12);        // "Hello, "
sb.append("Java");       // "Hello, Java"
sb.reverse();            // "avaJ ,olleH"

System.out.println(sb.toString());
```

Use `StringBuilder` whenever you are building or modifying strings inside loops or across many operations. For simple, one-off concatenation, the `+` operator is fine.

## String Methods Reference

| Method | Description | Return Type |
|--------|-------------|-------------|
| `charAt(index)` | Returns the character at the specified index | `char` |
| `compareTo(str)` | Compares two strings lexicographically | `int` |
| `contains(seq)` | Checks whether a string contains a sequence of characters | `boolean` |
| `endsWith(suffix)` | Checks whether a string ends with the specified characters | `boolean` |
| `equals(str)` | Compares two strings for equality | `boolean` |
| `equalsIgnoreCase(str)` | Compares two strings, ignoring case | `boolean` |
| `indexOf(str)` | Returns the index of the first occurrence of a substring | `int` |
| `isEmpty()` | Returns true if the string is empty | `boolean` |
| `lastIndexOf(str)` | Returns the index of the last occurrence of a substring | `int` |
| `length()` | Returns the length of the string | `int` |
| `replace(old, new)` | Replaces occurrences of a value with another | `String` |
| `split(regex)` | Splits the string into an array of substrings | `String[]` |
| `startsWith(prefix)` | Checks whether a string starts with the specified characters | `boolean` |
| `substring(start)` | Returns a substring from the given index | `String` |
| `toCharArray()` | Converts the string to a character array | `char[]` |
| `toLowerCase()` | Converts the string to lower case | `String` |
| `toUpperCase()` | Converts the string to upper case | `String` |
| `trim()` | Removes whitespace from both ends | `String` |
| `valueOf(value)` | Returns the string representation of a value | `String` |