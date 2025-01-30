/**
 * @author Huan Zhou
 * @version 01/30/2025
 */
import java.util.Scanner;
public class BasicIOProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter two whole numbers separated by one or more spaces:");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();

        System.out.println("You entered " + num1 + " and " +num2);

        scanner.nextLine();

        System.out.println("Next Enter two numbers. A decimal point is OK.");
        double num3 = scanner.nextDouble();
        double num4 = scanner.nextDouble();
        System.out.println("You entered " + num3 + " and " +num4);

        scanner.nextLine();

        System.out.println("Next enter two words: ");
        String word1 = scanner.next();
        String word2 = scanner.next();
        System.out.println("You entered \"" + word1 + " \" and \" " + word2 + "\"");

        scanner.nextLine();

        System.out.println("Next enter a line of text:");
        String lineOfText = scanner.nextLine();
        System.out.println("You entered: \"" + lineOfText + "\"");

        scanner.close();
    


    }
    
}
