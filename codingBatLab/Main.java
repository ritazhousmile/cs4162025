
/**
 * @author HUan ZHou
 * @version 01/30/2025
 */

//import java.util.Arrays;

public class Main {

    public static boolean sleepIn(boolean weekday, boolean vacation) {
		return !weekday || vacation;
    }

    public static boolean nearHundred(int x) {
        //Your solution here
        return (x >= 90 && x <= 100) || (x >= 190 && x <= 200) || (x >= 100 && x <= 110) || (x >= 200 && x <= 210);

    }

    public static boolean sameFirstLast(int[] nums) {
        //Your solution here
        return nums.length >= 1 && nums[0] == nums[nums.length - 1];
    }

    public static boolean no23(int[] nums) {
        //Your solution here
        //return Arrays.stream(nums).anyMatch(num -> num == 2 || num == 3);
        return !(nums[0] == 2 || nums[1] == 3 || nums[0] == 3 || nums[1] == 2);
    }

    public static String helloName(String name) {
        //Your solution here
        return "Hello " + name + "!";
    }
}