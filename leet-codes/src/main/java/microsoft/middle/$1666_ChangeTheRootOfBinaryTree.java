package microsoft.middle;

public class $1666_ChangeTheRootOfBinaryTree {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    /**
     * 从leaf开始沿父节点一直遍历到root
     * 时间复杂度O(d)，d为root到leaf的深度
     * 空间复杂度O(1)
     */
    public Node flipBinaryTree(Node root, Node leaf) {
        Node pre = null;// 从leaf向root遍历，前一个遍历的节点会成为下一个遍历节点的父节点
        Node cur = leaf;// 当前遍历节点
        while (cur.parent != null) {
            // 先把原父节点指向自己的指针置为null，这也是为什么当前节点至少总有一个子节点为null的原因
            if (cur.parent.left == cur)
                cur.parent.left = null;
            else
                cur.parent.right = null;

            if (cur.left != null)// cur 有左子节点，则该子节点变为 cur 的右子节点（条件1）
                cur.right = cur.left;

            cur.left = cur.parent;// cur 的原父节点变为 cur 的左子节点（条件2）
            cur.parent = pre;// 前一个遍历过的节点作为父节点
            pre = cur;
            cur = cur.left;// 沿原父节点继续遍历（此时的左节点是原父节点）
        }
        cur.parent = pre;// 处理根节点，可以看到整个过程可以不用到root参数
        return leaf;
    }

    public static void main(String[] args) {

    }
}
