package UserInterface;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class CustomizedButton extends JButton {
	
	private static final long serialVersionUID = 2974049251791679769L;

	public CustomizedButton(String text) {
		setText(text);
		setBackground(Color.decode("0xededed"));
		setForeground(Color.BLACK);
		setFont(new Font("Serif", Font.BOLD, 16));
	}

}
