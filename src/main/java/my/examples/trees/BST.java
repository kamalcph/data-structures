package in.co.nmsworks.nms.system.server;

public class BST {

    Node root;

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

    static class Counter {
        int count;
    }

    void insert(int key) {
       root = insertUtil(root, key);
    }

    Node insertUtil(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.data) {
            node.left = insertUtil(node.left, key);
        } else {
            node.right = insertUtil(node.right, key);
        }
        return node;
    }

    void delete(int key) {
        root = deleteUtil(root, key);
    }

    private Node deleteUtil(Node node, int key) {
        if (node == null)
            return node;

        if (node.data == key) {
            // only one child or no child
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            node.data = minValue(node.right);
            node.right = deleteUtil(node.right, node.data);
        } else if (key < node.data) {
            node.left = deleteUtil(node.left, key);
        } else {
            node.right = deleteUtil(node.right, key);
        }
        return node;
    }

    private int minValue(Node node) {
        int min = node.data;
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    Node search(int key) {
        return searchUtil(root, key);
    }

    private Node searchUtil(Node node, int key) {
        if (node == null)
            return node;

        if (key == node.data) {
            return node;
        } else if (key < node.data) {
            return searchUtil(node.left, key);
        } else {
            return searchUtil(node.right, key);
        }
    }

    private void floorCeil(Node node, Node floor, Node ceil, int key) {
        if (node == null)
            return;

        if (node.data == key) {
            floor = node;
            ceil = node;
        } else if (key < node.data) {
            ceil = node;
            floorCeil(node.left, floor, ceil, key);
        } else {
            floor = node;
            floorCeil(node.right, floor, ceil, key);
        }
    }

    private void printRange(Node node, int k1, int k2) {
        if (node == null)
            return;

        if (k1 < node.data) {
            printRange(node.left, k1, k2);
        }

        if (k1 <= node.data & node.data <= k2) {
            System.out.print(node.data + "  ");
        }

        if (k2 > node.data) {
            printRange(node.right, k1, k2);
        }
    }

    private void kthLargestUtil(Node node, int k, Counter counter) {
        if (node == null || counter.count >= k)
            return;

        kthLargestUtil(node.right, k, counter);
        counter.count++;
        if (counter.count == k) {
            System.out.println(node.data);
            return;
        }
        kthLargestUtil(node.left, k, counter);
    }

    private void kthLargestUtil(Node node, int k) {
        kthLargestUtil(node, k, new Counter());
    }

    public static void main(String[] args) {
        BST tree = new BST();
        tree.root = new Node(20);
        tree.root.left = new Node(8);
        tree.root.right = new Node(22);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(12);

        int k1 = 10, k2 = 25;
        tree.printRange(tree.root, k1, k2);
        System.out.println();
        tree.kthLargestUtil(tree.root, 1);
    }
}
