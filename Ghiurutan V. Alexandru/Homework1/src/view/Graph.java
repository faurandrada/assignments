package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * 
 * @author Ghiurutan Draw graph class.
 * 
 */
@SuppressWarnings("serial")
public class Graph extends JPanel {
	private static final int HEIGHT = 400;
	private static final int WIDTH = 400;
	private static final Point ORIGIN = new Point(WIDTH / 2, HEIGHT / 2);
	private double[] xCoordinates;
	private double[] yCoordinates;
	private static double STEP = 0.2;

	public Graph() {
		// Setting the xCoordinates for the drawing,according to our ORIGIN
		// value.
		initializeXCoordinates();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// Draw the lines in the coordinate system
		g2d.setColor(Color.BLACK);
		g2d.drawLine(ORIGIN.x, ORIGIN.y, WIDTH, HEIGHT / 2);
		g2d.drawLine(ORIGIN.x, ORIGIN.y, 0, HEIGHT / 2);
		g2d.drawLine(ORIGIN.x, ORIGIN.y, WIDTH / 2, 0);
		g2d.drawLine(ORIGIN.x, ORIGIN.y, WIDTH / 2, HEIGHT);
		drawAxis(g2d);
		plotPolynomial(g2d);
	}

	private void drawAxis(Graphics2D g2d) {

		for (int i = 0; i <= 10; i++) {
			g2d.drawString("" + i, ORIGIN.x + (20 * i), ORIGIN.y - 5);
			g2d.drawLine(ORIGIN.x + (20 * i), HEIGHT / 2, ORIGIN.x + (20 * i), HEIGHT / 2 - 3);
		}
		for (int i = 1; i <= 10; i++) {
			g2d.drawString("-" + i, ORIGIN.x - (20 * i), ORIGIN.y - 5);
			g2d.drawLine(ORIGIN.x - (20 * i), HEIGHT / 2, ORIGIN.x - (20 * i), HEIGHT / 2 - 3);
		}
		for (int i = 1; i <= 10; i++) {
			g2d.drawString("" + i, HEIGHT / 2 + 5, ORIGIN.y - (19 * i));
			g2d.drawLine(WIDTH / 2, ORIGIN.y - (20 * i), WIDTH / 2 + 3, ORIGIN.y - (20 * i));
		}
		for (int i = 1; i <= 10; i++) {
			g2d.drawString("-" + i, WIDTH / 2 + 5, ORIGIN.y + (20 * i));
			g2d.drawLine(HEIGHT / 2, ORIGIN.y + (20 * i), HEIGHT / 2 + 3, ORIGIN.y + (20 * i));
		}
	}

	private void plotPolynomial(Graphics2D g2d) {
		for (int i = 0; i < 100; i++) {
			xCoordinates[i] = 200 + 20 * xCoordinates[i];
			yCoordinates[i] = 200 - 20 * yCoordinates[i];
		}
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xCoordinates.length);
		g2d.setColor(Color.RED);
		path.moveTo(xCoordinates[0], yCoordinates[0]);
		for (int i = 1; i < xCoordinates.length; i++) {
			path.lineTo(xCoordinates[i], yCoordinates[i]);
		}

		g2d.draw(path);
	}

	private void initializeXCoordinates() {
		this.xCoordinates = new double[100];
		double j = -10;
		for (int i = 0; i < 100; i++, j += STEP) {
			xCoordinates[i] = j;
		}
	}

	public void setYCoordinates(double[] yCoordinates) {
		this.yCoordinates = yCoordinates;
	}

	public double[] getXCoordinates() {
		return xCoordinates;
	}

	public double[] getYCoordinates() {
		return this.yCoordinates;
	}
}
