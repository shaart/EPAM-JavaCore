package shaart.task4;

/**
 * Task 4
 * Create simple java application (for example text file parsing or something else)
 * with at least two memory leaks and find them using Yourkit Profiler tool.
 */
public class LeakedAdder {
    private long longValue = 0L;
    private int intValue = 0;

    public void addLong(long l) {
        Long incremented = longValue + l;
        longValue = incremented;
    }

    public void addInt(int i) {
        Integer incremented = intValue + i;
        intValue = incremented;
    }

    public static void main(String[] args) {
        LeakedAdder leakedAdder = new LeakedAdder();
        for (int i = 0; i < 1_000_000; i++) {
            leakedAdder.addInt(i);
            leakedAdder.addLong(i);
        }
        System.out.format(" == Result\n   Int value: %d\n   Long value: %d\n",
                leakedAdder.intValue, leakedAdder.longValue);
    }
}