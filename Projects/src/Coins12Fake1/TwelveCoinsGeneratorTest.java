package Coins12Fake1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TwelveCoinsGeneratorTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private final TwelveCoinsGenerator generator = new TwelveCoinsGenerator();
    private final int ATTEMPTS_TO_GET_RANDOM = 1000;

    @Test
    public void getRandomIntInt() {
        try {
            Method getRandom = generator.getClass().getDeclaredMethod("getRandom", int.class, int.class);
            getRandom.setAccessible(true);
            int number;
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
    public void getRandomIntIntBorderValues() {
        try {
            Method getRandom = generator.getClass().getDeclaredMethod("getRandom", int.class, int.class);
            getRandom.setAccessible(true);

            int number;
            int min = 0;
            int max = 3;
            boolean wasMin = false;
            boolean wasMax = false;
            for (int i = 0; i < ATTEMPTS_TO_GET_RANDOM && (!wasMin || !wasMax); i++) {
                number = (int) getRandom.invoke(generator, min, max);
                if (number == min) {
                    wasMin = true;
                }
                if (number == max) {
                    wasMax = true;
                }
            }
            assertTrue(wasMax && wasMax);

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
            double number;
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

    @Test
    public void getRandomDoubleDoubleBorderValues() {
        try {
            Method getRandom = generator.getClass().getDeclaredMethod("getRandom", double.class, double.class);
            getRandom.setAccessible(true);

            double number;
            double min = 0.55;
            double max = 3.33;
            boolean wasMin = false;
            boolean wasMax = false;
            for (int i = 0; i < ATTEMPTS_TO_GET_RANDOM && (!wasMin || !wasMax); i++) {
                number = (double) getRandom.invoke(generator, min, max);
                if (Math.round(number * 100.0) / 100.0 == min) {
                    wasMin = true;
                }
                if (Math.round(number * 100.0) / 100.0 == max) {
                    wasMax = true;
                }
            }
            assertTrue(wasMax && wasMax);

        } catch (java.lang.NoSuchMethodException e) {
            fail("Method getRandom(int, int) not found");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
