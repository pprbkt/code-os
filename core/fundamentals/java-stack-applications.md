# Stack Applications

## Overview

A stack is a Last-In, First-Out (LIFO) data structure — the last element pushed on is the first one popped off. Java provides a `Stack` class in `java.util`, though `Deque` (via `ArrayDeque`) is preferred in modern code for the same purpose.

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);
stack.push(2);
stack.push(3);

System.out.println(stack.pop());  // Prints: 3
System.out.println(stack.peek()); // Prints: 2 (does not remove)
```

| Method | Description |
|--------|-------------|
| `push(element)` | Adds an element to the top |
| `pop()` | Removes and returns the top element |
| `peek()` | Returns the top element without removing it |
| `isEmpty()` | Returns `true` if the stack is empty |

---

## Application 1 — Balanced Parentheses

Given a string of brackets, determine whether every opening bracket has a matching closing bracket in the correct order.

**Approach:** Push every opening bracket onto the stack. When a closing bracket is encountered, pop from the stack and check if it matches. If the stack is empty at the end, the string is balanced.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class BalancedParentheses {
    public static boolean isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("({[]})"));  // Prints: true
        System.out.println(isBalanced("({[})"));   // Prints: false
        System.out.println(isBalanced("((())"));   // Prints: false
    }
}
```

### Step-by-step trace for `"({[]})"`

| Character | Action | Stack |
|-----------|--------|-------|
| `(` | push | `(` |
| `{` | push | `( {` |
| `[` | push | `( { [` |
| `]` | pop `[`, matches | `( {` |
| `}` | pop `{`, matches | `(` |
| `)` | pop `(`, matches | empty |
| end | stack empty → balanced | — |

---

## Application 2 — Evaluate Postfix Expression

In postfix (Reverse Polish Notation), operators come after their operands — `3 4 +` means `3 + 4`. Postfix removes the need for parentheses and maps directly to stack-based evaluation.

**Approach:** Scan left to right. Push numbers onto the stack. When an operator is found, pop two operands, apply the operator, and push the result back.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class PostfixEvaluator {
    public static int evaluate(String expr) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : expr.split(" ")) {
            switch (token) {
                case "+": stack.push(stack.pop() + stack.pop()); break;
                case "*": stack.push(stack.pop() * stack.pop()); break;
                case "-": {
                    int b = stack.pop(), a = stack.pop();
                    stack.push(a - b);
                    break;
                }
                case "/": {
                    int b = stack.pop(), a = stack.pop();
                    stack.push(a / b);
                    break;
                }
                default: stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("3 4 +"));       // Prints: 7
        System.out.println(evaluate("5 1 2 + 4 * + 3 -")); // Prints: 14
    }
}
```

### Step-by-step trace for `"5 1 2 + 4 * + 3 -"`

| Token | Action | Stack |
|-------|--------|-------|
| `5` | push | `5` |
| `1` | push | `5 1` |
| `2` | push | `5 1 2` |
| `+` | pop 2, 1 → push 3 | `5 3` |
| `4` | push | `5 3 4` |
| `*` | pop 4, 3 → push 12 | `5 12` |
| `+` | pop 12, 5 → push 17 | `17` |
| `3` | push | `17 3` |
| `-` | pop 3, 17 → push 14 | `14` |

---

## Application 3 — Undo / Redo

Text editors implement undo/redo using two stacks. Every action is pushed onto the undo stack. When the user undoes an action, it is popped from the undo stack and pushed onto the redo stack. Redo reverses this.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class UndoRedo {
    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();
    private String current = "";

    public void type(String text) {
        undoStack.push(current);
        redoStack.clear(); // new action clears redo history
        current = text;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(current);
            current = undoStack.pop();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(current);
            current = redoStack.pop();
        }
    }

    public String getState() { return current; }

    public static void main(String[] args) {
        UndoRedo editor = new UndoRedo();
        editor.type("Hello");
        editor.type("Hello World");
        editor.type("Hello World!");

        System.out.println(editor.getState()); // Prints: Hello World!
        editor.undo();
        System.out.println(editor.getState()); // Prints: Hello World
        editor.undo();
        System.out.println(editor.getState()); // Prints: Hello
        editor.redo();
        System.out.println(editor.getState()); // Prints: Hello World
    }
}
```

---

## Complexity

| Operation | Time |
|-----------|------|
| `push` | O(1) |
| `pop` | O(1) |
| `peek` | O(1) |
| Balanced parentheses check | O(n) |
| Postfix evaluation | O(n) |
| Undo / Redo | O(1) per operation |

---

## When to Use a Stack

A stack is the right structure when the problem has a **LIFO** access pattern — the most recently seen item needs to be processed first. Common signals in a problem:

- Matching or validating nested structures (brackets, tags, calls)
- Evaluating or converting arithmetic expressions
- Tracking history with the ability to reverse steps (undo, backtracking)
- Depth-first traversal (explicit stack as an alternative to recursion)