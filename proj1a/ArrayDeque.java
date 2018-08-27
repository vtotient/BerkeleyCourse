public class ArrayDeque<T> {
    /** Constants */
    public static final int START_SIZE = 8;
    private static final int RFACTOR = 2;
    private static final int LARGE = 16;

    /** Fields */
    private Object[] items;
    private int size;
    private int nextFirst, nextLast;

    /** Creates an empty array. */
    public ArrayDeque() {
        items = (T[]) new Object[START_SIZE];
        size = 0;
        nextFirst = 0;
        nextLast = START_SIZE-1;
    }

    /** Resizes items array to desired capacity */
    public void resize(int capacity) {
        Object[] a = (T[]) new Object[capacity];

        for(int i = 0; i < nextFirst; i++) {
            a[i] = items[i];
        }

        int i = items.length - 1;
        int j = capacity - 1;
        int offset = items.length - nextLast;
        while(i > nextLast) {
            a[j] = items[i];
            i--;
            j--;
        }

        nextLast = capacity - offset;
        items = a;

    }

//    public void resizeDown(int capacity) {
//        Object[] a = (T[]) new Object[capacity];
//        int i = nextFirst - 1;
//        while(i >= 0) {
//            a[i] = items[i];
//            i--;
//        }
//
//        i = items.length - 1;
//        int j = capacity - 1;
//        while(i > nextLast) {
//            a[j] = items[i];
//            i--;
//            j--;
//        }
//
//        items = a;
//    }

    /** Returns array's Usage Factor */
    public double UFactor() {
        double dsize = (double) size;
        double dlength = items.length;
        return dsize / dlength;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if(size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextFirst] = item;
        size++;
        nextFirst++;
    }

    /**  Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if(size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextLast] = item;
        size++;
        nextLast--;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }

        return false;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        int i = nextFirst - 1;
        while(i >= 0) {
            System.out.print(items[i] + " ");
            i--;
        }

        i = nextLast + 1;
        while(i < items.length) {
            System.out.print(items[i] + " ");
            i++;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item
     *  exists, returns null.
     */
    public T removeFirst() {
        if(size == 0) {
            return null;
        }

        T frontItem = (T) items[nextFirst - 1];
        items[nextFirst - 1] = null;
        nextFirst--;
        size--;

        /* Check if items array needs to be resized as per specified requirements. */
        if(UFactor() < 0.25 && items.length >= 16) {
            resize(items.length / RFACTOR);
        }

        return frontItem;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists,
     * returns null.
     */
    public T removeLast() {
        if(size == 0) {
            return null;
        }

        T lastItem = (T) items[nextLast + 1];
        items[nextLast + 1] = null;
        nextLast++;
        size--;

        /* Check if items array needs to be resized as per specified requirements. */
        if(UFactor() < 0.25 && items.length >= 16) {
            resize(items.length / RFACTOR);
        }

        return lastItem;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null.
     */
    public T get(int index) {
        if(index >= size) {
            return null;
        }

        if(index < nextFirst) {
            return (T) items[index];
        }

        int offset = index - (nextFirst - 1);

        return (T) items[items.length - offset];
    }

}
