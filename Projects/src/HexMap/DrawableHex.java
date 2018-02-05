package HexMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class DrawableHex extends Hex implements Drawable {
    public static final int SIDES_COUNT = 6;
    public static boolean drawCoords = true;

    private int size = 30;
    private boolean isFlatTopped = true;

    private Color borderColor;
    private Color fillColor;

    public DrawableHex() {
        this(0, 0, true, Color.white, Color.black);
    }

    public DrawableHex(int column, int row) {
        this(column, row, true);
    }

    public DrawableHex(int column, int row, boolean isFlatTopped) {
        this(column, row, isFlatTopped, Color.white, Color.black);
    }

    public DrawableHex(int column, int row, boolean isFlatTopped, Color fillColor) {
        this(column, row, isFlatTopped, fillColor, Color.black);
    }

    public DrawableHex(int column, int row, Color fillColor) {
        this(column, row, true, fillColor, Color.black);
    }

    public DrawableHex(int column, int row, Color fillColor, Color borderColor) {
        this(column, row, true, fillColor, borderColor);
    }

    public DrawableHex(int column, int row, boolean isFlatTopped, Color fillColor, Color borderColor) {
        super(column, row);
        this.isFlatTopped = isFlatTopped;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setSize(int newSize) {
        size = newSize;
    }

    public int getCenterX() {
        int x = 0;
        int w = getWidth();
        if (isFlatTopped) {
            x = w * q * 3 / 4;
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
        if (isFlatTopped) {
            if (q % 2 == 0) {
                y = r * h; // Formula 3
            } else {
                y = h / 2 + r * h; // Formula 4
            }
        } else {
            y = h * r * 3 / 4;
        }
        return size + y;
    }

    public Point getCenter() {
        return new Point(getCenterX(), getCenterY());
    }

    public void draw(Graphics g) {
        Point center = getCenter();
        fill(g, fillColor);

        Color graphicsColor = g.getColor();
        g.setColor(borderColor);
        g.drawPolygon(getPolygon());
        g.setColor(graphicsColor);

        if (drawCoords) {
            g.drawString(q + ", " + r, center.x - 10, center.y + 5);
        }
    }

    private Polygon getPolygon() {
        Point center = getCenter();
        int[] xs = new int[SIDES_COUNT];
        int[] ys = new int[SIDES_COUNT];
        Point p;
        for (int i = 0; i < SIDES_COUNT; i++) {
            p = cornerPoint(center, this.size, this.isFlatTopped, i);
            xs[i] = p.x;
            ys[i] = p.y;
        }

        return new Polygon(xs, ys, SIDES_COUNT);
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

    public void rotate() {
        isFlatTopped = !isFlatTopped;
    }

    public void fill(Graphics g, Color fillColor) {
        Color graphiicsColor = g.getColor();

        g.setColor(fillColor);
        g.fillPolygon(getPolygon());

        g.setColor(graphiicsColor);
    }

    public int calculateWidth() {
        return size * 2;
    }

    public int calculateHeight() {
        return (int) Math.round(Math.sqrt(3) / 2 * calculateWidth());
    }

    @Override
    public int getWidth() {
        return isFlatTopped ? calculateWidth() : calculateHeight();
    }

    @Override
    public int getHeight() {
        return isFlatTopped ? calculateHeight() : calculateWidth();
    }
}
