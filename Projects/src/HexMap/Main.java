package HexMap;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Hex> obstacles = new ArrayList<Hex>() {{
            add(new Hex(-1, -1));
            add(new Hex(0, -2));
            add(new Hex(1, -3));
        }};
        Hex start = new Hex(0, 0);
        Hex destination = new Hex(0, -3);
        List<Hex> path = Hex.path(start, destination, obstacles);

        System.out.println("== Obstacles:");
        for (Hex obstacle : obstacles) {
            System.out.println(obstacle);
        }

        System.out.format("== Path from %s to %s:\n", start, destination);
        int step = 1;
        for (Hex stepHex : path) {
            System.out.println(step++ + ". " + stepHex);
        }
        System.out.format("== Direct Distance between %s and %s: \n%d\n", start, destination, Hex.distance(start, destination));
    }
}
