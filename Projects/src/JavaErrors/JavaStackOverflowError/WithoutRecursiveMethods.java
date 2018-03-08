package JavaErrors.JavaStackOverflowError;

/**
 * [Task 5] java.lang.StackOverflowError: Do not use recursive methods.
 * Don't tune stack size.
 */
public class WithoutRecursiveMethods {
    public static void main(String[] args) {
        try {
            // But this is implicit recursion
            overriddenToString();
        } catch (Exception | Error e) {
            showError(e);
        } finally {
            System.out.println();
        }

        try {
            // Two classes with "has-a" relation to each other
            twoClassesEachContainsAnother();
        } catch (Exception | Error e) {
            showError(e);
        } finally {
            System.out.println();
        }

        try {
            // Or we can just do this
            justThrow();
        } catch (Exception | Error e) {
            showError(e);
        } finally {
            System.out.println();
        }
    }

    private static void showError(Throwable e) {
        System.out.println("Catch: " + e.getClass());
        if (e instanceof StackOverflowError) {
            StackOverflowError soe = (StackOverflowError) e;
            StackTraceElement[] stackTraceElements = soe.getStackTrace();
            String cause = (stackTraceElements.length > 0 ? stackTraceElements[0].toString() : "none");
            System.out.println("Cause: " + cause);
            System.out.println("Stack trace length: " + stackTraceElements.length);
        }
    }

    // ----------------------------------------------------------------
    private static class OverriddenToString {
        private String value;

        @Override
        public String toString() {
            return value + this;
        }
    }

    private static void overriddenToString() {
        System.out.println(new OverriddenToString());
    }

    // ----------------------------------------------------------------
    private static class Son {
        private Daddy daddy;

        Son() {
            daddy = new Daddy();
        }
    }

    private static class Daddy {
        private Son son;

        Daddy() {
            son = new Son();
        }
    }

    private static void twoClassesEachContainsAnother() {
        Daddy daddy = new Daddy();
    }

    // ----------------------------------------------------------------
    private static void justThrow() {
        throw new StackOverflowError("It's legal too");
    }
}
