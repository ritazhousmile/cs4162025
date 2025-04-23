package postfix;
import java.util.Stack;

public class PostfixEvaluator {

    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();

        for (String token : expression.split(" ")) {
            if (token.matches("-?\\d+")) {
                // 是数字就压入栈
                stack.push(Integer.parseInt(token));
            } else {
                // 是操作符就弹出两个数进行运算
                int b = stack.pop();
                int a = stack.pop();
                int result;

                switch (token) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    case "/": result = a / b; break;
                    default: throw new IllegalArgumentException("无效操作符: " + token);
                }

                stack.push(result); // 把结果压回栈中
            }
        }

        return stack.pop(); // 栈顶元素即为最终结果
    }

    public static void main(String[] args) {
        String postfixExpr = "5 2 + 8 3 - *"; // 等价于 (5 + 2) * (8 - 3)
        int result = evaluatePostfix(postfixExpr);
        System.out.println("结果是: " + result); // 输出 35
    }
}
