package ArraysSpiralOut;

public class Main {

    public static void main(String[] args) {
        int[][] array = ArrayGenerator.generate();
        standardPrint(array);
        System.out.println();
        SpiralPrinter.print(array);
    }

    private static void standardPrint(int[][] array) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                System.out.print(array[row][col] + " ");
            }
            System.out.println();
        }
    }
}
