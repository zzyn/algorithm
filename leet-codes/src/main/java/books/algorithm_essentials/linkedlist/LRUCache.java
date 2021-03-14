package books.algorithm_essentials.linkedlist;

import java.util.HashMap;

public class LRUCache {
    private int capacity;
    private final HashMap<Integer, Node> map;
    private Node head;
    private Node end;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if(map.containsKey(key)){
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);

        } else {
            Node created = new Node(key, value);
            if(map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
                setHead(created);
            } else {
                setHead(created);
            }
            map.put(key, created);
        }
    }

    private void remove(Node n) {
        if(n.prev != null) {
            n.prev.next = n.next;
        } else {
            head = n.next;
        }

        if(n.next != null) {
            n.next.prev = n.prev;
        } else {
            end = n.prev;
        }
    }

    private void setHead(Node n) {
        n.next = head;
        n.prev = null;
        if(head != null) head.prev = n;

        head = n;

        if(end == null) end = head;
    }
}

//double linked list
class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
