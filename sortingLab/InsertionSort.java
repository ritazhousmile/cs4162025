
/**
 * Insertion sort implementation that sorts samples in descending order
 * based on measurement.
 * @author huan zhou Rita
 * @version 1.0
 */
public class InsertionSort extends Sort {
    
    /**
     * Constructor that calls the parent constructor.
     * @param filename The name of the file to load
     */
    public InsertionSort(String filename) {
        super(filename);
    }
    
    /**
     * Sorts the samples in descending order using insertion sort algorithm.
     */
    @Override
    public void sort() {
        // Get the size of the samples ArrayList
        int n = samples.size();
        
        // Insertion sort algorithm
        for (int i = 1; i < n; i++) {
            Sample key = samples.get(i);
            int j = i - 1;
            
            // Modified comparison for descending order (< instead of >)
            while (j >= 0 && samples.get(j).measurement < key.measurement) {
                samples.set(j + 1, samples.get(j));
                j = j - 1;
            }
            samples.set(j + 1, key);
        }
    }
}