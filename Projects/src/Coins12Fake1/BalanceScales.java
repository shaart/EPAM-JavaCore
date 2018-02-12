package Coins12Fake1;

import java.util.List;

/**
 * Scales with two bowls for weighing
 */
public class BalanceScales {
    /**
     * Compares weights of two objects.
     * <br>
     * Note:
     * <pre>
     * - <b>null</b> value is <b>lighter</b> than any other.
     * - Both <b>null</b> objects return 0.
     * </pre>
     *
     * @param left  object with weight to be placed at left side
     * @param right object with weight to be placed at right side
     * @return 0 - elements are equal <br>
     * 1 - left is heavier <br>
     * -1 - left is lighter
     */
    public static int compare(Weighable left, Weighable right) {
        if (left == null || right == null) {
            if (left == right) return 0; // both are null
            if (right == null)
                return 1; // only right is null
            else
                return -1; // only left is null
        }

        double weightLeft = left.getWeight();
        double weightRight = right.getWeight();

        if (weightLeft > weightRight) {
            return 1;
        } else if (weightLeft < weightRight) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Compares weights of two piles (lists with objects).<br>
     * If item in the list is <b>null</b> - it will be ignored (like its weight is 0).
     *
     * @param left  objects with weight to be placed at left side
     * @param right objects with weight to be placed at right side
     * @return 0 - piles are equal <br>
     * 1 - left pile is heavier, right pile is lighter <br>
     * -1 - left pile is lighter, right pile is heavier
     * @throws NullPointerException one or both lists are null
     */
    public static <T extends Weighable> int compare(List<T> left, List<T> right) throws NullPointerException {
        if (left == null || right == null)
            throw new NullPointerException("One or both of the arguments are null");

        double weightLeft = 0;
        for (T element : left) {
            if (element != null) {
                weightLeft += element.getWeight();
            }
        }
        double weightRight = 0;
        for (T element : right) {
            if (element != null) {
                weightRight += element.getWeight();
            }
        }

        if (weightLeft > weightRight) {
            return 1;
        } else if (weightLeft < weightRight) {
            return -1;
        } else {
            return 0;
        }
    }
}
