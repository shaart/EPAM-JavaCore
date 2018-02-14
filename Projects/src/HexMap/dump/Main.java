package HexMap.dump;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JWindow;

class AppWindow extends JWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;

	public AppWindow() {
		initGUI(400, 400, "HexMap");
	}

	public void showFrame() {
		mainFrame.setVisible(true);
	}

	private void initGUI(int width, int height, String title) {
		mainFrame = new JFrame(title);
		mainFrame.setSize(width, height);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		mainFrame.setVisible(true);
		int defaultMapWidth = 15;
		int defaultMapHeight = 7;
		HexMapPanel hexMap = new HexMapPanel(defaultMapWidth, defaultMapHeight);
		mainFrame.add(hexMap);
	}
}

public class Main {

	public static void main(String[] args) {
		AppWindow window = new AppWindow();
		window.showFrame();
	}
}
