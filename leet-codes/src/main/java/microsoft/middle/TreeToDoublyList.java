package microsoft.middle;

import java.util.Stack;

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

public class TreeToDoublyList {

    Node first = null;
    Node last = null;

    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return null;
        }
        //中序遍历
        dfs(root);
        //首尾相连
        first.left = last;
        last.right = first;
        return first;
    }

    private void dfs(Node node){
        if(node == null) return;
        dfs(node.left);
        //node
        if(last != null){
            // link the previous node (last)
            // with the current one (node)
            last.right = node;
            node.left = last;
        } else {
            first = node;
        }
        last = node;
        dfs(node.right);
    }

    public static void main(String[] args) {

        Node n1 = new Node(1);
        Node n3 = new Node(3);
        Node n2 = new Node(2);
        n2.left = n1;
        n2.right = n3;
        Node n5 = new Node(5);
        Node root = new Node(4);
        root.left = n2;
        root.right = n5;

        TreeToDoublyList s = new TreeToDoublyList();

        Node res = s.treeToDoublyList(root);

        System.out.println(res);

    }

}


