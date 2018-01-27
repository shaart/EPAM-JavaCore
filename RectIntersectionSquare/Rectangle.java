package RectIntersectionSquare;

public class Rectangle {
	private final String NULL_POINTER_MESSAGE = "One of points was null";

	private final Point leftBottom;
	private final Point rightTop;

	public final double leftX;
	public final double rightX;
	public final double bottomY;
	public final double topY;

	public Point getLeftTop() throws NullPointerException {
		if (leftBottom == null || rightTop == null)
			throw new NullPointerException(NULL_POINTER_MESSAGE);
		return new Point(leftBottom.x, rightTop.y);
	}

	public Point getLeftBottom() throws NullPointerException {
		if (leftBottom == null)
			throw new NullPointerException(NULL_POINTER_MESSAGE);
		return leftBottom;
	}

	public Point getRightTop() throws NullPointerException {
		if (rightTop == null)
			throw new NullPointerException(NULL_POINTER_MESSAGE);
		return rightTop;
	}

	public Point getRightBottom() throws NullPointerException {
		if (leftBottom == null || rightTop == null)
			throw new NullPointerException(NULL_POINTER_MESSAGE);
		return new Point(rightTop.x, leftBottom.y);
	}

	public Rectangle() {
		this(null, null);
	}
	
	public Rectangle(double x1, double y1, double x2, double y2) {
		this(new Point(x1, y1), new Point(x2, y2));
	}

	public Rectangle(Point p1, Point p2) {
		if (p1 == null && p2 == null) {
			leftX = rightX = bottomY = topY = 0;
			leftBottom = new Point();
			rightTop = new Point();
		} else if (p1 != null && p2 != null) {
			leftX = p1.x < p2.x ? p1.x : p2.x;
			rightX = p1.x > p2.x ? p1.x : p2.x;
			bottomY = p1.y < p2.y ? p1.y : p2.y;
			topY = p1.y > p2.y ? p1.y : p2.y;
			this.leftBottom = new Point(leftX, bottomY);
			this.rightTop = new Point(rightX, topY);
		} else {
			Point p = p2 == null ? p1 : p2;
			
			leftX = p.x < 0 ? p.x : 0;
			rightX = p.x > 0 ? p.x : 0;
			bottomY = p.y < 0 ? p.y : 0;
			topY = p.y > 0 ? p.y : 0;
			this.leftBottom = new Point(leftX, bottomY);
			this.rightTop = new Point(rightX, topY);
		}
	}

	public static Rectangle intersection(Rectangle r1, Rectangle r2) {
		// same object
		if (r1 == r2)
			return r1;

		double xl = r1.leftX >= r2.leftX ? r1.leftX : r2.leftX;
		double yb = r1.bottomY >= r2.bottomY ? r1.bottomY : r2.bottomY;
		double xr = r1.rightX <= r2.rightX ? r1.rightX : r2.rightX;
		double yt = r1.topY <= r2.topY ? r1.topY : r2.topY;

		// if not degenerated
		if (xl <= xr && yb <= yt) {
			return new Rectangle(new Point(xl, yb), new Point(xr, yt));
		}

		return null;
	}

	public Rectangle intersection(Rectangle another) {
		return Rectangle.intersection(this, another);
	}

	public double square() {
		return square(this.leftBottom, this.rightTop);
	}

	public static double square(Point p1, Point p2) {
		return square(p1.x, p1.y, p2.x, p2.y);
	}

	public static double square(double x1, double y1, double x2, double y2) {
		return Math.abs(x1 - x2) * Math.abs(y1 - y2);
	}

	@Override
	public boolean equals(Object another) {
		if (another == null)
			return false;
		if (this == another)
			return true;

		if (another instanceof Rectangle) {
			return Rectangle.equals(this, (Rectangle) another);
		}
		return false;
	}

	public static boolean equals(Rectangle r1, Rectangle r2) {
		return r1 == r2 || r1.getLeftBottom().equals(r2.getLeftBottom()) && r1.getRightTop().equals(r2.getRightTop());
	}

}
