package HexMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import HexMap.Point;

public class DrawableHex extends Hex implements Drawable {
	public static final int SIDES_COUNT = 6;

	private int size = 30;
	boolean isFlat = true;

	public DrawableHex() {
	}

	public DrawableHex(int q, int r) {
		this(q, r, true);
	}

	public DrawableHex(int q, int r, boolean isFlat) {
		super(q, r);
		this.isFlat = isFlat;
	}

	public void rotate() {
		isFlat = !isFlat;
	}

	public int getCenterX() {
		int x = 0;
		int w = getWidth();
		if (isFlat) {
			if (q % 2 == 0) {
				x = (w * 3 / 2) * q / 2; // Formula 1
			} else {
				x = (w * 3 / 4) * q; // Formula 2
			}
		} else {
			if (r % 2 == 0) {
				x = q * w; // Formula 3
			} else {
				x = w / 2 + q * w; // Formula 4
			}
		}
		return size + x;
	}

	public int getCenterY() {
		int y = 0;
		int h = getHeight();
		if (!isFlat) { // inverted
			if (r % 2 == 0) {
				y = (h * 3 / 2) * r / 2; // Formula 1
			} else {
				y = (h * 3 / 4) * r; // Formula 2
			}
		} else {
			if (q % 2 == 0) {
				y = r * h; // Formula 3
			} else {
				y = h / 2 + r * h; // Formula 4
			}
		}
		return size + y;
	}

	public Point getCenter() {
		return new Point(getCenterX(), getCenterY());
	}

	public void draw(Graphics g) {
		Point center = getCenter();
		g.drawPolygon(getPolygon());
		g.drawString(q + ", " + r, center.x - 10, center.y + 5);
	}

	private Polygon getPolygon() {
		Point center = getCenter();
		int[] xs = new int[SIDES_COUNT];
		int[] ys = new int[SIDES_COUNT];
		Point p;
		for (int i = 0; i < SIDES_COUNT; i++) {
			p = cornerPoint(center, this.size, this.isFlat, i);
			xs[i] = p.x;
			ys[i] = p.y;
		}

		return new Polygon(xs, ys, SIDES_COUNT);
	}

	public void fill(Graphics g, Color fillColor) {
		Color c = g.getColor();
		g.setColor(fillColor);
		g.fillPolygon(getPolygon());
		g.setColor(c);
	}

	private Point cornerPoint(Point center, int size, boolean isFlat, int i) {
		i = i % SIDES_COUNT;
		if (i < 0) {
			i = SIDES_COUNT + i;
		}
		double angle_deg = 60 * i + (isFlat ? 0 : 30);
		double angle_rad = Math.PI / 180 * angle_deg;
		return new Point(center.x + (int) Math.round(size * Math.cos(angle_rad)),
				center.y + (int) Math.round(size * Math.sin(angle_rad)));
	}

	public int calculateWidth() {
		return size * 2;
	}

	public int calculateHeight() {
		return (int) Math.round(Math.sqrt(3) / 2 * calculateWidth());
	}

	@Override
	public int getWidth() {
		return isFlat ? calculateWidth() : calculateHeight();
	}

	@Override
	public int getHeight() {
		return isFlat ? calculateHeight() : calculateWidth();
	}
}
