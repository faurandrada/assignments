package UserInterface;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 7109734555548217338L;

	/// constants for ease of maintenance and flexibility for positioning and resizing the input frame/////
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 400;
	private static final int FRAME_COORD_X = 180;
	private static final int FRAME_COORD_Y = 150;
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private OperationsPanel operationsPanel;
	
	public UserFrame(String title) {
		
		////customizing the main frame and adding the panel containing the operations and text fields///////
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLayout(new GridLayout(1, 1));

		operationsPanel = new OperationsPanel();
		add(operationsPanel);

		setVisible(true);
		setLocation(FRAME_COORD_X, FRAME_COORD_Y);
	}
}
