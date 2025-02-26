/**
* LinkedList lab.
* @author huan zhou
* @version 02/25
*/
public class LinkedList {

    /**
    * Internal Node class used for the linked list.
    */
    public class Node {
        String key;
        int value;
        Node next;

        /**
        * Node Constructor.
        * @param key The Key
        * @param value The Value
        */
        public Node(String key, int value) {
            this.key = key;
            this.value = value;
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
    * Add the key, value pair to the head of the linkedlist.
    * @param key The Key
    * @param val The Value
    *
    */
    public void addHead(String key, int val) {
        Node n = new Node(key, val);

        if (head == null) {
            head = n;
            tail = n;
        } else {
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
    static double average(LinkedList sList){
        if(sList.head == null){
            return 0.0;
        }
        
        Node curr = sList.head;

        int sum = 0;
        int count = 0;

        while(curr != null){
            sum += curr.value;
            count += 1;
            curr = curr.next;
        }

        return (double) sum/count;

    }

    public static boolean ordered(LinkedList sList) {
        if (sList.head == null || sList.head.next == null) {
            return true; // Empty or single-node list is always ordered
        }
    
        Node curr = sList.head;
        while (curr.next != null) { // ✅ Fixed condition
            if (curr.key.compareTo(curr.next.key) > 0) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }

    static LinkedList reversed(LinkedList sList){
        if (sList.head == null || sList.head.next == null) {
            return sList; // Empty list or single element is considered ordered
        }

        Node curr = sList.head;
        LinkedList newList = new LinkedList();
        while(curr != null){
            newList.addHead(curr.key, curr.value);
            curr = curr.next;
        }
        return newList;
    }

    static LinkedList compressList(LinkedList sList){
        LinkedList compressedList = new LinkedList();
        Node current = sList.head;
        if (sList.head == null || sList.head.next == null) {
            return sList; // Empty list or single element is considered ordered
        }

        while (current != null) {
            Node existingNode = compressedList.find(current.key);
            if (existingNode != null) {
                // If the key exists, add to its value
                existingNode.value += current.value;
            } else {
                // Otherwise, add a new node with the key and value
                compressedList.addTail(current.key, current.value);
            }
            current = current.next;
        }

        return compressedList;

    }

    ///////////////////////////////////////////////////////////////

}