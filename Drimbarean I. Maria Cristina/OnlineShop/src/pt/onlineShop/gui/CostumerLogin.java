package pt.onlineShop.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pt.onlineShop.processing.OPDept;

public class CostumerLogin extends JFrame{
	private static final long serialVersionUID = 1337173503903428914L;
	private OPDept department=new OPDept();
	private JTextField b3=new JTextField();
	private JPasswordField b4=new JPasswordField();
	public CostumerLogin(){
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);

		this.setLayout(new GridLayout(2,2));
		
		JButton b1=new JButton("Name");
		b1.setBackground(Color.pink);
		b1.setEnabled(false);
		this.add(b1);
		
		JButton b2=new JButton("Password");
		b2.setBackground(Color.pink);
		b2.setEnabled(false);
		this.add(b2);
		
		b3.setBackground(Color.pink);
		this.add(b3);
		
		b4.setBackground(Color.pink);
		this.add(b4);
		
		Handler handler=new Handler();
		b3.addActionListener(handler);
		b4.addActionListener(handler);
		
		this.setVisible(true);
	}

	  /**
	   */
	  private class Handler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent event) {
				String textName=b3.getText();
				@SuppressWarnings("deprecation")
				String textPassword=b4.getText();
				
				if (department.getCostumer1().getUsername().equals(textName) && department.getCostumer1().getPasswoard().equals(textPassword))
					new CostumerView(department);
				else if (department.getCostumer2().getUsername().equals(textName) && department.getCostumer2().getPasswoard().equals(textPassword))
					new CostumerView2(department);
				else
					JOptionPane.showMessageDialog(null, "Wrong username or password!", "LoginIssue", JOptionPane.INFORMATION_MESSAGE);
			}
		}
}
