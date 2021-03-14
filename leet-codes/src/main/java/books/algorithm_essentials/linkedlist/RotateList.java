package books.algorithm_essentials.linkedlist;

public class RotateList {

    public ListNode rotateRight(ListNode head, int k){
        if(head == null || k == 0) return head;
        int len = 1;
        ListNode p = head;
        while (p.next != null) {
            len++;
            p = p.next;
        }

        k = len - k % 10;
        //首尾相连
        p.next = head;

        for(int step = 0; step < k; step++){
            p = p.next;
        }

        head = p.next; //新的首节点
        p.next = null;//断开环
        return head;
    }
}
