

public class CSLinkedList<T> extends CSList<T> {
    private Node head;
    private Node tail;
    private int size;

    public CSLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public boolean add(T value) {
        if (contains(value)) return false;
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    public void add(int index, T value) {
        if (contains(value) || index < 0 || index > size) return;
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head;
            if (head != null) head.prev = newNode;
            head = newNode;
            if (size == 0) tail = newNode;
        } else if (index == size) {
            add(value);
            return;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public T get(Object o) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(o)) return current.value;
            current = current.next;
        }
        return null;
    }

    public boolean contains(Object o) {
        return get(o) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) return null;
        Node current = head;
        if (index == 0) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            if (current.next != null) current.next.prev = current.prev;
            if (current.prev != null) current.prev.next = current.next;
            if (current == tail) tail = current.prev;
        }
        size--;
        return current.value;
    }

    public boolean remove(Object o) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(o)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                    else tail = null;
                } else {
                    current.prev.next = current.next;
                    if (current.next != null) current.next.prev = current.prev;
                    if (current == tail) tail = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}
