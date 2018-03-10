package JavaErrors.JavaOutOfMemoryError.HeapSpace;

import java.math.BigInteger;

/**
 * [Task 2] java.lang.OutOfMemoryError: Java heap space.
 * Create big objects continuously and make them stay in memory.
 * Do not use arrays or collections.
 */
public class CreatingBigObjects {
    public static void main(String[] args) {
        try {
            String curArg = args.length > 0 ? args[0] : "";
            switch (curArg) {
                case "0":
                    createLinkedElements();
                    break;
                case "1":
                    createAndSaveObjects();
                    break;
                case "2":
                default:
                    makeBigString();
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
    private static class LinkedElement {
        LinkedElement next = null;
        private long over = 9000L;
        private final String BIG_VALUE = "99999999999999999999999" +
                "999999999999999999999999999999999999999999999999";
        private BigInteger bigInteger = new BigInteger(BIG_VALUE);

        LinkedElement() {
        }
    }

    private final static LinkedElement head = new LinkedElement();

    private static void createLinkedElements() {
        LinkedElement current = head;
        long counter = 0;
        while (true) {
            current.next = new LinkedElement();
            current = current.next;
            counter++;
            if (counter % 1_000_000_000L == 0) {
                System.out.println(counter + " " + (counter * 8) + " bytes");
            }
        }

    }

    //-------------------------------------------------
    static class ProtectorClass {
        public static Object safe;
    }

    static class ImportantClass {
        private static BigInteger count = BigInteger.ONE;
        private BigInteger id;

        private long bigField = 9000L;
        private static final String BIG_VALUE = "99999999999999999999999" +
                "999999999999999999999999999999999999999999999999";
        private static BigInteger currectValue = new BigInteger(BIG_VALUE);

        private BigInteger bigInteger = new BigInteger(
                (currectValue = currectValue.add(BigInteger.ONE)).toString()
        ) {
            @Override
            public void finalize() {
                System.out.println("Save BigInteger: " + this.toString());
                ProtectorClass.safe = this;
            }
        };

        ImportantClass() {
            id = new BigInteger(count.toString());
            count = count.add(BigInteger.ONE);
        }

        @Override
        protected void finalize() {
            System.out.println("Save ImportantClass: " + id.toString());
            ProtectorClass.safe = this;
        }
    }

    private static void createAndSaveObjects() {
        while (true) {
            new ImportantClass();
        }
    }

    //-------------------------------------------------
    private static void makeBigString() {
        String s = "A";
        while (true) {
            s += s;
        }
    }
}