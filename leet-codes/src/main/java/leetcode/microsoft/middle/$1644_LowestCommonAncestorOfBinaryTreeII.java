package leetcode.microsoft.middle;

/*
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.

 */
public class $1644_LowestCommonAncestorOfBinaryTreeII {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    /*
        构造一个函数，进行深度搜索，完成两个任务，
        [1] 记录满足条件的根节点
        [2] 返回该点以及其子节点是否有满足条件true or false

        任务一
        如果满足条件
        ((left || right) && (root->val == p->val || root->val == q->val)) 或者 (left && right)
        即，
        1）根结点和p或者q之一相同，且p或者q有一个在根结点的子节点中
        2）p或者q都在根结点的子节点中
        上面两个条件满足一个即可。
        则将结果记录下来

        任务二
        同时该函数返回条件
        root->val == p->val || root->val == q->val || left || right
        即
        1）根的值等于p的值
        2）根的值等于q的值
        3）p或者q在左子树中
        4）p或者q在右子树中
        满足上述四条件之一，返回true，都不满足返回false

        该题类似 leetcode 236，解法相同
     */
    private boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return false;
        boolean left = dfs(node.left, p, q);
        boolean right = dfs(node.right, p, q);

        if ((
                (left || right) && (node.val == p.val || node.val == q.val)
        ) || (left && right)) {
            ans = node;
        }

        return node.val == p.val || node.val == q.val || left || right;

    }

    public static void main(String[] args) {

    }

}
