/**
 * Implementation of a Map using a LinkedList
 */
public class CSLinkedMap<K, V> implements CSMap<K, V> {
    
    private CSLinkedList<Entry> list;
    
    /**
     * Constructor for LinkedMap
     */
    public CSLinkedMap() {
        this.list = new CSLinkedList<>();
    }
    
    /**
     * Returns the LinkedList being used by this map
     * @return the LinkedList containing entries
     */
    public CSLinkedList<Entry> getList() {
        return list;
    }
    
    /**
     * Associates the given key with the given value in the map
     * @param key the key to associate with the value
     * @param value the value to associate with the key
     * @return the previous value associated with the key, or null
     */
    public V put(K key, V value) {
        // Look for existing entry with the same key
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            if (entry.getKey().equals(key)) {
                // Key exists, replace value and return old value
                V oldValue = entry.getValue();
                list.remove(i);
                list.add(new Entry(key, value));
                return oldValue;
            }
        }
        
        // Key doesn't exist, add new entry
        list.add(new Entry(key, value));
        return null;
    }
    
    /**
     * If the key is not already mapped, associates it with the given value
     * @param key the key to associate with the value
     * @param value the value to associate with the key
     * @return the current value associated with the key, or null
     */
    public V putIfAbsent(K key, V value) {
        // Look for existing entry with the same key
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            if (entry.getKey().equals(key)) {
                // Key exists, return current value without changing
                return entry.getValue();
            }
        }
        
        // Key doesn't exist, add new entry
        list.add(new Entry(key, value));
        return null;
    }
    
    /**
     * Returns string representation of the map
     * @return string in format "[(key1, val1), (key2, val2), ...]"
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Removes all elements from the map
     */
    public void clear() {
        list.clear();
    }
    
    /**
     * Returns the value for the specified key
     * @param key the key whose value to return
     * @return the value, or null if the key is not found
     */
    public V get(K key) {
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    /**
     * Checks if the map contains the specified key
     * @param key the key to check
     * @return true if the key exists, false otherwise
     */
    public boolean containsKey(K key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the map is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    /**
     * Removes the mapping for the specified key
     * @param key the key to remove
     * @return the value that was associated with the key, or null
     */
    public V remove(K key) {
        for (int i = 0; i < list.size(); i++) {
            Entry entry = list.get(i);
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                list.remove(i);
                return oldValue;
            }
        }
        return null;
    }
    
    /**
     * Returns the number of elements in the map
     * @return the number of elements
     */
    public int size() {
        return list.size();
    }
    
    /**
     * Inner class for map entries
     */
    public class Entry implements CSMap.Entry<K, V> {
        private K key;
        private V value;
        
        /**
         * Constructor for Entry
         * @param key the key
         * @param value the value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        /**
         * Returns this entry's key
         * @return the key
         */
        public K getKey() {
            return key;
        }
        
        /**
         * Returns this entry's value
         * @return the value
         */
        public V getValue() {
            return value;
        }
        
        /**
         * Returns a string representation of this Entry
         * @return string in format "(key, value)"
         */
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
        
        /**
         * Compares an object to this entry
         * @param o the object to compare
         * @return true if equal, false otherwise
         */
        public boolean equals(Object o) {
            if (o instanceof CSMap.Entry) {
                CSMap.Entry<?, ?> entry = (CSMap.Entry<?, ?>) o;
                return (key == null ? entry.getKey() == null : key.equals(entry.getKey())) &&
                    (value == null ? entry.getValue() == null : value.equals(entry.getValue()));
            }
            return key == null ? o == null : key.equals(o);
        }
    }
}