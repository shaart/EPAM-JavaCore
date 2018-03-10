package JavaErrors.JavaStackOverflowError;

/**
 * [Task 5] java.lang.StackOverflowError: Do not use recursive methods.
 * Don't tune stack size.
 */
public class WithoutRecursion {
    public static void main(String[] args) {
        try {
            // But this is implicit recursion
            toStringWithThis();
        } catch (Exception | Error e) {
            showError(e);
        } finally {
            System.out.println();
        }

        try {
            // Two classes with "has-a" relation to each other
            cycleCreating();
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
    private static class ToStringWithThis {
        private String value;

        @Override
        public String toString() {
            return value + this;
        }
    }

    private static void toStringWithThis() {
        System.out.println(new ToStringWithThis());
    }

    // ----------------------------------------------------------------
    private static class Son {
        private Dad dad;

        Son() {
            dad = new Dad();
        }
    }

    private static class Dad {
        private Son son;

        Dad() {
            son = new Son();
        }
    }

    private static void cycleCreating() {
        Dad dad = new Dad();
    }

    // ----------------------------------------------------------------
    private static void justThrow() {
        throw new StackOverflowError("It's legal too");
    }
}
