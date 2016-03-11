package pt.polynomials.views;
/**
 * the working board for polynomial processing
 * @author Chiti
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import pt.polynomials.controllers.PolynomialDivide;
import pt.polynomials.controllers.PolynomialOperations;
import pt.polynomials.models.IntegerMonom;
import pt.polynomials.models.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class Board extends JFrame{
  private JButton addButton;
  private JButton substractButton;
  private JButton multiplyButton;
  private JButton divideButton;
  private JButton multiplyScalarButton;
  private JButton evaluateButton;
  private JButton integrateButton;
  private JButton derivateButton;
  
  private JTextField polynom1Field;
  private JTextField polynom2Field;
  private JTextField polynom1View;
  private JTextField polynom2View ;
  private JTextField result;
  private JTextField rest;
  private JTextField scalarField;
  private JTextField evaluateField;
  
  private Polynomial<Integer> p1;
  private Polynomial<Integer> p2;
  
  private PolynomialOperations operator=new PolynomialOperations();
  private PolynomialDivide divideOperator=new PolynomialDivide();
  
  private double scalarNumber;
  private double evaluateNumber;
  public Board(){
	  this.setTitle("Polynomial Operations");
		this.setPreferredSize(new Dimension(600, 600));
		this.setMaximumSize(new Dimension(600, 600));
		this.setMinimumSize(new Dimension(600, 600));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setBackground(Color.black);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(4, 4));
		
		polynom1Field=new JTextField("Enter first Polynom");
		this.add(polynom1Field);
		
		polynom1View=new JTextField("View1");
		polynom1View.setEditable(false);
		this.add(polynom1View);
		
		evaluateButton=new JButton("Evaluate first polynom!");
		evaluateButton.setBackground(Color.YELLOW);
		this.add(evaluateButton);
		
		multiplyScalarButton=new JButton("Multiply first polynom!");
		multiplyScalarButton.setBackground(Color.YELLOW);
		this.add(multiplyScalarButton);
		
		polynom2Field=new JTextField("Enter second Polynom");
		this.add(polynom2Field);
		
		polynom2View=new JTextField("View2");
		polynom2View.setEditable(false);
		this.add(polynom2View);
		
		evaluateField=new JTextField("Enter term for evaluation");
		this.add(evaluateField);
		
		scalarField=new JTextField("Enter scalar");
		this.add(scalarField);
		
		addButton=new JButton("Add!");
		addButton.setBackground(Color.YELLOW);
		this.add(addButton);
		
		substractButton=new JButton("Substract!");
		substractButton.setBackground(Color.YELLOW);
		this.add(substractButton);
		
		multiplyButton=new JButton("Multiply!");
		multiplyButton.setBackground(Color.YELLOW);
		this.add(multiplyButton);
		
		divideButton=new JButton("Divide!");
		divideButton.setBackground(Color.YELLOW);
		this.add(divideButton);
		
		result=new JTextField("result here");
		result.setEditable(false);
		this.add(result);
		
		rest=new JTextField("rest here");
		rest.setEditable(false);
		this.add(rest);
		
		integrateButton=new JButton("Integrate!");
		integrateButton.setBackground(Color.YELLOW);
		this.add(integrateButton);
		
		derivateButton=new JButton("Derivate!");
		derivateButton.setBackground(Color.YELLOW);
		this.add(derivateButton);
		
		HandlerFieldForPolynom1 handler1=new HandlerFieldForPolynom1();
		polynom1Field.addActionListener(handler1);
		
		HandlerFieldForPolynom2 handler2=new HandlerFieldForPolynom2();
		polynom2Field.addActionListener(handler2);
		
		HandlerScalarField handler3=new HandlerScalarField();
		scalarField.addActionListener(handler3);
		
		HandlerEvaluateField handler4=new HandlerEvaluateField();
		evaluateField.addActionListener(handler4);
		
		HandlerScalarAndEvaluateButton handler5=new HandlerScalarAndEvaluateButton();
		multiplyScalarButton.addActionListener(handler5);
		evaluateButton.addActionListener(handler5);
		
		HandlerOperations handler6=new HandlerOperations();
		addButton.addActionListener(handler6);
		substractButton.addActionListener(handler6);
		multiplyButton.addActionListener(handler6);
		divideButton.addActionListener(handler6);
		integrateButton.addActionListener(handler6);
		derivateButton.addActionListener(handler6);
		
		this.setVisible(true);
  }
 /*Class for reading first Polinom and display it*/ 
  private class HandlerFieldForPolynom1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			p1=new Polynomial<Integer>();
			String text=polynom1Field.getText();
			String[] coeffs =text.split(new String(","));
			int i;
			for (i = 0; i < coeffs.length; i++) {
				p1.addMonom(new IntegerMonom(i,Integer.parseInt(coeffs[i])));
			}
			operator.cleanPolynomial(p1);
			polynom1View.setText(p1.toString());
		}
	}
  /**
   * Class for reading second Polinom and display it*/ 
  private class HandlerFieldForPolynom2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			p2=new Polynomial<Integer>();
			String text=polynom2Field.getText();
			String[] coeffs =text.split(new String(","));
			int i;
			for (i = 0; i < coeffs.length; i++) {
				p2.addMonom(new IntegerMonom(i,Integer.parseInt(coeffs[i])));
			}
			operator.cleanPolynomial(p2);
			polynom2View.setText(p2.toString());
		}
	}
  /**
   * Class for reading the scalar
   */
  private class HandlerScalarField implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String text=scalarField.getText();
			scalarNumber=Double.parseDouble(text);
		}
	}
 
   /**
    *  Class for reading the evaluate term*/
  private class HandlerEvaluateField implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String text=evaluateField.getText();
			evaluateNumber=Double.parseDouble(text);
		}
	}
  /**
   * Class for multipling polynom 1 when you press the button Multiply with Scalar!*/
  private class HandlerScalarAndEvaluateButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == multiplyScalarButton){
			Polynomial<Double> resultD=operator.multiplyScalar(p1, scalarNumber);
			result.setText(resultD.toString());}
			else if (event.getSource() == evaluateButton){
				Double resultD=operator.evaluate(p1, evaluateNumber);
				result.setText(resultD.toString());
			}
		}
	}
  /**
   * Class for performing operations when buttons are pressed*/
  private class HandlerOperations implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Polynomial<Double> resultD=new Polynomial<Double>();
			Polynomial<Integer> resultI=new Polynomial<Integer>();
			if ( event.getSource()== addButton )
			{
				resultI=operator.add(p1, p2);
				result.setText(resultI.toString());
			}else if ( event.getSource()== substractButton ){
				resultI=operator.substract(p1, p2);
				result.setText(resultI.toString());
			}else if (event.getSource()== multiplyButton){
				resultI=operator.multiply(p1, p2);
				result.setText(resultI.toString());
			}else if (event.getSource()== divideButton){
				resultD=divideOperator.divide(p1,p2);
				result.setText(resultD.toString());
				rest.setText(divideOperator.rest.toString());
			}else if (event.getSource()==integrateButton){
				resultD=operator.integrate(p1);
				result.setText(resultD.toString());
			}else if (event.getSource()==derivateButton){
				resultI=operator.derivate(p1);
				result.setText(resultI.toString());
			}
		}
	}
}
