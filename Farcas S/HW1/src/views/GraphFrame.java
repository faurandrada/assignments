package views;

import java.awt.*;
import java.awt.geom.GeneralPath;

import javax.swing.*;

import models.*;

/**
 * 
 * frame used for plotting graphs of polynomials graphs are poltted on the
 * (-5,5)x(-10,10) real domain, and the window is nearly 400x400
 *
 */
public class GraphFrame extends JFrame {

	private Polynomial p;
	private GraphPanel graphPanel;
	public static double STEP = 0.01;

	public GraphFrame(Polynomial p, String title) {
		this.p = p;
		setTitle(title);
		setSize(430, 430);
		graphPanel = new GraphPanel();
		add(graphPanel);
		setVisible(true);
	}

	/**
	 * Normalizes x in case p(x) is out of the visible range (-10, 10)
	 * 
	 * @param x
	 *            -- value to be normalized from
	 * @return -- normalized x
	 */
	public double excludeOutOfBounds(double x) {
		while ((((Functions.evaluateAt(p, x) * 20 < -200) || (Functions.evaluateAt(p, x) * 20 > 200)) && (x <= 5))) {
			x += STEP;
		}
		return x;
	}

	public class GraphPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			double x;
			int i;
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, 400, 400);
			g2.translate(200, 200);
			g2.setColor(Color.BLACK);
			g2.drawLine(-200, 0, 200, 0);
			g2.drawLine(0, 200, 0, -200);
			for (i = -5; i <= 5; i++) {
				if (i != 0) {
					g2.drawLine(i * 40, -2, i * 40, 2);
					g2.drawString(String.valueOf(i), i * 40, -3);
				}
			}
			for (i = -10; i <= 10; i += 2) {
				if (i != 0) {
					g2.drawLine(-2, i * (-20), 2, i * (-20));
					g2.drawString(String.valueOf(i), 3, i * (-20));
				}
			}
			g2.drawString("0", 3, -3);
			x = -5;
			g2.setColor(Color.RED);
			GeneralPath gp = new GeneralPath();
			x = excludeOutOfBounds(x);
			if (x <= 5) {
				gp.moveTo(x * 40, Functions.evaluateAt(p, x) * (-20));
				while (x <= 5) {
					x = excludeOutOfBounds(x);
					if (x <= 5) {
						gp.lineTo(x * 40, Functions.evaluateAt(p, x) * (-20));
						x += STEP;
					} else {
						break;
					}
				}
				g2.draw(gp);
			}
		}
	}

}
