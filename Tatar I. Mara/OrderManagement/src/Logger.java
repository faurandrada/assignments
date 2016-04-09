import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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


public class Logger extends JFrame implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
List<Customer> users; 
String admin;
String adminPass;
private Customer currentUser;	
JFrame frame;
JButton loginButton ;
JButton registerButton;
JTextField userText;
JPasswordField passwordText ;
JRadioButton adminButton,userButton;
TestData sample;
public Logger(){
	users=new ArrayList<Customer>();
	admin="admin";
	adminPass="admin";
	JFrame frame = new JFrame("Log In");
	frame.setSize(500,350);
	frame.setLayout(new BorderLayout());

	JPanel northPanel = new JPanel();
	northPanel.setBackground(new Color(255,255,204));
	
		
		ImageIcon myPicture = new ImageIcon(getClass().getResource("mara.jpg"));
		JLabel picLabel = new JLabel(myPicture);
		northPanel.add(picLabel);

		frame.add(northPanel,BorderLayout.NORTH);
		
		JPanel centrePanel = new JPanel();
		centrePanel.setBackground(new Color(255,255,204));
		frame.add(centrePanel,BorderLayout.CENTER);
		JPanel gridPanel=new JPanel(new GridLayout(2,1));
		gridPanel.setBackground(new Color(255,255,204));
		JPanel panel1 = new JPanel(new GridLayout(2,1));
		panel1.setBackground(new Color(255,255,204));
		JPanel panel2 = new JPanel(new GridLayout(2,2));
		panel2.setBackground(new Color(255,255,204));
	
		gridPanel.add(panel1);
		gridPanel.add(panel2);
		centrePanel.add(gridPanel);
		
	
		adminButton =new JRadioButton("Admin");
	//	adminButton.setMnemonic(KeyEvent.VK_A);
		adminButton.setBackground(new Color(255,255,204));
	//	adminButton.setActionCommand("Admin");
		adminButton.setSelected(true);
		adminButton.addActionListener(this);
		
		userButton =new JRadioButton("User");
		userButton.setBackground(new Color(255,255,204));
	//	userButton.setMnemonic(KeyEvent.VK_U);
	//	userButton.setActionCommand("User");
        userButton.addActionListener(this);
        
		
		ButtonGroup group=new ButtonGroup();
		group.add(adminButton);
		group.add(userButton);
		panel1.add(adminButton);
		panel1.add(userButton);
		
		
		JLabel userLabel = new JLabel("User      ");
		panel2.add(userLabel);

		userText = new JTextField();
		userText.setBackground(new Color(255,255,230));
		panel2.add(userText);

		JLabel passwordLabel = new JLabel("Password      ");
		panel2.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBackground(new Color(255,255,230));
		panel2.add(passwordText);

		
		
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(255,255,204));
		frame.add(southPanel,BorderLayout.SOUTH);
		loginButton = new JButton("login");
		loginButton.setBackground(new Color(255,204,204));
		loginButton.addActionListener(this);
		southPanel.add(loginButton);
	
		
		registerButton = new JButton("register");
		registerButton.setBackground(new Color(255,204,204));
		registerButton.addActionListener(this);
		southPanel.add(registerButton);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

public boolean checkAdmin(String username,String pass){
	if (username.compareTo(admin)==0)
		if (pass.compareTo(adminPass)==0)
			return true;
    return false;
}
public void register(String username,String pass){
//	Order newOrder=new Order();
	Customer newCustomer=new Customer(username,pass);
	
	users.add(newCustomer);

}

public boolean login(String username,String pass){
	for (Customer p:users)
		if ((p.getUsername().compareTo(username)==0)&&(p.getPass().compareTo(pass)==0)){
			currentUser=p;
			return true;
		}  
    return false;
}
public Customer getCustomer(){
	return currentUser;
}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	
		if (adminButton.isSelected()){
			if (e.getSource() == loginButton) {
				if (checkAdmin(userText.getText(), new String(passwordText.getPassword())))
					{
					new AdminView();
					this.setVisible(false);
					repaint();
					}
		
			}
		}
		else if  (userButton.isSelected()){
			if (e.getSource() == loginButton) {
				if (login(userText.getText(), new String(passwordText.getPassword()))){
					new CustomerView(currentUser).setVisible(true);
					this.setVisible(false);
					repaint();
				
				}
				else {
					JOptionPane.showMessageDialog(null,"Please register first!");
                    repaint();
				}
			}
			else if (e.getSource()==registerButton){
				register(userText.getText(), new String(passwordText.getPassword()));
				JOptionPane.showMessageDialog(null,"Thank you for registering!");
				repaint();
			}

		}
   }
}
