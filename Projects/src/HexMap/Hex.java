package HexMap;

import java.util.ArrayList;

public class Hex {
    public final int q;
    public final int r;
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
     * Creates default Hex with the default column (0) and the default row (0)
     */
    public Hex() {
        this(0, 0);
    }

    /**
     * Creates Hex with column (q) and row (r)
     *
     * @param q Column
     * @param r Row
     */
    public Hex(int q, int r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
    }

    public static Hex add(Hex a, Hex b) {
        return new Hex(a.q + b.q, a.r + b.r);
    }

    public static Hex subtract(Hex a, Hex b) {
        return new Hex(a.q - b.q, a.r - b.r);
    }

    public static Hex scale(Hex a, int coef) {
        return new Hex(a.q * coef, a.r * coef);
    }

    public static Hex rotateLeft(Hex hex) {
        return new Hex(-hex.s, -hex.q);
    }

    public static Hex rotateRight(Hex hex) {
        return new Hex(-hex.r, -hex.s);
    }

    public enum Directions {
        RIGHT, UP_RIGHT, UP_LEFT, LEFT, DOWN_LEFT, DOWN_RIGHT
    }

    public static ArrayList<Hex> directions = new ArrayList<Hex>() {{
        add(new Hex(1, 0));
        add(new Hex(1, -1));
        add(new Hex(0, -1));
        add(new Hex(-1, 0));
        add(new Hex(-1, 1));
        add(new Hex(0, 1));
    }};

    public static Hex neighbor(Hex hex, int direction) {
        return Hex.add(hex, hex.directions.get(direction % directions.size()));
    }

    public static Hex neighbor(Hex hex, Directions direction) {
        return Hex.add(hex, hex.directions.get(direction.ordinal()));
    }

    public enum DiagonalDirections {
        UP_RIGHT, UP, UP_LEFT, DOWN_LEFT, DOWN, DOWN_RIGHT
    }

    public static ArrayList<Hex> diagonals = new ArrayList<Hex>() {{
        add(new Hex(2, -1));
        add(new Hex(1, -2));
        add(new Hex(-1, -1));
        add(new Hex(-2, 1));
        add(new Hex(-1, 2));
        add(new Hex(1, 1));
    }};

    public static Hex diagonalNeighbor(Hex hex, int direction) {
        return Hex.add(hex, Hex.diagonals.get(direction % diagonals.size()));
    }

    public static Hex diagonalNeighbor(Hex hex, DiagonalDirections direction) {
        return Hex.add(hex, Hex.diagonals.get(direction.ordinal()));
    }
    private static int distanceFromStart(Hex hex) {
        return (Math.abs(hex.q) + Math.abs(hex.r) + Math.abs(hex.s)) / 2;
    }
    public static int distance(Hex a, Hex b) {
        return Hex.distanceFromStart(Hex.subtract(a, b));
    }
}
