package leetcode.microsoft.middle;

import java.util.ArrayDeque;

/**
 * Definition for a binary tree node.
 */



/**
 * 查找节点p的在tree中序遍历的顺序后继
 * (中序遍历顺序中的p的后一位)
 * H为树的高度
 * T = O(H)
 * S = O(H)
 */
public class $285_InorderSuccessorInBST {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //P的右child不为null，找子树中最左的一个节点就是后继
        while (p.right != null){
            p = p.right;
            while (p.left != null){
                p = p.left;
            }
            return p;
        }

        //P的右child为null,后继在P的parent中
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        int inOrder = Integer.MIN_VALUE;

        //中序遍历 left->root->right;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            // if the previous node was equal to p
            // then the current node is its successor
            if(inOrder == p.val) return root;
            inOrder = root.val;
            root = root.right;
        }

        return null;
    }
}
