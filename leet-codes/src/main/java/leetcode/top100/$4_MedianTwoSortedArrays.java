package leetcode.top100;

/**
 * two sorted array
 */
public class $4_MedianTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m1 = nums1.length;
        int m2 = nums2.length;
        int t = m1 + m2;

        if (t == 0) {
            return 0.0;
        }

        if (t == 1) {
            return nums1.length > 0 ? nums1[0] : nums2[0];
        }

        int[] all = new int[t];
        int[] small = null;
        int[] big = null;
        int s = 0;
        int b = 0;

        if (m1 <= m2) {
            s = m1;
            b = m2;
            small = nums1;
            big = nums2;
        } else {
            s = m2;
            b = m1;
            small = nums2;
            big = nums1;
        }


        //merged two sorted array into one
        int x = 0;
        int y = 0;
        int i = 0;
        while (i < t) {
            if (x < s && y < b && small[x] < big[y]) {
                all[i] = small[x];
                x++;
            } else if (y < b) {
                all[i] = big[y];
                y++;
            } else if (x < s) {
                all[i] = small[x];
                x++;
            }
            i++;
        }

        double result = 0;

        int middle = t / 2;
        if (t % 2 == 0) {
            result = (all[middle - 1] + all[middle]) / (double) 2;
        } else {
            result = all[middle];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1,2};
        int[] b = {3,4};
        System.out.println(findMedianSortedArrays(a, b));
    }
}
