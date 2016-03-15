package UserInterface;

import java.awt.Color;

import javax.swing.JButton;
/*
 * Class used for customizing the buttons on the main frame
 */
public class CustomizedButton extends JButton {
	
	private static final long serialVersionUID = 892171781684188690L;
	
	public CustomizedButton(String text) {
		setText(text);
		setBackground(Color.WHITE);
		
	}

}
