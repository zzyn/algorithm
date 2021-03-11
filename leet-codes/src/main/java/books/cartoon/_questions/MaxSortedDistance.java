package books.cartoon._questions;

/**
 * 无序数组排序后的最大相邻差
 * 桶排序
 */
public class MaxSortedDistance {

    static class Bucket {
        Integer min;
        Integer max;
    }

    public static int getMaxSortedDistance(int[] array) {
        int len = array.length;

        //1. get max and min
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < len; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        if(d == 0 ){
            return 0;
        }

        //2. init buckets
        int bucketNum = len;
        Bucket[] buckets = new Bucket[bucketNum];
        for(int i = 0; i < bucketNum; i++){
            buckets[i] = new Bucket();
        }

        //3. loop array value for get max and min of bucket
        for(int i = 0; i < len; i++){
            int index = ((array[i] - min) * (bucketNum -1) / d);
            if(buckets[index].min == null || buckets[index].min > array[i]){
                buckets[index].min = array[i];
            }

            if(buckets[index].max == null || buckets[index].max < array[i]){
                buckets[index].max = array[i];
            }
        }

        //4. loop buckets and find max distance
        int leftMax = buckets[0].max;
        int maxDistance = 0;
        for(int i = 0; i < bucketNum; i++ ){
            if(buckets[i].min == null) {
                continue;
            }

            if(buckets[i].min - leftMax > maxDistance) {
                maxDistance = buckets[i].min - leftMax;
            }

            leftMax = buckets[i].max;
        }

        return maxDistance;
    }

    public static void main(String[] args) {

        int[] array = new int[]{2, 6, 3, 4, 5, 10, 9};
        System.out.println(getMaxSortedDistance(array));
    }
}


