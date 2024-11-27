package LinkedList;

public class LinkedList<T> {

    private No<T> head;
    private No<T> tail;

    public void addValue(T value) {
        No<T> celula = new No<T>(value);
        if (this.head == null) {
            this.head = celula;
            this.tail = celula;
        }
        celula = this.head;
        this.head = celula;
    }

    @Override
    public String toString() {
        return "LinkedList [head=" + head + "]";
    }

}