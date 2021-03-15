package leetcode.microsoft.middle;

/*
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.

 */
public class $1650_LowestCommonAncestorOfBinaryTreeIII {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestorV1(Node p, Node q) {
        Node tmpP = p;
        while(tmpP.parent != null){
            tmpP = tmpP.parent;
        }
        tmpP.parent = q;

        Node slow = p;
        slow = slow.parent;
        Node fast = p;
        fast = fast.parent.parent;
        while(slow!=fast){
            slow = slow.parent;
            fast = fast.parent.parent;
        }
        slow = p;
        while(slow != fast){
            slow = slow.parent;
            fast = fast.parent;
        }
        return slow;
    }

    public Node lowestCommonAncestorV2(Node p, Node q) {
        Node a = p;
        Node b = q;
        while(a != b){
            a = a.parent == null ? q : a.parent;
            b = b.parent == null ? p : b.parent;
        }
        return a;
    }


    public Node lowestCommonAncestor(Node p, Node q) {
        // step 1: get height of both nodes
        int ph = getHeight(p);
        int qh = getHeight(q);
        int diff = Math.abs(ph - qh);
        if (ph > qh) {
            while (diff > 0) {
                p = p.parent;
                diff--;
            }
        } else {
            while (diff > 0) {
                q = q.parent;
                diff--;
            }
        }
        while (p != null && q != null) {
            if (p == q) {
                return q;
            }
            p = p.parent;
            q = q.parent;
        }
        return null;
    }
    private int getHeight(Node node) {
        int height = 0;
        while (node != null) {
            node = node.parent;
            height++;
        }
        return height;
    }

    public static void main(String[] args) {

    }

}
