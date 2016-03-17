package poly.IO;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Dia
 *
 *         The public class DegreeFrame implementing ChangeListener is for
 *         establishing the degrees of the two input polynomials.
 *         It describes 2 JSliders, which can be modified to obtain the final desired degree.
 *         The class implements the method stateChanged.
 */
public class DegreeFrame implements ChangeListener {
	private static final int INITIAL_DEGREE1 = 4;
	private static final int INITIAL_DEGREE2 = 6;
	private static final int MAX_GRADE = 20;

	public JFrame frame;
	private JSlider jSliderDegree1;
	private JSlider jSliderDegree2;
	private JLabel jLabelGrade1;
	private JLabel jLabelGrade2;
	public JButton okButton;

	public int degree1 = INITIAL_DEGREE1;
	public int degree2 = INITIAL_DEGREE2;

	public DegreeFrame() {
		frame = new JFrame("Polynomials");

		jLabelGrade1 = new JLabel("DEGREE of P1(x):", JLabel.CENTER);
		jLabelGrade2 = new JLabel("DEGREE of P2(x):", JLabel.CENTER);

		okButton = new JButton("OK");
		// okButton.addActionListener((ActionListener) this);

		jSliderDegree1 = new JSlider(JSlider.HORIZONTAL, 0, MAX_GRADE, INITIAL_DEGREE1);
		jSliderDegree1.setMinorTickSpacing(1);
		jSliderDegree1.setMajorTickSpacing(2);
		jSliderDegree1.setPaintTicks(true);
		jSliderDegree1.setPaintLabels(true);
		jSliderDegree1.addChangeListener((ChangeListener) this);

		jSliderDegree2 = new JSlider(JSlider.HORIZONTAL, 0, MAX_GRADE, INITIAL_DEGREE2);
		jSliderDegree2.setMinorTickSpacing(1);
		jSliderDegree2.setMajorTickSpacing(2);
		jSliderDegree2.setPaintTicks(true);
		jSliderDegree2.setPaintLabels(true);
		jSliderDegree2.addChangeListener((ChangeListener) this);

		frame.add(jLabelGrade1);
		frame.add(jSliderDegree1);
		frame.add(jLabelGrade2);
		frame.add(jSliderDegree2);
		frame.add(okButton);
		frame.setLayout(new GridLayout(5, 1));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void stateChanged(ChangeEvent event) {
		if (event.getSource() == jSliderDegree1) {
			if (!jSliderDegree1.getValueIsAdjusting()) {
				degree1 = (int) jSliderDegree1.getValue();
				System.out.println("" + degree1 + " " + degree2);
			}
		} else if (event.getSource() == jSliderDegree2) {
			if (!jSliderDegree2.getValueIsAdjusting()) {
				degree2 = (int) jSliderDegree2.getValue();
				System.out.println("" + degree1 + " " + degree2);
			}
		}
	}
}
