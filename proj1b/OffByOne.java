/** Task 4
 *  Generalized Palindrome and OffByOne
 * @author vtotient
 */

public class OffByOne implements CharacterComparator {

    /** Task 4
     * Returns true if two given chars are off by exactly one. Case matters.
     * @param x char
     * @param y char
     * @return boolean
     */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;

        if(diff == 1 || diff == -1) {
            return true;
        } else {
            return false;
        }
    }
}
