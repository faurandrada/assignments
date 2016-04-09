package Control;

import javax.swing.JOptionPane;
/*
 * Dialogs for interacting with the user for specifying the input parameters like degree and coefficients
 */
public class UserDialog {

	private String userInput = null;
	private int degree;
	private int[] coefficients;

	public int askForDegree() {
		userInput = JOptionPane.showInputDialog("Input Polynomial Degree");
		try {
			degree = Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please insert a valid positive Integer", "Invalid Degree",
					JOptionPane.ERROR_MESSAGE);
			askForDegree();
		}
		return this.degree;
	}

	public int[] askForCoefficients() {
		coefficients = new int[degree + 1];
		String coefficient = "";
		for (int i = 0; i <= degree; i++) {
			coefficient = JOptionPane.showInputDialog("Input coefficient for X^" + i + " :");
			try {
				coefficients[i] = Integer.parseInt(coefficient);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please insert a valid Integer coefficient", "Invalid coefficient",
						JOptionPane.ERROR_MESSAGE);
				askForCoefficients();
			}
		}
		return this.coefficients;
	}
}
