package my.examples.trees;

public class DetectAndRemoveLoopInLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    ListNode root;

    public boolean detectLoop(ListNode node) {
        ListNode fast_ptr = node, slow_ptr = node;
        while (fast_ptr != null && slow_ptr != null && fast_ptr.next != null) {
            fast_ptr = fast_ptr.next.next;
            slow_ptr = slow_ptr.next;

            if (fast_ptr == slow_ptr) { // Loop detected
                removeLoop(slow_ptr, node);
                return true;
            }
        }
        return false;
    }

    private void removeLoop(ListNode loop, ListNode head) {
        ListNode ptr1 = loop;
        ListNode ptr2 = loop;

        int k = 1; // count the number of nodes in the loop
        while (ptr2.next != ptr1) {
            ptr2 = ptr2.next;
            k++;
        }

        ptr1 = head;
        ptr2 = head;
        // move one pointer to k nodes after head
        for (int i=0; i<k; i++) {
            ptr2 = ptr2.next;
        }

        // Now both pointers meet at the same place where the loop begins
        while (ptr1 == ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        ptr2 = ptr2.next; // find the last node
        while (ptr2.next != ptr1) {
            ptr2 = ptr2.next;
        }

        ptr2.next = null; // removes the loop
    }
}
