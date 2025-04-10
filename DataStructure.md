## Data Structures

# abstract data type (ADT) that captures the key ideas of a data structure and can be used in many different situations

## LinkedList
- a list is a sequence of items with an index or position for each item.
- The first item is at the head of the list
- The last value is at the tail of the list
-  When items are added/removed, subsequent items are shifted as necessary

## Linked List Structure
- A node is commonly used to refer to one of the objects in a linked data structure
- Objects of type Node can be chained together as shown in the following illustration
- The last node in a list can be identified by the instance variable next holds the value null
- Each node holds a String and a reference to the next node in the list if any
- class Node {
    String item;
    Node next;
}
- Common operations on linked lists include:
    - Deleting nodes from the list
    - Inserting new nodes into the list
    - Searching for a specified value among the items in the list
- For a linked list to be used in a program, we need a variable that refers to the first node in the list, This is commonly referred to as the head of the list

- We can access all the other nodes of the list starting at the head and following the links (references) from one node to the next
- head has two states:
    - References the first Node in the list
    - null (an empty list)


## Linked List Traversal
- The approach is to Start at the head of the list
- Move from each node to the next following the reference in the node
- Stopping when the null that marks the end of the list is reached
- Node temp = head;
while( temp != null ) {
    process(temp.item);
    temp = temp.next;
}
- Our only access to the list is through the variable head
- Start by getting a copy of the value head (Node temp = head)
- We need a copy of head because we are going to change what temp refers too
- We can’t change the value of head or we lose access to the list!
- The variable temp will refer to each node in the list in turn
- When temp references one of the the nodes in the list, temp.next is a reference to the next node in the list
- temp = temp.next “moves” the reference along the list
- We know we reach the end of the list when temp becomes equal to null
    - Node temp = head;
    while( temp != null ) {
        System.out.println(temp.item);
    temp = temp.next;
    }
- for(Node temp = head; temp != null; temp = temp.next) {
        System.out.println(temp.item);
    }

# Inserting into a Linked List
- General Idea:
- keep two references while we traverse the list finding the correct position
- Delink the two references Link in the new Node
- Inserting the new item at the front of the list
- Inserting the new item into an empty list
- Inserting the new item to the end of the list



## stack
- A Stack consists of a sequence of items Thought of as piled one on top of the other like a physical stack of boxes or plates in a cafeteria
- Only the top item on the stack is accessible at any given time
- Top item can be removed from the stack using the pop operation
- An item lower down on the stack can only be removed after all the items on top of it have been popped off the stack
- New item can be added to the top of the stack with a push operation
- We can make a stack of any type of items
- It is an error to try to pop an item from an empty stack
Need to be able to tell whether a stack is empty
- push, pop, isEmpty
- In a LinkedList implementation of a Stack, the top of the stack can be the node at the head of the list
- We could use an array to implement a Stack ADT
- If the counter is called top, then the items on the stack are stored in positions 0, 1, …. top-1, in the array
Item 0 is the bottom and top-1 is the top of the stackt
<pre lang="markdown">
```javas
public class StackOfInts {

   /**
    * An object of type Node holds one of the items in the linked list 
    * that represents the stack.
    */
   private static class Node {
      int item;
      Node next;
   }
   
   private Node top;  // Pointer to the Node that is at the top of
                      //   of the stack.  If top == null, then the
                      //   stack is empty.
   
   /**
    * Add N to the top of the stack.
    */
   public void push( int N ) {
      Node newTop;         // A Node to hold the new item.
      newTop = new Node();
      newTop.item = N;     // Store N in the new Node.
      newTop.next = top;   // The new Node points to the old top.
      top = newTop;        // The new item is now on top.
   }
   
   /**
    * Remove the top item from the stack, and return it.
    * Throws an IllegalStateException if the stack is empty when
    * this method is called.
    */
   public int pop() {
      if ( top == null )
         throw new IllegalStateException("Can't pop from an empty stack.");
      int topItem = top.item;  // The item that is being popped.
      top = top.next;          // The previous second item is now on top.
      return topItem;
   }
   
   /**
    * Returns true if the stack is empty.  Returns false
    * if there are one or more items on the stack.
    */
   public boolean isEmpty() {
      return (top == null);
   }

} // end class 
```
</pre>


Here is a second implementation of the StackOfInts class, using a dynamic array:
<pre lang="markdown">
```javas
import java.util.Arrays;  // For the Arrays.copyOf() method.

public class StackOfInts {  // (alternate version, using an array)

   private int[] items = new int[10];  // Holds the items on the stack.
   
   private int top = 0;  // The number of items currently on the stack.
   
   /**
    * Add N to the top of the stack.
    */
   public void push( int N ) {
       if (top == items.length) {
              // The array is full, so make a new, larger array and
              // copy the current stack items into it. 
           items = Arrays.copyOf( items, 2*items.length );
       }
       items[top] = N;  // Put N in next available spot.
       top++;           // Number of items goes up by one.
   }
   
   /**
    * Remove the top item from the stack, and return it.
    * Throws an IllegalStateException if the stack is empty when
    * this method is called.
    */
   public int pop() {
       if ( top == 0 )
          throw new IllegalStateException("Can't pop from an empty stack.");
       int topItem = items[top - 1];  // Top item in the stack.
       top--;    // Number of items on the stack goes down by one.
       return topItem;
   }
   
   /**
    * Returns true if the stack is empty.  Returns false
    * if there are one or more items on the stack.
    */
   public boolean isEmpty() {
      return (top == 0);
   }

} // end class StackOfInts

```
</pre>


## Queues
- Similar to a Stack in that it consists of a sequence of items
    - There are restrictions about how items can be added or removed from the list
- Has two ends: The front and the back of the queue
- Items are added to the queue at the back Called enqueue
- Items are removed from the queue at the front Called dequeue
- Might also be called push/pop by others

- Queue Behavior
    - An item that is added to the back of the queue will remain until all the items in front of it have been removed
- Queue operations
    - All operation take place at one end of the queue or the other
    - enqueue - adds an item to the “back” of the queue
    - dequeue - removes an item from the “front” and returns it
- A queue can be implemented as a linked list or as an array.
- Queue - Linked List Implementation
    - The first item in the list is at the front of the queue
    - Dequeue-ing an item from the from the front of the queue is just like popping an item off the stack.
    - Enqueue-ing an item involves setting a reference in the last node of the current list to a new node that contains the item we want to add to the queue

- Stacks v.s. Queues
    - Queues implement the  FIFO policy
        - First In, First Out 
     Stacks implement the LIFO policy
        - Last In, First Out
        - The item that comes out of the stack is the last one that was put in


<pre lang="markdown">
```java
public class QueueOfInts {

   /**
    * An object of type Node holds one of the items
    * in the linked list that represents the queue.
    */
   private static class Node {
      int item;
      Node next;
   }

   private Node head = null;  // Points to first Node in the queue.
                              // The queue is empty when head is null.
   
   private Node tail = null;  // Points to last Node in the queue
                              // when the queue is not empty.

   /**
    * Add N to the back of the queue.
    */
   public void enqueue( int N ) {
      Node newTail = new Node();  // A Node to hold the new item.
      newTail.item = N;
      if (head == null) {
            // The queue was empty.  The new Node becomes
            // the only node in the list.  Since it is both
            // the first and last node, both head and tail
            // point to it.
         head = newTail;
         tail = newTail;
      }
      else {
            // The new node becomes the new tail of the list.
            // (The head of the list is unaffected.)
         tail.next = newTail;
         tail = newTail;
      }
   }
   
   /**
    * Remove and return the front item in the queue.
    * Throws an IllegalStateException if the queue is empty.
    */
   public int dequeue() {
      if ( head == null)
          throw new IllegalStateException("Can't dequeue from an empty queue.");
      int firstItem = head.item;
      head = head.next;  // The previous second item is now first.
                         // If we have just removed the last item,
                         // then head is null.
      if (head == null) {
            // The queue has become empty.  The Node that was
            // deleted was the tail as well as the head of the
            // list, so now there is no tail.  (Actually, the
            // class would work fine without this step.)
         tail = null;
      } 
      return firstItem;
   }
   
   /**
    * Return true if the queue is empty.
    */
   boolean isEmpty() {
      return (head == null);
   }
   
} // end class QueueOfInts


```
</pre>

## TREE

- In a binary tree each Node of the tree has two references (typically called left and right)
- class TreeNode {
    int item;
    TreeNode left;
    TreeNode right;
}
- The left and right references in a TreeNode can be either null or refer to another TreeNode object
- A node that “points” to another node is said to be the parent and the node it points to is called a child 
- A child is either a “left child” or a “right child”
- Nodes can have:
    - 0 children
    - 1 child (either a left or right)
    - 2 children
- A binary tree must have the following properties:
- There is exactly one node in the tree which has no parent This is called the root of the tree
- Every other node has exactly on parent
- There can be no loops in a binary tree (typically)
- A node that has no children is called a leaf  Both the left and right references are null


# M-ary Trees
- Not all Trees have just 2 children
- An m-ary tree is a tree where each node has no more than m children
- A binary is an m-ary tree, where m=2












## Binary search Tree

## Delete a node 
- step 1 find 
- step 2 determine configuration
- detect number of children the target node has 
    - no children: left == null && right == null
    - one child: left == null || right == null
    - two children left != null && right != null 
- delete a node with 0 child
    - need to reset parent's child pointer to null
    - determine which of parent's reference left or right is child being deleted 
    - could compare child values to value being deleted
    - could keep boolean variable  for last direction moved 
    - change parents reference from child to null 
- delete a node with 1 child 
    - preparing to delete the node 
        - when find the parent , need to reroute the child pointer to child's child, 
        - determine which of child's references is not null
        - change parents reference to the new child 
- delete a node with 2 children 
    - 
