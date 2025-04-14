/**
 * Implementation of a generic LinkedList
 */
public class CSLinkedList<E> {
    
    // Node class for linked list - made public for testing
    public class Node {
        public E value;
        public Node next;
        
        public Node(E value) {
            this.value = value;
            this.next = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    /**
     * Constructor for CSLinkedList
     */
    public CSLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Gets the head node of the list (for testing purposes)
     * @return the head node
     */
    public Node getHead() {
        return head;
    }
    
    /**
     * Gets the tail node of the list (for testing purposes)
     * @return the tail node
     */
    public Node getTail() {
        return tail;
    }
    
    /**
     * Adds an element to the end of the list
     * @param element the element to add
     * @return true (as per the Collection interface)
     */
    public boolean add(E element) {
        Node newNode = new Node(element);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        
        size++;
        return true;
    }
    
    /**
     * Adds an element at the specified index
     * @param index the index at which to add
     * @param element the element to add
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == size) {
            add(element);
            return;
        }
        
        Node newNode = new Node(element);
        
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        
        size++;
    }
    
    /**
     * Removes all elements from the list
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    /**
     * Checks if the list contains the specified element
     * @param o the element to check for
     * @return true if found, false otherwise
     */
    public boolean contains(Object o) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * Returns the element at the specified index
     * @param index the index of the element
     * @return the element
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.value;
    }
    
    /**
     * Checks if the list is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Removes the element at the specified index
     * @param index the index of the element to remove
     * @return the removed element
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        E removedValue;
        
        if (index == 0) {
            removedValue = head.value;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            
            removedValue = current.next.value;
            current.next = current.next.next;
            
            if (current.next == null) {
                tail = current;
            }
        }
        
        size--;
        return removedValue;
    }
    
    /**
     * Removes the first occurrence of the specified element
     * @param o the element to remove
     * @return true if found and removed, false otherwise
     */
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }
        
        if (head.value.equals(o)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }
        
        Node current = head;
        while (current.next != null) {
            if (current.next.value.equals(o)) {
                current.next = current.next.next;
                if (current.next == null) {
                    tail = current;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    /**
     * Sets the element at the specified index
     * @param index the index to set
     * @param element the element to set
     * @return the previous element
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        E oldValue = current.value;
        current.value = element;
        return oldValue;
    }
    
    /**
     * Returns the number of elements in the list
     * @return the number of elements
     */
    public int size() {
        return size;
    }
    
    /**
     * Returns a string representation of the list
     * @return a string representation
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }
}