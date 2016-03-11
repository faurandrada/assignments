package pt.polynomials.views;
/**
 * a panel that provieds some specification for the user
 * @author Chiti
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class StartWindow extends JFrame {
	public StartWindow() {
		this.setTitle("Programming techniques-Assignment1-Drimbarean Maria");
		this.setPreferredSize(new Dimension(400, 300));
		this.setMaximumSize(new Dimension(200, 200));
		this.setMinimumSize(new Dimension(400, 400));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(Color.pink);
		this.setLocationRelativeTo(null);

		this.setLayout(new BorderLayout());

		JTextField text1 = new JTextField("Insert polynoms like an array of integers separated by commas");
		text1.setBackground(Color.yellow);
		text1.setEditable(false);
		this.add(text1, BorderLayout.PAGE_START);
		JTextField text2 = new JTextField("Exemple: 2,1,0,-12 will be 2+(1*x^1)+(-12*x^3) ");
		text2.setBackground(Color.yellow);
		text2.setEditable(false);
		this.add(text2, BorderLayout.CENTER);

		JButton start = new JButton("Start Application!");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setInvisible();
				new Board();
			}
		});
		this.add(start, BorderLayout.PAGE_END);
		this.setVisible(true);
	}

	public void setInvisible() {
		this.setVisible(false);
	}
}
