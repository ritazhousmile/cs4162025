//package stackLab;

/*
 * @author huan zhou
 */
import java.util.ArrayList;

public class LabStack {

    public static ArrayList<Integer> popN (Stack st, int n){
        ArrayList<Integer> poppedItems = new ArrayList<>();
        while (!st.isEmpty()) {
            poppedItems.add(st.pop());
        }
        return poppedItems;
    }

        // popAll: Pops all items from the stack and returns them in an ArrayList
    public static ArrayList<Integer> popAll(Stack st) {
        ArrayList<Integer> poppedItems = new ArrayList<>();
        while (!st.isEmpty()) { // While stack is not empty
            poppedItems.add(st.pop()); // Pop and add to the list
        }
        return poppedItems; // Return the list of popped elements
    }

     // reverse: Reverses an array using a stack
    public static int[] reverse(int[] arr) {
        Stack stack = new Stack();
        for (int value : arr) {
            stack.push(value);
        }

        int[] reversedArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversedArray[i] = stack.pop();
        }

        return reversedArray;
    }

    public static void main(int[] args) {
        Stack st = new Stack();
        st.push(10);
        st.push(20);
        st.push(30);

        ArrayList<Integer> result = LabStack.popAll(st);
        System.out.println(result); // Expected Output: [30, 20, 10]
    }
}
