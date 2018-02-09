package ArraysSpiralOut;

public class SpiralPrinter {
    private static boolean isRectangularSize(int[][] array) {
        if (array.length < 1) return false;
        final int COLUMNS = array[0].length;

        for (int row = 1; row < array.length; row++) {
            if (COLUMNS != array[row].length) {
                return false;
            }
        }

        return true;
    }

    public static void print(int[][] array) {
        if (array.length == 0) return;

        if (!isRectangularSize(array)) {
            System.out.println("Array must be rectangular (M x N)");
            return;
        }

        int curRow = 0;
        int curCol = 0;

        int nowMustPassCols = array[0].length;
        int nowMustPassRows = array.length - 1; // 1 element is at columns that we will pass

        int mustPass = array.length * array[0].length;

        while (mustPass > 0 && (nowMustPassCols > 0 || nowMustPassRows > 0)) {

            for (int i = 0; i < nowMustPassCols; curCol++, i++) {
                System.out.print(array[curRow][curCol] + " ");
                mustPass--;
            }
            nowMustPassCols--;
            curCol--;
            curRow++;
            if (mustPass <= 0) break;

            for (int i = 0; i < nowMustPassRows; curRow++, i++) {
                System.out.print(array[curRow][curCol] + " ");
                mustPass--;
            }
            nowMustPassRows--;
            curRow--;
            curCol--;
            if (mustPass <= 0) break;

            for (int i = 0; i < nowMustPassCols; curCol--, i++) {
                System.out.print(array[curRow][curCol] + " ");
                mustPass--;
            }
            nowMustPassCols--;
            curCol++;
            curRow--;
            if (mustPass <= 0) break;

            for (int i = 0; i < nowMustPassRows; curRow--, i++) {
                System.out.print(array[curRow][curCol] + " ");
                mustPass--;
            }
            nowMustPassRows--;
            curRow++;
            curCol++;
            if (mustPass <= 0) break;
        }
    }
}

