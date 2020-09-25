package org.acm.linkedlist;

public class RemoveDuplicatesFromSortedList {

    /**
     * remove duplicates from sorted list I
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        for(ListNode prev = head, cur = head.next; cur != null; cur = prev.next){
            if(prev.val == cur.val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
        }
        return head;
    }

    /**
     * remove duplicates from sorted list II
     * @param head
     * @return
     */
    public ListNode deleteAllDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        dummy.next = head;
        ListNode prev = dummy, cur = head;
        while (cur != null) {
            boolean duplicated = false;
            while (cur.next != null && cur.val == cur.next.val) {
                duplicated = true;
                cur = cur.next;
            }

            if(duplicated) {
                cur = cur.next;
                continue;
            }

            prev.next = cur;
            prev = prev.next;
            cur = cur.next;
        }
        prev.next = cur;
        return dummy.next;
    }
}
