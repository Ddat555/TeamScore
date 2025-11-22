package org.example.task7;


public class PriorityStack<T> {

    private StackNode<T> head;
    private int size;

    public PriorityStack() {
        this.head = new StackNode<T>(0, null);
        size = 0;
    }


    public int size() {
        return size;
    }

    public void push(int priority, T value) {
        StackNode<T> stackNode = new StackNode<>(priority,value);
        StackNode<T> curNode = head;
        while (curNode.getNext() != null && stackNode.getPriority() <= curNode.getNext().getPriority()) {
            curNode = curNode.getNext();
        }
        stackNode.setNext(curNode.getNext());
        curNode.setNext(stackNode);
        size++;
    }

    public T pop() {
        if (head.getNext() == null) {
            return null;
        }
        StackNode<T> curNode = head.getNext();
        if (curNode.getNext() != null) {
            head.setNext(curNode.getNext());
        } else {
            head.setNext(null);
        }
        size--;
        return curNode.getValue();
    }

    public T peek() {
        if (head.getNext() == null) {
            return null;
        }
        return head.getNext().getValue();

    }
}
