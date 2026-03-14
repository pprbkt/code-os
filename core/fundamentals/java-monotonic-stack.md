# Monotonic Stack

## Overview

A monotonic stack is a stack that maintains its elements in a strictly increasing or strictly decreasing order from bottom to top. Before pushing a new element, you pop all elements that would violate the ordering property.

It is not a separate data structure — it is a pattern applied to a regular stack, used to efficiently solve problems that ask about the **next or previous greater/smaller element** for each position in an array.

| Type | Stack order (bottom → top) | Used to find |
|------|---------------------------|--------------|
| Monotonic increasing | Small → Large | Next greater element, largest rectangle |
| Monotonic decreasing | Large → Small | Next smaller element, trapping rain water |

---

## Core Pattern

```java
Deque<Integer> stack = new ArrayDeque<>(); // stores indices

for (int i = 0; i < arr.length; i++) {
    // For monotonic increasing: pop while top >= current
    // For monotonic decreasing: pop while top <= current
    while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
        stack.pop();
    }
    stack.push(i);
}
```

Storing **indices** rather than values is the standard approach — it lets you look up the original value while also knowing the position.

---

## Application 1 — Next Greater Element

Given an array, for each element find the first element to its right that is strictly greater. If none exists, return `-1`.

**Approach:** Use a monotonic increasing stack (bottom to top). When a new element is greater than the top, the top has found its next greater element.

```java
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterElement {
    public static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        Deque<Integer> stack = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int idx = stack.pop();
                result[idx] = arr[i]; // arr[i] is the next greater for arr[idx]
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 4};
        System.out.println(Arrays.toString(nextGreater(arr)));
        // Prints: [5, 5, -1, 4, -1]
    }
}
```

### Step-by-step trace for `[2, 1, 5, 3, 4]`

| i | arr[i] | Stack (indices) | Action | Result updated |
|---|--------|-----------------|--------|----------------|
| 0 | 2 | — | push 0 | — |
| 1 | 1 | [0] | 1 < 2, push 1 | — |
| 2 | 5 | [0, 1] | 5 > 1, pop 1 → result[1]=5; 5 > 2, pop 0 → result[0]=5; push 2 | result[0]=5, result[1]=5 |
| 3 | 3 | [2] | 3 < 5, push 3 | — |
| 4 | 4 | [2, 3] | 4 > 3, pop 3 → result[3]=4; 4 < 5, push 4 | result[3]=4 |
| end | — | [2, 4] | remaining indices → result stays -1 | — |

Final result: `[5, 5, -1, 4, -1]`

---

## Application 2 — Daily Temperatures

Given an array of daily temperatures, for each day find how many days until a warmer temperature. Return 0 if no warmer day exists.

This is a direct application of next greater element — the answer for each index is the distance to its next greater, not the value itself.

```java
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temps) {
        int n = temps.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temps[stack.peek()] < temps[i]) {
                int idx = stack.pop();
                result[idx] = i - idx; // distance to the warmer day
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temps)));
        // Prints: [1, 1, 4, 2, 1, 1, 0, 0]
    }
}
```

---

## Application 3 — Largest Rectangle in Histogram

Given an array where each value represents the height of a bar, find the area of the largest rectangle that can be formed.

**Approach:** Use a monotonic increasing stack. When a bar shorter than the top is encountered, the top bar can no longer extend to the right — calculate its maximum rectangle using the current index as the right boundary and the new top of the stack as the left boundary.

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangle {
    public static int largestRectangle(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangle(heights)); // Prints: 10
    }
}
```

The sentinel value `0` appended at `i == n` flushes all remaining bars from the stack at the end without special-casing the loop.

---

## Complexity

| Operation | Time | Space |
|-----------|------|-------|
| Each element is pushed and popped at most once | O(n) total | O(n) for the stack |

All three applications above run in O(n) time — the key advantage over a brute-force O(n²) approach.

---

## When to Use a Monotonic Stack

A monotonic stack is the right tool when a problem asks:

- For each element, find the **next or previous greater/smaller** element
- How far until the next increase or decrease (distances, wait times)
- The largest area bounded by heights (histograms, water trapping)
- Any problem where elements are processed and their answer depends on a future or past element that breaks a monotonic trend