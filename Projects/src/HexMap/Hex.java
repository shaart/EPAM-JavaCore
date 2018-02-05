package HexMap;

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

}
