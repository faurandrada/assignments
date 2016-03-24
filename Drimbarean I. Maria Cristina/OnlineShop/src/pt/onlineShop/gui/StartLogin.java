package pt.onlineShop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StartLogin extends JFrame {
	public StartLogin() {
		this.setTitle("Programming techniques-Assignment2-Drimbarean Maria");
		this.setPreferredSize(new Dimension(600, 150));
		this.setMaximumSize(new Dimension(200, 150));
		this.setMinimumSize(new Dimension(600, 300));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());
		
		JPanel panel=new JPanel();
		panel.setBackground(Color.pink);
		this.add(panel,BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		
		JButton b1=new JButton();
		b1.setBackground(Color.pink);
		b1.setEnabled(false);
		this.add(b1, BorderLayout.NORTH);
		
		JButton b2=new JButton();
		b2.setBackground(Color.pink);
		b2.setEnabled(false);
		this.add(b2, BorderLayout.SOUTH);
		
		JButton b3=new JButton();
		b3.setBackground(Color.pink);
		b3.setEnabled(false);
		this.add(b3, BorderLayout.EAST);
		
		JButton b4=new JButton();
		b4.setBackground(Color.pink);
		b4.setEnabled(false);
		this.add(b4, BorderLayout.WEST);
		
		
		JButton admin=new JButton("Login as Admin!");
		admin.setBackground(Color.yellow);
		panel.add(admin, BorderLayout.EAST);
		JButton costumer=new JButton("Login as Costumer!");
		costumer.setBackground(Color.yellow);
		panel.add(costumer, BorderLayout.WEST);
		panel.setVisible(true);
		
		this.setVisible(true);
		 admin.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              new AdminLogin();
	            }
	        });
		 costumer.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	              new CostumerLogin();
	            }
	        });
	}
}
