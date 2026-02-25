# Sliding Window Technique

The sliding window is a powerful pattern for solving array/string problems efficiently by maintaining a contiguous subset of elements and moving it across the data structure.

## Fixed-Size Window

Use this when you need to find something across every consecutive **k** elements.

**Template:**
```python
def fixed_window(arr, k):
    # Calculate initial window
    window_sum = sum(arr[:k])
    max_sum = window_sum
    
    # Slide the window
    for i in range(k, len(arr)):
        window_sum = window_sum - arr[i - k] + arr[i]
        max_sum = max(max_sum, window_sum)
    
    return max_sum
```

**Key idea:** Remove the leftmost element, add the rightmost element, repeat.

**Example:** Maximum sum of any 3 consecutive elements in `[1, 3, 2, 6, -1, 4, 1]` → window moves from `[1,3,2]` → `[3,2,6]` → `[2,6,-1]` etc.

## Variable-Size Window

Use this when the window size changes based on a condition (e.g., "find longest substring where X").

**Template:**
```python
def variable_window(s, condition):
    left = 0
    result = 0
    
    for right in range(len(s)):
        # Expand window by moving right pointer
        
        # Shrink window while condition is violated
        while not condition_met(left, right):
            left += 1
        
        # Update result
        result = max(result, right - left + 1)
    
    return result
```

**Key idea:** Two pointers expand and contract. Right pointer grows the window, left shrinks it when needed.

**Example:** Longest substring without repeating characters in `"abcabcbb"` → Track seen characters, shrink from left when you hit a duplicate.

## When to Use Each

**Fixed-size** is simpler and faster—use it when k is predetermined. **Variable-size** is more flexible—use it when you need to find an optimal window that satisfies some constraint.

Both achieve O(n) time complexity by visiting each element at most twice, avoiding the O(n·k) or worse nested loop approach.