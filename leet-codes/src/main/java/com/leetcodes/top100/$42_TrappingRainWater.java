package com.leetcodes.top100;

public class $42_TrappingRainWater {

    /**
     * 暴力法
     * T = O(n^2)
     * S = O(1)
     * @param height
     * @return
     */
    public static int trapV1(int[] height) {
        int waters = 0;
        int size = height.length;

        for(int i = 1; i < size - 1; i++){
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            waters += Math.min(max_left, max_right) - height[i];

        }
        return waters;
    }


    /**
     * 动态规划
     * T = O(n)
     * S = O(n)
     * @param height
     * @return
     */
    public static int trapV2(int[] height) {

        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[size];
        int[] right_max = new int[size];

        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }

        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    /**
     * 双指针
     * T = O(n)
     * S = O(1)
     * @param height
     * @return
     */
    public static int trapV3(int[] height) {
        //指针
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }


    public static void main(String[] args){

        int[] a1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trapV3(a1));
        int[] a2 = {4,2,0,3,2,5};
        System.out.println(trapV3(a2));

    }
}
