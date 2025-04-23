package postfix;

import java.util.*;

public class InfixToPostfix {

    // 运算符优先级
    private static int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    public static String toPostfix(String infix) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : infix.toCharArray()) {
            if (Character.isWhitespace(ch)) continue;

            if (Character.isLetterOrDigit(ch)) {
                output.append(ch).append(" ");
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(stack.pop()).append(" ");
                }
                stack.pop(); // 弹出 '('
            } else {
                // 是运算符
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            }
        }

        // 弹出剩余运算符
        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }

        return output.toString().trim();
    }

    public static void main(String[] args) {
        String infix = "(A+B)*(C-D)";
        String postfix = toPostfix(infix);
        System.out.println("中缀表达式: " + infix);
        System.out.println("后缀表达式: " + postfix);
    }
}