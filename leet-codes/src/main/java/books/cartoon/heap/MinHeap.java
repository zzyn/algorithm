package books.cartoon.heap;

import java.util.Arrays;

/**
 * 以数组的方式存储树
 * 最大堆(栈顶最大)(完全二叉树)
 * 最小堆(栈顶最小)(完全二叉树)
 * leftChildIndex = parentIndex * 2 + 1;
 * rightChildIndex = parentIndex * 2 + 2;
 */



public class MinHeap {

    /**
     * 上浮
     *
     * @param array
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = temp;

    }

    /**
     * 下沉
     *
     * @param array
     * @param parentIndex
     * @param length
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            //rightChild < leftChild 取 rightChild
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }

            //parent < any children
            if (temp <= array[childIndex]) {
                break;
            }

            //switch
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    public static void buildHeap(int[] array) {

        for (int i = (array.length - 2) / 2; i > 0; i--) {
            downAdjust(array, i, array.length);
        }

    }

    public static void main(String[] args) {
        int[] array = new int[] {1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[] {7,1,3,10,5,2,8,9,6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
