package my.training.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesInUnSortedList {

    static Node head;

    void removeDuplicates() {
        Node outer = head, inner;
        while (outer != null) {
            inner = outer;
            while (inner.next != null) {
                if (outer.data == inner.next.data) { // duplicate remove it
                    inner.next = inner.next.next;
                } else { // advance
                    inner = inner.next;
                }
            }
            outer = outer.next;
        }
    }

    void removeDup() {
        Node prev = null;
        Node current = head;
        Set<Integer> set = new HashSet<>();
        while (current != null) {
            final int data = current.data;
            if (set.contains(data)) {
                prev.next = current.next;
            } else {
                prev = current;
                set.add(data);
            }
            current = current.next;
        }
    }

    void printList(Node node) {
        Node current = node;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    void pairwiseSwap(Node node) {
        Node current = node;
        while (current != null && current.next != null) {
            swap (current, current.next);
            current = current.next.next;
        }
    }

    void swap(Node n1, Node n2) {
        int temp = n1.data;
        n1.data = n2.data;
        n2.data = temp;
    }

    public static void main(String[] args) {
        RemoveDuplicatesInUnSortedList list = new RemoveDuplicatesInUnSortedList();
        list.head = new Node(10);
        list.head.next = new Node(12);
        list.head.next.next = new Node(11);
        list.head.next.next.next = new Node(11);
        list.head.next.next.next.next = new Node(12);
        list.head.next.next.next.next.next = new Node(11);
        list.head.next.next.next.next.next.next = new Node(10);

        list.printList(list.head);
        list.removeDup();

        list.printList(list.head);

        RemoveDuplicatesInUnSortedList xList = new RemoveDuplicatesInUnSortedList();
        xList.head = new Node(1);
        xList.head.next = new Node(2);
        xList.head.next.next = new Node(3);
        xList.head.next.next.next = new Node(4);
        xList.head.next.next.next.next = new Node(5);
        xList.head.next.next.next.next.next = new Node(6);
        xList.head.next.next.next.next.next.next = new Node(7);

        xList.printList(xList.head);
        xList.pairwiseSwap(xList.head);
        xList.printList(xList.head);

    }
}
