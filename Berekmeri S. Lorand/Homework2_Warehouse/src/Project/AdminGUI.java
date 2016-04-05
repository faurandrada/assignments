package Project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	Interface aop = new Interface();
	static int i = 3;

	public AdminGUI() {
	}

	public boolean checkUser() {
		String test1 = JOptionPane.showInputDialog(null, "Please type the administrator password");
	    if (test1.equals("admin")){
	    	return true;
	    }
	    return false;
	}
}