package RectIntersectionSquare;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

class RectangleTest {

	@Test
	void testRectangle() {
		Rectangle r = new Rectangle();
		Rectangle r2 = new Rectangle(new Point(0, 0), new Point(0, 0));
		assertTrue(r.equals(r2));
		Rectangle r3 = new Rectangle(null, null);
		assertTrue(r3.equals(r2));
		r3 = new Rectangle(new Point(0, 0), null);
		assertTrue(r3.equals(r2));
		r3 = new Rectangle(null, new Point(0, 0));
		assertTrue(r3.equals(r2));
	}

	@Test
	void testGetLeftTop() {		
		Rectangle r = new Rectangle(new Point(0,0), new Point(5,5));
		assertTrue(r.getLeftTop().equals(new Point(0,5)));
	}
	
	@Test
	void testGetLeftBottom() {
		Point expected = new Point(0,0);
		Rectangle r = new Rectangle(new Point(0,0), new Point(5,5));
		assertTrue(expected.equals(r.getLeftBottom()));
		r = new Rectangle(new Point(5,0), new Point(0,5));
		assertTrue(expected.equals(r.getLeftBottom()));
		
		expected = new Point(-1,1);
		r = new Rectangle(new Point(-1,1), new Point(5,5));
		assertTrue(expected.equals(r.getLeftBottom()));
		r = new Rectangle(new Point(-1,5), new Point(5,1));
		assertTrue(expected.equals(r.getLeftBottom()));
	}

	@Test
	void testGetRightTop() {
		Rectangle r = new Rectangle(new Point(0,0), new Point(5,5));
		assertTrue(r.getRightTop().equals(new Point(5,5)));
		r = new Rectangle(new Point(5,0), new Point(0,5));
		assertTrue(r.getRightTop().equals(new Point(5,5)));
	}

	@Test
	void testGetRightBottom() {		
		Rectangle r = new Rectangle(new Point(0,0), new Point(5,5));
		assertTrue(r.getRightBottom().equals(new Point(5,0)));
	}

	@Test
	void testIntersection() {
		Rectangle r1 = new Rectangle(new Point(0, 0), new Point(5, 5));

		// One in another
		Rectangle r2 = new Rectangle(new Point(1, 1), new Point(4, 4));
		Rectangle result = Rectangle.intersection(r1, r2);
		Rectangle expected = new Rectangle(new Point(1, 1), new Point(4, 4));
		assertTrue(expected.equals(result));

		// Same
		r2 = new Rectangle(new Point(0, 0), new Point(5, 5));
		result = Rectangle.intersection(r1, r2);
		expected = new Rectangle(new Point(0, 0), new Point(5, 5));
		assertTrue(expected.equals(result));

		// Intersection
		r2 = new Rectangle(new Point(-1, -1), new Point(1, 1));
		result = Rectangle.intersection(r1, r2);
		expected = new Rectangle(new Point(0, 0), new Point(1, 1));
		assertTrue(expected.equals(result));
		
		// Intersection on one side
		r2 = new Rectangle(new Point(0, 5), new Point(6, 6));
		result = Rectangle.intersection(r1, r2);
		expected = new Rectangle(new Point(0, 5), new Point(5, 5));
		assertTrue(expected.equals(result));

		// No intersection
		r2 = new Rectangle(new Point(-1, -1), new Point(-2, -2));
		result = Rectangle.intersection(r1, r2);
		assertTrue(result == null);
	}

	@Test
	void testSquare() {
		Rectangle r = new Rectangle();
		assertTrue(Double.compare(r.square(), 0) == 0);
		r = new Rectangle(new Point(0, 0), new Point(5, 5));
		assertTrue(Double.compare(r.square(), 25) == 0);
		r = new Rectangle(new Point(0, 0), new Point(-5, -5));
		assertTrue(Double.compare(r.square(), 25) == 0);
		r = new Rectangle(new Point(0, 0), new Point(-5, 1));
		assertTrue(Double.compare(r.square(), 5) == 0);
		r = new Rectangle(new Point(-1, -1), new Point(1, 1));
		assertTrue(Double.compare(r.square(), 4) == 0);
		r = new Rectangle(new Point(1, -5), new Point(-1, 5));
		assertTrue(Double.compare(r.square(), 20) == 0);
	}

	@Test
	void testSquarePointPoint() {
		double r = 0;
		r = Rectangle.square(new Point(0, 0), new Point(5, 5));
		assertTrue(Double.compare(r, 25) == 0);
		r = Rectangle.square(new Point(0, 0), new Point(-5, -5));
		assertTrue(Double.compare(r, 25) == 0);
		r = Rectangle.square(new Point(0, 0), new Point(-5, 1));
		assertTrue(Double.compare(r, 5) == 0);
		r = Rectangle.square(new Point(-1, -1), new Point(1, 1));
		assertTrue(Double.compare(r, 4) == 0);
		r = Rectangle.square(new Point(1, -5), new Point(-1, 5));
		assertTrue(Double.compare(r, 20) == 0);
	}

	@Test
	void testSquareDoubleDoubleDoubleDouble() {
		double r = 0;
		r = Rectangle.square(0, 0, 5, 5);
		assertTrue(Double.compare(r, 25) == 0);
		r = Rectangle.square(0, 0, -5, -5);
		assertTrue(Double.compare(r, 25) == 0);
		r = Rectangle.square(0, 0, -5, 1);
		assertTrue(Double.compare(r, 5) == 0);
		r = Rectangle.square(-1, -1, 1, 1);
		assertTrue(Double.compare(r, 4) == 0);
		r = Rectangle.square(1, -5, -1, 5);
		assertTrue(Double.compare(r, 20) == 0);
	}

	@Test
	void testEqualsRectangle() {
		Rectangle r = new Rectangle(new Point(0,0), new Point(5,5));
		Rectangle r2 = new Rectangle(new Point(0,5), new Point(5,0));
		assertTrue(r.equals(r2));
	}

}
