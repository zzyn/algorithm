package leetcode.microsoft.middle;

import java.util.*;

/*
You need to construct a binary tree
from a string consisting of parenthesis and integers.
The whole input represents a binary tree.
It contains an integer followed by zero,
one or two pairs of parenthesis.
The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]

Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]

Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]

 */
public class $536_ConstructBinaryTreeFromString {

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


    public TreeNode str2treeV1(String s) {
        if (s == null || s.isEmpty()) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') stack.pop();
            else if (s.charAt(i) >= '0' && s.charAt(i) <= '9' || s.charAt(i) == '-') {
                int start = i;
                while (i < s.length() - 1 && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                TreeNode node = new TreeNode(Integer.parseInt(s.substring(start, i + 1)));
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left == null) parent.left = node;
                    else parent.right = node;
                }
                stack.push(node);
            }
        }
        if (stack.isEmpty()) return null;
        else return stack.peek();
    }

    int curIndex=0;
    public TreeNode str2tree(String s) {
        if(s==null || s.length()==0) return null;
        curIndex=0;
        return constructTree(s);
    }

    public TreeNode constructTree(String s){
        if(curIndex>=s.length()) return null;
        int rootValue=getRootValue(s);
        TreeNode root=new TreeNode(rootValue);

        //构建左边的值
        //跳过第一个(
        if(curIndex<s.length() && s.charAt(curIndex)=='('){
            curIndex++;
            root.left=constructTree(s);
            //跳过左结尾的括号位置的值
            curIndex++;
            if(curIndex<s.length() && s.charAt(curIndex)=='('){
                //构建右子树
                curIndex++;
                root.right=constructTree(s);
                curIndex++;
            }
        }

        return root;
    }

    //得到的位置是第一个(的位置
    public int getRootValue(String s){
        int ans=0;
        boolean isNegative=false;
        while(curIndex<s.length()){
            char curChar=s.charAt(curIndex);
            if(curChar=='-'){
                isNegative=true;
            }else if(curChar>='0' && curChar<='9'){
                ans= ans*10+(curChar-'0');
            }else{
                break;
            }
            curIndex++;
        }
        if(isNegative) ans= -ans;
        return ans;

    }

    public static void main(String[] args) {

        $536_ConstructBinaryTreeFromString a = new $536_ConstructBinaryTreeFromString();
        TreeNode root = a.str2tree("4(2(3)(1))(6(5))");
        System.out.println(root.val);

    }
}
