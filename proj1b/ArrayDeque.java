/** Part 1a
 *  Array Deque
 * @author vtotient
 */

public class ArrayDeque<Item> implements Deque<Item> {
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
        items = (Item[]) new Object[START_SIZE];
        size = 0;
        nextFirst = 0;
        nextLast = START_SIZE-1;
    }

    /** Resizes items array to desired capacity */
    public void resize(int capacity) {
        Object[] a = (Item[]) new Object[capacity];

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

    /** Returns array's Usage Factor */
    public double UFactor() {
        double dsize = (double) size;
        double dlength = items.length;
        return dsize / dlength;
    }

    /** Adds an item of type Item to the front of the deque. */
    @Override
    public void addFirst(Item item) {
        if(size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextFirst] = item;
        size++;
        nextFirst++;
    }

    /**  Adds an item of type Item to the back of the deque. */
    @Override
    public void addLast(Item item) {
        if(size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextLast] = item;
        size++;
        nextLast--;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }

        return false;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
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
    @Override
    public Item removeFirst() {
        if(size == 0) {
            return null;
        }

        if(nextFirst == 0) {
            return (Item) items[0];
        }

        Item frontItem = (Item) items[nextFirst - 1];
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
    @Override
    public Item removeLast() {
        if(size == 0) {
            return null;
        }

        Item lastItem = (Item) items[nextLast + 1];
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
    public Item get(int index) {
        if(index >= size) {
            return null;
        }

        if(index < nextFirst) {
            return (Item) items[index];
        }

        int offset = index - (nextFirst - 1);

        return (Item) items[items.length - offset];
    }

}
