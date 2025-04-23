/**
 * ArraySearch class implementing the Search interface.
 * @param <T> Comparable Type
 */
public class ArraySearch<T extends Comparable<? super T>> implements Search<T> {
    
    // Instance variable to store the array
    private T[] list;
    
    /**
     * Constructor for ArraySearch.
     * @param list array of Comparable objects
     */
    public ArraySearch(T[] list) {
        this.list = list;
    }
    
    /**
     * @return true if there are no elements in the list
     */
    public boolean isEmpty() {
        return list == null || list.length == 0;
    }
    
    /**
     * @return the number of elements in the list
     */
    public int size() {
        return list == null ? 0 : list.length;
    }
    
    /**
     * @return true if the list is sorted in ascending order
     */
    public boolean isSorted() {
        if (isEmpty() || list.length == 1) {
            return true; // Empty list or single element list is always sorted
        }
        
        for (int i = 0; i < list.length - 1; i++) {
            // Use compareTo from Comparable interface
            if (list[i].compareTo(list[i + 1]) > 0) {
                return false; // If any element is greater than the next, not sorted
            }
        }
        return true;
    }
    
    /**
     * @param obj object to be searched for
     * @return the location of the element in the list starting at 0 or return -1 if not present
     */
    public int index(Object obj) {
        if (obj == null || isEmpty()) {
            return -1;
        }
        
        try {
            // Try to cast the object to type T
            @SuppressWarnings("unchecked")
            T element = (T) obj;
            
            // If the list is sorted, we can use binary search for efficiency
            if (isSorted()) {
                return binarySearch(element, 0, list.length - 1);
            } else {
                // Otherwise, use linear search
                return linearSearch(element);
            }
        } catch (ClassCastException e) {
            // If obj cannot be cast to type T, return -1
            return -1;
        }
    }
    
    /**
     * @param element element to search for
     * @param low starting index
     * @param high ending index
     * @return index of element if found, -1 otherwise
     */
    private int binarySearch(T element, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            int comparison = list[mid].compareTo(element);
            
            if (comparison == 0) {
                return mid; // Element found at index mid
            } else if (comparison > 0) {
                high = mid - 1; // Search in left half
            } else {
                low = mid + 1; // Search in right half
            }
        }
        
        return -1; // Element not found
    }
    
    /**
     * Helper method for linear search
     * @param element element to search for
     * @return index of element if found, -1 otherwise
     */
    private int linearSearch(T element) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].compareTo(element) == 0) {
                return i; // Element found at index i
            }
        }
        return -1; // Element not found
    }
}