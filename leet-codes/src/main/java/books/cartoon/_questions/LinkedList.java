package books.cartoon._questions;

/**
 * 1.链表是否有环
 * 2.环的长度
 * 3.环的起点
 */
public class LinkedList {

    public static boolean isCycle(Node head){
        Node p1 = head;
        Node p2 = head;
        while (p2!=null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return true;
            }
        }
        return false;
    }

    public static int CycleLength(Node head){

        int len = 0;
        int time = 0;
        Node p1 = head;
        Node p2 = head;
        while (p2!=null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                time++;
                if(time == 2){
                    return len;
                }
            }
            if(time == 1) {
                len++;
            }
        }
        return 0;
    }

    public static Node CycleEnterPoint(Node head){
        Node p1 = head;
        Node p2 = head;
        Node result = null;
        while (p2!=null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                break;
            }
        }
        p2 = head;
        while (p1!=null && p2 != null){
            p1 = p1.next;
            p2 = p2.next;
            if(p1 == p2){
                result = p1;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        System.out.println(isCycle(node1));

        System.out.println(CycleLength(node1));

        System.out.println(CycleEnterPoint(node1));
    }
}

class Node {
    int data;
    Node next;
    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "[data="+ data + ", next=" + next.data + "]";
    }
}
