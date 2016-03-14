package poly.processing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poly.IO.ConsolePrinter;
import poly.IO.DegreeFrame;
import poly.IO.ProcessingFrame;
import poly.model.Polynomial;
import poly.operations.Functions;

/**
 * 
 * @author Dia
 *
 *         The public class Controller implementing ActionListener describes the
 *         relationships between the GUI interface and the logical structures of
 *         the App. Its main purpose is to implement the method actionPerformed,
 *         for dealing with the events that occur when pressing buttons.
 */
public class Controller implements ActionListener {

	private DegreeFrame degreeFrame;
	private ProcessingFrame processingFrame;
	private Polynomial p1, p2, result;
	private Functions func;
	private ConsolePrinter cP;

	public Controller(DegreeFrame degreeFrame) {
		this.degreeFrame = degreeFrame;
		degreeFrame.okButton.addActionListener(this);

		cP = new ConsolePrinter();
		func = new Functions();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == degreeFrame.okButton) {
			degreeFrame.frame.setVisible(false);
			degreeFrame.frame.dispose();
			System.out.println("ok dispose");
			p1 = new Polynomial(degreeFrame.degree1);
			System.out.println("ok p1");
			p2 = new Polynomial(degreeFrame.degree2);
			System.out.println("ok p2");
			this.processingFrame = new ProcessingFrame(degreeFrame.degree1, degreeFrame.degree2);
			for (int i = 0; i < ProcessingFrame.NR_OF_OP; i++) {
				processingFrame.arithOp[i].addActionListener(this);
			}
			processingFrame.backButton.addActionListener(this);
			for (int i = 0; i <= degreeFrame.degree1 * 2 + 1; i++) {
				processingFrame.buttonsP1[i].addActionListener(this);
			}
			for (int i = 0; i <= degreeFrame.degree2 * 2 + 1; i++) {
				processingFrame.buttonsP2[i].addActionListener(this);
			}
			processingFrame.updateDivideButton(p1, p2);
		} else if (processingFrame.backButton == source) {
			processingFrame.frame.setVisible(false);
			processingFrame.frame.dispose();
			degreeFrame = new DegreeFrame();
			degreeFrame.okButton.addActionListener(this);
		} else {
			int found = findSourceArithOp(source);
			if (found != -1) {
				if (found == ProcessingFrame.PLUS) {
					result = func.sum(p1, p2);
				} else if (found == ProcessingFrame.MINUS) {
					result = func.difference(p1, p2);
				} else if (found == ProcessingFrame.MUL) {
					result = func.multiply(p1, p2);
				} else if (found == ProcessingFrame.DIV) {
					result = func.divide(p1, p2);
				} else if (found == ProcessingFrame.DIFF) {
					result = func.differentiate(p1);
				} else {
					result = func.integrate(p1);
				}
				// result.normalizePoly();
				processingFrame.updateOutput(result);
			} else {
				found = findSourceP1(source);
				if (found != -1) {
					if (found % 2 == 0) {
						if (found == p1.degree * 2 && p1.degree != 0 && p1.coeff[p1.degree].getCoeff() == -1) {
							p1.coeff[p1.degree].updateCoeff(0); // ++
						}
						p1.coeff[found / 2].updateCoeff(p1.coeff[found / 2].getCoeff() + 1);
					} else {
						if (found == p1.degree * 2 + 1 && p1.degree != 0 && p1.coeff[p1.degree].getCoeff() == 1) {
							p1.coeff[p1.degree].updateCoeff(0);
						}
						p1.coeff[found / 2].updateCoeff(p1.coeff[found / 2].getCoeff() - 1);
					}
				} else {
					found = findSourceP2(source);
					if (found % 2 == 0) {
						if (found == p2.degree * 2 && p2.degree != 0 && p2.coeff[p2.degree].getCoeff() == -1) {
							p2.coeff[p2.degree].updateCoeff(0); // ++
						}
						p2.coeff[found / 2].updateCoeff(p2.coeff[found / 2].getCoeff() + 1);
					} else {
						if (found == p2.degree * 2 + 1 && p2.degree != 0 && p2.coeff[p2.degree].getCoeff() == 1) {
							p2.coeff[p2.degree].updateCoeff(0);
						}
						p2.coeff[found / 2].updateCoeff(p2.coeff[found / 2].getCoeff() - 1);
					}
				}
				processingFrame.updateInputs(p1, p2);
				processingFrame.updateDivideButton(p1, p2);
				cP.printPolynomial(p1);
				cP.printPolynomial(p2);
			}
		}
	}

	public int findSourceP1(Object source) {
		for (int i = 0; i <= degreeFrame.degree1 * 2 + 1; i++) {
			if (processingFrame.buttonsP1[i] == source)
				return i;
		}
		return -1;
	}

	public int findSourceP2(Object source) {
		for (int i = 0; i <= degreeFrame.degree2 * 2 + 1; i++) {
			if (processingFrame.buttonsP2[i] == source)
				return i;
		}
		return -1;
	}

	public int findSourceArithOp(Object source) {
		for (int i = 0; i < ProcessingFrame.NR_OF_OP; i++) {
			if (processingFrame.arithOp[i] == source)
				return i;
		}
		return -1;
	}
}
