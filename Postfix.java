/*
 * @author huan zhou 
 */

import java.util.Scanner;
import java.util.Stack;
 
public class Postfix {
    public static char checkValidOperator(String input) {
        switch (input) {
            case "+":
            case "-":
            case "*":
            case "/":
                return input.charAt(0);
            default:
                return '?';
        }
    }

    public static int execute(Stack<Integer> stack, char operator) {
        int rightOperand = stack.pop();
        int leftOperand = stack.pop();
        int result = 0;

        switch (operator) {
            case '+':
                result = leftOperand + rightOperand;
                break;
            case '-':
                result = leftOperand - rightOperand;
                break;
            case '*':
                 result = leftOperand * rightOperand;
                break;
            case '/':
                result = leftOperand / rightOperand;
                break;
        }

        stack.push(result);
        return result;
    }

    public static Number evaluateExpression(String expr) {
        String tooFewOperands = "Too few operands";
        String unknownOperator = "Unknown operator: ";
        String tooManyOperands = "Too many operands.";

        Stack<Integer> stack = new Stack<>();
        String errMsg = null;
        Scanner scanner = new Scanner(expr);

        while (scanner.hasNext() && errMsg == null) {
            if (scanner.hasNextInt()) {
                int operand = scanner.nextInt();
                System.out.println("Operand read: " + operand);
                stack.push(operand);
            } else {
                String token = scanner.next();
                char operator = checkValidOperator(token);
 
                if (operator != '?') {
                    System.out.println("Operator read: " + operator);
                    if (stack.size() >= 2) {
                        execute(stack, operator);
                    } else {
                        errMsg = tooFewOperands;
                    }
                } else {
                    errMsg = unknownOperator + token;
                }
            }

            System.out.println("------ Stack state -----");
            System.out.println(stack);
        }

        if (errMsg != null) {
            System.out.println("Failed evaluation of |" + expr + "|\n" + errMsg);
            return null;
        }

        if (stack.size() > 1) {
            System.out.println("Failed evaluation of |" + expr + "|\n" + tooManyOperands + stack);
            return null;
        }

        return stack.peek();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a postfix expression");
        String input = scanner.nextLine();

        Number result = evaluateExpression(input);
        if (result != null) {
            System.out.println("Expression: " + input + " --> " + result);
        }
    }
    
}
 