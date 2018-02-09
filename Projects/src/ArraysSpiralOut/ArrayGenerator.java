package ArraysSpiralOut;

import java.util.Random;

public class ArrayGenerator {
    final static Random random = new Random();

    private static int randomNumber(int min, int max) {
        return min + random.nextInt(max + 1 - min);
    }

    /**
     * Generates 2D array with: <br>
     *     - rows amount from <b>1</b> to <b>10</b>; <br>
     *     - columns amount from <b>1</b> to <b>10</b>; <br>
     *     - values from <b>0</b> to <b>99</b>
     * @return generated 2D array
     */
    public static int[][] generate() {
        return generate(randomNumber(1, 10), randomNumber(1, 10), 0, 99);
    }

    /**
     * Generates 2D array with <b>rowCount</b> rows, <b>colCount</b> columns and values from <b>0</b> to <b>99</b>
     * @param rowCount Number of rows
     * @param colCount Number of columns
     * @return generated 2D array
     */
    public static int[][] generate(int rowCount, int colCount) {
        return generate(rowCount, colCount, 0, 99);
    }

    /**
     * Generates 2D array with <b>rowCount</b> rows, <b>colCount</b> columns and values from <b>valueMin</b> to <b>valueMax</b>
     * @param rowCount Number of rows
     * @param colCount Number of columns
     * @param valueMin Minimal value of array's element
     * @param valueMax Maximal value of array's element
     * @return generated 2D array
     */
    public static int[][] generate(int rowCount, int colCount, int valueMin, int valueMax) {
        int[][] result = new int[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                result[row][col] = randomNumber(valueMin, valueMax);
            }
        }
        return result;
    }
}
