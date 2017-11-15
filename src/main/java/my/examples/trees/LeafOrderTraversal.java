package my.examples.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeafOrderTraversal {

    public static boolean isSame(Node r1, Node r2) {

        if (r1 == null && r2 != null)
            return false;

        if (r1 != null && r2 == null)
            return false;

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(r1);
        s2.push(r2);

        while (!s1.isEmpty() || !s2.isEmpty()) {

            // As one stack is empty and another stack is not which means that the other stack have higher number of
            // leaves
            if (s1.isEmpty() || s2.isEmpty()) {
                return false;
            }

            Node temp1 = s1.pop();
            while (temp1 != null && !temp1.isLeaf()) {
                if (temp1.right != null) {
                    s1.push(temp1.right);
                }
                if (temp1.left != null) {
                    s1.push(temp1.left);
                }
                temp1 = s1.pop();
            }

            Node temp2 = s2.pop();
            while (temp2 != null && !temp2.isLeaf()) {
                if (temp2.right != null) {
                    s2.push(temp2.right);
                }
                if (temp2.left != null) {
                    s2.push(temp2.left);
                }
                temp2 = s2.pop();
            }

            if (temp1 == null && temp2 != null)
                return false;
            if (temp1 != null && temp2 == null)
                return false;

            if (temp1 != null && temp2 != null) {
                if (temp1.data != temp2.data)
                    return false;
            }
        }
        // If control reaches here, all the leaves are matched.
        return true;
    }

    public static boolean isSame1(Node r1, Node r2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        for (int level=1; level<=height(r1); level++) {
            collectLeafs(r1, level, leaves1);
        }

        for (int level=1; level<=height(r2); level++) {
            collectLeafs(r2, level, leaves2);
        }
        return leaves1.equals(leaves2);
    }

    public static void collectLeafs(Node node, int level, List<Integer> leaves) {
        if (node == null)
            return;

        if (level == 1 && node.isLeaf())
            leaves.add(node.data);
        else {
            collectLeafs(node.left, level-1, leaves);
            collectLeafs(node.right, level-1, leaves);
        }
    }

    public static int height(Node node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.left = new Node(2);
        n1.right = new Node(3);
        n1.left.left = new Node(4);
        n1.right.left = new Node(6);
        n1.right.right = new Node(7);

        Node n2 = new Node(0);
        n2.left = new Node(5);
        n2.right = new Node(8);
        n2.left.right = new Node(4);
        n2.right.left = new Node(6);
        n2.right.right = new Node(7);

        System.out.println("Is same : " + isSame1(n1, n2));

    }
}
