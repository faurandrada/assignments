package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import io.IOStream;
import models.Customer;
import sample.CustomerSample;

public class LoginView extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1822481452647856361L;
	IOStream io;
	CustomerSample sample;
	private String admin;
	private String adminPass;
	private JFrame frame;
	private JButton loginButton;
	private JButton registerButton;
	private JTextField userText;
	private JPasswordField passwordText;
	private JRadioButton adminButton, userButton;

	public LoginView(){
        io=new IOStream();
        sample=new CustomerSample();
        sample=io.deserializeCustomerSample();
     //   sample.print();
		admin = "admin";
		adminPass = "admin";
		frame = new JFrame("Log In");
		frame.setSize(500, 350);
		frame.setLayout(new BorderLayout());

		
		//NORTH PANEL
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 255, 204));
		ImageIcon myPicture = new ImageIcon(getClass().getResource("mara.jpg"));
		JLabel picLabel = new JLabel(myPicture);
		northPanel.add(picLabel);
		frame.add(northPanel, BorderLayout.NORTH);

		//CENTRE PANEL
		JPanel centrePanel = new JPanel();
		centrePanel.setBackground(new Color(255, 255, 204));
		frame.add(centrePanel, BorderLayout.CENTER);
		JPanel gridPanel = new JPanel(new GridLayout(2, 1));
		gridPanel.setBackground(new Color(255, 255, 204));
		JPanel panel1 = new JPanel(new GridLayout(2, 1));
		panel1.setBackground(new Color(255, 255, 204));
		JPanel panel2 = new JPanel(new GridLayout(2, 2));
		panel2.setBackground(new Color(255, 255, 204));
		gridPanel.add(panel1);
		gridPanel.add(panel2);
		centrePanel.add(gridPanel);

		//ADMIN BUTTON
		adminButton = new JRadioButton("Admin");
		adminButton.setBackground(new Color(255, 255, 204));
	//	adminButton.setSelected(true);
		adminButton.addActionListener(this);

		//USER BUTTON
		userButton = new JRadioButton("User");
		userButton.setBackground(new Color(255, 255, 204));
		userButton.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(adminButton);
		group.add(userButton);
		panel1.add(adminButton);
		panel1.add(userButton);

		JLabel userLabel = new JLabel("Name      ");
		panel2.add(userLabel);

		userText = new JTextField();
		userText.setBackground(new Color(255, 255, 230));
		panel2.add(userText);

		JLabel passwordLabel = new JLabel("Password      ");
		panel2.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBackground(new Color(255, 255, 230));
		panel2.add(passwordText);

		//SOUTH PANEL
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(255, 255, 204));
		frame.add(southPanel, BorderLayout.SOUTH);
		loginButton = new JButton("login");
		loginButton.setBackground(new Color(255, 204, 204));
		loginButton.addActionListener(this);
		southPanel.add(loginButton);

		registerButton = new JButton("register");
		registerButton.setBackground(new Color(255, 204, 204));
		registerButton.addActionListener(this);
		southPanel.add(registerButton);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public boolean checkAdmin(String username, String pass) {
		if (username.compareTo(admin) == 0)
			if (pass.compareTo(adminPass) == 0)
				return true;
		return false;
	}

	
	public void register(String username, String pass) {

	}

	public Customer login(String username, String pass) {
		System.out.println("shakira");
		for (Customer customer:sample.getCustomers()){
			System.out.println(customer.getName());
			if ((username.compareTo(customer.getName())==0)&&(pass.compareTo(customer.getPassword()))==0){
				System.out.println(customer.getUsername());
				return customer;
			}
		}
	     return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (adminButton.isSelected()) {
			if (e.getSource() == loginButton) {
				if (checkAdmin(userText.getText(), new String(passwordText.getPassword()))) {
					new AdminView();
					this.dispose();
					repaint();
				}

			}
		} else if (userButton.isSelected()) {
			if (e.getSource() == loginButton) {
				Customer customer=login(userText.getText(), new String(passwordText.getPassword()));
				if (customer!=null) {
					new CustomerView(customer);
					this.setVisible(false);
					repaint();

				} else {
					JOptionPane.showMessageDialog(null, "Please register first!");
					repaint();
				}
			} else if (e.getSource() == registerButton) {
				Customer customer=new Customer(userText.getText(), new String(passwordText.getPassword()));
				sample.addCustomer(customer);
			//	sample.print();
				io.SerializeCustomerSample(sample);
				JOptionPane.showMessageDialog(null, "Thank you for registering!");
				repaint();
			}

		}
	}

}

