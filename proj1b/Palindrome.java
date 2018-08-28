import cucumber.api.java.gl.E;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;

/** Proj 1b
 *  wordToDeque
 * @author vtotient
 */

public class Palindrome {

    /** Task 2
     * Given a String, will return a Deque where the chars appear in the same
     * order as in String. With help from https://goo.gl/exQGWM */
    public Deque<Character> wordToDeque(String word) {

        LinkedListDeque<Character> deque = new LinkedListDeque<>();

        for(int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }

        return deque;
    }

    /** Task 3
     * Return true if word is a palindrome. Any word of length 0 or 1 is
     * a palindrome. Case sensitive. */
    public boolean isPalindrome(String word) {

       Deque<Character> deque = wordToDeque(word);
       return isPalindromeRec(deque);
    }

    /** Helper method for recursion. With help from https://goo.gl/WPRzfr and
     * https://goo.gl/M43jTg */
    private boolean isPalindromeRec(Deque<Character> d) {

        if(d.size() == 0 || d.size() == 1) {
            return true;
        }

        char first = Character.toLowerCase(d.removeFirst());
        char last = Character.toLowerCase(d.removeLast());
        int diff = first - last;

        if(diff != 0) {
            return false;
        }

        return isPalindromeRec(d);
    }

    /** Task 4
     * Overloading the isPalindrome method. Returns if given String is a palindrome.
     * @param word String
     * @param cc CharacterComparator Interface
     * @return True or False
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {

        Deque<Character> d = wordToDeque(word);
        return isPalindromeRec(d, cc);
    }

    /** Helper method for recursion. With help from https://goo.gl/WPRzfr and
     * https://goo.gl/M43jTg */
    private boolean isPalindromeRec(Deque<Character> d, CharacterComparator cc) {

        if(d.size() == 0 || d.size() == 1) {
            return true;
        }

        char x = d.removeFirst();
        char y = d.removeLast();

        if(!cc.equalChars(x, y)) {
            return false;
        }

        return isPalindromeRec(d, cc);
    }
}
