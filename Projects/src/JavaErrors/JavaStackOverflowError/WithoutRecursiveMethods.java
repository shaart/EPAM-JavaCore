package JavaErrors.JavaStackOverflowError;

/**
 * [Task 5] java.lang.StackOverflowError: Do not use recursive methods.
 * Don't tune stack size.
 */
public class WithoutRecursiveMethods {
    public static void main(String[] args) {
        throw new UnsupportedOperationException();
    }
}
