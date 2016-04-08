package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mathOperations.IDOp;
import polynom.Polynom;

public class GUI implements ActionListener {

	private JFrame myFrame = new JFrame("Polynom Calculator");

	private JButton plus = new JButton("+");
	private JButton subtract = new JButton("-");
	private JButton multiply = new JButton("*");
	private JButton division = new JButton("/");
	private JButton integralFirst = new JButton("Integral 1st");
	private JButton integralSecond = new JButton("Integral 2nd");
	private JButton deriveFirst = new JButton("Derive 1st");
	private JButton deriveSecond = new JButton("Derive 2nd");

	private JTextField firstEnter = new JTextField();
	private JTextField secondEnter = new JTextField();
	private JTextField resultBox = new JTextField();
	
	private Polynom polynom;

	public String firstString;
	public String secondString;

	public GUI(int HEIGHT, int WIDTH) {
		buildFrame(HEIGHT, WIDTH);

		// main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		// content panel with two sections, UP and DOWN
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(2, 1));
		contentPanel.setOpaque(false);

		// content Panel up
		JPanel upPanel = new JPanel();
		buildUpPanel(upPanel);
		contentPanel.add(upPanel);
		setHelp();

		// content panel down
		JPanel downPanel = new JPanel();
		buildDownPanel(downPanel);
		contentPanel.add(downPanel);
		mainPanel.add(contentPanel);

		myFrame.add(mainPanel);
		myFrame.setVisible(true);

	}

	public GUI() {
		// TODO Auto-generated constructor stub
	}

	private void buildDownPanel(JPanel downPanel) {
		// TODO Auto-generated method stub

		downPanel.setLayout(new GridBagLayout());
		GridBagConstraints gpcDown = new GridBagConstraints();
		gpcDown.insets = new Insets(10, 10, 10, 10);

		Font font = new Font("Arial", Font.BOLD, 25);
		Font font2 = new Font("Arial", Font.BOLD, 15);

		createButton(plus, font, gpcDown, 0, 0);
		downPanel.add(plus, gpcDown);

		createButton(subtract, font, gpcDown, 1, 0);
		downPanel.add(subtract, gpcDown);

		createButton(multiply, font, gpcDown, 2, 0);
		downPanel.add(multiply, gpcDown);

		createButton(division, font, gpcDown, 3, 0);
		downPanel.add(division, gpcDown);

		createButton(integralFirst, font2, gpcDown, 0, 1);
		downPanel.add(integralFirst, gpcDown);

		createButton(integralSecond, font2, gpcDown, 1, 1);
		downPanel.add(integralSecond, gpcDown);

		createButton(deriveFirst, font2, gpcDown, 2, 1);
		downPanel.add(deriveFirst, gpcDown);

		createButton(deriveSecond, font2, gpcDown, 3, 1);
		downPanel.add(deriveSecond, gpcDown);

		downPanel.setBackground(Color.DARK_GRAY);
	}

	private void createButton(JButton button, Font font, GridBagConstraints gpcDown, int i, int j) {
		// TODO Auto-generated method stub
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(150, 50));
		button.setBackground(Color.WHITE);
		button.setFont(font);
		gpcDown.gridx = i;
		gpcDown.gridy = j;
	}

	private void buildUpPanel(JPanel upPanel) {
		// TODO Auto-generated method stub

		upPanel.setLayout(new GridBagLayout());
		upPanel.setPreferredSize(new Dimension(750, 300));

		GridBagConstraints gpcUp = new GridBagConstraints();
		gpcUp.insets = new Insets(10, 10, 10, 10);

		createInput(upPanel, "FIRST POLYNOM", gpcUp, 0, 0, 1, 0);
		firstEnter.setPreferredSize(new Dimension(600, 30));
		upPanel.add(firstEnter, gpcUp);

		createInput(upPanel, "SECOND POLYNOM", gpcUp, 0, 1, 1, 1);
		secondEnter.setPreferredSize(new Dimension(600, 30));
		upPanel.add(secondEnter, gpcUp);

		createInput(upPanel, "RESULT", gpcUp, 0, 2, 1, 2);
		resultBox.setPreferredSize(new Dimension(600, 30));
		resultBox.setEditable(false);
		upPanel.add(resultBox, gpcUp);
		upPanel.setBackground(Color.DARK_GRAY);
	}

	private void createInput(JPanel panel, String string, GridBagConstraints gpcUp, int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		gpcUp.gridx = i;
		gpcUp.gridy = j;
		JLabel label = new JLabel(string);
		label.setForeground(Color.WHITE);
		panel.add(label, gpcUp);
		gpcUp.gridx = k;
		gpcUp.gridy = l;
	}

	private void buildFrame(int HEIGHT, int WIDTH) {
		// TODO Auto-generated method stub
		myFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		myFrame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		myFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		myFrame.setResizable(false);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setFirstString(firstEnter.getText());
		setSecondString(secondEnter.getText());

		if (e.getSource() == plus) {
			polynom = new Polynom(firstString, secondString, IDOp.Sum);
		}

		if (e.getSource() == subtract) {
			polynom = new Polynom(firstString, secondString, IDOp.Difference);
		}

		if (e.getSource() == multiply) {
			polynom = new Polynom(firstString, secondString, IDOp.Multiply);
		}

		if (e.getSource() == division) {
			polynom = new Polynom(firstString, secondString, IDOp.Division);
		}

		if (e.getSource() == integralFirst) {
			polynom = new Polynom(firstString, IDOp.Integral1);
		}

		if (e.getSource() == integralSecond) {
			polynom = new Polynom(secondString, IDOp.Integral2);
		}

		if (e.getSource() == deriveFirst) {
			polynom = new Polynom(firstString, IDOp.Derive1);
		}

		if (e.getSource() == deriveSecond) {
			polynom = new Polynom(secondString, IDOp.Derive2);
		}

		resultBox.setText(polynom.res);

	}

	private void setHelp() {
		setResult("INPUT SHOULD MATCH THE NEXT FORM: [COEFFICIENT]x^[POWER]");
	}

	private void setSecondString(String secondString) {
		this.secondString = secondString;
	}

	private void setFirstString(String firstString) {
		this.firstString = firstString;
	}

	public void setResult(String result) {
		resultBox.setText(result);
	}

}
