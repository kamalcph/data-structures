package in.co.nmsworks.nms.system.server;

public class SplitCircularLinkedList {

    Node head, head1, head2;

    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    public void split() {
        Node fast = head, slow = head;
        if (head == null)
            return;

        while (fast.next != head && fast.next.next != head) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // on even number of nodes moves the fast ptr to get the last node
        if (fast.next.next == head) {
            fast = fast.next;
        }

        head1 = head;
        if (head1.next != head) {
            head2 = slow.next;
        }

        fast.next = slow.next;
        slow.next = head1;

    }

    public void printList(Node node) {
        if (node != null) {
            Node temp = node;
            do {
                System.out.println(temp.data + " ");
                temp = temp.next;
            } while (temp != node);
        }
    }

    public static void main(String[] args) {

    }
}
