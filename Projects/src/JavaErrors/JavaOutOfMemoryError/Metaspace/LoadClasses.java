package JavaErrors.JavaOutOfMemoryError.Metaspace;

import javassist.ClassPool;

/**
 * [Task 3] java.lang.OutOfMemoryError: Metaspace.
 * Load classes continuously and make them stay in memory.
 */
public class LoadClasses {
    public static void main(String[] args) {
        try {
            createClasses();
        } catch (Exception | Error e) {
            System.out.println("Catch: " + e.getClass());
            if (e instanceof OutOfMemoryError) {
                System.out.println("Cause: " + e.getMessage());
            } else {
                e.printStackTrace();
            }
        } catch (Throwable t) {
            System.out.println("Catch as Throwable: " + t.toString());
            t.printStackTrace();
        }
    }

    private static ClassPool pool = ClassPool.getDefault();

    private static void createClasses() {
        final String classNameStr = "JavaErrors.JavaOutOfMemoryError.Metaspace.Classes.Generated";

        for (int i = 1; ; i++) {
            try {
                Class generated = pool.makeClass(classNameStr + i).toClass();

                if (i % 10_000 == 0) {
                    System.out.println("Generated classes: " + i);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
        }
    }
}
