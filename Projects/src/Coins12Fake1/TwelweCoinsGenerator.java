package Coins12Fake1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwelweCoinsGenerator {
    public static final int COINS_COUNT = 12;
    private static final Random random = new Random();

    private static int getRandom(int min, int max) {
        if (min > max) throw new IllegalArgumentException("'min' must be less than 'max'");

        return min + random.nextInt(max + 1 - min);
    }

    private static double getRandom(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static List<Coin> generate() {
        return generate(random.nextBoolean());
    }

    public static List<Coin> generate(boolean isFakeHeavier) {
        return generate(isFakeHeavier, getRandom(0, COINS_COUNT - 1));
    }

    public static List<Coin> generate(boolean isFakeHeavier, int fakePosition) {
        return generate(isFakeHeavier, fakePosition, 1, 10);
    }

    public static List<Coin> generate(boolean isFakeHeavier, int fakePosition, int valueMin, int valueMax) {
        if (fakePosition < 0 || fakePosition >= COINS_COUNT) {
            fakePosition = getRandom(0, COINS_COUNT - 1);
        }

        List<Coin> generated = new ArrayList<>();
        double commonWeight = random.nextDouble() + 1;

        for (int i = 0; i < COINS_COUNT; i++) {
            if (i == fakePosition) {
                double fakeWeight;
                if (isFakeHeavier) {
                    fakeWeight = commonWeight + getRandom(commonWeight / 4, commonWeight / 2);
                } else {
                    fakeWeight = commonWeight - getRandom(commonWeight / 4, commonWeight / 2);
                }
                generated.add(new Coin(getRandom(valueMin, valueMax), fakeWeight));
            } else {
                generated.add(new Coin(getRandom(valueMin, valueMax), commonWeight));
            }
        }

        return generated;
    }
}
