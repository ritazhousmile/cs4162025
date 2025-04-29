package postfix;
<pre>
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


public class BinarySearchTree {
    TreeNode root;

    // 插入节点
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) return new TreeNode(value);
        if (value < root.value)
            root.left = insertRec(root.left, value);
        else if (value > root.value)
            root.right = insertRec(root.right, value);
        return root;
    }

    // 中序遍历（递增顺序）
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    // 查找节点
    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(TreeNode root, int value) {
        if (root == null) return false;
        if (root.value == value) return true;
        return value < root.value
            ? searchRec(root.left, value)
            : searchRec(root.right, value);
    }

    
}

public class MyHashMap {
    private Entry[] table; // 存储 Entry 的数组
    private int capacity = 16; // 默认大小

    public MyHashMap() {
        table = new Entry[capacity];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // 添加或更新键值对
    public void put(String key, Integer value) {
        int index = hash(key);
        Entry head = table[index];

        // 遍历链表，查找是否已存在
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value; // 更新
                return;
            }
            head = head.next;
        }

        // 头插法添加新节点
        Entry newNode = new Entry(key, value);
        newNode.next = table[index];
        table[index] = newNode;
    }

    // 根据 key 获取 value
    public Integer get(String key) {
        int index = hash(key);
        Entry head = table[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    // 根据 key 删除节点
    public void remove(String key) {
        int index = hash(key);
        Entry head = table[index];
        Entry prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    table[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                return;
            }
            prev = head;
            head = head.next;
        }
    }
}

</pre>