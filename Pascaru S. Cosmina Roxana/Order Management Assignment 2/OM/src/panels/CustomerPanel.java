package panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerPanel extends JPanel  {
	
	private JLabel instruction = new JLabel("Complete your profile");
	private JLabel empty = new JLabel("");
	private JLabel address = new JLabel("Address");
	private JTextField addressText = new JTextField();
	private JLabel email = new JLabel("Email");
	private JTextField emailText = new JTextField();
	private JLabel phone = new JLabel("Phone");
	private JTextField phoneText = new JTextField();
	private String userAddress;
	private String userEmail;
	private String userPhone;

	public CustomerPanel(JButton back, JButton save,  JTextField addressText, 
			JTextField emailText, JTextField phoneText) {
		this.addressText = addressText;
		this.emailText = emailText;
		this.phoneText = phoneText;

		setLayout(new GridLayout(5, 2));
		add(instruction);
		add(empty);
		add(address);
		add(addressText);
		add(email);
		add(emailText);
		add(phone);
		add(phoneText);
		add(back);
		add(save);

	}

	public String getAddress() {
		return userAddress;
	}

	public String getEmail() {
		return userEmail;
	}

	public String getPhone() {
		return userPhone;
	}

}
