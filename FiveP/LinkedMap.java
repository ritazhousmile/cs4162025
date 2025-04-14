import java.util.Objects;

/**
 * LinkedMap implementation using LinkedList.
 * @param <K> Key type
 * @param <V> Value type
 */
public class LinkedMap<K, V> implements Map<K, V> {

     /**
     * Inner class to represent an Entry in the LinkedMap.
     * @param <K> Key type
     * @param <V> Value type
     */
    public static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        /**
         * Constructor for Entry.
         * @param key Key for the entry
         * @param value Value for the entry
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) o;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }
    }
    
    private LinkedList<Entry<K, V>> list;

    /**
     * Constructor for LinkedMap.
     */
    public LinkedMap() {
        this.list = new LinkedList<>();
    }

    /**
     * Returns the LinkedList being used by this map.
     * @return LinkedList<Entry<K, V>>
     */
    public LinkedList<Entry<K, V>> getList() {
        return list;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean containsKey(K key) {
        for (Entry<K, V> entry : list) {
            if (Objects.equals(entry.getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        for (Entry<K, V> entry : list) {
            if (Objects.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        for (Entry<K, V> entry : list) {
            if (Objects.equals(entry.getKey(), key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        list.add(new Entry<>(key, value));
        return null;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        for (Entry<K, V> entry : list) {
            if (Objects.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        list.add(new Entry<>(key, value));
        return null;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < list.size(); i++) {
            Entry<K, V> entry = list.get(i);
            if (Objects.equals(entry.getKey(), key)) {
                list.remove(i);
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}