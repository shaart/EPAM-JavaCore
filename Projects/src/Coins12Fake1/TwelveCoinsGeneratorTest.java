package Coins12Fake1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TwelveCoinsGeneratorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private final TwelveCoinsGenerator generator = new TwelveCoinsGenerator();

    @Test
    public void getRandomIntInt() {
        try {
            Method getRandom = generator.getClass().getDeclaredMethod("getRandom", int.class, int.class);
            getRandom.setAccessible(true);
            int number = -1;
            int min = 9;
            int max = 10;
            number = (int) getRandom.invoke(generator, min, max);
            assertTrue(min <= number && number <= max);

            min = -1;
            number = (int) getRandom.invoke(generator, min, max);
            assertTrue(min <= number && number <= max);

            min = 100;
            max = 100;
            number = (int) getRandom.invoke(generator, min, max);
            assertTrue(min <= number && number <= max);

            try {
                min = 100;
                max = 0;
                getRandom.invoke(generator, min, max);
                fail("must not be here");
            } catch (InvocationTargetException invokeEx) {
                if (invokeEx.getCause() instanceof IllegalArgumentException) {
                    // We are here as we expect
                } else {
                    fail("Catch unknown exception when invoked getRandom(int,int) with min = 100, max = 0");
                }
            }
        } catch (java.lang.NoSuchMethodException e) {
            fail("Method getRandom(int, int) not found");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getRandomDoubleDouble() {
        try {
            Method getRandom = generator.getClass().getDeclaredMethod("getRandom", double.class, double.class);
            getRandom.setAccessible(true);
            double number = -1.0;
            double min = 9.3;
            double max = 9.9;
            number = (double) getRandom.invoke(generator, min, max);
            assertTrue(min <= number && number <= max);

            min = 9.3333;
            max = 9.3999;
            number = (double) getRandom.invoke(generator, min, max);
            assertTrue(min <= number && number <= max);

            min = -1.5;
            number = (double) getRandom.invoke(generator, min, max);
            assertTrue(min <= number && number <= max);

            min = 100.0;
            max = 100.0;
            number = (double) getRandom.invoke(generator, min, max);
            assertTrue(min <= number && number <= max);

            try {
                min = 100.0;
                max = 0.0;
                getRandom.invoke(generator, min, max);
                fail("must not be here");
            } catch (InvocationTargetException invokeEx) {
                if (invokeEx.getCause() instanceof IllegalArgumentException) {
                    // We are here as we expect
                } else {
                    fail("Catch unknown exception when invoked getRandom(double,double) with min = 100, max = 0");
                }
            }
        } catch (java.lang.NoSuchMethodException e) {
            fail("Method getRandom(double, double) not found");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
