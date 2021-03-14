package leetcode.microsoft.middle;

public class $255_VerifyPreorderSequenceInBinarySearchTree {

    public boolean verifyPreorder(int[] preorder) {

        int min = Integer.MIN_VALUE;
        int k = -1;
        for(int val : preorder) {
            if (val < min) {
                return false;
            }
            while ( k >= 0 && val > preorder[k]) {
                min = preorder[k--];
            }
            preorder[++k] = val;
        }
        return true;
    }

    public static void main(String[] args){

    }
}
