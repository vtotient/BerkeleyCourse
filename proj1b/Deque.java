/** Task 1
 *  Deque Interface. Information on methods may be found in LinkedListDeque.java
 *  or Array Deque.java.
 * @author vtotient
 */

public interface Deque<Item> {

    public void addFirst(Item i);

    public void addLast(Item i);

    public boolean isEmpty();

    public int size();

    public void printDeque();

    public Item removeFirst();

    public Item removeLast();
}