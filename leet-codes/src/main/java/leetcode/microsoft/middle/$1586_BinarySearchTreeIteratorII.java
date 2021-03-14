package leetcode.microsoft.middle;

import java.util.Deque;
import java.util.LinkedList;

/*
  Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
int next() Moves the pointer to the right, then returns the number at the pointer.
boolean hasPrev() Returns true if there exists a number in the traversal to the left of the pointer, otherwise returns false.
int prev() Moves the pointer to the left, then returns the number at the pointer.
Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() and prev() calls will always be valid. That is, there will be at least a next/previous number in the in-order traversal when next()/prev() is called.

 */
public class $1586_BinarySearchTreeIteratorII {

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


    class BSTIterator2 {
        // 哨兵节点
        private TreeNode head = new TreeNode(0);
        // 当前遍历到的节点
        private TreeNode curNode;
        public BSTIterator2(TreeNode root) {
            TreeNode prev = head;
            TreeNode node = root;
            // 中序遍历结果，构造double link
            Deque<TreeNode> stack = new LinkedList<>();
            while(node != null || !stack.isEmpty()){
                if(node != null){
                    stack.push(node);
                    node = node.left;
                }else {
                    node = stack.pop();

                    TreeNode newNode = new TreeNode(node.val);
                    prev.right = newNode;
                    newNode.left = prev;
                    prev = newNode;

                    node = node.right;
                }
            }
            curNode = head;
        }

        public boolean hasNext() {
            return curNode.right != null;
        }

        public int next() {
            curNode = curNode.right;
            return curNode.val;
        }

        public boolean hasPrev() {
            return curNode.left != head && curNode != head;
        }

        public int prev() {
            curNode = curNode.left;
            return curNode.val;
        }
    }

    class BSTIterator {
        private TreeNode head;
        private TreeNode pre;
        private TreeNode tail;
        private TreeNode current;
        public BSTIterator(TreeNode root) {

            visit(root);

            TreeNode newHead = new TreeNode(-1);
            newHead.right = head;
            head.left = newHead;
            head = newHead;

            TreeNode newTail = new TreeNode(-1);
            newTail.left = tail;
            tail.right = newTail;
            tail = newTail;

            current = head;
        }

        private void visit(TreeNode node) {
            if (node == null) {
                return;
            }
            visit(node.left);
            // set head
            if (head == null) {
                head = node;
            }
            // set link //双链表
            node.left = pre;
            if (pre != null) {
                pre.right = node;
            }
            pre = node;
            // set tail
            tail = node;
            visit(node.right);
        }

        public boolean hasNext() {
            return current != tail && current.right != tail;
        }

        public int next() {
            current = current.right;
            return current.val;
        }

        public boolean hasPrev() {
            return current != head && current.left != head;
        }

        public int prev() {
            current = current.left;
            return current.val;
        }
    }

    public static void main(String[] args){

    }
}
