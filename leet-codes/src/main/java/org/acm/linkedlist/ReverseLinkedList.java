package org.acm.linkedlist;

public class ReverseLinkedList {

    /**
     * reverse linked list I
     * @param head
     * @return
     */
    public ListNode reverseLinkedList(ListNode head) {

        if(head == null || head.next == null) return head;

        ListNode tail = null;
        //第一个指针指向head
        ListNode p = head;
        //第二个指针指向head的下一个Node
        ListNode q = p.next;

        while (q != null) {
            //存第三个节点
            ListNode old = q.next;
            //把第二个节点指向尾部
            p.next = tail;
            //把第三个节点指向前一个节点
            q.next = p;

            //存前一个到尾指针
            tail = p;
            //交换
            p = q;
            //剩余链赋给q
            q = old;
        }

        return p;
    }

    /**
     * reverse linked list II
     * @param head
     * @param m
     * @param n
     * 1<= m <= n <= len
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        //fast move to m
        for(int i = 0; i < m -1; ++i) {
            prev = prev.next;
        }
        //point m node
        ListNode head2 = prev;
        prev = head2.next;
        ListNode cur = prev.next;
        for(int i = m; i < n; ++i){
            prev.next = cur.next;
            cur.next = head2.next;
            head2.next = cur;
            cur = prev.next;
        }

        return dummy.next;
    }
}
