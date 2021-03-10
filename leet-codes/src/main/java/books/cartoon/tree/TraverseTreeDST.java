package books.cartoon.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class TraverseTreeDST {



    static void preOrderTraverse(TreeNode node){
        if(node == null) {
            return;
        }
        System.out.println(node.data);
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);

    }

    static void preOrderTraverseWithStack(TreeNode root){

        Stack<TreeNode> stack = new Stack<>();

        TreeNode treeNode = root;

        while (treeNode != null || !stack.isEmpty()) {

            //迭代访问节点的左孩子并入栈
            while (treeNode != null) {
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }

            //如果没有左孩子，弹出栈顶，访问节点的右孩子
            if(!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }

    }

    static void inOrderTraverse(TreeNode node) {
        if(node == null) {
            return;
        }
        inOrderTraverse(node.leftChild);
        System.out.println(node.data);
        inOrderTraverse(node.rightChild);

    }

    static void postOrderTraverse(TreeNode node){
        if(node == null) {
            return;
        }
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.println(node.data);

    }

    public static void main(String[] args) {

        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[] {
                3,2,9,null,null,10,null,null,8,null,4
        }));

        TreeNode treeNode = TreeNode.createBinaryTree(inputList);
        System.out.println("前序遍历:");
        preOrderTraverse(treeNode);
        System.out.println("前序遍历[非递归]:");
        preOrderTraverseWithStack(treeNode);
        System.out.println("中序遍历:");
        inOrderTraverse(treeNode);
        System.out.println("后序遍历:");
        postOrderTraverse(treeNode);

    }
}