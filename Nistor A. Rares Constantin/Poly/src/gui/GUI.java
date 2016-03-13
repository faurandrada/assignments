package gui;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import program.Operations;
import program.Polynomial;

public class GUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel jp = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();

	JLabel l1 = new JLabel("P1");
	JLabel l2 = new JLabel("P2");
	JLabel l3 = new JLabel("R");
	JLabel l4 = new JLabel("OPERATIONS");

	JTextField t1 = new JTextField(32);
	JTextField t2 = new JTextField(32);
	JTextField t3 = new JTextField(32);

	JButton bAdd = new JButton("+");
	JButton bSub = new JButton("-");
	JButton bMul = new JButton("*");
	JButton bDiv = new JButton("/");
	JButton diff = new JButton("diff");
	JButton integ = new JButton("integ");

	Polynomial p1 = new Polynomial();
	Polynomial p2 = new Polynomial();
	Operations o = new Operations();

	public GUI() {
		setLayout(new GridLayout(3, 1));
		setTitle("POLY");
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(400, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		jp.add(l1);
		jp.add(t1);
		jp.add(l2);
		jp.add(t2);
		jp.add(l3);
		jp.add(t3);

		jp2.add(l4);
		jp3.add(bAdd);
		jp3.add(bSub);
		jp3.add(bMul);
		jp3.add(bDiv);
		jp3.add(diff);
		jp3.add(integ);

		t1.addActionListener(this);
		t2.addActionListener(this);
		t3.addActionListener(this);
		bAdd.addActionListener(this);
		bSub.addActionListener(this);
		bMul.addActionListener(this);
		diff.addActionListener(this);
		integ.addActionListener(this);
		bDiv.addActionListener(this);

		t3.setEditable(false);
		add(jp);
		add(jp2);
		add(jp3);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == t1)
			p1.createPolynomial(t1.getText());
		else if (e.getSource() == t2)
			p2.createPolynomial(t2.getText());
		else if (e.getSource() == bAdd)
			t3.setText(o.add(p1, p2));
		else if (e.getSource() == bSub)
			t3.setText(o.sub(p1, p2));
		else if (e.getSource() == bMul)
			t3.setText(o.mul(p1, p2));
		else if (e.getSource() == diff)
			t3.setText(o.diff(p1));
		else if (e.getSource() == integ)
			t3.setText(o.integration(p1));
		else if (e.getSource() == bDiv)
			t3.setText(o.divi(p1, p2));
	}

}
