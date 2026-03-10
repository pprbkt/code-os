# Rotated Array Patterns

Rotated arrays are sorted arrays that have been rotated at some pivot point. These patterns are common in coding interviews and require modified binary search techniques.

## What is a Rotated Array?

A rotated sorted array is created by taking a sorted array and rotating it at some pivot.

```
Original: [1, 2, 3, 4, 5, 6, 7]
Rotated:  [4, 5, 6, 7, 1, 2, 3]  (rotated at index 3)
```

## Key Characteristics

- **Time Complexity**: O(log n) with binary search
- **Space Complexity**: O(1)
- **Property**: One half is always sorted
- **Strategy**: Identify which half is sorted, then decide where to search

## Core Concept

At any point in a rotated array, when you pick a middle element:
- Either the left half is sorted, or
- The right half is sorted

This property allows us to use modified binary search.

## Pattern 1: Search in Rotated Sorted Array

Find a target value in a rotated sorted array with no duplicates.

```java
public class SearchRotatedArray {
    
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check which half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
}
```

**Logic Breakdown:**

1. If `nums[left] <= nums[mid]`, left half is sorted
   - If target is in sorted range, search left
   - Otherwise, search right
2. If left half is not sorted, right half must be sorted
   - If target is in sorted range, search right
   - Otherwise, search left

## Pattern 2: Find Minimum in Rotated Array

Find the minimum element (the rotation pivot).

```java
public class FindMinimum {
    
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                // Minimum is in right half
                left = mid + 1;
            } else {
                // Minimum is in left half (including mid)
                right = mid;
            }
        }
        
        return nums[left];
    }
}
```

**Key Insight:**

Compare `nums[mid]` with `nums[right]`:
- If `nums[mid] > nums[right]`, minimum is to the right
- Otherwise, minimum is to the left (or is mid itself)

## Pattern 3: Search with Duplicates

Handle rotated arrays that contain duplicate elements.

```java
public class SearchWithDuplicates {
    
    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // Handle duplicates
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
                continue;
            }
            
            // Check which half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return false;
    }
}
```

**Handling Duplicates:**

When `nums[left] == nums[mid] == nums[right]`, we cannot determine which half is sorted. Solution: increment left and decrement right to skip duplicates.

Worst case: O(n) when all elements are the same.

## Pattern 4: Find Rotation Count

Determine how many times the array has been rotated.

```java
public class RotationCount {
    
    public static int countRotations(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            // Already sorted
            if (nums[left] < nums[right]) {
                return left;
            }
            
            int mid = left + (right - left) / 2;
            
            // Check if mid+1 is the minimum
            if (mid < right && nums[mid] > nums[mid + 1]) {
                return mid + 1;
            }
            
            // Check if mid is the minimum
            if (mid > left && nums[mid] < nums[mid - 1]) {
                return mid;
            }
            
            // Decide which half to search
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return 0;
    }
}
```

**Key Points:**

The number of rotations equals the index of the minimum element.

## Pattern 5: Find Peak Element in Rotated Array

A peak element is greater than its neighbors.

```java
public class FindPeak {
    
    public static int findPeak(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                // Peak is in left half (including mid)
                right = mid;
            } else {
                // Peak is in right half
                left = mid + 1;
            }
        }
        
        return left;
    }
}
```

## Common Templates

### Template 1: Identify Sorted Half

```java
if (nums[left] <= nums[mid]) {
    // Left half is sorted
    if (target >= nums[left] && target < nums[mid]) {
        // Target in sorted left half
        right = mid - 1;
    } else {
        // Target in unsorted right half
        left = mid + 1;
    }
} else {
    // Right half is sorted
    if (target > nums[mid] && target <= nums[right]) {
        // Target in sorted right half
        left = mid + 1;
    } else {
        // Target in unsorted left half
        right = mid - 1;
    }
}
```

### Template 2: Find Minimum/Pivot

```java
while (left < right) {
    int mid = left + (right - left) / 2;
    
    if (nums[mid] > nums[right]) {
        left = mid + 1;  // Minimum in right half
    } else {
        right = mid;      // Minimum in left half or is mid
    }
}
return left;
```

## Visual Examples

### Example 1: Search for 6

```
Array: [4, 5, 6, 7, 0, 1, 2]
Target: 6

Step 1: left=0, right=6, mid=3
  nums[mid]=7, nums[left]=4 <= nums[mid]=7 (left sorted)
  target=6 in [4,7), search left
  
Step 2: left=0, right=2, mid=1
  nums[mid]=5, nums[left]=4 <= nums[mid]=5 (left sorted)
  target=6 not in [4,5), search right
  
Step 3: left=2, right=2, mid=2
  nums[mid]=6, found!
```

### Example 2: Find Minimum

```
Array: [4, 5, 6, 7, 0, 1, 2]

Step 1: left=0, right=6, mid=3
  nums[mid]=7 > nums[right]=2, search right
  
Step 2: left=4, right=6, mid=5
  nums[mid]=1 < nums[right]=2, search left
  
Step 3: left=4, right=5, mid=4
  nums[mid]=0 < nums[right]=1, search left
  
Step 4: left=4, right=4
  Return nums[4]=0
```

## Decision Tree

```
At mid position:
├─ Is nums[mid] == target? → Return mid
├─ Is left half sorted? (nums[left] <= nums[mid])
│  ├─ Is target in left sorted range? → Search left
│  └─ Otherwise → Search right
└─ Right half is sorted
   ├─ Is target in right sorted range? → Search right
   └─ Otherwise → Search left
```

## Common Pitfalls

**Using wrong comparison operators**

- `nums[left] <= nums[mid]` not `nums[left] < nums[mid]`
- The equals handles single-element cases

**Not handling duplicates**

- Arrays with duplicates need special handling
- May degrade to O(n) in worst case

**Off-by-one errors**

- For finding minimum, use `left < right` not `left <= right`
- Return value should be carefully chosen

## When to Use These Patterns

Use rotated array patterns when:

- Array is sorted but rotated
- Need O(log n) solution
- Problem mentions "rotated sorted array"
- Finding pivot/minimum in rotated array
- Searching in rotated context