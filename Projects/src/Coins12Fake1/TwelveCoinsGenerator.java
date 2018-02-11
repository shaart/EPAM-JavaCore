package Coins12Fake1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwelveCoinsGenerator {
    public static final int COINS_COUNT = 12;
    private static final int DEFAULT_MIN_VALUE = 1;
    private static final int DEFAULT_MAX_VALUE = 10;
    private static final Random random = new Random();

    /**
     * Get random value from <b>min</b> to <b>max</b> inclusively.
     *
     * @param min minimal value
     * @param max maximal value
     * @return value, where: min &le; value &le; max
     */
    private static int getRandom(int min, int max) {
        if (min > max) throw new IllegalArgumentException("'min' must be less than 'max'");

        return min + random.nextInt(max + 1 - min);
    }

    /**
     * Get random value from <b>min</b> to <b>max</b> inclusively.
     *
     * @param min minimal value
     * @param max maximal value
     * @return value, where: min &le; value &le; max
     */
    private static double getRandom(double min, double max) {
        if (min > max) throw new IllegalArgumentException("'min' must be less than 'max'");

        return min + (max - min) * random.nextDouble();
    }

    /**
     * Generates specified list of coins.<br><br>
     *
     * <b>All coins</b> will have <b>default value</b> (from 1 to 10).<br>
     * <b>Common coins</b> will have <b>default weight</b> (from 1 to 2).<br>
     * <b>Fake coin</b> will have <b>default weight</b> (the difference with common is <b>randomly</b> greater
     * or less by a value from 1/4 to 1/2 of common's weight).<br>
     * <b>Fake</b> will be placed in a <b>random position</b>.
     *
     * @return list of 12 coins created by specified parameters
     */
    public static List<Coin> generate() {
        return generate(random.nextBoolean());
    }

    /**
     * Generates specified list of 12 coins.<br><br>
     *
     * <b>All coins</b> will have <b>default value</b> (from 1 to 10).<br>
     * <b>Common coins</b> will have <b>default weight</b> (from 1 to 2).<br>
     * <b>Fake coin</b> will have <b>default weight</b> (the difference with common is greater or less by a value
     * from 1/4 to 1/2 of common's weight).<br>
     * <b>Fake</b> will be placed in a <b>random position</b>.
     *
     * @param isFakeHeavier Is the generated fake coin heavier than others? If not, fake will be lighter.
     * @return list of 12 coins created by specified parameters
     */
    public static List<Coin> generate(boolean isFakeHeavier) {
        return generate(isFakeHeavier, getRandom(0, COINS_COUNT - 1));
    }

    /**
     * Generates specified list of 12 coins.<br><br>
     *
     * <b>All coins</b> will have <b>default value</b> (from 1 to 10).<br>
     * <b>Common coins</b> will have <b>default weight</b> (from 1 to 2).<br>
     * <b>Fake coin</b> will have <b>default weight</b> (the difference with common is greater or less by a value
     * from 1/4 to 1/2 of common's weight).
     *
     * @param isFakeHeavier Is the generated fake coin heavier than others? If not, fake will be lighter.
     * @param fakePosition  Position of fake coin at list
     * @return list of 12 coins created by specified parameters
     */
    public static List<Coin> generate(boolean isFakeHeavier, int fakePosition) {
        return generate(isFakeHeavier, fakePosition, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }

    /**
     * Generates specified list of 12 coins.<br><br>
     *
     * <b>Common coins</b> will have <b>default weight</b> (from 1 to 2).<br>
     * <b>Fake coin</b> will have <b>default weight</b> (the difference with common is greater or less by a value
     * from 1/4 to 1/2 of common's weight).
     *
     * @param isFakeHeavier Is the generated fake coin heavier than others? If not, fake will be lighter.
     * @param fakePosition  Position of fake coin at list
     * @param valueMin      Minimal value of coins
     * @param valueMax      Maximal value of coins
     * @return list of 12 coins created by specified parameters
     */
    public static List<Coin> generate(boolean isFakeHeavier, int fakePosition, int valueMin, int valueMax) {
        if (fakePosition < 0 || fakePosition >= COINS_COUNT) {
            fakePosition = getRandom(0, COINS_COUNT - 1);
        }

        double commonWeight = random.nextDouble() + 1;
        double fakeWeight;
        if (isFakeHeavier) {
            fakeWeight = commonWeight + getRandom(commonWeight / 4, commonWeight / 2);
        } else {
            fakeWeight = commonWeight - getRandom(commonWeight / 4, commonWeight / 2);
        }

        return generate(fakePosition, valueMin, valueMax, commonWeight, fakeWeight);
    }

    /**
     * Generates specified list of 12 coins.<br><br>
     *
     * @param fakePosition Position of fake coin at list. If out of bounds - will be randomly placed
     * @param valueMin     Minimal value of coins
     * @param valueMax     Maximal value of coins
     * @param commonWeight Weight of common coin
     * @param fakeWeight   Weight of fake coin
     * @return list of 12 coins created by specified parameters
     */
    public static List<Coin> generate(int fakePosition, int valueMin, int valueMax, double commonWeight, double fakeWeight) {
        if (fakePosition < 0 || fakePosition >= COINS_COUNT) {
            fakePosition = getRandom(0, COINS_COUNT - 1);
        }

        List<Coin> generated = new ArrayList<>();
        for (int i = 0; i < COINS_COUNT; i++) {
            if (i == fakePosition) {
                generated.add(new Coin(getRandom(valueMin, valueMax), fakeWeight));
            } else {
                generated.add(new Coin(getRandom(valueMin, valueMax), commonWeight));
            }
        }

        return generated;
    }
}
