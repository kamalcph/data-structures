package my.examples.trees;

/**
 * Created by kamal on 7/14/17.
 */
public class BinaryTree {

    Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void levelOrder(Node node) {
        levelOrder(node, false);
    }

    public void levelOrder(Node node, boolean isChar) {
        int height = height(node);
        for (int level = 1; level <= height; level++) {
            printGivenLevel(node, level, isChar);
        }
    }

    private void printGivenLevel(Node node, int level, boolean isChar) {
        if (node == null)
            return;

        if (level == 1) {
            if (isChar)
                System.out.print((char) node.data + " ");
            else
                System.out.print(node.data + " ");
        } else {
            printGivenLevel(node.left, level - 1, isChar);
            printGivenLevel(node.right, level - 1, isChar);
        }
    }

    /**
     * The diameter of a tree (sometimes called the width) is the number of nodes on the longest path
     * between two leaves in the tree.
     * @param node
     * @return
     */
    public int diameter(Node node) {
        if (node == null)
            return 0;

        int lh = height(node.left);
        int rh = height(node.right);

        final int ldiameter = diameter(node.left);
        final int rdiameter = diameter(node.right);
        return Math.max((1 + lh + rh), Math.max(ldiameter, rdiameter));
    }

    /**
     * A tree where no leaf is much farther away from the root than any other leaf.
     * Different balancing schemes allow different definitions of “much farther” and different amounts of work to
     * keep them balanced.
     *
     * Consider a height-balancing scheme where following conditions should be checked to determine if a binary tree is balanced.
     * An empty tree is height-balanced. A non-empty binary tree T is balanced if:
     * 1) Left subtree of T is balanced
     * 2) Right subtree of T is balanced
     * 3) The difference between heights of left subtree and right subtree is not more than 1.
     *
     * The above height-balancing scheme is used in AVL trees.
     * @param node
     * @return
     */
    public boolean isBalanced(Node node) {
        if (node == null)
            return true;

        boolean isBalanced = Math.abs(height(node.left) - height(node.right)) <= 1;
        return isBalanced && isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * Given an arbitrary binary tree, convert it to a binary tree that holds Children Sum Property.
     * You can only increment data values in any node (You cannot change structure of tree and cannot
     * decrement value of any node.
     * http://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
     * @param node
     */
    public void convertTree(Node node) {
       if (node == null || (node.left == null && node.right == null))
           return;

       convertTree(node.left);
       convertTree(node.right);

       int leftData = node.left != null ? node.left.data : 0;
       int rightData = node.right != null ? node.right.data : 0;
       int diff = (leftData + rightData) - node.data;

       if (diff > 0) {
           node.data += diff;
       } else {
           incrementSubTree(node, -diff);
       }
    }

    private void incrementSubTree(Node node, int diff) {
        if (node.left != null) {
            node.left.data += diff;
            incrementSubTree(node.left, diff);
        } else if (node.right != null) {
            node.right.data += diff;
            incrementSubTree(node.right, diff);
        }
    }

    /**
     * Returns the height / depth of the tree
     * @param node
     * @return
     */
    public int height(Node node) {
        if (node == null)
            return 0;

        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Given a binary tree and a number, return true if the tree has a root-to-leaf path such that adding up all the
     * values along the path equals the given number. Return false if no such path can be found.
     * @param node
     * @param sum
     * @return
     */
    public boolean hasPathSum(Node node, int sum) {
        if (node == null) {
            return (sum == 0);
        }

        int subSum = sum - node.data;
        if (subSum == 0 && node.left == null && node.right == null) {
            return true;
        } else {
            return hasPathSum(node.left, subSum) || hasPathSum(node.right, subSum);
        }
    }

    /**
     * Construct a tree from the given In-order and Pre-order traversals
     * @param inorderSeq
     * @param preorderSeq
     * @return
     */
    int preIndex = 0;
    public Node constructTree(char[] inorderSeq, char[] preorderSeq, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        Node node = new Node(preorderSeq[preIndex++]);
        if (inStart == inEnd)
            return node;

        final int index = findIndex(inorderSeq, (char) node.data, inStart, inEnd);
        node.left = constructTree(inorderSeq, preorderSeq, inStart, index - 1);
        node.right = constructTree(inorderSeq, preorderSeq, index + 1, inEnd);
        return node;
    }

    /**
     * Construct a tree from the given In-order and Pre-order traversals
     * @param inorderSeq
     * @param postorderSeq
     * @return
     */
    int postIndex = 8;
    public Node constructTreeFromPostOrder(char[] inorderSeq, char[] postorderSeq, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;

        Node node = new Node(postorderSeq[postIndex--]);
        if (inStart == inEnd)
            return node;

        final int index = findIndex(inorderSeq, (char) node.data, inStart, inEnd);
        node.right = constructTreeFromPostOrder(inorderSeq, postorderSeq, index + 1, inEnd);
        node.left = constructTreeFromPostOrder(inorderSeq, postorderSeq, inStart, index - 1);
        return node;
    }

    private int findIndex(char[] array, char eltToFind, int start, int end) {
        int idx = -1;
        for (int i=start; i<=end; i++) {
            if (array[i] == eltToFind)
                return i;
        }
        return idx;
    }

    public Node doubleTree(Node node) {
        if (node == null)
            return null;

        Node xNode = new Node(node.data);
        xNode.left = node.left;
        node.left = xNode;

        doubleTree(xNode.left);
        doubleTree(node.right);
        return node;
    }

    public int sum(Node node) {
        if (node == null)
            return 0;
        return sum(node.left) + node.data + sum(node.right);
    }

    public boolean isSumTree(Node node) {
        if (node == null || (node.left == null && node.right == null))
            return true;

        return (node.data == (sum(node.left) + sum(node.right)) && isSumTree(node.left) && isSumTree(node.right));
    }

    public int maxWidth(Node node) {
        int maxWidth = -1;
        for (int height = 1; height <= height(node); height++) {
            final int width = getWidth(node, height);
            if (width > maxWidth)
                maxWidth = width;
        }
        return maxWidth;
    }

    private int getWidth(Node node, int height) {
        if (node == null)
            return 0;

        if (height == 1) {
            return 1;
        } else {
            return getWidth(node.left, height - 1) + getWidth(node.right, height - 1);
        }
    }

    public boolean isLeaf(Node node) {
        return (node.left == null && node.right == null);
    }

    public static void main(String[] args) {
        /**
         *  Creating a binary tree and entering the nodes
         *      1
         *     / \
         *    2   3
         *   /\
         *  4 5
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        BinaryTree tree = new BinaryTree(root);
        System.out.println("The diameter of given binary tree is : "
                + tree.diameter(root));

        System.out.println("Has Path Sum = 10 : " + tree.hasPathSum(root, 10));
        System.out.println("Has Path Sum = 8 : " + tree.hasPathSum(root, 8));

        char[] inorderSequence = "ABCDEFGHI".toCharArray();
        int seqLength = inorderSequence.length;
        System.out.print("Level order of node : ");
        tree.levelOrder(tree.constructTree(inorderSequence, "FBADCEGIH".toCharArray(), 0, seqLength - 1), true);
        System.out.print("\nLevel order of node : ");
        tree.levelOrder(tree.constructTreeFromPostOrder(inorderSequence, "ACEDBHIGF".toCharArray(), 0, seqLength - 1), true);

        System.out.println("\nLevel order of double tree : ");
        tree.levelOrder(tree.doubleTree(root));
    }
}
