
/**
 * @author huan zhou
 * @version 03/27
 */
public class Recursion {

    public static boolean isPalindrome(String input){
        if ( input.length()<= 1 ){
            return true;
        } else if (input.charAt(0) != input.charAt(input.length()-1)){
            return false;
        }else {
        String newInput = input.substring(1, input.length()-1);
            return isPalindrome(newInput);
        }
    }
    /*
    * 
    Return the number of occurrences of the character "letter" in the string “input”.
    Base case 1: The length of the string is 0; there are no characters left in the string that match the letter parameter; return 0.
    Recursion case: If the first character doesn’t match the parameter letter, return the result of calling countLetter on the rest of the string; 
    if it does match, return 1 + the result of calling countLetter on the rest of the string.
    */
    public static int countLetter( String input, char letter ) {
        if (input.length() == 0) {
            return 0;
        }

        // Check if first character matches the letter
        int count = (input.charAt(0) == letter) ? 1 : 0;

        // Recursive call on the rest of the string (excluding first character)
        return count + countLetter(input.substring(1), letter);
    }
    

    /*
     * Find the max value in the array list[0,...,n-1]; n is the number of elements in the portion of the array being tested.

Hint: Math.max() is useful here

Base case: n = 1; the only element of the array must be the max, so return it.
Recursion case: For n > 1, return the larger of the “last” element of 
(this portion of) the array and the maxValue of everything before it in the array.


     */
public static int maxValue( int[] list, int n ) {

    if (n == 1) {
        return list[0];
    }
        return Math.max(list[n - 1], maxValue(list, n - 1));
    }

}
