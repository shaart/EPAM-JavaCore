package HexMap;

import java.util.ArrayList;

/**
 * Hex in cube coordinates <code>q, r, s</code>
 */
public class Hex {
    /**
     * Column
     */
    public final int q;
    /**
     * Row
     */
    public final int r;
    /**
     * Auxiliary coordinate<br>
     * <code>s = -q - r</code>
     */
    public final int s;

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = prime + q;
        hash = prime * hash + r;

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Hex) {
            Hex other = (Hex) obj;
            return q == other.q && r == other.r;
        }

        return false;
    }

    /**
     * Creates default Hex with the default column (0) and the default row (0).
     */
    public Hex() {
        this(0, 0);
    }

    /**
     * Creates Hex with column (q) and row (r)<br>.
     *
     * @param q Column
     * @param r Row
     */
    public Hex(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
    }

    /**
     * Sums two Hexes.
     *
     * @param a Summable hex
     * @param b Summable hex
     * @return Sum Hex
     */
    public static Hex add(Hex a, Hex b) {
        return new Hex(a.q + b.q, a.r + b.r);
    }

    /**
     * Subtracts Hex B from Hex A.
     *
     * @param a Decreasing hex
     * @param b Subtracted hex
     * @return result of subtract Hex
     */
    public static Hex subtract(Hex a, Hex b) {
        return new Hex(a.q - b.q, a.r - b.r);
    }

    /**
     * Scales Hex (a) to coefficient (coef).
     *
     * @param a    Hex
     * @param coef Scale coefficient
     * @return scaled Hex
     */
    public static Hex scale(Hex a, int coef) {
        return new Hex(a.q * coef, a.r * coef);
    }

    /**
     * Rotates current hex to the left.
     *
     * @param hex Hex for rotation
     * @return rotated hex
     */
    public static Hex rotateLeft(Hex hex) {
        return new Hex(-hex.s, -hex.q);
    }

    /**
     * Rotates current hex to the right.
     *
     * @param hex Hex for rotation
     * @return rotated hex
     */
    public static Hex rotateRight(Hex hex) {
        return new Hex(-hex.r, -hex.s);
    }

    /**
     * Counterclockwise Directions
     * <pre>
     *      [2]     [1]
     * [3]      [*]     [0]
     *      [4]     [5]
     * </pre>
     */
    public enum Directions {
        RIGHT, UP_RIGHT, UP_LEFT, LEFT, DOWN_LEFT, DOWN_RIGHT
    }

    /**
     * Counterclockwise Directions
     * <pre>
     *      [2]     [1]
     * [3]      [*]     [0]
     *      [4]     [5]
     * </pre>
     */
    private final static ArrayList<Hex> directions = new ArrayList<Hex>() {{
        add(new Hex(1, 0));
        add(new Hex(1, -1));
        add(new Hex(0, -1));
        add(new Hex(-1, 0));
        add(new Hex(-1, 1));
        add(new Hex(0, 1));
    }};

    /**
     * Get neighbor hex from the direction.
     *
     * @param hex       Current hex
     * @param direction Direction to the Neighbor
     * @return Neighbor hex
     */
    public static Hex neighbor(Hex hex, int direction) {
        return Hex.add(hex, hex.directions.get(direction % directions.size()));
    }

    /**
     * Get neighbor hex from the direction.
     *
     * @param hex       Current hex
     * @param direction Direction to the Neighbor
     * @return Neighbor hex
     */
    public static Hex neighbor(Hex hex, Directions direction) {
        return Hex.add(hex, hex.directions.get(direction.ordinal()));
    }

    /**
     * Counterclockwise diagonal directions
     * <pre>
     *       [ ]   <b>[1]</b>   [ ]
     *    <b>[2]</b>   [ ]   [ ]   <b>[0]</b>
     * [ ]   [ ]   [*]   [ ]   [ ]
     *    <b>[3]</b>   [ ]   [ ]   <b>[5]</b>
     *       [ ]   <b>[4]</b>   [ ]
     * </pre>
     */
    public enum DiagonalDirections {
        UP_RIGHT, UP, UP_LEFT, DOWN_LEFT, DOWN, DOWN_RIGHT
    }

    /**
     * Counterclockwise diagonal directions
     * <pre>
     *       [ ]   <b>[1]</b>   [ ]
     *    <b>[2]</b>   [ ]   [ ]   <b>[0]</b>
     * [ ]   [ ]   [*]   [ ]   [ ]
     *    <b>[3]</b>   [ ]   [ ]   <b>[5]</b>
     *       [ ]   <b>[4]</b>   [ ]
     * </pre>
     */
    private final static ArrayList<Hex> diagonals = new ArrayList<Hex>() {{
        add(new Hex(2, -1));
        add(new Hex(1, -2));
        add(new Hex(-1, -1));
        add(new Hex(-2, 1));
        add(new Hex(-1, 2));
        add(new Hex(1, 1));
    }};

    /**
     * Get neighbor hex from the diagonal direction.
     *
     * @param hex       Current hex
     * @param direction Diagonal direction to the Neighbor
     * @return Neighbor hex
     */
    public static Hex diagonalNeighbor(Hex hex, int direction) {
        return Hex.add(hex, Hex.diagonals.get(direction % diagonals.size()));
    }

    /**
     * Get neighbor hex from the diagonal direction.
     *
     * @param hex       Current hex
     * @param direction Diagonal direction to the Neighbor
     * @return Neighbor hex
     */
    public static Hex diagonalNeighbor(Hex hex, DiagonalDirections direction) {
        return Hex.add(hex, Hex.diagonals.get(direction.ordinal()));
    }

    /**
     * Distance between start and current hex.
     *
     * @param hex Current hex
     * @return Distance in hexes
     */
    private static int distanceFromStart(Hex hex) {
        return (Math.abs(hex.q) + Math.abs(hex.r) + Math.abs(hex.s)) / 2;
    }

    /**
     * Distance between two hexes
     *
     * @param a Start hex
     * @param b End hes
     * @return Distance in hexes
     */
    public static int distance(Hex a, Hex b) {
        return Hex.distanceFromStart(Hex.subtract(a, b));
    }
}
