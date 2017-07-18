package my.examples.trees;

/**
 * Created by kamal on 7/14/17.
 */
public class Node {

    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
