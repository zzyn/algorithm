package books.algorithm_essentials.linkedlist;

public class RemoveNthFromEndList {

    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p = dummy, q = dummy;
        for(int i = 0; i< n; i++){
            q = q.next;
        }

        while (q.next != null) {
            p = p.next;
            q = q.next;
        }

        p.next = p.next.next;
        return dummy.next;

    }
}
