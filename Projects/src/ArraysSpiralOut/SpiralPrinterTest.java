package ArraysSpiralOut;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SpiralPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    public void resetStreams() {
        outContent.reset();
        errContent.reset();
    }

    @Test
    public void print() {
        int[][] testArraySquare3x3 = {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}
        };
        SpiralPrinter.print(testArraySquare3x3);
        assertEquals("1 2 3 4 5 6 7 8 9", outContent.toString().trim());

        resetStreams();
        int[][] testArray5x3 = {
                {1, 2, 3, 4, 5},
                {12, 13, 14, 15, 6},
                {11, 10, 9, 8, 7}
        };
        SpiralPrinter.print(testArray5x3);
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", outContent.toString().trim());

        resetStreams();
        int[][] testArray3x5 = {
                {1, 2, 3},
                {12, 13, 4},
                {11, 14, 5},
                {10, 15, 6},
                {9, 8, 7}
        };
        SpiralPrinter.print(testArray3x5);
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", outContent.toString().trim());

        resetStreams();
        int[][] testArray2x2 = {
                {1, 2},
                {4, 3}
        };
        SpiralPrinter.print(testArray2x2);
        assertEquals("1 2 3 4", outContent.toString().trim());

        resetStreams();
        int[][] testArray2x1 = {
                {1, 2}
        };
        SpiralPrinter.print(testArray2x1);
        assertEquals("1 2", outContent.toString().trim());

        resetStreams();
        int[][] testArrayRandomNumbers = {
                {1, 2, 0 , 5},
                {25, 3, 6, 99},
                {9, 70, 8, 3}
        };
        SpiralPrinter.print(testArrayRandomNumbers);
        assertEquals("1 2 0 5 99 3 8 70 9 25 3 6", outContent.toString().trim());
    }
}
