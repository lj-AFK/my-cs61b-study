package deque;

import java.util.Iterator;

public class LinkedListDeque<T> {

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node() {
            this.next = null;
            this.prev = null;
        }

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> H;
    private Node<T> tail;
    private int size;

    public LinkedListDeque() {
        H = new Node<>();
        size = 0;
        tail = new Node<>();
    }

    public void addLast(T item){
        Node<T> now = new Node<>(item);
        if(isEmpty()){
            H= now;
            tail = now;
        }else if(tail == H){
            H.next = now;
            now.prev = H;
            tail = now;
        }else{
            tail.next = now;
            now.prev = tail;
            tail = now;
        }
        size++;
    }

    public void addFirst(T item){
        Node<T> now = new Node<>(item);
        if(isEmpty()){
            H = now;
            tail = now;
        }else if(H == tail) {
            now.next = H;
            H.prev = now;
            tail = now;
        }
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = 0;i<size;i++){
            System.out.print(H.data+" ");
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T cur = H.data;
        if(H ==tail){
            tail = null;
            H = null;
        }else{
            H = H.next;
            H.prev = null;
        }
        size--;
        return cur;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T cur = tail.data;
        if(H == tail){
            H = null;
            tail = null;
        }else{
            tail = tail.prev;
            tail.next = tail;
        }
        size--;
        return cur;
    }

    public T get(int index){
        if(index<0 ||index>size){
            return null;
        }
        Node<T> cur = H;
        for(int i = 0;i<index;i++){
            cur = cur.next;
        }
        return cur.data;
    }

    public Iterator<T> iterator(){
        return new Iterator<T>() {
            private Node<T> cur = H;

            public boolean hasNext(){
                return cur.next != tail;
            }

            public T next(){
                T item = cur.data;
                cur = cur.next;
                return item;
            }

        };
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof LinkedListDeque)) return false;
        LinkedListDeque<?> other = (LinkedListDeque<?>) o;

        if (this.size() != other.size()) return false;

        Iterator<T> it1 = this.iterator();
        Iterator<?> it2 = other.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }
        }
        return true;
    }

}
