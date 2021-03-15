package leetcode.microsoft.middle;

/*
  Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

 */
public class $742_ClosestLeafInBinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //最小步数
    private int dist = Integer.MAX_VALUE;
    //节点的值
    private int val = -1;

    public int findClosestLeaf(TreeNode root, int k) {
        dfs1(root, k);
        return val;
    }

    private int dfs1(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        if (root.val == k) {
            if (root.left == null && root.right == null) {
                val = k;
                return -1;
            }
            dfs2(root.left, 1);
            dfs2(root.right, 1);
            return 1;
        }
        int left = dfs1(root.left, k);
        if (left > 0) {
            dfs2(root.right, left + 1);
            return left + 1;
        }
        int right = dfs1(root.right, k);
        if (right > 0) {
            dfs2(root.left, right + 1);
            return right + 1;
        }
        return -1;
    }

    private void dfs2(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (dist > sum) {
                dist = sum;
                val = root.val;
            }
            return;
        }
        dfs2(root.left, sum + 1);
        dfs2(root.right, sum + 1);
    }

    public static void main() {

    }
}
