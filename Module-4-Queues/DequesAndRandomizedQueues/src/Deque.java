import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first ;
    private Node last;
    private int n;
    private class Node{

        private Item item;
        private Node next;
        private Node previous;
        public Node(Item item){
            this.item = item;
            this.next = null;
            this.previous = null;
        }

    }



    public Deque(){
        n = 0;
    }


    public boolean isEmpty(){
        return n==0;
    }


    public int size(){
        return n;
    }


    public void addFirst(Item item){
        if (item == null) throw new NullPointerException();
        Node node = new Node(item);
        node.next =first;
        first = node;
        if (last == null) last =first;
        else first.next.previous = first;
        n++;

    }


    public void addLast(Item item){
        if (item == null) throw new NullPointerException();
        Node node = new Node(item);
        node.previous = last;
        last = node;
        if (first == null) first =last;
        else last.previous.next = last;
        n++;
    }


    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if (isEmpty()) {
            last = null;
            first = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        n--;

        return item;
    }


    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException();
        Item temp = last.item;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            last = last.previous;
            last.next = null;
        }
        n--;
        return temp;
    }




    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }

    private class DequeueIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }
    }





    public static void main(String[] args){
        Deque<Integer> dq = new Deque<>();
        dq.addFirst(5);
        dq.addFirst(3);
        dq.addLast(4);
        Iterator<Integer> loop = dq.iterator();
       while (loop.hasNext()){
           System.out.println(loop.next());
       }

    }



}