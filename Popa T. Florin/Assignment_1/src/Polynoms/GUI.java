package Polynoms;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;


public class GUI {

	JFrame frame;
	private JTextField polyOneField;
	private JTextField polyTwoField;
	private JTextField resultField;
	private String polyOneString;
	private String polyTwoString;
	private int[] polyOneInt = new int[100];
	private int[] polyTwoInt = new int[100];
	private int marker = 0;
	
	Operations o = new Operations();
	private JButton btnSubmit;
	private JButton btnAdd;
	private JButton btnSubtract;
	private JButton btnMultiply;
	private JButton btnDerivate;
	private JButton btnIntegrate;


	public GUI() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		polyOneField = new JTextField();
		polyOneField.setBounds(160, 136, 391, 38);
		frame.getContentPane().add(polyOneField);
		
		polyTwoField = new JTextField();
		polyTwoField.setBounds(160, 210, 391, 38);
		frame.getContentPane().add(polyTwoField);
		
		resultField = new JTextField();
		resultField.setBounds(160, 287, 391, 38);
		frame.getContentPane().add(resultField);
		
		btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBounds(269, 352, 117, 29);
		frame.getContentPane().add(btnSubmit);
		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(6, 409, 117, 29);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblFirst = new JLabel("First polynom:");
		lblFirst.setBounds(54, 147, 107, 16);
		frame.getContentPane().add(lblFirst);
		
		JLabel lblSecond = new JLabel("Second polynom:");
		lblSecond.setBounds(35, 221, 126, 16);
		frame.getContentPane().add(lblSecond);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(94, 298, 50, 16);
		frame.getContentPane().add(lblResult);
		
		btnSubtract = new JButton("SUBTRACT");
		btnSubtract.setBounds(124, 409, 117, 29);
		frame.getContentPane().add(btnSubtract);
		
		btnMultiply = new JButton("MULTIPLY");
		btnMultiply.setBounds(250, 409, 117, 29);
		frame.getContentPane().add(btnMultiply);
		
		btnDerivate = new JButton("DERIVATE");
		btnDerivate.setBounds(379, 409, 126, 29);
		frame.getContentPane().add(btnDerivate);
		
		btnIntegrate = new JButton("INTEGRATE");
		btnIntegrate.setBounds(517, 409, 117, 29);
		frame.getContentPane().add(btnIntegrate);
		
		JLabel note1 = new JLabel("*The coefficients must be entered in the polynom fields, starting from the highest power to the lowest");
		note1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		note1.setBounds(6, 36, 628, 46);
		frame.getContentPane().add(note1);
		
		JLabel note2 = new JLabel("*The coefficients must be separated with spaces");
		note2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		note2.setBounds(6, 56, 628, 46);
		frame.getContentPane().add(note2);

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
					marker=0;
					resultField.setText("ERROR! Invalid input data!");
				}
				
				try{
					polyTwoInt = readPol(polyTwoString);
					marker = 1;
					resultField.setText("SUCCESS! Data submitted. Select the desired operation.");
				}catch(NumberFormatException err){
					marker=0;
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
					
					temp = temp + " " + Integer.toString(resultInt[i]) +  "X^" + i + " ";
					if(i > 0){
						temp = temp + " +";
					}
				}
				resultField.setText(temp);
			}else{
				resultField.setText("ERROR! A valid polynom must be submitted!");
			}
		}
		});
	
	btnSubtract.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent event){
			
			int maxSize;
			if(marker == 1){
			if(polyOneInt.length < polyTwoInt.length){
				maxSize = polyTwoInt.length;
			}
			else {
				maxSize = polyOneInt.length;
			}
			int resultInt[] = new int[maxSize];
			
			resultInt = o.subP(polyOneInt, polyTwoInt);
			String temp = new String();
			
			for(int i = maxSize-1; i >= 0;  i--){
				temp = temp + " " + Integer.toString(resultInt[i]) +  "X^" + i + " ";	
				if(i > 0){
					temp = temp + " +";
				}
			}
			resultField.setText(temp);
		}else{
			resultField.setText("ERROR! A valid polynom must be submitted!");
		}
			
		}
	});
	
	btnMultiply.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {						
			int size = polyOneInt.length + polyTwoInt.length;
			int resultInt[] = new int[size];
			
			if(marker==1){
			resultInt = o.mulP(polyOneInt, polyTwoInt);
			String temp = new String();
			for(int i = size-2; i >= 0; i--){ 
				temp = temp + " " + Integer.toString(resultInt[i]) +  "X^" + i + " ";
				if(i > 0){
					temp = temp + " +";
				}
			}
			resultField.setText(temp);
		
			}else{
				resultField.setText("ERROR! A valid polynom must be submitted!");
			}
		}
	});
	
	btnDerivate.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			int size = polyOneInt.length - 1;
			int resultInt[] = new int[size];
			if(marker==1){
			resultInt = o.derP(polyOneInt);
			String temp = new String();
			for(int i = size-1; i >=0; i--){			
				temp = temp + " " + Integer.toString(resultInt[i]) +  "X^" + i + " ";
				if(i > 0){
					temp = temp + " +";
				}
			}
			resultField.setText(temp);
			}else{
				resultField.setText("ERROR! A valid polynom must be submitted!");
			}
		}
	});
	
	btnIntegrate.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			int size = polyOneInt.length + 1;
			double[] tempPoly=new double[100];
			double resultInt[] = new double[size];
			
			for(int j = 0;j<polyOneInt.length;j++){
				tempPoly[j]=polyOneInt[j];
			}
			
			if(marker==1){
			resultInt = o.intgP(tempPoly);
			String temp = new String();
			for(int i = size-1; i >= 1; i--){
				temp = temp + " " + Double.toString(resultInt[i]) +  "X^" + i + " ";
				if(resultInt[i] >= 0){
					temp = temp + " +";
				}
			}
			temp = temp + " Constant";
			resultField.setText(temp);
			}else{
				resultField.setText("ERROR! A valid polynom must be submitted!");
			}
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

	


