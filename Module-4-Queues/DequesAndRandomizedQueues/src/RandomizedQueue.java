import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Object;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n; private int base=2;
   // @SuppressWarnings("unchecked")
    private Item [] arr ;

    public RandomizedQueue(){
        n=0;
        arr = (Item[]) new Object[base];
    }


    public boolean isEmpty(){
        return n == 0;
    }


    public int size(){
        return n;
    }
    private void resize(int capacity) {
        //@SuppressWarnings("unchecked")
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = arr[i];
        }
        arr= temp;
    }
    // add the item
    public void enqueue(Item item){
        if (item == null) throw new NullPointerException();
        if (n == arr.length) resize(2*n);
        arr[n++] = item;
    }


    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        if (n == arr.length/4) resize(n*2);
        int idx = StdRandom.uniformInt(n);
        Item item = arr[idx];
        arr[idx] = arr[--n];
        arr[n]= null; // avoid loitering
        return item;
    }


    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException();
        int idx = StdRandom.uniformInt(n);
        return arr[idx];
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i;
        private final int[] random;
        public ArrayIterator() {
            i = 0;
            random = new int[n];
            for (int j = 0; j < n; j++) {
                random[j] = j;
            }
            StdRandom.shuffle(random);
        }


        public boolean hasNext() {
            return i < n;
        }


        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr[random[i++]];
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }

    }



    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> rdq = new RandomizedQueue<>();
        rdq.enqueue(1);
        rdq.enqueue(2);
        rdq.enqueue(3);
        for (Integer integer : rdq) {
            System.out.println(integer);
        }
    }



}