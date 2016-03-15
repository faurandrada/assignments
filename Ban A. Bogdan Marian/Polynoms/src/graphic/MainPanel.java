package graphic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	private JTextField textPol1 = new JTextField(30);
	private JTextField textPol2 = new JTextField(30);
	private JTextArea areaOutput = new JTextArea();
	private JTextArea areaInput = new JTextArea();
	private JButton add = new JButton("Add");
	private JButton subtract = new JButton("Subtract");
	private JButton multiplication = new JButton("Multiply");
	private JButton division = new JButton("Divide");
	private JButton integrate = new JButton("Integrate");
	private JButton derivate = new JButton("Derivate");
	private JLabel polLabel = new JLabel("Enter the polynomials:");
	private JLabel operationLabel = new JLabel("Choose the operation:");
	private JLabel infoLabel = new JLabel("Write the coefficients of the polynomials with space between them");
	private JLabel outputLabel = new JLabel("Your output:");
	private JLabel inputLabel = new JLabel("Your input:");
	private static final int MIDDLE_COORDONATE = 80;

	public MainPanel() {

		setLayout(null);
		setBound(this.polLabel, new Rectangle(MIDDLE_COORDONATE, 10, 250, 20));
		add(this.polLabel);

		setBound(textPol1, new Rectangle(MIDDLE_COORDONATE, 30, 250, 20));
		add(this.textPol1);

		setBound(textPol2, new Rectangle(MIDDLE_COORDONATE, 60, 250, 20));
		add(this.textPol2);

		setBound(this.inputLabel, new Rectangle(MIDDLE_COORDONATE, 85, 90, 30));
		add(this.inputLabel);

		setBound(this.areaInput, new Rectangle(MIDDLE_COORDONATE, 110, 250, 70));
		areaInput.setEditable(false);
		areaInput.setBackground(Color.GRAY);
		add(this.areaInput);

		setBound(this.operationLabel, new Rectangle(140, 200, 250, 20));
		add(this.operationLabel);

		setBound(this.add, new Rectangle(10, 220, 90, 30));
		add(this.add);

		setBound(this.subtract, new Rectangle(110, 220, 90, 30));
		add(this.subtract);

		setBound(this.multiplication, new Rectangle(210, 220, 90, 30));
		add(this.multiplication);

		setBound(this.division, new Rectangle(310, 220, 90, 30));
		add(this.division);

		setBound(this.derivate, new Rectangle(110, 260, 90, 30));
		add(this.derivate);
		derivate.setToolTipText("This works only for the first input");

		setBound(this.integrate, new Rectangle(210, 260, 90, 30));
		add(this.integrate);
		integrate.setToolTipText("This works only for the first input");

		setBound(this.outputLabel, new Rectangle(MIDDLE_COORDONATE, 285, 90, 30));
		add(this.outputLabel);

		setBound(this.areaOutput, new Rectangle(MIDDLE_COORDONATE, 310, 250, 70));
		areaOutput.setEditable(false);
		areaOutput.setBackground(Color.GRAY);
		add(this.areaOutput);

		setBound(this.infoLabel, new Rectangle(25, 385, 400, 20));
		add(this.infoLabel);
		infoLabel.setForeground(Color.RED);
	}

	public void setBound(Component comp, Rectangle bounds) {
		comp.setBounds(bounds);
	}

	public JTextField getTextPol1() {
		return textPol1;
	}

	public void setTextPol1(JTextField textPol1) {
		this.textPol1 = textPol1;
	}

	public JTextField getTextPol2() {
		return textPol2;
	}

	public void setTextPol2(JTextField textPol2) {
		this.textPol2 = textPol2;
	}

	public JTextArea getAreaInput() {
		return areaInput;
	}

	public void setAreaInput(JTextArea areaInput) {
		this.areaInput = areaInput;
	}

	public JTextArea getAreaOutput() {
		return areaOutput;
	}

	public void setAreaOutput(JTextArea areaOutput) {
		this.areaOutput = areaOutput;
	}

	public JLabel getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(JLabel infoLabel) {
		this.infoLabel = infoLabel;
	}

	public JButton getAdd() {
		return add;
	}

	public JButton getSubstract() {
		return subtract;
	}

	public JButton getMultiplication() {
		return multiplication;
	}

	public JButton getDivision() {
		return division;
	}

	public JButton getIntegrate() {
		return integrate;
	}

	public JButton getDerivate() {
		return derivate;
	}

}
