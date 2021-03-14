package leetcode.microsoft.middle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]

 */
public class $366_FindLeavesOfBinaryTree {

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

    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> ans = new ArrayList<>();

        while (root != null) {
            List<Integer> list = new ArrayList<>();
            root = postOrder(root, list);

            ans.add(list);
        }
        return ans;
    }

    private TreeNode postOrder(TreeNode node, List<Integer> list) {
        if (node == null) return null;
        //当前节点
        if (node.left == null && node.right == null) {
            list.add(node.val);
            return null;
        }
        //左子树
        node.left = postOrder(node.left, list);
        //右子树
        node.right = postOrder(node.right, list);
        return node;
    }

    public static void main(String[] args){

    }
}
