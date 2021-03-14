package books.algorithm_essentials.linkedlist;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        int carry = 0;
        ListNode prev = dummy;
        for (ListNode pa = l1, pb = l2; pa != null || pb != null;
             pa = pa == null ? null : pa.next,
                     pb = pb == null ? null : pb.next,
                     prev = prev.next
        ) {
            final int ai = pa == null ? 0 : pa.val;
            final int bi = pb == null ? 0 : pb.val;
            final  int value = (ai + bi + carry) % 10;
            carry = (ai + bi + carry) / 10;
            prev.next = new ListNode(value);
        }

        if(carry > 0) {
            prev.next = new ListNode(carry);
        }

        return  dummy.next;
    }
}
