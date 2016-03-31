package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Gui {
	private JFrame frame=new JFrame();
	private JPanel labelPanel=new JPanel(new GridLayout(2, 1, 20, 20));
	private JPanel textPanel=new JPanel(new GridLayout(2, 1, 20, 20));
	private JLabel userLabel=new JLabel("Username:");
	private JLabel passLabel=new JLabel("Password:");
	private JTextField userText=new JTextField();
	private JPasswordField passText=new JPasswordField();
	private JButton loginBtn=new JButton("Login");
	private static String username=new String();
	
	public Gui(){
		frame.setLayout(new BorderLayout());
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username=userText.getText();
				if(passText.getPassword().length!=0)
				{
					if(Objects.equals(username, "Admin")){
						frame.setVisible(false);
						new AdminGui(username);
					}
					else
					{
						frame.setVisible(false);
						new RegularGui(username);
					}
				}
			}
		});
		labelPanel.add(userLabel);
		textPanel.add(userText);
		labelPanel.add(passLabel);
		textPanel.add(passText);
		frame.add(labelPanel, BorderLayout.WEST);
		frame.add(textPanel, BorderLayout.CENTER);
		frame.add(loginBtn, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 150);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static String getUsername(){
		return username;
	}
}
