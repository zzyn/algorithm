package books.cartoon._questions;

import java.util.Stack;

public class UseTwoStackCreateQueue {

    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
        stackQueue.enQueue(4);
        System.out.println(stackQueue.deQueue());
        System.out.println(stackQueue.deQueue());
    }
}

class StackQueue {
    //入队
    private Stack<Integer> stackA = new Stack<>();
    //出队
    private Stack<Integer> stackB = new Stack<>();

    public void enQueue(int element){
        stackA.push(element);
    }

    public Integer deQueue(){
        if(stackB.isEmpty()){
            if(stackA.isEmpty()){
                return null;
            }
            transfer();
        }
        return stackB.pop();
    }

    //move stack a to stack b
    private void transfer(){
        while (!stackA.isEmpty()){
            stackB.push(stackA.pop());
        }
    }
}