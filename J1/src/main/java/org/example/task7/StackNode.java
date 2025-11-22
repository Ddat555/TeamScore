package org.example.task7;

class StackNode<T> {
    private StackNode<T> next;
    private final int priority;
    private final T value;



    public StackNode<T> getNext() {
        return next;
    }

    public void setNext(StackNode<T> next) {
        this.next = next;
    }

    public int getPriority() {
        return priority;
    }

    public T getValue() {
        return value;
    }

    public StackNode(int priority, T object) {
        this.priority = priority;
        this.value = object;
    }
}
