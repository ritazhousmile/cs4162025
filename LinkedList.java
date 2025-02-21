/*
 * find the sum of integers in a list 
 * searching a linked list 
 */


public class LinkedList {
    
    private IntNode head;

    // Inner class to represent a node
    public class IntNode {
        int item;
        IntNode next;
        // Constructor to create a new node
        public IntNode(int item) {
            this.item = item;
            this.next = null;
        }
    }

    //method to add a node at the beginning 
    public void insertAtBeginning(int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = head;
        head = newNode;
    }


    // Method to add a node at the end
    public void add(int value) {
        if (head == null) {
            head = new IntNode(value);
            return;
        }
        IntNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new IntNode(value);
    }

    // Method to calculate the sum of all nodes
    public int sum() {
        int sum = 0;
        IntNode temp = head;
        while (temp != null) {
            sum += temp.item;
            temp = temp.next;
        }
        return sum;
    }

    // Insert at a specific position
    public void insertAtPosition(int value, int position) {
        if (position == 0) {
            insertAtBeginning(value);
            return;
        }

        IntNode newNode = new IntNode(value);
        IntNode temp = head;

        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds!");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

         // Method to search for a value (Iterative)
    public boolean search(int target) {
        IntNode temp = head;
        while (temp != null) {
            if (temp.item == target) {
                return true; // Found the target
            }
            temp = temp.next;
        }
        return false; // Target not found
    }

     // Delete from the beginning
    public void deleteFromBeginning() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        head = head.next;
    }

    // Delete from the end
    public void deleteFromEnd() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (head.next == null) { // Only one node in the list
            head = null;
            return;
        }
        IntNode temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
    }
    // Delete by value
    public void deleteByValue(int value) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (head.item == value) { // If head is the target
            head = head.next;
            return;
        }
        IntNode temp = head;
        while (temp.next != null && temp.next.item != value) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Value not found!");
            return;
        }
        temp.next = temp.next.next;
    }

    // Main method to test the class
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("Sum of LinkedList: " + list.sum()); // Output: 60
    }
}