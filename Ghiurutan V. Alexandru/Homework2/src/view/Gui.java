package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener{
private static final long serialVersionUID = 211431534849779406L;
private static String ADMIN_USERNAME="Admin";
private static String ADMIN_PASSWORD="Admin";
private static String CUSTOMER_USERNAME="Customer1";
private static String CUSTOMER_PASSWORD="Customer1";
private JTextArea username;
private JPasswordField password;
private JPanel mainView,userSection,passSection,loginSection;
private JTextField user,pass;
private JButton login;

public Gui()
{
	this.setTitle("Program");
	this.setSize(300,300);
	this.setLocationRelativeTo(null);
	initializeView();
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
}
private void initializeView()
{
mainView=new JPanel();
mainView.setLayout(new BoxLayout(mainView,BoxLayout.Y_AXIS));
user=new JTextField("Username:");
user.setEditable(false);
pass=new JTextField("Password:");
pass.setEditable(false);
username=new JTextArea(1,10);
password=new JPasswordField(10);
userSection=new JPanel();
userSection.setLayout(new FlowLayout());
passSection=new JPanel();
passSection.setLayout(new FlowLayout());
loginSection=new JPanel();
login=new JButton("Log in");
login.addActionListener(this);
userSection.add(user);
userSection.add(username);
passSection.add(pass);
passSection.add(password);
loginSection.add(login);
mainView.add(userSection);
mainView.add(passSection);
mainView.add(loginSection);
this.add(mainView);
}

@Override
public void actionPerformed(ActionEvent event)
{
	if(event.getSource()==login)
	{
		String loginUsername=username.getText();
		String loginPassword=String.copyValueOf(password.getPassword());
		if(loginUsername.equals(ADMIN_USERNAME)&&loginPassword.equals(ADMIN_PASSWORD))
		{
			this.dispose();
			new UserView();
		}else if(loginUsername.equals(CUSTOMER_USERNAME)&&loginPassword.equals(CUSTOMER_PASSWORD))
		{
			this.dispose();
			new CustomerView();
		}else{
			JOptionPane.showMessageDialog(this,"Not correct username or password.Please write again more carefully.","Error" ,JOptionPane.ERROR_MESSAGE);
		}
	}
}
}
