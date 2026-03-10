# Dummy Node Technique

The dummy node technique is a pattern used in linked list problems to simplify edge cases and avoid special handling for the head node.

## What is a Dummy Node?

A dummy node (also called sentinel node) is a placeholder node created at the beginning of operations. It points to the actual head of the list and is removed before returning the result.

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
```

## Key Characteristics

- **Purpose**: Simplifies edge case handling
- **Usage**: Linked list operations that modify the head
- **Benefit**: Uniform treatment of all nodes including head
- **Pattern**: Create dummy, use dummy.next as new head

## Why Use Dummy Nodes?

**Without Dummy Node:**

```java
// Need special handling for head
if (head == null) return null;
if (head.val == target) {
    head = head.next;  // Special case
}
ListNode curr = head;
while (curr.next != null) {
    if (curr.next.val == target) {
        curr.next = curr.next.next;
    } else {
        curr = curr.next;
    }
}
```

**With Dummy Node:**

```java
// Uniform handling
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode curr = dummy;

while (curr.next != null) {
    if (curr.next.val == target) {
        curr.next = curr.next.next;
    } else {
        curr = curr.next;
    }
}
return dummy.next;
```

## Common Use Cases

### Use Case 1: Removing Elements

Remove all nodes with a specific value.

```java
public class RemoveElements {
    
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        
        return dummy.next;
    }
}
```

### Use Case 2: Merge Two Sorted Lists

Merge two sorted linked lists.

```java
public class MergeLists {
    
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        // Attach remaining nodes
        curr.next = (l1 != null) ? l1 : l2;
        
        return dummy.next;
    }
}
```

### Use Case 3: Partition List

Partition list around value x.

```java
public class PartitionList {
    
    public static ListNode partition(ListNode head, int x) {
        ListNode lessHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);
        
        ListNode less = lessHead;
        ListNode greater = greaterHead;
        
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }
        
        greater.next = null;  // Important: prevent cycle
        less.next = greaterHead.next;
        
        return lessHead.next;
    }
}
```

### Use Case 4: Remove Nth Node from End

Remove the nth node from the end of the list.

```java
public class RemoveNthFromEnd {
    
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // Remove the node
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}
```

### Use Case 5: Insertion Sort List

Sort a linked list using insertion sort.

```java
public class InsertionSortList {
    
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            
            // Find insertion position
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }
            
            // Insert curr
            curr.next = prev.next;
            prev.next = curr;
            
            curr = next;
        }
        
        return dummy.next;
    }
}
```

### Use Case 6: Add Two Numbers

Add two numbers represented by linked lists.

```java
public class AddTwoNumbers {
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        
        return dummy.next;
    }
}
```

## Standard Template

```java
public ListNode operation(ListNode head) {
    // Create dummy node
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    
    // Perform operations using dummy
    ListNode curr = dummy;
    while (curr.next != null) {
        // Process curr.next
        // Modify pointers as needed
    }
    
    // Return new head
    return dummy.next;
}
```

## Multiple Dummy Nodes

Some problems require multiple dummy nodes to maintain separate lists.

```java
public ListNode splitList(ListNode head) {
    ListNode evenHead = new ListNode(0);
    ListNode oddHead = new ListNode(0);
    
    ListNode even = evenHead;
    ListNode odd = oddHead;
    
    while (head != null) {
        if (head.val % 2 == 0) {
            even.next = head;
            even = even.next;
        } else {
            odd.next = head;
            odd = odd.next;
        }
        head = head.next;
    }
    
    even.next = null;
    odd.next = null;
    
    // Now evenHead.next and oddHead.next point to separate lists
    return evenHead.next;
}
```

## Key Benefits

**Eliminates Head Special Cases**

No need to check if removing or modifying the head node.

**Uniform Node Treatment**

All nodes including head can be accessed via prev.next pattern.

**Simplifies Pointer Logic**

Always have a previous node to work with.

**Cleaner Code**

Reduces conditional branches and edge case handling.

## Common Patterns

**Pattern 1: Single List Modification**

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode curr = dummy;

while (curr.next != null) {
    // Process or modify curr.next
}

return dummy.next;
```

**Pattern 2: Building New List**

```java
ListNode dummy = new ListNode(0);
ListNode curr = dummy;

while (condition) {
    curr.next = new ListNode(value);
    curr = curr.next;
}

return dummy.next;
```

**Pattern 3: Merging Lists**

```java
ListNode dummy = new ListNode(0);
ListNode curr = dummy;

while (list1 != null || list2 != null) {
    // Choose node from list1 or list2
    curr.next = chosenNode;
    curr = curr.next;
}

return dummy.next;
```

## Common Pitfalls

**Forgetting to Return dummy.next**

Always return `dummy.next`, not `dummy`.

**Not Setting Last Node's Next to Null**

When building or partitioning lists, ensure the last node points to null to prevent cycles.

```java
// Correct
lastNode.next = null;
```

**Moving Dummy Instead of Curr**

Keep dummy stationary, use a separate pointer for traversal.

```java
// Wrong
while (dummy.next != null) {
    dummy = dummy.next;
}

// Correct
ListNode curr = dummy;
while (curr.next != null) {
    curr = curr.next;
}
```

**Incorrect Dummy Value**

Choose dummy value that won't interfere with logic. Common choices: 0, -1, Integer.MIN_VALUE.

## When to Use Dummy Nodes

Use dummy nodes when:

- Operations may modify the head
- Removing nodes from the list
- Building a new list incrementally
- Merging or partitioning lists
- Need uniform treatment of all nodes
- Want to avoid head special cases
