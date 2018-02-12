package Coins12Fake1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.*;

public class TwelveCoinsGeneratorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private final TwelveCoinsGenerator generator = new TwelveCoinsGenerator();
    private final int ATTEMPTS_TO_GET_RANDOM = 1000;
    private final int COINS_COUNT = 12;
    private final int DIFFERENT_WEIGHTS = 2;

    private boolean isGeneratedFakeWeightCorrect(boolean isFakeMustBeHeavier, Map<Double, Integer> differentWeights) {
        Double fakeWeight = 0.0;
        Double commonWeight = 0.0;
        for (Map.Entry<Double, Integer> entry : differentWeights.entrySet()) {
            Integer weightsCount = entry.getValue();
            if (weightsCount == COINS_COUNT - 1) {
                commonWeight = entry.getKey();
            } else if (weightsCount == 1) {
                fakeWeight = entry.getKey();
            }
        }
        return isFakeMustBeHeavier ? commonWeight < fakeWeight : commonWeight > fakeWeight;
    }

    private boolean isGeneratedCoinsCountCorrect(Map<Double, Integer> differentWeights) {
        for (Map.Entry<Double, Integer> entry : differentWeights.entrySet()) {
            Integer weightsCount = entry.getValue();
            if (weightsCount != COINS_COUNT - 1 && weightsCount != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isGeneratedFakePositionCorrect(int expectedFakePos, List<Coin> generatedCoins, Map<Double, Integer> differentWeights) {
        double fakeWeight = 0.0;
        for (Map.Entry<Double, Integer> entry : differentWeights.entrySet()) {
            if (entry.getValue() == 1) {
                fakeWeight = entry.getKey();
                break;
            }
        }
        boolean fakeFound = false;
        for (int curPos = 0; curPos < generatedCoins.size(); curPos++) {
            if (fakeWeight == generatedCoins.get(curPos).getWeight()) {
                fakeFound = true;
                if (expectedFakePos != curPos) {
                    return false;
                }
                break;
            }
        }
        return fakeFound;
    }

    private boolean isGeneratedFakeValuesCorrect(int minValue, int maxValue, List<Coin> generatedCoins) {
        boolean isCorrect = true;
        int coinValue = 0;
        for (Coin coin : generatedCoins) {
            coinValue = coin.getValue();
            if (coinValue < minValue || maxValue < coinValue) {
                isCorrect = false;
                break;
            }
        }

        return isCorrect;
    }

    private Map<Double, Integer> getWeightsMap(List<Coin> coins) {
        Map<Double, Integer> differentWeights = new HashMap<>();
        double curWeight;
        Integer count;
        for (Coin coin : coins) {
            curWeight = coin.getWeight();
            count = differentWeights.get(curWeight);
            if (count == null) {
                differentWeights.put(coin.getWeight(), 1);
            } else {
                differentWeights.put(coin.getWeight(), count + 1);
            }
        }
        return differentWeights;
    }

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

    @Test
    public void generate() {
        List<Coin> coins = TwelveCoinsGenerator.generate();

        assertTrue(coins.size() == COINS_COUNT);
        Map<Double, Integer> differentWeights = getWeightsMap(coins);
        assertTrue(differentWeights.size() == DIFFERENT_WEIGHTS);
        assertTrue(isGeneratedCoinsCountCorrect(differentWeights));
    }

    @Test
    public void generateWithFakePosition() {
        for (int fakePosition = 0; fakePosition < COINS_COUNT; fakePosition++) {

            List<Coin> coins = TwelveCoinsGenerator.generate(fakePosition);

            assertTrue(coins.size() == COINS_COUNT);
            Map<Double, Integer> differentWeights = getWeightsMap(coins);
            assertTrue(differentWeights.size() == DIFFERENT_WEIGHTS);
            assertTrue(isGeneratedCoinsCountCorrect(differentWeights));
            assertTrue(isGeneratedFakePositionCorrect(fakePosition, coins, differentWeights));
        }
    }

    @Test
    public void generateIsFakeHeavier() {
        for (int callCounter = 0; callCounter < 2; callCounter++) {
            boolean isFakeHeavier = callCounter == 0;

            List<Coin> coins = TwelveCoinsGenerator.generate(isFakeHeavier);

            assertTrue(coins.size() == COINS_COUNT);
            Map<Double, Integer> differentWeights = getWeightsMap(coins);
            assertTrue(differentWeights.size() == DIFFERENT_WEIGHTS);
            assertTrue(isGeneratedCoinsCountCorrect(differentWeights));
            assertTrue(isGeneratedFakeWeightCorrect(isFakeHeavier, differentWeights));
        }
    }

    @Test
    public void generateIsFakeHeavierAndFakePosition() {
        for (int fakePosition = 0; fakePosition < COINS_COUNT; fakePosition++) {
            boolean isFakeHeavier = fakePosition % 2 == 0;

            List<Coin> coins = TwelveCoinsGenerator.generate(isFakeHeavier, fakePosition);

            assertTrue(coins.size() == COINS_COUNT);
            Map<Double, Integer> differentWeights = getWeightsMap(coins);
            assertTrue(differentWeights.size() == DIFFERENT_WEIGHTS);
            assertTrue(isGeneratedCoinsCountCorrect(differentWeights));
            assertTrue(isGeneratedFakeWeightCorrect(isFakeHeavier, differentWeights));
            assertTrue(isGeneratedFakePositionCorrect(fakePosition, coins, differentWeights));
        }
    }

    @Test
    public void generateIsFakeHeavierAndFakePositionAndMinValueAndMaxValue() {
        for (int fakePosition = 0; fakePosition < COINS_COUNT; fakePosition++) {
            boolean isFakeHeavier = fakePosition % 2 == 0;
            int minValue = fakePosition + 1;
            int maxValue = (fakePosition + 1) * 2;

            List<Coin> coins = TwelveCoinsGenerator.generate(isFakeHeavier, fakePosition, minValue, maxValue);

            assertTrue(coins.size() == COINS_COUNT);
            Map<Double, Integer> differentWeights = getWeightsMap(coins);
            assertTrue(differentWeights.size() == DIFFERENT_WEIGHTS);
            assertTrue(isGeneratedCoinsCountCorrect(differentWeights));
            assertTrue(isGeneratedFakeWeightCorrect(isFakeHeavier, differentWeights));
            assertTrue(isGeneratedFakePositionCorrect(fakePosition, coins, differentWeights));
            assertTrue(isGeneratedFakeValuesCorrect(minValue, maxValue, coins));
        }
    }
}