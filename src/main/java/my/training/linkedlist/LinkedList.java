package my.training.linkedlist;

public class LinkedList {

    static Node head;

    Node reverse(Node node) {
        Node prev = null;
        Node current = node;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
        return node;
    }

    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    Node sortedMerge(Node a, Node b) {
        Node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.data < b.data) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    void printReverse(Node head) {
        if (head == null)
            return;

        printReverse(head.next);
        System.out.print(head.data + " ");
    }

    int getNode(Node head1, Node head2) {
        int c1 = count(head1);
        int c2 = count(head2);

        int d;
        if (c1 > c2) {
            d = c1 - c2;
            return getIntersectingNode(d, head1, head2);
        } else {
            d = c2 -c1;
            return getIntersectingNode(d, head2, head1);
        }
    }

    int getIntersectingNode(int d, Node head1, Node head2) {
        Node current1 = head1;
        Node current2 = head2;

        for (int i=0; i<d; i++) {
            if (current1 == null)
                return -1;
            current1 = current1.next;
        }

        while (current1 != null && current2 != null) {
            if (current1.data == current2.data)
                return current1.data;

            current1 = current1.next;
            current2 = current2.next;
        }
        return -1;
    }

    int count(Node node) {
        int count = 0;
        Node current = node;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    Node removeDuplicates(Node node) {
        Node current = node;
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else { // advance if no duplicates
                current = current.next;
            }
        }
        return current;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.head = new Node(85);
        list.head.next = new Node(15);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(20);

        System.out.println("Given Linked list");
        list.printList(head);
        head = list.reverse(head);
        System.out.println("");
        System.out.println("Reversed linked list ");
        list.printList(head);

        System.out.println("Printing reversed linked list : \n");
        list.printReverse(head);

        LinkedList myList = new LinkedList();
        myList.head = new Node(11);
        myList.head.next = new Node(11);
        myList.head.next.next = new Node(12);
        myList.head.next.next.next = new Node(13);
        myList.head.next.next.next.next = new Node(13);

        System.out.println();
        myList.printList(head);

        myList.removeDuplicates(head);

        System.out.println();
        myList.printList(head);
    }
}
