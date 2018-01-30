package HexMap;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class HexMapPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	HexMap map = new HexMap();

	public HexMapPanel() {
		this(10, 10);
	}

	public HexMapPanel(int width, int height) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (width > 3 && i == 3 && height > 4 && (j == 3 || j == 4)) {
					map.setObject(new DrawableHex(i, j), HexMap.NO_HEX);
				} else {
					map.setObject(new DrawableHex(i, j), HexMap.BLANK);
				}
			}
		}
	}

	public void add(DrawableHex hex) {
		map.setObject(hex, "dh");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Hex h : map.getGrid()) {
			if (h instanceof DrawableHex) {
				DrawableHex dh = (DrawableHex) h;
				Random rand = new Random();
				dh.fill(g, Color.getHSBColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
				dh.draw(g);
			}
		}
	}
}
