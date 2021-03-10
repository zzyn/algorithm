package books.cartoon.sort.compare.nlogn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class QuickSort {
    /**
     * 双边循环法(递归)
     * 两个指针left 和 right
     * 基准 pivot
     *
     * @param array
     */
    public static void sort_two_side_loop(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition_two_side_loop(array, startIndex, endIndex);
        sort_two_side_loop(array, startIndex, pivotIndex - 1);
        sort_two_side_loop(array, pivotIndex + 1, endIndex);
    }

    public static int partition_two_side_loop(int[] array, int startIndex, int endIndex) {
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            //use right point and move to left
            while (left < right && array[right] > pivot) {
                right--;
            }

            //use left point and move to right
            while (left < right && array[left] <= pivot) {
                left++;
            }

            //switch left pointed and right pointed value
            if (left < right) {
                int p = array[left];
                array[left] = array[right];
                array[right] = p;
            }

        }

        array[startIndex] = array[left];
        array[left] = pivot;

        return left;
    }

    /**
     * 单边循环法(递归)
     * 一个指针 mark
     * 基准 pivot
     *
     * @param array
     */
    public static void sort_single_side_loop(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition_single_side_loop(array, startIndex, endIndex);
        sort_single_side_loop(array, startIndex, pivotIndex - 1);
        sort_single_side_loop(array, pivotIndex + 1, endIndex);
    }

    public static int partition_single_side_loop(int[] array, int startIndex, int endIndex) {

        int pivot = array[startIndex];
        int mark = startIndex;

        for (int i = startIndex; i <= endIndex; i++) {
            if (array[i] < pivot) {
                mark++;
                int p = array[mark];
                array[mark] = array[i];
                array[i] = p;
            }
        }

        array[startIndex] = array[mark];
        array[mark] = pivot;
        return mark;
    }

    /**
     * 单边循环法(非递归)
     * 一个指针 mark
     *
     * @param array
     */
    public static void sort_single_side_loop_stack(int[] array, int startIndex, int endIndex) {

        Stack<Map<String, Integer>> stack = new Stack<>();
        Map rootParam = new HashMap();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        stack.push(rootParam);

        while (!stack.isEmpty()) {
            Map<String, Integer> param = stack.pop();
            int paramStartIndex = param.get("startIndex");
            int paramEndIndex = param.get("endIndex");
            int pivotIndex = partition_single_side_loop(array, paramStartIndex, paramEndIndex);

            if(paramStartIndex < pivotIndex - 1){
                Map left = new HashMap();
                left.put("startIndex", paramStartIndex);
                left.put("endIndex", pivotIndex - 1);
                stack.push(left);
            }

            if(paramEndIndex > pivotIndex + 1){
                Map right = new HashMap();
                right.put("startIndex", pivotIndex + 1);
                right.put("endIndex", paramEndIndex);
                stack.push(right);
            }
        }

    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        sort_two_side_loop(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

        array = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        sort_single_side_loop(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

        array = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        sort_single_side_loop_stack(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
