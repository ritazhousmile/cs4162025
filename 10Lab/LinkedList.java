/**
* LinkedList lab.
* @author huan zhou
* @version 1
*/
public class LinkedList {

    /**
    * Internal Node class used for the linked list.
    */
    public static class Node {
        String key;
        int value;
        Node next;
        Node prev;

        /**
        * Node Constructor.
        * @param key The Key
        * @param value The Value
        */
        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
        * Gets the next Node in the List.
        * @return Node
        */
        public Node getNext() {
            return next;
        }

        /**
        * Gets the previous Node in the List.
        * @return Node
        */
        public Node getPrev() {
            return prev;
        }

        /**
        * Gets the String Key.
        * @return String
        */
        public String getKey() {
            return key;
        }

        /**
        * Gets the Value.
        * @return int
        */
        public int getValue() {
            return value;
        }
    }

    private Node head;
    private Node tail;

    /**
    *  The default constructor.
    */
    public LinkedList() {
        head = null;
        tail = null;
    }

    /**
    * Gets the head of the List.
    * @return Node Head Node
    */
    public Node getHead() {
        return head;
    }

    /**
    * Gets the tail of the List.
    * @return Node Tail Node
    */
    public Node getTail() {
        return tail;
    }

    /**
    * Add the key, value pair to the head of the linkedlist.
    * @param key The Key
    * @param val The Value
    */
    public void addHead(String key, int val) {
        Node n = new Node(key, val);

        if (head == null) {
            head = n;
            tail = n;
        } else {
            head.prev = n;
            n.next = head;
            head = n;
        }
    }

    /**
    * Add the key, val pair to the tail of the linkedlist.
    * @param key The Key
    * @param val The Value
    */
    public void addTail(String key, int val) {
        Node n = new Node(key, val);

        if (tail == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
    }

    /**
    * Returns the String format of the linkedlist.
    * @return String The String format.
    */
    public String toString() {
        String ret = "";

        Node curr = head;

        while (curr != null) {
            if (curr.next != null) {
                ret += curr.key + ":" + curr.value + ", ";
            } else {
                ret += curr.key + ":" + curr.value;
            }

            curr = curr.next;
        }

        return ret;
    }

    /**
    * Locate the Node in the linkedlist with the given key.
    * @param key  The key to find in the LinkedList
    * @return Node Returns the Node with the given key or null if non-existent.
    */
    public Node find(String key) {
        Node curr = head;

        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr;
            }

            curr = curr.next;
        }

        return null;
    }


    //////////////////////// YOUR CODE HERE ////////////////////////
/*
 * A non-static void method called unlinkNode that takes a Node n as a parameter.  
 * It should ensure that the node is unlinked from the nodes before and after it. 
 * You will need to change the prev of the node after n and the next of the node before n. 
 * Remember that if n is the head of this list,
 *  it will have no node before it (but the value of head will need to move past it) and if n is the tail of this list, 
 * it will have no node after it (but the value of tail will need to move before it.)


    public void unlinkNode(Node n){
        if (n == head){
            head = head.next;
        }else if (n == tail){
            tail = tail.prev;
        } else {
            n.prev .next = n.next;
        }

    }
*/
    public void unlinkNode(Node n) {
        if (n == null) return; 
        
        if (n == head) {
            head = head.next; // Move head forward
            if (head != null) head.prev = null; // Ensure new head's prev is null
        } else if (n == tail) {
            tail = tail.prev; // Move tail backward
            if (tail != null) tail.next = null; // Ensure new tail's next is null
        } else {
            n.prev.next = n.next; // Skip over `n`
            n.next.prev = n.prev; // Maintain link integrity
        }
    
        // Help garbage collection
        n.next = null;
        n.prev = null;
    }

    /*
     * A non-static void method called addAfter that takes a Node n and a Node before as parameters. 
     * This method inserts n to be in the list just after the node named before. 
     * You will need to change the next from before, the prev from the node following before, and assign n's next and prev.
     
    public void addAfter(Node n, Node before){
        if (before == head){
            before.next = n;
            n.next = head.next;
        } else if (before == tail) {
            before.prev = n;
            n.prev = before.prev;
        }

    }
        */
    public void addAfter(Node n, Node before) {
        if (n == null || before == null) return; 
            n.next = before.next; // Link `n` to the node after `before`
            n.prev = before; // `n`'s prev should be `before`
            
            if (before.next != null) {
                before.next.prev = n; // The old next node should point back to `n`
            }
            
            before.next = n; // `before` should point to `n`
        
            // If `before` was the tail, now `n` is the new tail
            if (before == tail) {
                tail = n;
            }
        }

    /*
     * A non-static method called swapIfNeeded that returns a boolean and takes a Node n as a parameter. 
     * It compares the key of n with the key of the node after n, swapping the two if n's key is greater than the next node's key. 
     * Remember that if n is the tail of this list there is no node after, so it does not ever need to be swapped.
     *  It returns true if the nodes needed to be swapped, and false otherwise.
    *One easy way to swap the nodes would be to unlink n from the list and then add it after the other node 
    (which you would have to store in a temporary variable.)

     */

    public boolean swapIfNeeded(Node n) {
        if (n == null || n == tail || n.next == null) {
            return false; // No swap needed if n is null or the last node
        }
    
        if (n.key.compareTo(n.next.key) > 0) { // Check if swap is needed
            Node after = n.next; // Store the next node
            
            // Unlink `n` from the list
            unlinkNode(n);
    
            // Add `n` after `after`
            addAfter(n, after);
    
            return true; // Swap occurred
        }
        
        return false; // No swap needed
    }

    /*
     * A non-static void method called sort that takes no parameters. 
     * Its goal is to sort the list in ascending order by key.
     *  It will use an outer loop that continues until it is sure the list is sorted (for which you will need a boolean variable.) 
     * Inside this loop it then iterates through this list (starting at the head) and swaps any nodes that are out of order. 
     * If any swaps were needed, it records that the list is not sorted and continues the outer loop. 
     * It can stop the outer loop when an iteration through the list finishes without any swaps.
     */

    public void sort() {
        if (head == null || head.next == null) {
            return; // No sorting needed if list is empty or has only one node
        }
    
        boolean swapped;
        
        do {
            swapped = false; // Assume the list is sorted
            Node current = head;
    
            while (current != null && current.next != null) {
                if (current.key.compareTo(current.next.key) > 0) {
                    swapIfNeeded(current); // Swap if needed
                    swapped = true; // A swap happened, so we continue sorting
                } else {
                    current = current.next; // Move forward if no swap needed
                }
            }
            
        } while (swapped); // Repeat until no swaps occur in a full pass
    }

    ///////////////////////////////////////////////////////////////

}