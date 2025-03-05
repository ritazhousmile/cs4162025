//package stackLab;
/*
 * @author huan zhou
 */
public class Stack {
    private int[] stackArr;
    private int index;
    private static final int SIZE = 10;

    public Stack(){
        index = -1;
        stackArr = new int[10];
    }

    public boolean isEmpty(){
        return index == -1;
    }

    public boolean push(int m){
        if(index >= SIZE -1){
            return false; //check if stack array is full, if its full, then you can't push

        }
        stackArr[++index] = m;//Increment index and add value
        return true;
    }

    public int pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return stackArr[index--];
    }

    public int peek(){
        if (!isEmpty()) { // Check if stack is empty
            return stackArr[index];
        }
        return -1; // Return top element
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i >= 0; i--) {
            sb.append(stackArr[i]);
            if (i > 0) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
