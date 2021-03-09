package org.acm.stack;

import java.util.Stack;

public class MiniStack {

    private Stack<Integer> s = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        s.push(x);
        int minValue = minStack.isEmpty() ? x: Math.min(minStack.peek(), x);
        minStack.push(minValue);
    }

    public void pop() {
        s.pop();
        minStack.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
