package books.cartoon.sort.compare.nlogn;

import java.util.Arrays;

/**
 * 从大到小 构建最小堆
 * 从小到大 构建最大堆
 * 循环删除堆顶元素，替换到二叉堆末尾,调整堆产生新的堆顶
 */
public class HeapSort {

    public static void downAdjust(int[] array, int parentIndex, int len) {
        int temp = array[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < len) {
            if(childIndex + 1 < len && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }

            if(temp >= array[childIndex]) {
                break;
            }

            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;

    }

    public static void heapSort(int[] array) {

        //构建最大堆
        for(int i = array.length/ 2; i >=0 ; i--){
            downAdjust(array, i, array.length - 1);
        }
        System.out.println(Arrays.toString(array));

        //循环删除堆顶， 移到集合尾部， 调整堆产生新的堆顶
        for(int i = array.length - 1; i>0; i--){
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            downAdjust(array, 0, i);
        }

    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}
