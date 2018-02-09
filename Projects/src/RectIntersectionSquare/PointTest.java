package RectIntersectionSquare;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class PointTest {

	@Test
	public void testPoint() {
		Point p = new Point();
		assertTrue(p.x == 0 && p.y == 0);
	}
	
	@Test
	public void testEqualsPoint() {
		Point p1 = new Point(5, 5);
		Point p2 = new Point(5, 5);
		Point p3 = new Point(5, 6);
		Point p4 = new Point(6, 5);
		Point p5 = new Point(6, 6);
		assertTrue(p1.equals(p1));
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
		assertFalse(p1.equals(p4));
		assertFalse(p1.equals(p5));
		assertFalse(p3.equals(p4));
		assertFalse(p1.equals(null));
	}
	
	@Test
	public void testPointClone() {
		Point p = new Point();
		Point cloned = p.clone();
		assertTrue(p.equals(cloned));
		p = new Point(5,6);
		cloned = p.clone();
		assertTrue(p.equals(cloned));
	}
}