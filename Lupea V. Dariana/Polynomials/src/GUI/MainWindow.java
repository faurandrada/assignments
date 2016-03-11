package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Model.Operations;
import Model.Polynomial;

/**
 * Class that creates the Graphical User Interface
 */
public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JFrame f = new JFrame();

	private JPanel panel1, panel2, panel3, panel4;
	private JButton addButt, subButt, divButt, mulButt, operations;
	private JLabel pOfX, qOfX, rOfx;// images
	private JTextField pol1, pol2, result;// used for taking user's input
	private JButton derivativeButt, integralButt, evaluateButt, defIntegralButt, mulScalar;
	private JButton clean, instructions;
	private ArrayList<JButton> buttons;
	private ArrayList<JButton> secondButtons;
	
	public MainWindow() {

		f.setTitle("Polynomials");

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();

		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new GridBagLayout());
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER));

		buttons = new ArrayList<JButton>();
		operations = new JButton("Operations:");
		operations.setPreferredSize(new Dimension(100, 50));
		panel1.add(operations);

		addButt = new JButton("+");
		subButt = new JButton("-");
		divButt = new JButton("/");
		mulButt = new JButton("*");
		buttons.add(addButt);
		buttons.add(subButt);
		buttons.add(divButt);
		buttons.add(mulButt);

		for (int i = 0; i < 4; i++) {
			buttons.get(i).addActionListener(this);
			buttons.get(i).setPreferredSize(new Dimension(50, 50));
			buttons.get(i).setBackground(Color.cyan);
			panel1.add(buttons.get(i));
		}

		secondButtons = new ArrayList<JButton>();
		derivativeButt = new JButton("Find derivative");
		integralButt = new JButton("Find indefinite integral");
		defIntegralButt = new JButton("Find definite integral");
		evaluateButt = new JButton("Evaluate");
		mulScalar = new JButton("Multiply with scalar");

		secondButtons.add(derivativeButt);
		secondButtons.add(integralButt);
		secondButtons.add(defIntegralButt);
		secondButtons.add(evaluateButt);
		secondButtons.add(mulScalar);

		for (int i = 0; i < 5; i++) {
			secondButtons.get(i).addActionListener(this);
			secondButtons.get(i).setBackground(Color.cyan);
			panel1.add(secondButtons.get(i));
		}

		pOfX = new JLabel();
		qOfX = new JLabel();
		rOfx = new JLabel();
		pOfX.setIcon(new ImageIcon("Images/firstPol.jpg"));
		qOfX.setIcon(new ImageIcon("Images/secondPol.jpg"));
		rOfx.setIcon(new ImageIcon("Images/thirdPol.jpg"));

		pol1 = new JTextField("                                       ");
		pol2 = new JTextField("                                       ");
		result = new JTextField("                                       ");

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		panel2.add(pOfX, c);
		c.gridx = 1;
		c.gridy = 0;
		panel2.add(pol1, c);
		c.gridx = 0;
		c.gridy = 1;
		panel2.add(qOfX, c);
		c.gridx = 1;
		c.gridy = 1;
		panel2.add(pol2, c);
		c.gridx = 0;
		c.gridy = 3;
		panel2.add(rOfx, c);
		c.gridx = 1;
		c.gridy = 3;
		panel2.add(result, c);
		validate();

		instructions = new JButton("Instructions");
		instructions.addActionListener(this);
		panel3.add(instructions);
		clean = new JButton("Press here to clean!");
		clean.addActionListener(this);
		panel4.add(clean);

		f.getContentPane().setLayout(new BorderLayout());
		f.add(panel1, BorderLayout.NORTH);
		f.add(panel2, BorderLayout.CENTER);
		f.add(panel3, BorderLayout.WEST);
		f.add(panel4, BorderLayout.SOUTH);

		f.setVisible(true);
		f.setSize(1020, 400);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent e) {

		Operations op = new Operations();// create new Operations object
		int[] firstPolCoeff, secondPolCoeff;
		firstPolCoeff = getPolynomial(pol1);// get user input for first
											// polynomial
		secondPolCoeff = getPolynomial(pol2);// get user input for second
												// polynomial

		if (e.getSource() == clean) {
			cleanArea();
		}
		if (e.getSource() == instructions) {
			String categories[] = { "1. Operation using 2 polynomials:",
					"    - enter the coefficients of the first polynomial in the right-hand side blank space of P(x)",
					"    - enter the coefficients of the second polynomial in the right-hand side blank space of Q(x)",
					"    - if a term of order >= 1 is missing, please enter a 0 instead",
					"    - the result will be displayed in the blank space near R(x) as a real polynomial",
					"                                                                                          ",
					"2. Operation on 1 polynomial",
					"    - please use the P(x) blank area to enter the coefficients of your polynomial",
					"    - the Q(x) area remains empty", "    - the result will be displayed on the R(x) area",
					"                                   ",
					"                                                                   Good luck!"

			};
			JList<String> list = new JList<String>(categories);
			JScrollPane scrollpane = new JScrollPane(list);
			scrollpane.setPreferredSize(new Dimension(450, 300));
			JOptionPane.showMessageDialog(null, scrollpane, "Instructions", JOptionPane.PLAIN_MESSAGE);
		}

		if (e.getSource() == addButt) {
			Polynomial addResult = op.addPolynomials(new Polynomial(firstPolCoeff), new Polynomial(secondPolCoeff));
			int[] addResultCorrect = addResult.reverseCoefficients(addResult.getCoeff());
			displayPolynomial(addResultCorrect);

		} else if (e.getSource() == subButt) {
			Polynomial subResult = op.subtractPolynomials(new Polynomial(firstPolCoeff),
					new Polynomial(secondPolCoeff));
			int[] subResultCorrect = subResult.reverseCoefficients(subResult.getCoeff());
			displayPolynomial(subResultCorrect);
		} else if (e.getSource() == mulButt) {
			Polynomial mulResult = op.multiplyPolynomials(new Polynomial(firstPolCoeff),
					new Polynomial(secondPolCoeff));
			int[] mulResultCorrect = mulResult.reverseCoefficients(mulResult.getCoeff());
			displayPolynomial(mulResultCorrect);
		} else if (e.getSource() == derivativeButt) {

			Polynomial result = op.findDerivative(new Polynomial(firstPolCoeff));
			int[] derivativeResult = result.reverseCoefficients(result.getCoeff());
			displayPolynomial(derivativeResult);

		} else if (e.getSource() == evaluateButt) {
			String number = JOptionPane.showInputDialog(this, "Please enter value for evaluation:");
			int n = Integer.parseInt(number);
			int result = op.evaluatePolynomial(new Polynomial(firstPolCoeff), n);
			displayIntResult(result);

		} else if (e.getSource() == integralButt) {
			Polynomial result = op.integratePolynomial(new Polynomial(firstPolCoeff));
			int[] integrResult = result.reverseCoefficients(result.getCoeff());
			displayPolynomial(integrResult);

		} else if (e.getSource() == defIntegralButt) {
			String number = JOptionPane.showInputDialog(this, "Enter value for a:");
			int a = Integer.parseInt(number);
			String number2 = JOptionPane.showInputDialog(this, "Enter value for b:");
			int b = Integer.parseInt(number2);
			Polynomial thisPol = new Polynomial(firstPolCoeff);
			int defIntResult = op.findDefiniteIntegral(a, b, thisPol);
			displayIntResult(defIntResult);
		}
		if (e.getSource() == mulScalar) {
			String scalar = JOptionPane.showInputDialog(this, "Enter value for a:");
			int a = Integer.parseInt(scalar);
			Polynomial result = op.multiplyWithScalar(new Polynomial(firstPolCoeff), a);
			int[] integrResult = result.reverseCoefficients(result.getCoeff());
			displayPolynomial(integrResult);
		}
	}

	public void displayPolynomial(int[] res) {
		Polynomial correctPolynomial = new Polynomial(res);
		result.setText(correctPolynomial.toString());
	}

	public void displayIntResult(int x) {
		String s = Integer.toString(x);
		result.setText(s);
	}

	public void cleanArea() {
		pol1.setText("");
		pol2.setText("");
		result.setText("");
	}

	/**
	 * Get user's input as a string and converts it into an array of integers
	 * 
	 * @param polyn  text entered by user
	 * @return array of integer (the coefficients of the polynomial)
	 */
	public int[] getPolynomial(JTextField polyn) {
		int[] pol = null;
		
		try {
			String text = (polyn.getText()).trim();
			String[] splitActionCommand = text.split(" ");
			pol = new int[splitActionCommand.length];

			for (int i = 0; i < splitActionCommand.length; i++) {
				pol[i] = Integer.parseInt(splitActionCommand[i]);
			}
		} catch (NumberFormatException exception) {
			
		}
		return pol;
	}
}
