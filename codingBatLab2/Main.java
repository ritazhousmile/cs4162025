/**
 * @author: Rita huan Zhou
 * @version: 02/10/2025
 * 
 */
public class Main {

    public static boolean scoresIncreasing(int[] scores) {
        for(int i = 0; i < scores.length - 1 ; i++) {
            if (scores[i] > scores[i+1]) {
                return false;
            }

        }
        

        return true;
    }

    public static boolean scores100(int[] scores) {

        for(int i = 0; i < scores.length - 1 ; i++) {
            if (scores[i] == 100 &&  scores[i+1]==100) {
                return true;
            }

        }
        return false;
    }

    public static int wordsCount(String[] words, int len) {
        int count = 0;
        for (String word : words) {
            if (word.length() == len) {
                count ++;
            }

        }
        return count;
    }

    public static boolean hasOne(int n) {
        n = Math.abs(n);

        while( n > 0){
            if (n%10 == 1) {
                return true;
            }
            n = n / 10;
        }

        return false;
    }
}