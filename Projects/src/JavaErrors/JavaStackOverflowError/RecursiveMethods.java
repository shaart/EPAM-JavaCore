package JavaErrors.JavaStackOverflowError;

/**
 * [Task 4] java.lang.StackOverflowError: Use recursive methods.
 * Don't tune stack size.
 */
public class RecursiveMethods {
    public static void main(String[] args) {
        try {
            recursive();
        } catch (Exception | Error e) {
            System.out.println("Catch: " + e.getClass());
            if (e instanceof StackOverflowError) {
                StackOverflowError soe = (StackOverflowError) e;
                StackTraceElement[] stackTraceElements = soe.getStackTrace();
                System.out.println("Cause: " + stackTraceElements[0]);
                System.out.println("Stack trace length: " + stackTraceElements.length);
            }
        }
    }

    private static void recursive() {
        recursive();
    }
}