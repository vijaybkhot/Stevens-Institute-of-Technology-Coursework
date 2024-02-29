/*
 * Assignment 8
 * Name : Vijay Khot
 * CWID : 20021838
 * */

import java.util.Stack;

public class MaxStack extends Stack<Integer> {

    // Additional stack to track the maximum element
    Stack<Integer> stackMax;


    // Constructor to initialize the stacks
    public MaxStack() {
        stackMax = new Stack<>();
    }
    // Override the push method to update the stackMax
    public void push(int x) {
        // If stackMax is empty or x is greater than the current max, push x onto stackMax
        if (stackMax.isEmpty() || x > stackMax.peek()) {
            stackMax.push(x);
        }
        // Call the push method of the parent class (Stack)
        super.push(x);
    }

    // Override the pop method to update the stackMax
    @Override
    public Integer pop() {
        // Peek at the top element of the parent stack
        int x = super.peek();
        // If x is the current max, pop the top element from stackMax
        if (x == stackMax.peek()) {
            stackMax.pop();
        }
        // Call the pop method of the parent class (Stack)
        return super.pop();
    }

    // Retrieve the maximum element without removing it
    public int peekMax() {
        if (stackMax.isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        // Peek at the top element of stackMax
        return stackMax.peek();
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(2);
        stack.push(1);
        stack.push(5);
        stack.push(3);
        int max = stack.peekMax();
        System.out.println(max);

        stack.pop();
        stack.pop();
        max = stack.peekMax();
        System.out.println(max);
    }
}
