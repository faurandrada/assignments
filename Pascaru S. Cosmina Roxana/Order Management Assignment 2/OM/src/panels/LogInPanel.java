package panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPanel extends JPanel implements ActionListener {

	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton login = new JButton("Log In");

	private String name;
	private String passwd;

	public LogInPanel(JButton login) {
		this.login = login;
		
		setLayout(new GridLayout(3, 1));
		
		username.setToolTipText("Add Username");
		username.setActionCommand("username");
		username.addActionListener(this);
		password.setToolTipText("Add Password");
		password.addActionListener(this);
		login.setActionCommand("login");
		login.addActionListener(this);
		
		add(username);
		add(password);
		add(login);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("login")) {
			name = username.getText();
			passwd = password.getText();
			setVisible(false);
		}

	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return passwd;
	}

}
