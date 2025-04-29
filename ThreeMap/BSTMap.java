


public class BSTMap<K extends Comparable, V> implements CSMap<K, V> {
    
    public class Entry<K extends Comparable, V> implements CSMap.Entry<K, V>, Comparable {
        private K key;
        private V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
        
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
        
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o instanceof Entry) {
                Entry<K, V> entry = (Entry<K, V>) o;
                return (key == null ? entry.key == null : key.equals(entry.key)) &&
                    (value == null ? entry.value == null : value.equals(entry.value));
            }
            return key == null ? o == null : key.equals(o);
        }
        
        @SuppressWarnings("unchecked")
        public int compareTo(Object o) {
            if (o instanceof Entry) {
                Entry<K, V> entry = (Entry<K, V>) o;
                return key.compareTo(entry.key);
            }
            return key.compareTo(o);
        }
    }
    
    private BST<Entry<K, V>> tree;
    
    public BSTMap() {
        tree = new BST<Entry<K, V>>();
    }
    
    public BST<Entry<K, V>> getTree() {
        return tree;
    }
    
    public V put(K key, V value) {
        if (key == null) {
            return null;
        }
        
        Entry<K, V> entry = new Entry<>(key, value);
        Entry<K, V> existingEntry = tree.get(key);
        
        if (existingEntry != null) {
            V oldValue = existingEntry.getValue();
            tree.remove(key);
            tree.add(entry);
            return oldValue;
        }
        
        tree.add(entry);
        return null;
    }
    
    public V putIfAbsent(K key, V value) {
        if (key == null) {
            return null;
        }
        
        Entry<K, V> existingEntry = tree.get(key);
        
        if (existingEntry != null) {
            return existingEntry.getValue();
        }
        
        Entry<K, V> entry = new Entry<>(key, value);
        tree.add(entry);
        return null;
    }
    
    public String toString() {
        return tree.toString();
    }
    
    public void clear() {
        tree.clear();
    }
    
    public V get(K key) {
        if (key == null) {
            return null;
        }
        
        Entry<K, V> entry = tree.get(key);
        return entry != null ? entry.getValue() : null;
    }
    
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        
        return tree.contains(key);
    }
    
    public boolean isEmpty() {
        return tree.isEmpty();
    }
    
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        
        Entry<K, V> entry = tree.get(key);
        
        if (entry != null) {
            V value = entry.getValue();
            tree.remove(key);
            return value;
        }
        
        return null;
    }
    
    public int size() {
        return tree.size();
    }
}