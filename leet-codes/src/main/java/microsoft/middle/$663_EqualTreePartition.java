package microsoft.middle;

/*
  Given a binary tree with n nodes,
  your task is to check if it's possible to partition the tree to two trees
  which have the equal sum of values after removing exactly one edge on the original tree.
 */
public class $663_EqualTreePartition {

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }


    public boolean checkEqualTree(TreeNode root) {
        int sum = getSum(root);
        //不是偶数意味着不能均分树
        if((sum & 1) != 0) return false;
        // 当为0时，只能验证左子树或者右子树中有没有合适的
        return validSubtree(root.left, sum / 2) || validSubtree(root.right, sum / 2);

    }

    public boolean validSubtree(TreeNode node, int halfSum) {
        if(node == null) return false;
        if(getSum(node) == halfSum) return true;
        return validSubtree(node.left, halfSum) || validSubtree(node.right, halfSum);
    }

    public int getSum(TreeNode root) {
        if(root == null) return 0;
        return root.val + getSum(root.left) + getSum(root.right);
    }

    public static void main(String[] args){

    }
}
