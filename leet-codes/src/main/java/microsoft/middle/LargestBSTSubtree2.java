package microsoft.middle;

public class LargestBSTSubtree2 {

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

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        if (isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            return countNodes(root);
        }
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    boolean isValidBST(TreeNode node, int leftMax, int rightMin) {
        if (node == null) {
            return true;
        }

        if (node.val <= leftMax || node.val >= rightMin) {
            return false;
        }

        return isValidBST(node.left, leftMax, node.val) && isValidBST(node.right, node.val, rightMin);
    }

    public static void main(String[] args) {

        LargestBSTSubtree2 t = new LargestBSTSubtree2();
        System.out.println(t.largestBSTSubtree(null));
    }
}
