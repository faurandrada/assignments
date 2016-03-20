package Polynomials;
import javax.swing.*;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	// we define the dimensions of the frame
	private static final int FRAME_WIDTH = 575;
	private static final int FRAME_HEIGHT = 210;
	private ControlPanel gui = new ControlPanel();

	// We construct the main frame,set it's dimensions and add a label
	public Main() {
		this.add(gui);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		this.setTitle("Polynomials");
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		JFrame frame = new Main();
	}

}
