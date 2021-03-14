package microsoft.middle;

public class $333_LargestBSTSubtree {

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

    /**
     *
     * @param root
     * @return
     */
    int ans = 0;

    public int largestBSTSubtree(TreeNode root) {
        int[] temp = postOrder(root);
        if (temp != null) ans = Math.max(ans, temp[2]);
        return ans;
    }

    /*
        后序遍历 left->right->root
        每一个节点若是一个包含所有后代的二叉搜索树的根的话,
        返回int[]{子树最小值,子树最大值,子树节点数},
        根的val满足大于左子树的最大值,小于右子树的最小值.
        若不能包含就返回null;
        时间复杂度O(n).
    */
    public int[] postOrder(TreeNode node) {
        if (node == null) return null;

        //left->right->root 后序遍历
        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        if (left != null) ans = Math.max(ans, left[2]);
        if (right != null) ans = Math.max(ans, right[2]);

        //叶子节点
        if (left == null && right == null) {
            if (node.left == null && node.right == null) {
                return new int[]{node.val, node.val, 1};
            } else {
                return null;
            }
        }
        //没有左子树
        else if (left == null && right != null) {
            //当前值小于右边最小值且没有左子树(isBST)
            if (node.val < right[0] && node.left == null) {
                return new int[]{node.val, right[1], right[2] + 1};
            } else {
                return null;
            }
        }
        //没有右子树
        else if (left != null && right == null) {
            //当前值大于左边最大值且没有右子树(isBST)
            if (node.val > left[1] && node.right == null) {
                return new int[]{left[0], node.val, left[2] + 1};
            } else {
                return null;
            }
        }
        //左右都有子树
        else {
            //当前值大于左边最大值且小于右边最小(isBST)
            if (node.val > left[1] && node.val < right[0]) {
                return new int[]{left[0], right[1], left[2] + right[2] + 1};
            } else {
                return null;
            }
        }
    }
}
