# Two Pointers

## Overview

The two pointers technique uses two index variables that move through a data structure — typically an array or string — to solve a problem in a single pass. Instead of using nested loops to compare every pair of elements, two pointers narrow the search space from both ends (or from the same starting point) and advance based on a condition.

This reduces many problems that would otherwise be O(n²) down to O(n).

The technique has two common forms:

- **Opposite ends** — one pointer starts at the beginning, one at the end, and they move toward each other
- **Same direction** — both pointers start at the beginning and one advances ahead of the other (also called the fast/slow or sliding window pattern)

## Target Sum in a Sorted Array

Given a sorted array of integers and a target value, find two numbers that add up to the target. Return their indices.

Because the array is sorted, you can place one pointer at the left end and one at the right end. If their sum is too small, move the left pointer right to increase it. If the sum is too large, move the right pointer left to decrease it. Repeat until the sum matches the target or the pointers meet.

### Example

```
Input:  [1, 3, 5, 7, 9, 11], target = 12
Output: indices 1 and 4  (3 + 9 = 12)
```

```java
public class TwoSum {
    public static int[] twoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;  // sum too small, move left pointer right
            } else {
                right--; // sum too large, move right pointer left
            }
        }

        return new int[]{-1, -1}; // no pair found
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int target = 12;

        int[] result = twoSum(arr, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]); // Prints: Indices: 1, 4
        System.out.println("Values: " + arr[result[0]] + ", " + arr[result[1]]); // Prints: Values: 3, 9
    }
}
```

### Step-by-step trace

| Step | left | right | arr[left] | arr[right] | Sum | Action |
|------|------|-------|-----------|------------|-----|--------|
| 1 | 0 | 5 | 1 | 11 | 12 | — wait, that equals target |

Actually tracing with target = 16 to better illustrate all three cases:

```
arr = [1, 3, 5, 7, 9, 11], target = 16
```

| Step | left | right | arr[left] | arr[right] | Sum | Action |
|------|------|-------|-----------|------------|-----|--------|
| 1 | 0 | 5 | 1 | 11 | 12 | sum < target, move left right |
| 2 | 1 | 5 | 3 | 11 | 14 | sum < target, move left right |
| 3 | 2 | 5 | 5 | 11 | 16 | sum == target, return {2, 5} |

## Why This Works

The array being sorted is the key requirement. It guarantees that moving the left pointer right always increases the sum, and moving the right pointer left always decreases it. This gives the algorithm a predictable way to home in on the target without ever needing to revisit a pair.

If the array were unsorted, this approach would not work — you would need to sort it first (O(n log n)) or use a hash map to track seen values (O(n) time, O(n) space).

## Complexity

| | Value |
|-|-------|
| Time | O(n) — each pointer moves at most n steps |
| Space | O(1) — no extra data structures used |

## When to Use Two Pointers

Two pointers is a good fit when the problem involves:

- A sorted array and a condition on pairs of elements
- Finding a subarray or substring that meets a length or sum constraint
- Removing duplicates or partitioning an array in place
- Checking whether a string or array is a palindrome