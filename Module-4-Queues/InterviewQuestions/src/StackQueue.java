import java.util.NoSuchElementException;
import java.util.Stack;

public class StackQueue<Item> {

    public StackQueue(){}
    private Stack<Item> input = new Stack<>();
    private Stack<Item> output = new Stack<>();

    public boolean isempty(){
        return output.empty() && input.empty();
    }

    public void enqueue(Item item){
        if (item == null) throw new NullPointerException();
        input.push(item);

    }
    public  Item dequeue(){
        if (isempty()) throw new  NoSuchElementException();

        if (output.empty()){
            while (!input.empty()){
                output.push(input.pop());
            }
        }

        return output.pop();

    }

//    public static void main(String[] args) {
//        StackQueue<String> stackQueue = new StackQueue<>();
//        stackQueue.enqueue("Ebrahim");
//        System.out.println(stackQueue.dequeue());
//
//    }

}
