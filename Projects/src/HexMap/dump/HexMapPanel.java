package HexMap.dump;

import java.awt.*;
import HexMap.Hex;

import javax.swing.JPanel;

public class HexMapPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public boolean drawHexGrid = true;
    HexMap<Drawable> map = new HexMap<Drawable>();

    public HexMapPanel() {
        this(10, 10);
    }

    public HexMapPanel(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map.createHex(i, j);

                if (width > 3 && i == 3 && height > 4 && (j == 3 || j == 4)) {
                    if (j == 3)
                        map.setObject(i, j, new DrawableHex(i, j, true, Color.cyan));

                } else {
                    map.setObject(i, j, new DrawableHex(i, j, true, Color.green));
                }
            }
        }
    }

    public void add(Hex coords, Drawable object) {
        map.setObject(coords, object, true);
    }

    public void rotateGrid() {
        for (Hex h : map.getHexGrid()) {
            if (h instanceof DrawableHex) {
                ((DrawableHex) h).rotate();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Drawable currentDrawable : map.getObjects()) {
            if (currentDrawable != null) {
                currentDrawable.draw(g);
            }
        }
    }
}
