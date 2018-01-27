package RectIntersectionSquare;

public class Point {
	public final double x;
	public final double y;

	public Point() {
		x = 0;
		y = 0;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		
		if (o instanceof Point) {
			Point p = (Point) o;
			return p.x == this.x && p.y == this.y;
		}
		
		return false;
	}
	
	public Point clone() {
		return new Point(this.x, this.y);
	}
}