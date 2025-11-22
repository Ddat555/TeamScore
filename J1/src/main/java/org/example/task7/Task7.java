package org.example.task7;

public class Task7 {

    public static void main(String[] args) {
        PriorityStack<String> integerPriorityStack = new PriorityStack<>();
        integerPriorityStack.push(5,"5");
        integerPriorityStack.push(5,"5");
        integerPriorityStack.push(5,"5");
        integerPriorityStack.push(5,"5");


        System.out.println(integerPriorityStack.size());


        System.out.println(integerPriorityStack.pop());
        System.out.println(integerPriorityStack.size());
        System.out.println(integerPriorityStack.pop());
        System.out.println(integerPriorityStack.size());
        System.out.println(integerPriorityStack.pop());
        System.out.println(integerPriorityStack.size());
    }
}
