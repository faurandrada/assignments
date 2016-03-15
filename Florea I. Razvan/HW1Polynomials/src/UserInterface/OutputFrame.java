package UserInterface;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class OutputFrame extends JFrame {

	private static final long serialVersionUID = -6654121713624766247L;

	/// constants for ease of maintenance and flexibility for positioning and resizing the output frame///// 
	private static final int FRAME_COORD_X = 700;
	private static final int FRAME_COORD_Y = 80;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 600;
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private Fonts fonts;
	private JTextArea outputArea;

	public OutputFrame(String string) {
		
		/// customizing the output frame///////////////////////////////////////////////
		setTitle(string);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		setLocation(FRAME_COORD_X, FRAME_COORD_Y);
		setLayout(new GridLayout(1, 1));
		
		outputArea = new JTextArea();
		fonts = new Fonts();
		
		/// adding and initializing the area for displaying the output///////////////////
		outputArea.disable();
		outputArea.setDisabledTextColor(Color.BLUE);
		outputArea.setFont(fonts.getOutputFont());
		add(outputArea);
	}

	public JTextArea getOutputArea() {
		return outputArea;
	}
}
