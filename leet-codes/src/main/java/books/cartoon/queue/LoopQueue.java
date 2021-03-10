package books.cartoon.queue;

/**
 * 循环队列
 */
public class LoopQueue {
    private int[] array;
    private int front;
    private int rear;

    public LoopQueue(int capacity) {
        this.array = new int[capacity];
    }

    public void enQueue(int element) throws Exception {

        if ((rear + 1) % array.length == front) {
            throw new Exception("queue is full");
        }

        array[rear] = element;
        rear = (rear + 1) % array.length;

    }

    public int deQueue() throws Exception {

        if (rear == front) {
            throw new Exception("queue is empty");
        }

        int deQueueElement = array[front];
        front = (front + 1) % array.length;

        return deQueueElement;
    }

    public void output() {
        for(int i = front; i != rear; i = (i+1)% array.length ) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        LoopQueue queue = new LoopQueue(6);
        queue.enQueue(3);
        queue.enQueue(5);
        queue.enQueue(6);
        queue.enQueue(8);
        queue.enQueue(1);
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.enQueue(2);
        queue.enQueue(4);
        queue.enQueue(9);
        queue.output();

    }
}
