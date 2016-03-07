package Polynoms;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {

	JFrame frame;
	private JTextField polyOneField;
	private JTextField polyTwoField;
	private JTextField resultField;
	private JButton btnSubmit;
	private JButton btnAdd;
	private String polyOneString;
	private String polyTwoString;
	private int[] polyOneInt = new int[100];
	private int[] polyTwoInt = new int[100];
	private int marker = 0;
	
	Operations o = new Operations();
	private JButton btnSubstract;
	private JButton btnMultiply;
	private JButton btnTba;


	public GUI() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		polyOneField = new JTextField();
		polyOneField.setBounds(157, 101, 391, 38);
		frame.getContentPane().add(polyOneField);
		
		polyTwoField = new JTextField();
		polyTwoField.setBounds(157, 175, 391, 38);
		frame.getContentPane().add(polyTwoField);
		
		resultField = new JTextField();
		resultField.setBounds(157, 252, 391, 38);
		frame.getContentPane().add(resultField);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBounds(24, 409, 117, 29);
		frame.getContentPane().add(btnSubmit);
		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(147, 409, 117, 29);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblNewLabel = new JLabel("First polynom:");
		lblNewLabel.setBounds(51, 112, 107, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSecondPolynom = new JLabel("Second polynom:");
		lblSecondPolynom.setBounds(32, 186, 126, 16);
		frame.getContentPane().add(lblSecondPolynom);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(91, 263, 50, 16);
		frame.getContentPane().add(lblResult);
		
		btnSubstract = new JButton("SUBSTRACT");
		btnSubstract.setBounds(266, 409, 117, 29);
		frame.getContentPane().add(btnSubstract);
		
		btnMultiply = new JButton("MULTIPLY");
		btnMultiply.setBounds(383, 409, 117, 29);
		frame.getContentPane().add(btnMultiply);
		
		btnTba = new JButton("TBA");
		btnTba.setBounds(500, 409, 117, 29);
		frame.getContentPane().add(btnTba);

//BUTTON EVENTS:
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
			
				polyOneString = polyOneField.getText();
				polyTwoString = polyTwoField.getText();
				
				try{
					polyOneInt = readPol(polyOneString);
					marker = 1;
					resultField.setText("SUCCESS! Data submitted. Select the desired operation.");
				}catch(NumberFormatException err){
					resultField.setText("ERROR! Invalid input data!");
				}
				
				try{
					polyTwoInt = readPol(polyTwoString);
					marker = 1;
					resultField.setText("SUCCESS! Data submitted. Select the desired operation.");
				}catch(NumberFormatException err){
					resultField.setText("ERROR! Invalid input data!");
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int maxSize;
				if(marker == 1){
				if(polyOneInt.length < polyTwoInt.length){
					maxSize = polyTwoInt.length;
				}
				else {
					maxSize = polyOneInt.length;
				}
				int resultInt[] = new int[maxSize];
				
				resultInt = o.addP(polyOneInt, polyTwoInt);
				String temp = new String();
				
				for(int i = maxSize-1; i >= 0;  i--){
					System.out.print(resultInt[i] + "X^" + i);
					if(resultInt[i] >= 0){
						temp = temp + " +";
					}
					temp = temp + " " + Integer.toString(resultInt[i]) +  "X^" + i + " ";							
				}
				resultField.setText(temp);
			}else{
				resultField.setText("ERROR! A valid polynom must be submitted!");
			}
		}
		});
	
	btnSubstract.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event){
			
			//SUBSTRACT
			
		}
	});
	
	btnMultiply.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event){
			
			//MULTIPLY
			
		}
	});
	
}
	
	public int[] readPol(String s){
		
		String[] parts = s.split(" "); 
		int result[] = new int[parts.length];
		
		for(int i = parts.length-1; i >= 0; i--){
			result[i] = Integer.parseInt(parts[(parts.length-1)-i]);
		}
		
		return result;
	}
	
}

	


