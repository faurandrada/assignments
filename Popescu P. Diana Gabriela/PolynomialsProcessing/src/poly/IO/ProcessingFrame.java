package poly.IO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import poly.model.IntCoeff;
import poly.model.Polynomial;
import poly.model.RealCoeff;

/**
 * 
 * @author Dia
 *
 *         The public class ProcessingFrame contains the main user interface of the application. It
 *         contains the arithmetical operation' buttons, and also some other
 *         buttons for modifying the coefficients of the input polynomials. The
 *         described methods are updateInputs, updateOutput and
 *         updateDivideButton.
 */
public class ProcessingFrame {

	public static final int PLUS = 0;
	public static final int MINUS = 1;
	public static final int MUL = 2;
	public static final int DIV = 3;
	public static final int INTEGRATE = 4;
	public static final int DIFF = 5;
	public static final int NR_OF_OP = 6;

	private static final int LINE_BORDER_THICKNESS = 3;

	public JFrame frame;
	public JButton[] arithOp;
	public JButton backButton;
	public BasicArrowButton[] buttonsP1;
	public BasicArrowButton[] buttonsP2;
	
	private JLabel jLabelP1;
	private JLabel jLabelP2;
	private JLabel jLabelP3;
	private JLabel jLabelResult;
	private JPanel jPanelLine1;
	private JPanel jPanelLine2;
	private JPanel jPanelLine3;
	private JPanel jPanelLine4; // arithOp + back button line

	private JPanel jPanelPlusMinus;
	private JPanel jPanelMulDiv;
	private JPanel jPanelDiffInt;

	private JPanel[] jPanelButtonsP1;
	private JPanel[] jPanelButtonsP2;

	private JLabel[] jLabelCoeffP1;
	private JLabel[] jLabelCoeffP2;

	private JLabel[] jLabelPowersOfX1;
	private JLabel[] jLabelPowersOfX2;

	public ProcessingFrame(int degree1, int degree2) {
		frame = new JFrame("Polynomials Processing");
		jLabelP1 = new JLabel("P1(x)=");
		jLabelP2 = new JLabel("P2(x)=");
		jLabelP3 = new JLabel("R(x)=");

		jPanelLine1 = new JPanel();
		jPanelLine2 = new JPanel();
		jPanelLine3 = new JPanel();
		jPanelLine4 = new JPanel();
		jPanelLine1.add(jLabelP1);
		jPanelLine2.add(jLabelP2);
		jPanelLine3.add(jLabelP3);

		jPanelPlusMinus = new JPanel();
		jPanelMulDiv = new JPanel();
		jPanelDiffInt = new JPanel();

		jLabelCoeffP1 = new JLabel[degree1 + 1];
		jLabelCoeffP2 = new JLabel[degree2 + 1];
		jLabelResult = new JLabel(" "); // ////////////////

		jLabelPowersOfX1 = new JLabel[degree1 + 1]; // ex: x^2
		jLabelPowersOfX2 = new JLabel[degree2 + 1]; // ex: x^2

		jPanelButtonsP1 = new JPanel[degree1 + 1];
		jPanelButtonsP2 = new JPanel[degree2 + 1];

		arithOp = new JButton[NR_OF_OP];
		arithOp[PLUS] = new JButton(" + ");
		arithOp[PLUS].setBackground(Color.RED);
		arithOp[MINUS] = new JButton(" - ");
		arithOp[MINUS].setBackground(Color.ORANGE);
		arithOp[MUL] = new JButton(" * ");
		arithOp[MUL].setBackground(Color.YELLOW);
		arithOp[DIV] = new JButton(" / ");
		arithOp[DIV].setBackground(Color.GREEN);
		arithOp[DIFF] = new JButton(" Differentiate P ");
		arithOp[DIFF].setBackground(Color.BLUE);
		arithOp[INTEGRATE] = new JButton(" Integrate P ");
		arithOp[INTEGRATE].setBackground(Color.MAGENTA);
		for (int i = 0; i < NR_OF_OP; i++) {
			arithOp[i].setBorder(new LineBorder(Color.DARK_GRAY, LINE_BORDER_THICKNESS));
			// arithOp[i].setBackground(new Color(i * 100 + 1));
		}

		backButton = new JButton(" BACK ");
		buttonsP1 = new BasicArrowButton[(degree1 + 1) * 2];
		buttonsP2 = new BasicArrowButton[(degree2 + 1) * 2];

		for (int i = degree1; i >= 0; i--) { // initialize LINE 1
			jLabelPowersOfX1[i] = new JLabel("x^" + i);
			jLabelCoeffP1[i] = new JLabel("+0");
			if (i == degree1) {
				jLabelCoeffP1[i].setText("+1");
			}
			buttonsP1[i * 2] = new BasicArrowButton(SwingConstants.NORTH);
			buttonsP1[i * 2 + 1] = new BasicArrowButton(SwingConstants.SOUTH);

			jPanelButtonsP1[i] = new JPanel();
			jPanelButtonsP1[i].add(buttonsP1[i * 2]);
			jPanelButtonsP1[i].add(buttonsP1[i * 2 + 1]);
			jPanelButtonsP1[i].setLayout(new GridLayout(2, 1));

			jPanelLine1.add(jLabelCoeffP1[i]);
			jPanelLine1.add(jPanelButtonsP1[i]);
			jPanelLine1.add(jLabelPowersOfX1[i]);
		}
		for (int i = degree2; i >= 0; i--) { // start from n-th term
			jLabelPowersOfX2[i] = new JLabel("x^" + i);
			jLabelCoeffP2[i] = new JLabel("+0");
			if (i == degree2) {
				jLabelCoeffP2[i].setText("+1");
			}
			buttonsP2[i * 2] = new BasicArrowButton(SwingConstants.NORTH);
			buttonsP2[i * 2 + 1] = new BasicArrowButton(SwingConstants.SOUTH);

			jPanelButtonsP2[i] = new JPanel();
			jPanelButtonsP2[i].add(buttonsP2[i * 2]);
			jPanelButtonsP2[i].add(buttonsP2[i * 2 + 1]);
			jPanelButtonsP2[i].setLayout(new GridLayout(2, 1));

			jPanelLine2.add(jLabelCoeffP2[i]);
			jPanelLine2.add(jPanelButtonsP2[i]);
			jPanelLine2.add(jLabelPowersOfX2[i]);
		}

		// buttonsP1;

		jPanelPlusMinus.add(arithOp[PLUS]);
		jPanelPlusMinus.add(arithOp[MINUS]);
		jPanelPlusMinus.setLayout(new GridLayout(2, 1));
		jPanelPlusMinus.setSize(new Dimension(50, 100));
		jPanelMulDiv.add(arithOp[MUL]);
		jPanelMulDiv.add(arithOp[DIV]);
		jPanelMulDiv.setLayout(new GridLayout(2, 1));
		jPanelMulDiv.setSize(new Dimension(50, 100));
		jPanelDiffInt.add(arithOp[DIFF]);
		jPanelDiffInt.add(arithOp[INTEGRATE]);
		jPanelDiffInt.setLayout(new GridLayout(2, 1));
		jPanelDiffInt.setSize(new Dimension(50, 100));

		jPanelLine3.add(jLabelResult);
		jPanelLine4.add(jPanelPlusMinus);
		jPanelLine4.add(jPanelMulDiv);
		jPanelLine4.add(jPanelDiffInt);

		jPanelLine1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jPanelLine2.setLayout(new FlowLayout(FlowLayout.LEFT));
		jPanelLine3.setLayout(new FlowLayout(FlowLayout.LEFT));

		backButton.setSize(new Dimension(300, 100));
		jPanelLine4.add(backButton);

		jPanelLine1.setSize(new Dimension(1000, 100));
		jPanelLine2.setSize(new Dimension(1000, 100));
		jPanelLine3.setSize(new Dimension(1000, 100));
		jPanelLine4.setSize(new Dimension(1000, 100));

		frame.add(jPanelLine1);
		frame.add(jPanelLine2);
		frame.add(jPanelLine3);
		frame.add(jPanelLine4);
		frame.setLayout(new GridLayout(4, 1));
		frame.setVisible(true);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void updateInputs(Polynomial p1, Polynomial p2) {
		for (int i = 0; i < p1.degree + 1; i++) {
			if (p1.coeff[i].getCoeff() >= 0)
				jLabelCoeffP1[i].setText("" + p1.coeff[i].getSign() + p1.coeff[i].getCoeff());
			else
				jLabelCoeffP1[i].setText("" + p1.coeff[i].getCoeff());
		}
		for (int i = 0; i < p2.degree + 1; i++) {
			if (p2.coeff[i].getCoeff() >= 0)
				jLabelCoeffP2[i].setText("" + p2.coeff[i].getSign() + p2.coeff[i].getCoeff());
			else
				jLabelCoeffP2[i].setText("" + p2.coeff[i].getCoeff());
		}
	}

	public void updateOutput(Polynomial p1) {
		String text = "";
		for (int i = p1.degree; i >= 0; i--) {
			if (p1.coeff[i].isZero()) {
				continue;
			} else {
				if (p1.coeff[i] instanceof IntCoeff) {
					if (p1.coeff[i].getCoeff() < 0) {
						text = text.concat(" " + p1.coeff[i].getCoeff() + " x^" + i + "  ");
					} else {
						text = text.concat(" +" + p1.coeff[i].getCoeff() + " x^" + i + "  ");
					}
				} else if (p1.coeff[i] instanceof RealCoeff) {
					if (p1.coeff[i].getRealCoeff() < 0) {
						text = text.concat(" " + p1.coeff[i].getRealCoeff() + " x^" + i + "  ");
					} else {
						text = text.concat(" +" + p1.coeff[i].getRealCoeff() + " x^" + i + "  ");
					}
				}
			}
		}
		jLabelResult.setText(text);
	}

	public void updateDivideButton(Polynomial p1, Polynomial p2) {
		if (p2.degree > p1.degree || (p2.degree == 0 && p2.coeff[0].isZero())) {
			// arithOp[DIV].setEnabled(false);
			arithOp[DIV].setOpaque(false);
			arithOp[DIV].setContentAreaFilled(false);
			arithOp[DIV].setBorderPainted(false);
		} else {
			// arithOp[DIV].setEnabled(true);
			arithOp[DIV].setOpaque(true);
			arithOp[DIV].setContentAreaFilled(true);
			arithOp[DIV].setBorderPainted(true);
		}
	}
}
