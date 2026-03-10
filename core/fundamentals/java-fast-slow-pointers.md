# Fast Slow Pointers

Fast slow pointers (also called the tortoise and hare algorithm) is a pattern that uses two pointers moving at different speeds to solve linked list and array problems.

## Core Concept

Two pointers traverse the data structure at different speeds:
- **Slow pointer**: Moves one step at a time
- **Fast pointer**: Moves two steps at a time

This speed difference creates useful properties for detecting cycles, finding middle elements, and solving other problems.

## Key Characteristics

- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- **Speed Ratio**: Usually 1:2 (slow:fast)
- **Strategy**: Exploit relative speed difference

## Pattern 1: Detect Cycle in Linked List

Determine if a linked list has a cycle.

```java
public class CycleDetection {
    
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        return false;
    }
}
```

**Why This Works:**

If there is a cycle, fast will eventually catch up to slow inside the cycle. If no cycle, fast reaches the end.

## Pattern 2: Find Cycle Start

Find the node where the cycle begins.

```java
public class CycleStart {
    
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Detect if cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Cycle detected, find start
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        
        return null;
    }
}
```

**Mathematical Proof:**

When slow and fast meet:
- Slow traveled: x + y
- Fast traveled: x + y + z + y

Since fast travels twice as fast: 2(x + y) = x + y + z + y
- Therefore: x = z

Starting from head and meeting point, both reach cycle start simultaneously.

## Pattern 3: Find Middle of Linked List

Find the middle node (for even length, return second middle).

```java
public class FindMiddle {
    
    public static ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}
```

**Variant: First Middle for Even Length**

```java
public static ListNode findFirstMiddle(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    
    ListNode slow = head;
    ListNode fast = head.next;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    return slow;
}
```

## Pattern 4: Check Palindrome Linked List

Determine if a linked list is a palindrome.

```java
public class PalindromeList {
    
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode secondHalf = reverse(slow);
        ListNode firstHalf = head;
        
        // Compare both halves
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true;
    }
    
    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
```

## Pattern 5: Reorder List

Reorder list to L0→Ln→L1→Ln-1→L2→Ln-2→...

```java
public class ReorderList {
    
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // Find middle
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode secondHalf = reverse(slow.next);
        slow.next = null;
        
        // Merge two halves
        ListNode first = head;
        ListNode second = secondHalf;
        
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            
            first.next = second;
            second.next = tmp1;
            
            first = tmp1;
            second = tmp2;
        }
    }
    
    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
}
```

## Pattern 6: Happy Number

Determine if a number is happy (sum of squares eventually reaches 1).

```java
public class HappyNumber {
    
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        
        do {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        } while (slow != fast);
        
        return slow == 1;
    }
    
    private static int sumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
```

**Why This Works:**

If not happy, numbers will cycle. Fast and slow will meet in the cycle. If happy, they meet at 1.

## Pattern 7: Find Duplicate Number

Find the duplicate in an array of n+1 integers where each integer is between 1 and n.

```java
public class FindDuplicate {
    
    public static int findDuplicate(int[] nums) {
        // Treat array as linked list
        int slow = nums[0];
        int fast = nums[0];
        
        // Find intersection point
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // Find cycle start
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
}
```

**Key Insight:**

Treat array as implicit linked list where nums[i] points to index nums[i]. Duplicate creates a cycle.

## Pattern 8: Nth Node from End

Find the nth node from the end of the list.

```java
public class NthFromEnd {
    
    public static ListNode nthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        
        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        
        // Move both until fast reaches end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}
```

## Common Templates

### Template 1: Cycle Detection

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    
    if (slow == fast) {
        // Cycle detected
        return true;
    }
}

return false;
```

### Template 2: Find Middle

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}

return slow; // Middle node
```

### Template 3: Nth from End

```java
ListNode slow = head;
ListNode fast = head;

// Create gap of n nodes
for (int i = 0; i < n; i++) {
    fast = fast.next;
}

// Move together
while (fast != null) {
    slow = slow.next;
    fast = fast.next;
}

return slow;
```

## Initialization Strategies

**Same Starting Point (head, head)**

Use for finding middle or detecting cycles.

```java
ListNode slow = head;
ListNode fast = head;
```

**Offset Starting Point (head, head.next)**

Use when you need first middle in even-length lists.

```java
ListNode slow = head;
ListNode fast = head.next;
```

**Gap Starting Point**

Use for nth from end problems.

```java
ListNode slow = head;
ListNode fast = head;
for (int i = 0; i < n; i++) {
    fast = fast.next;
}
```

## Loop Conditions

**Standard Condition**

```java
while (fast != null && fast.next != null) {
    // Safe to do fast.next.next
}
```

**Do-While for Cycle Detection**

```java
do {
    slow = slow.next;
    fast = fast.next.next;
} while (slow != fast);
```

## Visual Example: Cycle Detection

```
List: 1 → 2 → 3 → 4 → 5
              ↑       ↓
              8 ← 7 ← 6

Step 1: slow=1, fast=1
Step 2: slow=2, fast=3
Step 3: slow=3, fast=5
Step 4: slow=4, fast=7
Step 5: slow=5, fast=3
Step 6: slow=6, fast=5
Step 7: slow=7, fast=7 (meet!)
```

## Common Pitfalls

**Not Checking fast.next**

Always check both `fast != null` and `fast.next != null`.

```java
// Wrong
while (fast != null) {
    fast = fast.next.next; // Can throw NullPointerException
}

// Correct
while (fast != null && fast.next != null) {
    fast = fast.next.next;
}
```

**Wrong Initialization**

Choose initialization based on problem requirements.

**Off-by-One in Gap Creation**

For nth from end, ensure correct gap size.

**Forgetting to Reset Pointers**

When finding cycle start, reset one pointer to head.

## When to Use Fast Slow Pointers

Use this pattern when:

- Detecting cycles in linked lists
- Finding middle of linked list
- Checking for palindrome
- Finding nth node from end
- Problems involving meeting points
- Need O(1) space for linked list traversal
