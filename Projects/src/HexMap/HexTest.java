package HexMap;

import org.junit.Test;

import static org.junit.Assert.*;

public class HexTest {
    final Hex hDefault = new Hex();
    final Hex h00 = new Hex(0, 0);
    final Hex h10 = new Hex(1, 0);
    final Hex h11 = new Hex(1, 1);
    final Hex h12 = new Hex(1, 2);
    final Hex hm15 = new Hex(-1, 5);
    
    @Test
    public void equals() {
        Hex h2 = new Hex();
        // equal to Hex with same coords
        assertTrue(hDefault.equals(h2));
        assertTrue(hDefault.equals(h00));
        // not equal to null
        assertFalse(hDefault.equals(null));
        // not equal to another
        assertFalse(h00.equals(h11));
        assertFalse(h00.equals(h12));
        assertFalse(h11.equals(h12));
        // self-equal
        assertTrue(h11.equals(h11));
    }

    @Test
    public void hashCodes() {
        Hex hDefault2 = new Hex();
        Hex h12s = new Hex(1, 2);

        assertTrue(hDefault.hashCode() == hDefault2.hashCode());
        assertTrue(hDefault.hashCode() == h00.hashCode());
        assertTrue(hDefault.hashCode() != h11.hashCode());
        assertTrue(hDefault.hashCode() != h12.hashCode());
        assertTrue(h12.hashCode() == h12s.hashCode());
    }

    @Test
    public void add() {
        assertTrue(Hex.add(h00, h11).equals(new Hex(1, 1)));
        assertTrue(Hex.add(h11, h11).equals(new Hex(2, 2)));
        assertTrue(Hex.add(h11, hm15).equals(new Hex(0, 6)));
    }

    @Test
    public void subtract() {
        assertTrue(Hex.subtract(h00, h11).equals(new Hex(-1, -1)));
        assertTrue(Hex.subtract(h11, h11).equals(new Hex(0, 0)));
        assertTrue(Hex.subtract(h11, hm15).equals(new Hex(2, -4)));
    }

    @Test
    public void scale() {
        assertTrue(Hex.scale(h00, 2).equals(new Hex(0, 0)));
        assertTrue(Hex.scale(h11, 2).equals(new Hex(2, 2)));
        assertTrue(Hex.scale(hm15, 5).equals(new Hex(-5, 25)));
    }

    @Test
    public void rotateLeft() {
        assertTrue(Hex.rotateLeft(h00).equals(new Hex(0, 0)));
        assertTrue(Hex.rotateLeft(h11).equals(new Hex(2, -1)));
        assertTrue(Hex.rotateLeft(new Hex(2, -1)).equals(new Hex(1, -2)));
        assertTrue(Hex.rotateLeft(new Hex(1, -2)).equals(new Hex(-1, -1)));
        assertTrue(Hex.rotateLeft(new Hex(-1, -1)).equals(new Hex(-2, 1)));
        assertTrue(Hex.rotateLeft(new Hex(-2, 1)).equals(new Hex(-1, 2)));
        assertTrue(Hex.rotateLeft(new Hex(-1, 2)).equals(h11));
    }

    @Test
    public void rotateRight() {
        assertTrue(Hex.rotateRight(h00).equals(new Hex(0, 0)));
        assertTrue(Hex.rotateRight(h11).equals(new Hex(-1, 2)));
        assertTrue(Hex.rotateRight(new Hex(-1, 2)).equals(new Hex(-2, 1)));
        assertTrue(Hex.rotateRight(new Hex(-2, 1)).equals(new Hex(-1, -1)));
        assertTrue(Hex.rotateRight(new Hex(-1, -1)).equals(new Hex(1, -2)));
        assertTrue(Hex.rotateRight(new Hex(1, -2)).equals(new Hex(2, -1)));
        assertTrue(Hex.rotateRight(new Hex(2, -1)).equals(h11));
    }

    @Test
    public void neighbor() {
        assertTrue(Hex.neighbor(h10, Hex.Directions.RIGHT).equals(new Hex(2, 0)));
        assertTrue(Hex.neighbor(h10, Hex.Directions.UP_RIGHT).equals(new Hex(2, -1)));
        assertTrue(Hex.neighbor(h10, Hex.Directions.UP_LEFT).equals(new Hex(1, -1)));
        assertTrue(Hex.neighbor(h10, Hex.Directions.LEFT).equals(new Hex(0, 0)));
        assertTrue(Hex.neighbor(h10, Hex.Directions.DOWN_LEFT).equals(new Hex(0, 1)));
        assertTrue(Hex.neighbor(h10, Hex.Directions.DOWN_RIGHT).equals(new Hex(1, 1)));

        assertTrue(Hex.neighbor(h10, 0).equals(new Hex(2, 0)));
        assertTrue(Hex.neighbor(h10, 1).equals(new Hex(2, -1)));
        assertTrue(Hex.neighbor(h10, 2).equals(new Hex(1, -1)));
        assertTrue(Hex.neighbor(h10, 3).equals(new Hex(0, 0)));
        assertTrue(Hex.neighbor(h10, 4).equals(new Hex(0, 1)));
        assertTrue(Hex.neighbor(h10, 5).equals(new Hex(1, 1)));
        // Repeat
        assertTrue(Hex.neighbor(h10, 6).equals(new Hex(2, 0)));
    }

    @Test
    public void diagonalNeighbor() {
        assertTrue(Hex.diagonalNeighbor(h10, Hex.DiagonalDirections.UP_RIGHT).equals(new Hex(3, -1)));
        assertTrue(Hex.diagonalNeighbor(h10, Hex.DiagonalDirections.UP).equals(new Hex(2, -2)));
        assertTrue(Hex.diagonalNeighbor(h10, Hex.DiagonalDirections.UP_LEFT).equals(new Hex(0, -1)));
        assertTrue(Hex.diagonalNeighbor(h10, Hex.DiagonalDirections.DOWN_LEFT).equals(new Hex(-1, 1)));
        assertTrue(Hex.diagonalNeighbor(h10, Hex.DiagonalDirections.DOWN).equals(new Hex(0, 2)));
        assertTrue(Hex.diagonalNeighbor(h10, Hex.DiagonalDirections.DOWN_RIGHT).equals(new Hex(2, 1)));

        assertTrue(Hex.diagonalNeighbor(h10, 0).equals(new Hex(3, -1)));
        assertTrue(Hex.diagonalNeighbor(h10, 1).equals(new Hex(2, -2)));
        assertTrue(Hex.diagonalNeighbor(h10, 2).equals(new Hex(0, -1)));
        assertTrue(Hex.diagonalNeighbor(h10, 3).equals(new Hex(-1, 1)));
        assertTrue(Hex.diagonalNeighbor(h10, 4).equals(new Hex(0, 2)));
        assertTrue(Hex.diagonalNeighbor(h10, 5).equals(new Hex(2, 1)));
        // Repeat
        assertTrue(Hex.diagonalNeighbor(h10, 6).equals(new Hex(3, -1)));

    }

    @Test
    public void distance() {
        assertTrue(Hex.distance(h00, h10) == 1);
        assertTrue(Hex.distance(h00, h11) == 2);
        assertTrue(Hex.distance(h00, h12) == 3);

        assertTrue(Hex.distance(h12, h10) == 2);
        assertTrue(Hex.distance(h12, h11) == 1);
        assertTrue(Hex.distance(h12, h12) == 0);
    }
}