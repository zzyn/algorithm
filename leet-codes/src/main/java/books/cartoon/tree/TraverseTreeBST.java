package books.cartoon.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class TraverseTreeBST {

    static void levelOrderTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if(Objects.nonNull(node.leftChild)) {
                queue.offer(node.leftChild);
            }

            if(Objects.nonNull(node.rightChild)) {
                queue.offer(node.rightChild);
            }
        }

    }

    public static void main(String[] args){
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(new Integer[] {
                3,2,9,null,null,10,null,null,8,null,4
        }));

        TreeNode treeNode = TreeNode.createBinaryTree(inputList);
        System.out.println("层序遍历:");
        levelOrderTraverse(treeNode);
    }
}
