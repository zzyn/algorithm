package books.cartoon.tree;

import java.util.LinkedList;

public class TreeNode {
    public int data;
    public TreeNode leftChild;
    public TreeNode rightChild;

    public TreeNode(int data) {
        this.data = data;
    }

    static TreeNode createBinaryTree(LinkedList<Integer> inputList) {

        TreeNode node = null;

        if(inputList == null || inputList.isEmpty()) {
            return null;
        }

        Integer data = inputList.removeFirst();
        if(data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }
        return node;
    }
}
