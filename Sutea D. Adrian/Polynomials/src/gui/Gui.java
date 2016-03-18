package gui;

import poly.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gui {
	private JFrame frame = new JFrame();
	private JButton add = new JButton("+");
	private JButton sub = new JButton("-");
	private JButton mul = new JButton("*");
	private JButton div = new JButton("/");
	private JButton drv = new JButton("Derivate P1");
	private JButton intg = new JButton("Integrate P1");
	private JTextField pol1 = new JTextField();
	private JTextField pol2 = new JTextField();
	private JTextField re = new JTextField();
	private JLabel p1 = new JLabel("P1:");
	private JLabel p2 = new JLabel("P2:");
	private JLabel res = new JLabel("Result:");
	private JPanel IOPanel = new JPanel(new BorderLayout());
	private JPanel OpPanel = new JPanel(new GridLayout(2, 3, 50, 50));
	private JPanel labelPanel = new JPanel(new GridLayout(3, 1, 50, 50));
	private JPanel textPanel = new JPanel(new GridLayout(3, 1, 50, 50));
	private static String poly1 = new String();
	private static String poly2 = new String();
	private String result = new String();
	private Operations op = new Operations();
	private Polynomial p;
	private Polynomial polynom;
	private Monomial mon1, mon2;
	private String helper = new String();
	private String polyString;
	private int x, i = 0, size;

	public Gui() {
		re.setEditable(false);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				polynom = op.addition(getP1(), getP2());
				result = Gui.this.toString(polynom);
				re.setText(result);
			}
		});
		drv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				polynom = op.derivate(getP1());
				result = Gui.this.toString(polynom);
				re.setText(result);
			}
		});
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				polynom = op.subtraction(getP1(), getP2());
				result = Gui.this.toString(polynom);
				re.setText(result);
			}
		});
		intg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				polynom = op.integrate(getP1());
				result = Gui.this.toString(polynom);
				re.setText(result);
			}
		});
		div.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				polynom = op.divide(getP1(), getP2());
				result = Gui.this.toString(polynom);
				re.setText(result);
			}
		});
		mul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				polynom = op.multiply(getP1(), getP2());
				result = Gui.this.toString(polynom);
				re.setText(result);
			}
		});
		labelPanel.add(p1);
		labelPanel.add(p2);
		labelPanel.add(res);
		textPanel.add(pol1);
		textPanel.add(pol2);
		textPanel.add(re);
		IOPanel.add(labelPanel, BorderLayout.WEST);
		IOPanel.add(textPanel, BorderLayout.CENTER);
		OpPanel.add(add);
		OpPanel.add(mul);
		OpPanel.add(intg);
		OpPanel.add(sub);
		OpPanel.add(div);
		OpPanel.add(drv);
		frame.add(IOPanel);
		frame.add(OpPanel);
		frame.setLayout(new GridLayout(2, 1, 20, 20));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(1024, 480);
	}

	public Polynomial toPolynomial(String s) {
		Pattern ints = Pattern.compile("[\\+-]?\\d+");
		Matcher mat = ints.matcher(s);
		p = new Polynomial();
		mon1 = null;
		i = 0;
		while (mat.find() == true) {
			helper = mat.group();
			x = Integer.parseInt(helper);
			if (i % 2 == 0) {
				mon1 = new Monomial();
				mon1.setIntCoeff(x);
			} else {
				mon1.setPower(x);
				p.addToPoly(mon1);
			}
			if (i == 1) {
				p.setDegree(x);
			}
			i++;
		}
		for (i = 0; i < p.getSize(); i++) {
			mon1 = p.getElement(i);
			System.out.print(mon1.getIntCoeff() + " " + mon1.getPower() + " ");
		}
		System.out.println();
		return p;
	}

	public String toString(Polynomial pol) {
		size = pol.getSize();
		polyString = new String();
		mon2 = null;
		for (int i = 0; i < size; i++) {
			mon2 = pol.getElement(i);
			if (mon2.getIntCoeff() > 0)
				polyString = polyString + "+" + mon2.getIntCoeff() + "x^" + mon2.getPower();
			else {
				if (mon2.getIntCoeff() != 0)
					polyString = polyString + mon2.getIntCoeff() + "x^" + mon2.getPower();
			}
			if (mon2.getDoubleCoeff() > 0)
				polyString = polyString + "+" + mon2.getDoubleCoeff() + "x^" + mon2.getPower();
			else {
				if (mon2.getDoubleCoeff() != 0)
					polyString = polyString + mon2.getDoubleCoeff() + "x^" + mon2.getPower();
			}
		}
		System.out.println(polyString);
		return polyString;
	}

	public Polynomial getP1() {
		poly1 = pol1.getText();
		return toPolynomial(poly1);
	}

	public Polynomial getP2() {
		poly2 = pol2.getText();
		return toPolynomial(poly2);
	}
}
