package my.training.linkedlist;

public class Palindrome {

    Node left;

    public boolean isPalindrome(Node head) {
        left = head;
        return helper(head);
    }

    public boolean helper(Node right) {
        if (right == null)
            return true;

        final boolean isPalindrome = helper(right.next);
        if (!isPalindrome)
            return false;

        boolean result = left.data == right.data;
        left = left.next;
        return result;
    }

    public static void main(String[] args) {

    }
}
