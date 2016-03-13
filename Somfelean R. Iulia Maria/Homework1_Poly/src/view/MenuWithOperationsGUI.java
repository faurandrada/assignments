package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * @author iulia
 * 
 *         The graphical user interface or the view is represented by this
 *         class. It contains means of getting input from the user. Has a menu
 *         with the operations that can to chosen by the user and applied to the
 *         polynomials he provided as inputs.
 *
 */
public class MenuWithOperationsGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	// the graphic elements of the frame
	private JLabel background;
	private JButton addButton, subButton, mulButton, divButton, diffButton, integrButton;
	private JLabel poly1Label, poly2Label, resultLabel;
	private JTextField resultText;
	private JTextArea poly1TA, poly2TA;

	public MenuWithOperationsGUI(String nameOfFrame) {

		super(nameOfFrame);
		setSize(550, 550);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// setting a bacground image to the frame, because it doesn't have such
		// a utility, I put a background image
		// to a label, and made this label the principal container on which I
		// added the rest of the elements.
		ImageIcon backgroundImage = new ImageIcon(this.getClass().getResource("/backImg1.jpg")); // load
																										// the
																										// image
																										// to
																										// a
																										// imageIcon
		Image image = backgroundImage.getImage(); // transform it
		Image newimg = image.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_DEFAULT); // scale
																													// it
																													// the
		backgroundImage = new ImageIcon(newimg); /// transform it back
		background = new JLabel(backgroundImage);
		add(background);
		background.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;

		// panel with polys
		JPanel polyPanel = new JPanel(new GridBagLayout());
		GridBagConstraints cp = new GridBagConstraints();
		cp.fill = GridBagConstraints.HORIZONTAL;

		poly1Label = new JLabel("First polynomial: ");
		cp.gridx = 0;
		cp.gridy = 0;
		cp.insets = new Insets(10, 10, 5, 0);
		polyPanel.add(poly1Label, cp);

		poly1TA = new JTextArea();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		poly1TA.setBorder(border);
		cp.fill = GridBagConstraints.HORIZONTAL;
		cp.gridx = 1;
		cp.gridy = 0;
		cp.insets = new Insets(10, 0, 0, 10);
		cp.weightx = 1;
		polyPanel.add(poly1TA, cp);

		poly2Label = new JLabel("Second polynomial: ");
		cp.fill = GridBagConstraints.HORIZONTAL;
		cp.gridx = 0;
		cp.gridy = 1;
		cp.insets = new Insets(10, 10, 5, 0);
		polyPanel.add(poly2Label, cp);

		poly2TA = new JTextArea();
		poly2TA.setBorder(border);
		cp.fill = GridBagConstraints.HORIZONTAL;
		cp.gridx = 1;
		cp.gridy = 1;
		cp.insets = new Insets(10, 0, 0, 10);
		cp.weightx = 1;
		polyPanel.add(poly2TA, cp);

		resultLabel = new JLabel("Result:");
		cp.gridx = 0;
		cp.gridy = 2;
		cp.insets = new Insets(10, 10, 5, 0);
		polyPanel.add(resultLabel, cp);

		resultText = new JTextField();
		resultText.setBorder(border);
		resultText.setEditable(false);
		cp.fill = GridBagConstraints.HORIZONTAL;
		cp.gridx = 1;
		cp.gridy = 2;
		cp.insets = new Insets(10, 0, 0, 10);
		cp.weightx = 1;
		polyPanel.add(resultText, cp);

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(10, 0, 30, 0);
		background.add(polyPanel, c);

		// panel for the menu
		JPanel opsButtonsPanel = new JPanel();
		opsButtonsPanel.setLayout(new GridLayout(2, 2));

		addButton = new JButton(new ImageIcon(this.getClass().getResource("/addBtn.jpg")));
		addButton.setBackground(Color.WHITE);
		opsButtonsPanel.add(addButton);

		subButton = new JButton(new ImageIcon(this.getClass().getResource("/subBtn.jpg")));
		subButton.setBackground(Color.WHITE);
		opsButtonsPanel.add(subButton);

		diffButton = new JButton("Differentiate");
		diffButton.setBackground(Color.WHITE);
		diffButton.setForeground(Color.orange);
		diffButton.setFont(new Font("Calibri", Font.BOLD, 20));
		opsButtonsPanel.add(diffButton);

		mulButton = new JButton(new ImageIcon(this.getClass().getResource("/mulBtn.jpg")));
		mulButton.setBackground(Color.WHITE);
		opsButtonsPanel.add(mulButton);

		divButton = new JButton(new ImageIcon(this.getClass().getResource("/divBtn.jpg")));
		divButton.setBackground(Color.WHITE);
		opsButtonsPanel.add(divButton);

		integrButton = new JButton("Integrate");
		integrButton.setBackground(Color.WHITE);
		integrButton.setForeground(Color.MAGENTA);
		integrButton.setFont(new Font("Calibri", Font.BOLD, 20));
		opsButtonsPanel.add(integrButton);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.insets = new Insets(0, 0, 0, 0);
		background.add(opsButtonsPanel, c);

		setVisible(true);
	}

	// add action listeners on buttons
	public void addAdditionListener(ActionListener listenForAddButton) {
		addButton.addActionListener(listenForAddButton);
	}

	public void addSubtractionListener(ActionListener listenForSubButton) {
		subButton.addActionListener(listenForSubButton);
	}

	public void addMultiplicationListener(ActionListener listenForMulButton) {
		mulButton.addActionListener(listenForMulButton);
	}

	public void addDivisionListener(ActionListener listenForDivButton) {
		divButton.addActionListener(listenForDivButton);
	}

	public void addDifferentiationListener(ActionListener listenForDiffButton) {
		diffButton.addActionListener(listenForDiffButton);
	}

	public void addIntegrationListener(ActionListener listenForIntegrButton) {
		integrButton.addActionListener(listenForIntegrButton);
	}

	public String getFirstPolynomial() {
		return poly1TA.getText();
	}

	public String getSecondPolynomial() {
		return poly2TA.getText();
	}

	public void setResult(String res) {
		resultText.setText(res);
	}
}
