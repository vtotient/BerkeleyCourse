/** Performs some basic tests on the ArrayDeque Class. */
public class ArrayDequeTest {
    
    /** Tests empty array creation */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /** Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /** Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct, 
     * finally printing the results. 
     */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        
        ArrayDeque<Integer> AL1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, AL1.isEmpty());

        AL1.addFirst(1);

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, AL1.size()) && passed;
        passed = checkEmpty(false, AL1.isEmpty()) && passed;

        AL1.addLast(2);
        passed = checkSize(2, AL1.size()) && passed;

        AL1.addLast(3);
        passed = checkSize(3, AL1.size()) && passed;

        System.out.println("Printing out deque: ");
        AL1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> AL2 = new ArrayDeque<>();
        // should be empty 
        boolean passed = checkEmpty(true, AL2.isEmpty());

        AL2.addFirst(1);
        // should not be empty
        passed = checkEmpty(false, AL2.isEmpty()) && passed;

        AL2.removeFirst();
        // should be empty
        passed = checkEmpty(true, AL2.isEmpty()) && passed;

        printTestStatus(passed);

    }

    /** Test resizing due to Usage Factor. */
    public static void UFacCheck() {

        System.out.println("Running UFacCheck.");

        ArrayDeque<Integer> AL3 = new ArrayDeque<>();

        for(int i = 0; i < 9; i++) {
            AL3.addFirst(i);
        }

        AL3.addLast(9);
        AL3.addLast(10);

        System.out.println(AL3.get(9) + " " + AL3.get(3));

        AL3.printDeque();

        AL3.removeLast();
        AL3.printDeque();

        System.out.println(AL3.UFactor());

        for(int i = 0; i < 7; i++) {
            AL3.removeFirst();
        }

        AL3.printDeque();
        System.out.println(AL3.UFactor());

    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        UFacCheck();
    }
}
