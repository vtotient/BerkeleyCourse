public class LinkedListDeque<T> {

    public class Node {
        public Node prev;
        public Node next;
        public T item;

        public Node(T i, Node n, Node p) {
            prev = p;
            next = n;
            item = i;
        }
    }

    private Node sentinel;
    private int size;

    /** Creates an empty deque */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** Add an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node temp = sentinel.next;
        sentinel.next = new Node(item, sentinel.next, sentinel);
        temp.prev = sentinel.next;
        size += 1;
    }

    /** Add an item of type T to the back of list. */
    public void addLast(T item) {
        Node temp = sentinel.prev;
        sentinel.prev = new Node(item, sentinel, temp);
        temp.next = sentinel.prev;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    /** Returns number of items in deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node p = sentinel.next;

        while(p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists,
     * returns null.
     * */
    public T removeFirst() {
        if(this.isEmpty()) {
            return null;
        }

        Node temp = sentinel.next;
        if(size == 1) {
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
            size -= 1;
            return temp.item;
        } else {
            sentinel.next = temp.next;
            temp.next.prev = sentinel;
            size -= 1;
            return temp.item;
        }
    }

    /** Removes and returns the item at the back of the deque. If no such item exists,
     * returns null.
     * */
    public T removeLast() {
        if(this.isEmpty()) {
            return null;
        }

        Node temp = sentinel.prev;
        if(size == 1) {
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
            size -= 1;
            return temp.item;
        } else {
            sentinel.prev = temp.prev;
            temp.prev.next = sentinel;
            size -= 1;
            return temp.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null.
     * */
    public T get(int index) {
        if(isEmpty()) {
            return null;
        }

        Node p = sentinel.next;
        for(int i = 0; i < index; i++) {
            if(p.next != sentinel) {
                p = p.next;
            } else {
                return null;
            }
        }

        return p.item;

    }

    /** Same as get, but uses recursion. */
    public T getRecursive(Node p, int i) {
        if(i == 0) {
            return p.item;
        }

        return getRecursive(p.next, i-1);
    }

    public T getRecursive(int index) {

        if(isEmpty() || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

     /** Debug */
    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(10);
        L.addFirst(5);
        L.addFirst(90);
        L.addLast(100);
        L.addLast(-2);
        System.out.println(L.getRecursive(6));
        System.out.println(L.getRecursive(3));
        System.out.println(L.getRecursive(0));
        System.out.println();
        L.printDeque();
        L.removeFirst();
        System.out.println();
        L.printDeque();
        L.removeLast();
        System.out.println();
        L.printDeque();
    }

}
