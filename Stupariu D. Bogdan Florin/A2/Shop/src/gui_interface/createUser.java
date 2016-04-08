package gui_interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actors.User;
import run.Const;

public class createUser implements ActionListener {

	private JFrame newUser = new JFrame("New User");
	private JPanel contentPanel = new JPanel();
	private JTextField nameField = new JTextField();
	private JTextField passwordField = new JTextField();
	private JTextField countryField = new JTextField();
	private JTextField addressField = new JTextField();
	private JTextField ageField = new JTextField();
	private JLabel nameText = new JLabel("Name");
	private JLabel passwordText = new JLabel("Password");
	private JLabel countryText = new JLabel("Country");
	private JLabel addressText = new JLabel("Address");
	private JLabel ageText = new JLabel("Age");
	private JButton process = new JButton("Process");
	private String name = new String();
	private String password = new String();
	private String country = new String();
	private String address = new String();
	private String age = new String();
	private File file = new File("C:\\Users\\Stupariu\\workspace\\Shop\\src\\actors\\users.txt");

	public createUser() {
		buildFrame(Const.NEW_USER_HEIGHT, Const.NEW_USER_WIDTH);

		contentPanel.setLayout(new GridBagLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		nameText.setForeground(Color.WHITE);
		contentPanel.add(nameText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		nameField.setPreferredSize(new Dimension(200, 20));
		contentPanel.add(nameField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		passwordText.setForeground(Color.WHITE);
		contentPanel.add(passwordText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		passwordField.setPreferredSize(new Dimension(200, 20));
		contentPanel.add(passwordField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		countryText.setForeground(Color.WHITE);
		contentPanel.add(countryText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		countryField.setPreferredSize(new Dimension(200, 20));
		contentPanel.add(countryField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		addressText.setForeground(Color.WHITE);
		contentPanel.add(addressText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		addressField.setPreferredSize(new Dimension(200, 20));
		contentPanel.add(addressField, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		ageText.setForeground(Color.WHITE);
		contentPanel.add(ageText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		ageField.setPreferredSize(new Dimension(200, 20));
		contentPanel.add(ageField, gbc);

		gbc.insets = new Insets(30, 200, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		process.setBackground(Color.black);
		process.setForeground(Color.WHITE);
		process.setFocusPainted(false);
		process.addActionListener(this);
		contentPanel.add(process, gbc);

		newUser.add(contentPanel);
		newUser.setVisible(true);
	}

	private void buildFrame(int HEIGHT, int WIDTH) {
		// TODO Auto-generated method stub
		newUser.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		newUser.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		newUser.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		newUser.setResizable(false);
		newUser.setLocationRelativeTo(null);
		newUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newUser.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == process) {
			name = nameField.getText();
			password = passwordField.getText();
			country = countryField.getText();
			address = addressField.getText();
			age = ageField.getText();
			System.out.println(name);
			new User(name, password, country, address, age,0);
			try{
				
				FileWriter fw = new FileWriter(file,true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(name +','+ password +','+ country +','+ address +','+ age);
				pw.close();
			}
			catch (IOException t){
				System.out.println("ERROR!");
			}
			newUser.dispatchEvent(new WindowEvent(newUser, WindowEvent.WINDOW_CLOSING));
			
		}

	}

}
