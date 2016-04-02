package Frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Design.Administrator;
import Design.Customer;

public class LogInFrame implements ActionListener{
	private JFrame login;
	
	private JTextField username= new JTextField();
	private JPasswordField password=new JPasswordField();
	
	private JLabel user= new JLabel("Username:");
	private JLabel pass= new JLabel("Password:");
	
	private JButton ok=new JButton("Submit");
	
	private static JButton take= new JButton();
	
	private Customer c;
	private Administrator a;
	
	public LogInFrame(){
		login=new JFrame("LogIn");
		user.setBounds(5, 10, 120, 20);
		pass.setBounds(5, 33, 120, 20);
		username.setBounds(70, 10, 150, 20);
		password.setBounds(70, 33, 150, 20);
		ok.setBounds(230,7,80,50);
		login.add(take);
		
		login.setLayout(null);
		login.setBounds(0, 0, 525, 100);
		login.add(user);
		login.add(pass);
		login.add(username);
		login.add(password);
		login.add(ok);
		login.setSize(329, 95);
		ok.addActionListener(this);
		login.setLayout(null);
		login.setVisible(true);
		login.setLocationRelativeTo(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c=new Customer("user", "user");
		a=new Administrator("admin", "admin");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==ok)
		{
			if((username.getText().equals (a.getUser()) && (password.getText().equals(a.getPass())))){
				login.setVisible(false);
				take.setSelected(false);
				new AdminPanel();
				
			}
			if((username.getText().equals(c.getUsername())) && (password.getText().equals(c.getPass()))){
				login.setVisible(false);
				take.setSelected(true);
				new UserPanel();
				
			}
		}
	}
	
	public static JButton getTake(){
		return take;
	}
}
