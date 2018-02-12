package Coins12Fake1;

/**
 * Coin with value and weight
 */
public class Coin implements Weighable {
    private int value;
    private double weight;

    /**
     * Constructor of coin with default <b>value</b> (1) and default <b>weight</b> (1).
     */
    public Coin() {
        weight = 1;
        value = 1;
    }

    /**
     * Constructor of coin with specified <b>value</b> and <b>weight</b>.
     *
     * @param value coin's cost
     * @param weight coin's weight
     */
    public Coin(int value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("Weight: %f; Value: %d", weight, value);
    }
}
