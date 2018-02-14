package HexMap;

import java.util.*;

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

    @Override
    public String toString() {
        return String.format("Hex(%d, %d)", q, r);
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
     * Get all close neighbors
     *
     * @return List of neighbors
     */
    public List<Hex> neighbors() {
        List<Hex> neighbors = new ArrayList<>();

        for (Hex direction : directions) {
            neighbors.add(Hex.add(this, direction));
        }

        return neighbors;
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

    /**
     * Gets Hex by pixel coordinates (x, y) and size of hex.
     * By default Hex is pointy top
     *
     * @param x       X coordinate
     * @param y       Y coordinate
     * @param hexSize Size of hex's side
     * @return Hex at these coordinates
     */
    public static Hex hexAt(double x, double y, int hexSize) {
        return hexAt(x, y, hexSize, false);
    }

    /**
     * Gets Hex by pixel coordinates (x, y) and size of hex.
     *
     * @param x         X coordinate
     * @param y         Y coordinate
     * @param hexSize   Size of hex's side
     * @param isFlatTop Type of hex: <b>flat top</b> or <b>pointy top</b>
     * @return Hex at these coordinates
     */
    public static Hex hexAt(double x, double y, int hexSize, boolean isFlatTop) {
        double q;
        double r;
        if (isFlatTop) {
            q = x * 2 / 3 / hexSize;
            r = (-x / 3 + Math.sqrt(3) / 3 * y) / hexSize;
        } else {
            q = x * Math.sqrt(3) / 3 - y / 3 / hexSize;
            r = y * 2 / 3 / hexSize;
        }
        return (new FractionalHex(q, r)).toHex();
    }

    /**
     * Make line from <b>start</b> to <b>end</b>.
     *
     * @param start Line's start Hex
     * @param end   Line's end Hex
     * @return List of Hexes forming the line
     */
    public static List<Hex> line(final Hex start, final Hex end) {
        return FractionalHex.hexLine(start, end);
    }

    /**
     * Forms list of Hexes reachable by <b>steps</b> from <b>start</b> Hex considering <b>obstacles</b>.
     *
     * @param start     Start Hex
     * @param obstacles List of impassable Hexes
     * @param steps     Steps from <b>start</b> Hex
     * @return List of reachable Hexes by <b>steps</b> from <b>hex</b>
     */
    public static List<Hex> reachable(final Hex start, final List<Hex> obstacles, final int steps) {
        List<Hex> visited = new ArrayList<>();
        visited.add(start);

        ArrayList<ArrayList<Hex>> reachable = new ArrayList<>();
        reachable.add(new ArrayList<Hex>() {
            {
                add(start);
            }
        });

        for (int step = 1; step <= steps; step++) {
            reachable.add(new ArrayList<Hex>()); // initialize new step
            ArrayList<Hex> reachableAtPreviousStep = reachable.get(step - 1);
            ArrayList<Hex> reachableAtCurrentStep = reachable.get(step);
            for (Hex hex : reachableAtPreviousStep) {
                for (int dir = 0; dir < Hex.directions.size(); dir++) {
                    Hex neighbor = Hex.neighbor(hex, dir);
                    if (!visited.contains(neighbor) && (obstacles == null || !obstacles.contains(neighbor))) {
                        visited.add(neighbor);
                        reachableAtCurrentStep.add(neighbor);
                    }
                }
            }
        }

        return visited;
    }

    /**
     * Building path between <code>start</code> Hex and <code>destination</code> Hex considering <code>obstacles</code>.
     *
     * @param start       Start Hex
     * @param destination Destination Hex
     * @param obstacles   List of impassable Hexes
     * @return Found path <br>If found path length is <b>zero</b> - <code>destination</code> Hex is <b>unreachable</b>
     */
    public static List<Hex> path(final Hex start, final Hex destination, final List<Hex> obstacles) {
        // The set of nodes already evaluated
        Set<Hex> closedSet = new HashSet<>();

        // The set of currently discovered nodes that are not evaluated yet.
        // Initially, only the start node is known.
        HashSet<Hex> openSet = new HashSet<Hex>() {
            {
                add(start);
            }
        };

        // For each node, which node it can most efficiently be reached from.
        // If a node can be reached from many nodes, cameFrom will eventually contain the
        // most efficient previous step.
        HashMap<Hex, Hex> cameFrom = new HashMap<>();

        // For each node, the cost of getting from the start node to that node.
        Map<Hex, Integer> gScore = new HashMap<>();
        // The cost of going from start to start is zero.
        gScore.put(start, 0);

        // For each node, the total cost of getting from the start node to the goal
        // by passing by that node. That value is partly known, partly heuristic.
        Map<Hex, Integer> fScore = new HashMap<>();
        // For the first node, that value is completely heuristic.
        fScore.put(start, Hex.distance(start, destination));

        while (!openSet.isEmpty()) {
            Hex current = Hex.hexWithLowestScore(openSet, fScore);

            if (current.equals(destination)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Hex neigbor : current.neighbors()) {
                if (closedSet.contains(neigbor) || (obstacles != null && obstacles.contains(neigbor))) continue;

                if (!openSet.contains(neigbor) && !closedSet.contains(neigbor)) {
                    openSet.add(neigbor);
                }

                // The distance from start to a neighbor
                // the "dist_between" function may vary as per the solution requirements.
                Integer tentativeGScore = gScore.get(current) + Hex.distance(current, neigbor);
                Integer neighborGScore = gScore.get(neigbor);
                if (neighborGScore != null && tentativeGScore >= neighborGScore) continue;

                // This path is the best until now. Record it!
                cameFrom.put(neigbor, current);
                gScore.put(neigbor, tentativeGScore);
                fScore.put(neigbor, gScore.get(neigbor) + Hex.distance(neigbor, destination));
            }
        }

        // Fail
        return new ArrayList<>();
    }

    private static List<Hex> reconstructPath(HashMap<Hex, Hex> cameFrom, Hex current) {
        List<Hex> totalPath = new ArrayList<>();
        totalPath.add(current);

        while (cameFrom.keySet().contains(current) && current != cameFrom.get(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }
        totalPath.remove(0); // we don't need start position

        return totalPath;
    }

    private static Hex hexWithLowestScore(Set<Hex> openSet, Map<Hex, Integer> fScoresMap) {
        Integer minScore = null;
        Hex soughtHex = null;
        for(Hex curHex : openSet) {
            Integer curCore = fScoresMap.get(curHex);
            if (curCore == null) continue;
            if (minScore == null || curCore < minScore) {
                minScore = curCore;
                soughtHex = curHex;
            }
        }

        return soughtHex;
    }
}

/**
 * Auxiliary class for calculations and algorithms<br>
 * Hex with <code>double</code> coordinates
 */
class FractionalHex {
    public final double q;
    public final double r;
    public final double s;

    public FractionalHex(double q, double r) {
        this.q = q;
        this.r = r;
        this.s = -q - r;
    }

    /**
     * Calculates linear interpolated value between <b>start</b> and <b>end</b> using coefficient <b>t</b>.
     *
     * @param start Start value
     * @param end   End value
     * @param t     Interpolation coefficient. 0 &le; t &le; 1
     * @return Interpolated value
     */
    private static double linearInterpolation(double start, double end, double t) {
        if (t < 0) return start;
        if (t > 1) return end;

        return start + t * (end - start);
    }

    /**
     * Calculates linear interpolated fractional hex.
     *
     * @param start Start Hex
     * @param end   End Hex
     * @param t     Interpolation coefficient. 0 &le; t &le; 1
     * @return Interpolated Fractional Hex
     */
    public static FractionalHex linearInterpolation(FractionalHex start, FractionalHex end, double t) {
        double tq = linearInterpolation(start.q, end.q, t);
        double rq = linearInterpolation(start.r, end.r, t);

        return new FractionalHex(tq, rq);
    }

    /**
     * Make line from Hex <b>start</b> to Hex <b>end</b>.
     *
     * @param start Start of line
     * @param end   End of line
     * @return List of Hexes forming the line
     */
    public static List<Hex> hexLine(final Hex start, final Hex end) {
        List<Hex> line = new ArrayList<>();

        FractionalHex startFract = new FractionalHex(start.q, start.r);
        FractionalHex endFract = new FractionalHex(end.q, end.r);

        int distance = Hex.distance(start, end); // steps count
        double step = 1.0 / Math.max(distance, 1); // step length
        for (int i = 0; i <= distance; i++) {
            line.add(FractionalHex.linearInterpolation(startFract, endFract, step * i).toHex());
        }

        return line;
    }

    /**
     * Rounds fractional hex's coordinates and returns Hex.
     *
     * @return Rounded Hex
     */
    public Hex toHex() {
        return FractionalHex.hexRound(this);
    }

    /**
     * Rounds fractional hex's coordinates and returns Hex.
     *
     * @param h Fractional Hex for round
     * @return Rounded Hex
     */
    public static Hex hexRound(FractionalHex h) {
        int q = (int) (Math.round(h.q));
        int r = (int) (Math.round(h.r));
        int s = (int) (Math.round(h.s));
        double dq = Math.abs(q - h.q);
        double dr = Math.abs(r - h.r);
        double ds = Math.abs(s - h.s);
        // Save hex's state: q + r + s = 0
        // Reset coordinate with largest change
        if (dq > dr && dq > ds) {
            q = -r - s;
        } else if (dr > ds) {
            r = -q - s;
        }

        return new Hex(q, r);
    }
}