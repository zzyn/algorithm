package books.cartoon.heap;

import java.util.Arrays;

public class MaxPriorityQueue {

    private int[] array;
    private int size;

    public MaxPriorityQueue(int capacity) {
        array = new int[capacity];
    }

    public void enQueue(int key) {
        if (size >= array.length) {
            resize();
        }

        array[size++] = key;
        upAdjust();
    }

    public int deQueue() throws Exception {
        if (size <= 0) {
            throw new Exception("queue is empty");
        }

        int head = array[0];
        array[0] = array[--size];
        downAdjust();
        return head;

    }

    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = childIndex / 2;
        int temp = array[childIndex];

        while (childIndex > 0 && temp > array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        array[childIndex] = temp;
    }

    private void downAdjust() {
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex < size) {
            //rightChild > leftChild 取 rightChild
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }

            //parent >= any children
            if (temp >= array[childIndex]) {
                break;
            }

            //switch
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    private void resize() {

        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);

    }

    public static void main(String[] args) throws Exception {
        //最大优先队列
        MaxPriorityQueue queue = new MaxPriorityQueue(32);
        queue.enQueue(3);
        queue.enQueue(5);
        queue.enQueue(10);
        queue.enQueue(2);
        queue.enQueue(7);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
