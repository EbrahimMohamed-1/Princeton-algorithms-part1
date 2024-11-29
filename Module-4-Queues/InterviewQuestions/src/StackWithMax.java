package org.example;
import java.util.*;

public class StackWithMax {
    private class Node {
        int data;
        Node next;
        Node(int  data) {
            this.data = data;
            this.next = null;
        }

    }
    //store max and min items
    private static StackWithMax arrMax = new StackWithMax();
    private  static StackWithMax arrMin = new StackWithMax();
    private int max, min;

    Node head = null;

    public boolean isEmpty(){
        return head == null;
    }

    public  void push(int item){
        Node newNode = new Node(item);
        if (isEmpty()) {
            // update head
            head = newNode;
            max = min  = item;

        }
        newNode.next = head; // connect with next
        // update head
        head = newNode;

        if (item > max){
            arrMax.push(max);
            max=item;
        }
        if (item < min){
            arrMin.push(min);
            min=item;
        }
    }
    public void poparr() {
        if (isEmpty()) throw new NullPointerException(); // you can use exception handling
        head = head.next;
    }

    public int pop(){
        if (isEmpty()) return -1; // you can use exception handling
        int ans = head.data;
        head = head.next;

        if (ans ==  max){
           if (!arrMax.isEmpty()){
               max = arrMax.head.data;
               arrMax.poparr();
           }

        }

        if (ans == min){
            if (! arrMin.isEmpty()){
                min = arrMin.head.data;
                arrMin.poparr();
            }

        }
        return ans;
    }

    public int max(){
        return max;
    }
    public int min(){
        return min;
    }



    public  static void main( String [] args){

        StackWithMax stack = new StackWithMax();
        stack.push(5);
        stack.push(3);
        stack.pop();
        stack.push(1);
        System.out.println(stack.max);
        System.out.println(stack.min);
    }



}
