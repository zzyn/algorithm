package books.algorithm_essentials.linkedlist;

public class SwapNodeInPairs {

    public  ListNode swapPairs(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for(ListNode prev = dummy, cur = prev.next, next = cur.next;
            next != null;
            prev = cur, cur = cur.next, next = cur != null ? cur.next: null
            ) {
            prev.next = next;
            cur.next = next.next;
            next.next = cur;
        }
        return dummy.next;
    }
}
