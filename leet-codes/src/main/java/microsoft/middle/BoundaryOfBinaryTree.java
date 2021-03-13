package microsoft.middle;

import java.util.*;

public class BoundaryOfBinaryTree {

    /**
     * Definition for a binary tree node.
     */

    static class TreeNode {
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

    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, true, true, result);
        return result;
    }

    /**
     * 前序遍历 root->left->child
     * @param node
     * @param leftBound
     * @param rightBound
     * @param result
     */
    private static void dfs(TreeNode node, boolean leftBound, boolean rightBound, List<Integer> result){

        //边界
        if(node == null) {
            return;
        }

        //向左遍历
        if(leftBound){
            result.add(node.val);
        }

        //向右的叶子结点
        if(!leftBound && node.left == null && node.right == null){
            result.add(node.val);
            return;
        }

        //left 遍历
        dfs(node.left, leftBound, !leftBound && rightBound && node.right == null, result);
        //right 遍历
        dfs(node.right, !rightBound && leftBound && node.left == null , rightBound,result);

        //right most leaf
        if(!leftBound && rightBound){
            result.add(node.val);
        }

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2, new TreeNode(3), new TreeNode(4));

        System.out.println(boundaryOfBinaryTree(root));

        TreeNode root2Child1 = new TreeNode(4);
        TreeNode root2Child2 = new TreeNode(5);
        TreeNode root2Child3 = new TreeNode(7);
        TreeNode root2Child4 = new TreeNode(8);
        TreeNode root2Left = new TreeNode(2);
        root2Child2.left = root2Child3;
        root2Child2.right = root2Child4;
        root2Left.left  = root2Child1;
        root2Left.right = root2Child2;

        TreeNode root2Right = new TreeNode(3);
        TreeNode root2Child6 = new TreeNode(6);
        TreeNode root2Child7 = new TreeNode(9);
        TreeNode root2Child8 = new TreeNode(10);
        root2Child6.left = root2Child7;
        root2Child6.right = root2Child8;
        root2Right.left = root2Child6;
        TreeNode root2 = new TreeNode(1);
        root2.left = root2Left;
        root2.right = root2Right;

        System.out.println(boundaryOfBinaryTree(root2));

    }
}
