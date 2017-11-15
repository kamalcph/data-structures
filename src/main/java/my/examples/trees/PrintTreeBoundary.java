package in.co.nmsworks.nms.system.server;

public class PrintTreeBoundary {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

    public void printLeaves(TreeNode node) {
        if (node != null) {
            printLeaves(node.left);
            if (node.isLeaf())
                System.out.println(node.val + " ");
            printLeaves(node.right);
        }
    }

    public void printLeftBoundary(TreeNode node) {
        if (node != null) {
            if (node.left != null) {
                System.out.println(node.val);
                printLeftBoundary(node.left);
            } else if (node.right != null) {
                System.out.println(node.val);
                printLeftBoundary(node.right);
            }
            // leave the case for leaf
        }
    }

    public void printRightBoundary(TreeNode node) {
        if (node != null) {
            if (node.right != null) {
                printRightBoundary(node.right);
                System.out.println(node.val);
            } else if (node.left != null) {
                printRightBoundary(node.left);
                System.out.println(node.val);
            }
            // leave the case for leaf;
        }
    }

    public void printBoundary(TreeNode node) {
        System.out.println(node.val); // root;
        printLeftBoundary(node.left);
        printLeaves(node);
        printRightBoundary(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);

        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);

        PrintTreeBoundary boundary = new PrintTreeBoundary();
        boundary.printBoundary(root);
    }
}
