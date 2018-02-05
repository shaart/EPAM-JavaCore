package HexMap;

import org.junit.Test;

import static org.junit.Assert.*;

public class HexTest {

    @Test
    public void equals() {
        Hex h1 = new Hex();
        Hex h2 = new Hex();
        Hex h00 = new Hex(0, 0);
        Hex h11 = new Hex(1, 1);
        Hex h12 = new Hex(1, 2);
        // equal to Hex with same coords
        assertTrue(h1.equals(h2));
        assertTrue(h1.equals(h00));
        // not equal to null
        assertFalse(h1.equals(null));
        // not equal to another
        assertFalse(h00.equals(h11));
        assertFalse(h00.equals(h12));
        assertFalse(h11.equals(h12));
        // self-equal
        assertTrue(h11.equals(h11));
    }
}