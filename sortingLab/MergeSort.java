
/**
 * Merge sort implementation that sorts samples in descending order
 * based on measurement.
 * @author huan zhou rita
 * @version 1.0
 */
public class MergeSort extends Sort {
    
    /**
     * Constructor that calls the parent constructor.
     * @param filename The name of the file to load
     */
    public MergeSort(String filename) {
        super(filename);
    }
    
    /**
     * Sorts the samples in descending order using merge sort algorithm.
     */
    @Override
    public void sort() {
        mergeSort(0, samples.size() - 1);
    }
    
    /**
     * Helper method for merge sort recursion.
     * @param left The left index of the subarray
     * @param right The right index of the subarray
     */
    private void mergeSort(int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = left + (right - left) / 2;
            
            // Sort first and second halves
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            
            // Merge the sorted halves
            merge(left, mid, right);
        }
    }
    
    /**
     * Merges two subarrays in descending order.
     * @param left The left index of the subarray
     * @param mid The middle index of the subarray
     * @param right The right index of the subarray
     */
    private void merge(int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Create temp arrays
        Sample[] leftArray = new Sample[n1];
        Sample[] rightArray = new Sample[n2];
        
        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = samples.get(left + i);
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = samples.get(mid + 1 + j);
        }
            
        // Merge the temp arrays
        
        // Initial indices of first and second subarrays
        int i = 0;
        int j = 0;
        
        // Initial index of merged subarray
        int k = left;
        while (i < n1 && j < n2) {
            // Changed to >= for descending order
            if (leftArray[i].measurement >= rightArray[j].measurement) {
                samples.set(k, leftArray[i]);
                i++;
            } else {
                samples.set(k, rightArray[j]);
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of leftArray[] if any
        while (i < n1) {
            samples.set(k, leftArray[i]);
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray[] if any
        while (j < n2) {
            samples.set(k, rightArray[j]);
            j++;
            k++;
        }
    }
}