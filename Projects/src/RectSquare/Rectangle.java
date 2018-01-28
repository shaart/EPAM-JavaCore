package RectSquare;

public class Rectangle {

	public static double square(Point p1, Point p2) {
		return square(p1.x, p1.y, p2.x, p2.y);
	}

	public static double square(double x1, double y1, double x2, double y2) {
		return Math.abs(x1 - x2) * Math.abs(y1 - y2);
	}



}
