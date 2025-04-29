

/**
 * Bubble sort implementation that sorts samples in descending order
 * based on measurement.
 * @author huan Rita zhou
 * @version 1.0
 */
public class BubbleSort extends Sort {
    
    /**
     * Constructor that calls the parent constructor.
     * @param filename The name of the file to load
     */
    public BubbleSort(String filename) {
        super(filename);
    }
    
    /**
     * Sorts the samples in descending order using bubble sort algorithm.
     */
    @Override
    public void sort() {
        // Get the size of the samples ArrayList
        int n = samples.size();
        
        // Bubble sort algorithm
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements - changed to < for descending order
                if (samples.get(j).measurement < samples.get(j + 1).measurement) {
                    // Swap them if they are in wrong order
                    Sample temp = samples.get(j);
                    samples.set(j, samples.get(j + 1));
                    samples.set(j + 1, temp);
                }
            }
        }
    }
}