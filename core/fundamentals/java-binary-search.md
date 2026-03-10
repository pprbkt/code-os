# Binary Search

Binary search is an efficient algorithm for finding a target value in a **sorted array** by repeatedly dividing the search space in half.

## Key Characteristics

- **Time Complexity**: O(log n)
- **Space Complexity**: O(1) iterative, O(log n) recursive
- **Requirement**: Array must be sorted
- **Strategy**: Divide and conquer

## How It Works

1. Compare target with middle element
2. If target equals middle, return index
3. If target is less than middle, search left half
4. If target is greater than middle, search right half
5. Repeat until target is found or search space is empty

## Basic Implementation

```java
public class BinarySearch {
    
    // Iterative approach
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Target not found
    }
    
    // Recursive approach
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }
}
```

## Important Details

**Why `mid = left + (right - left) / 2`?**

This prevents integer overflow. The alternative `mid = (left + right) / 2` can overflow if left and right are large.

**Loop Condition: `left <= right`**

The equals sign is crucial. Without it, single-element ranges would be skipped.

## Common Variations

### Find First Occurrence

```java
public static int findFirst(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            result = mid;
            right = mid - 1; // Continue searching left
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return result;
}
```

### Find Last Occurrence

```java
public static int findLast(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    int result = -1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            result = mid;
            left = mid + 1; // Continue searching right
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return result;
}
```

### Search Insert Position

Find the index where target should be inserted to maintain sorted order.

```java
public static int searchInsert(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return left; // Insert position
}
```

## Template Pattern

Binary search problems often follow this template:

```java
public static int binarySearchTemplate(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (condition(arr[mid], target)) {
            // Found or update result
            return mid;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return -1; // Or left for insertion
}
```

## Common Pitfalls

**Off-by-One Errors**

- Using `right = arr.length` instead of `arr.length - 1`
- Forgetting `+1` or `-1` when updating pointers

**Infinite Loops**

- Not updating pointers correctly
- Wrong loop condition

**Integer Overflow**

- Using `(left + right) / 2` instead of `left + (right - left) / 2`

## When to Use Binary Search

Binary search is applicable when:

- Array is sorted
- Need to find a specific value
- Need to find insertion point
- Need to find boundary conditions
- Search space can be halved each iteration