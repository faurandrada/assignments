package graphic;

import java.awt.BorderLayout;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JTextField;

import listeners.OperationListener;
import listeners.ReadKeyListener;
import polynomials.Polynom;

@SuppressWarnings("serial")
public class Application extends JFrame {
	private MainPanel panel = new MainPanel();
	private Polynom p1;
	private Polynom p2;

	public Application() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Polynom");
		frame.setSize(450, 450);
		frame.add(this.panel, BorderLayout.CENTER);
		frame.setResizable(false);
		OperationListener operationListener = new OperationListener(this);
		panel.getAdd().addActionListener(operationListener);
		panel.getSubstract().addActionListener(operationListener);
		panel.getMultiplication().addActionListener(operationListener);
		panel.getDerivate().addActionListener(operationListener);
		panel.getIntegrate().addActionListener(operationListener);
		panel.getDivision().addActionListener(operationListener);
		ReadKeyListener key1 = new ReadKeyListener(this, 1);
		ReadKeyListener key2 = new ReadKeyListener(this, 2);
		panel.getTextPol1().addKeyListener(key1);
		panel.getTextPol2().addKeyListener(key2);
	}

	public Polynom readPolynom(JTextField textField) {
		int coef[] = new int[10];
		int i = 0;
		StringTokenizer c = new StringTokenizer(textField.getText().toLowerCase().trim());
		while (c.hasMoreTokens()) {
			coef[i] = Integer.parseInt(c.nextToken());
			i++;
		}
		Polynom p = new Polynom(i - 1, coef);
		return p;
	}

	public void reset() {
		this.p1 = readPolynom(panel.getTextPol1());
		this.p2 = readPolynom(panel.getTextPol2());
	}

	public Polynom getP1() {
		return p1;
	}

	public Polynom getP2() {
		return p2;
	}

	public void updateOutputText(String string) {
		panel.getAreaOutput().setText(string);
	}

	public MainPanel getPanel() {
		return panel;
	}

	public void setP1(Polynom p1) {
		this.p1 = p1;
	}

	public void setP2(Polynom p2) {
		this.p2 = p2;
	}

}
