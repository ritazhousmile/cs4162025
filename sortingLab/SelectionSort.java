

/**
 * Selection sort implementation that sorts samples in descending order
 * based on measurement.
 * @author huan zhou rita
 * @version 1.0
 */
public class SelectionSort extends Sort {
    
    /**
     * Constructor that calls the parent constructor.
     * @param filename The name of the file to load
     */
    public SelectionSort(String filename) {
        super(filename);
    }
    
    /**
     * Sorts the samples in descending order using selection sort algorithm.
     */
    @Override
    public void sort() {
        int n = samples.size();
        
        // Selection sort algorithm
        for (int i = 0; i < n - 1; i++) {
            // Find the maximum element in unsorted array (changed from minimum for descending)
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (samples.get(j).measurement > samples.get(maxIdx).measurement) {
                    maxIdx = j;
                }
            }
            
            // Swap the found maximum element with the first element
            Sample temp = samples.get(maxIdx);
            samples.set(maxIdx, samples.get(i));
            samples.set(i, temp);
        }
    }
}