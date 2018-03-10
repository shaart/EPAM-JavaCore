package JavaErrors.JavaOutOfMemoryError.HeapSpace;

import java.util.ArrayList;
import java.util.List;

/**
 * [Task 1] java.lang.OutOfMemoryError: Java heap space.
 * You can use different data structures.
 * Don't tune heap size.
 */
public class DifferentStructures {
    public static void main(String[] args) {
        try {
            String curArg = args.length > 0 ? args[0] : "";
            switch (curArg) {
                case "0":
                    makeBigArrays();
                    break;
                case "1":
                default:
                    saveSmallArrays();
                    break;
            }
        } catch (Exception | Error e) {
            System.out.println("Catch: " + e.getClass());
            if (e instanceof OutOfMemoryError) {
                System.out.println("Cause: " + e.getMessage());
            }
        } catch (Throwable t) {
            System.out.println("Catch: " + t.getClass());
        }
    }

    //-------------------------------------------------
    private static void makeBigArrays() {
        long[] array;
        while (true) {
            array = new long[1_000_000_000];
        }
    }

    //-------------------------------------------------
    private static void saveSmallArrays() {
        List<long[]> list = new ArrayList<>();
        long[] array;
        while (true) {
            array = new long[10_000];
            list.add(array);
        }
    }
}
