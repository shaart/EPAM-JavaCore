package JavaErrors.JVisualVM;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Uncomment one of these methods
//        initialCode();
//        callGCEveryTime();
        moreFrequentPeaks();
    }

    private static void initialCode() {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }
    }

    private static void callGCEveryTime() {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
            System.gc();
        }
    }

    private static void moreFrequentPeaks() {
        int i = 0;
        while (true) {
            new Object();
            i++;

            if (i % 1_000_000_000 == 0) {
                System.gc();
                try {
                    Thread.sleep(100);
                } catch (Exception e) { /* Nothing to do*/ }
            }
        }
    }
}
