package views;


/**
 * main window. The GUI. Note that the input is going to be an array of integers 
 * e.g.: p1 = 2 0 -1 4 (meaning p1 = 2x^3 - x + 4)
 * some functions (e.g. evaluateAt(p, x)) work properly only if x is provided
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window extends JFrame {
	
	private JTextField input1 = new JTextField(100);
	private JTextField input2 = new JTextField(100);
	private JTextField inputX = new JTextField(100);
	private JTextField output = new JTextField(100);
	private JPanel panelLeft = new JPanel();
	private JPanel panelRight = new JPanel();
	private JButton add = new JButton("+");
	private JButton sub = new JButton("-");
	private JButton mul = new JButton("*");
	private JButton div = new JButton("/");
	private JButton diff1 = new JButton("p1'");
	private JButton diff2 = new JButton("p2'");
	private JButton int1 = new JButton("int(p1)");
	private JButton int2 = new JButton("int(p2)");
	private JButton evalP1 = new JButton("P1(x)");
	private JButton evalP2 = new JButton("P2(x)");
	private JButton rootP1 = new JButton("P1=0 arround x");
	private JButton rootP2 = new JButton("P2=0 arround x");
	private JButton graphP1 = new JButton("graph p1");
	private JButton graphP2 = new JButton("graph p2");
	
	public Window(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setSize(750, 200);
		JLabel labelP1 = new JLabel("   p1");
		JLabel labelP2 = new JLabel("   p2");
		JLabel result = new JLabel("   result");
		JLabel labelX = new JLabel("   x");
		panelLeft.setLayout(new GridLayout(4,2));
		panelLeft.add(labelP1);
		panelLeft.add(input1);
		panelLeft.add(labelP2);
		panelLeft.add(input2);
		panelLeft.add(labelX);
		panelLeft.add(inputX);
		panelLeft.add(result);
		panelLeft.add(output);
		
		add(panelLeft);
		
		panelRight.setLayout(new GridLayout(4, 4));
		
		panelRight.add(add);
		panelRight.add(sub);
		panelRight.add(mul);
		panelRight.add(div);
		panelRight.add(diff1);
		panelRight.add(diff2);
		panelRight.add(int1);
		panelRight.add(int2);
		panelRight.add(evalP1);
		panelRight.add(evalP2);
		panelRight.add(rootP1);
		panelRight.add(rootP2);
		panelRight.add(graphP1);
		panelRight.add(graphP2);
		
		add(panelRight);
	
		setVisible(true);
	}

	public void setAddButtonListener(ActionListener a) {
		add.addActionListener(a);
	}
	
	public void setSubButtonListener(ActionListener a) {
		sub.addActionListener(a);
	}
	
	public void setMulButtonListener(ActionListener a) {
		mul.addActionListener(a);
	}
	
	public void setDivButtonListener(ActionListener a) {
		div.addActionListener(a);
	}
	
	public void setDiff1ButtonListener(ActionListener a) {
		diff1.addActionListener(a);
	}
	
	public void setDiff2ButtonListener(ActionListener a) {
		diff2.addActionListener(a);
	}
	
	public void setInt1ButtonListener(ActionListener a) {
		int1.addActionListener(a);
	}
	
	public void setInt2ButtonListener(ActionListener a) {
		int2.addActionListener(a);
	}
	
	public void setEvalP1ButtonListener(ActionListener a) {
		evalP1.addActionListener(a);
	}
	
	public void setEvalP2ButtonListener(ActionListener a) {
		evalP2.addActionListener(a);
	}
	
	public void setRootP1ButtonListener(ActionListener a) {
		rootP1.addActionListener(a);
	}
	
	public void setRootP2ButtonListener(ActionListener a) {
		rootP2.addActionListener(a);
	}
	
	public void setGraphP1ButtonListener(ActionListener a) {
		graphP1.addActionListener(a);
	}
	
	public void setGraphP2ButtonListener(ActionListener a) {
		graphP2.addActionListener(a);
	}
	
	public JTextField getInput1() {
		return input1;
	}

	public JTextField getInput2() {
		return input2;
	}
	
	public JTextField getInputX() {
		return inputX;
	}
	
	public JTextField getOutput() {
		return output;
	}
}
