package BinarySearchTree;

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
