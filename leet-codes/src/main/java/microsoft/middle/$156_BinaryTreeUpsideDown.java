package microsoft.middle;

/*
Given the root of a binary tree, turn the tree upside down and return the new root.

You can turn a binary tree upside down with the following steps:

The original left child becomes the new root.
The original root becomes the new right child.
The original right child becomes the new left child.

 */
public class $156_BinaryTreeUpsideDown {

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

    //实际就是链表反转 + 一个叶节点的处理
    public TreeNode upsideDownBinaryTree(TreeNode root) {

        TreeNode parent = null, parent_right = null;
        while(root != null){
            TreeNode root_left = root.left;
            root.left = parent_right;//3
            parent_right = root.right;
            root.right = parent; //2
            parent = root;
            root = root_left; //1
        }
        return parent;
    }

    public static void main(String[] args){

    }

}
