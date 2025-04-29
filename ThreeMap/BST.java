//package ThreeMap;

@SuppressWarnings("rawtypes")
public class BST<T extends Comparable> implements CSTree<T> {

        private Node root;
        private int size;

        public class Node implements CSTree.Node<T> {
        private T value;
        private Node left;
        private Node right;
        private Node parent;

        public Node(T value) {
                this.value = value;
                this.left = null;
                this.right = null;
                this.parent = null;
        }

        public void setValue(T value) {
                this.value = value;
        }

        public T getValue() {
                return this.value;
        }

        public Node getLeft() {
                return this.left;
        }

        public Node getRight() {
                return this.right;
        }

        public Node getParent() {
                return this.parent;
        }
}

        public BST() {
        this.root = null;
        this.size = 0;
        }

        public Node getRoot() {
        return this.root;
        }
        
        @SuppressWarnings("unchecked")
        public boolean add(T value) {
                if (value == null) {
                return false;
                }
                
                if (root == null) {
                root = new Node(value);
                size++;
                return true;
                }
                
                Node current = root;
                Node parent = null;
                
                while (current != null) {
                parent = current;
                int comparison = current.value.compareTo(value);
                
                if (comparison > 0) {
                        current = current.left;
                } else if (comparison < 0) {
                        current = current.right;
                } else {
                        // Value already exists in the tree
                        return false;
                }
                }
                
                Node newNode = new Node(value);
                newNode.parent = parent;
                
                int comparison = parent.value.compareTo(value);
                if (comparison > 0) {
                parent.left = newNode;
                } else {
                parent.right = newNode;
                }
                
                size++;
                return true;
        }
        
        public String toString() {
                if (root == null) {
                return "";
                }
                StringBuilder sb = new StringBuilder();
                toString(root, sb, 0, "");
                return sb.toString();
        }
        
        private void toString(Node node, StringBuilder sb, int level, String label) {
                if (node == null) {
                return;
                }
                
                // Indentation spaces - 6 per level
                for (int i = 0; i < level * 6; i++) {
                sb.append(" ");
                }
                
                // Add label for non-root nodes
                if (!label.isEmpty()) {
                sb.append(label).append(" ");
                }
                
                // Add value
                sb.append(node.value).append("\n");
                
                // Recursively add children
                toString(node.left, sb, level + 1, "L");
                toString(node.right, sb, level + 1, "R");
        }
        
        public void clear() {
                root = null;
                size = 0;
        }
        
        @SuppressWarnings("unchecked")
        public T get(Object o) {
                Node node = findNode(o);
                return node != null ? node.value : null;
        }
        
        @SuppressWarnings("unchecked")
        private Node findNode(Object o) {
                if (o == null || root == null) {
                return null;
                }
                
                Node current = root;
                
                while (current != null) {
                int comparison = current.value.compareTo(o);
                
                if (comparison == 0) {
                        return current;
                } else if (comparison > 0) {
                        current = current.left;
                } else {
                        current = current.right;
                }
                }
                
                return null;
        }
        
        @SuppressWarnings("unchecked")
        public boolean contains(Object o) {
                return findNode(o) != null;
        }
        
        public boolean isEmpty() {
                return size == 0;
        }
        
        @SuppressWarnings("unchecked")
        public boolean remove(Object o) {
                Node node = findNode(o);
                
                if (node == null) {
                return false;
                }
                
                // Case 1: Node has no children
                if (node.left == null && node.right == null) {
                if (node == root) {
                        root = null;
                } else {
                        Node parent = node.parent;
                        if (parent.left == node) {
                        parent.left = null;
                        } else {
                        parent.right = null;
                        }
                }
                }
                // Case 2: Node has one child
                else if (node.left == null) {
                replaceNode(node, node.right);
                } else if (node.right == null) {
                replaceNode(node, node.left);
                }
                // Case 3: Node has two children
                else {
                Node parent = node.parent;
                boolean isLeftChild = parent != null && parent.left == node;
                boolean isRoot = node == root;
                
                if (isRoot || isLeftChild) {
                        // If root or left child, replace with left child
                        Node leftChild = node.left;
                        replaceNode(node, leftChild);
                        
                        // Find the rightmost node in the left subtree
                        Node rightmost = leftChild;
                        while (rightmost.right != null) {
                        rightmost = rightmost.right;
                        }
                        
                        // Move the right subtree to the rightmost node
                        rightmost.right = node.right;
                        if (node.right != null) {
                        node.right.parent = rightmost;
                        }
                } else {
                        // If right child, replace with right child
                        Node rightChild = node.right;
                        replaceNode(node, rightChild);
                        
                        // Find the leftmost node in the right subtree
                        Node leftmost = rightChild;
                        while (leftmost.left != null) {
                        leftmost = leftmost.left;
                        }
                        
                        // Move the left subtree to the leftmost node
                        leftmost.left = node.left;
                        if (node.left != null) {
                        node.left.parent = leftmost;
                        }
                }
                }
                
                size--;
                return true;
        }
        
        private void replaceNode(Node oldNode, Node newNode) {
                if (oldNode == root) {
                root = newNode;
                if (newNode != null) {
                        newNode.parent = null;
                }
                } else {
                Node parent = oldNode.parent;
                if (parent.left == oldNode) {
                        parent.left = newNode;
                } else {
                        parent.right = newNode;
                }
                
                if (newNode != null) {
                        newNode.parent = parent;
                }
                }
        }
        
        public int size() {
                return size;
        }
}