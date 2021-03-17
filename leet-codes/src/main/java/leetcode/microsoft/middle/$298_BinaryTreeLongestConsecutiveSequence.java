package leetcode.microsoft.middle;

/*
Given the root of a binary tree, return the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The longest consecutive path needs to be from parent to child (cannot be the reverse).

Input: root = [1,null,3,2,4,null,null,null,5]
Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.

Input: root = [2,null,3,2,null,1]
Output: 2
Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

 */
public class $298_BinaryTreeLongestConsecutiveSequence {

    static class TreeNode {
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

    int max = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root, null, 0);
        return max;
    }

    private void dfs(TreeNode node, TreeNode parent, int length){
        if (node == null) return;
        length = (parent != null && node.val == parent.val + 1) ? length + 1 : 1;
        max = Math.max(max, length);
        dfs(node.left, node, length);
        dfs(node.right, node, length);
    }

    public static void main(String[] args){

    }
}
