package polynom;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gui.GUI;
import mathOperations.Derive;
import mathOperations.Difference;
import mathOperations.Division;
import mathOperations.IDOp;
import mathOperations.Integral;
import mathOperations.Multiplication;
import mathOperations.Sum;

public class Polynom {
	public ArrayList<Term> firstPoly = new ArrayList<Term>();
	public ArrayList<Term> secondPoly = new ArrayList<Term>();
	public ArrayList<Term> resultPoly = new ArrayList<Term>();

	GUI gui;
	public String res;

	public Polynom(String firstString, String secondString, IDOp opType) {
		gui = new GUI();
		parseString(firstString, firstPoly);
		parseString(secondString, secondPoly);
		biOp(opType);
		unparseString(resultPoly);
		checkResult(res);
		gui.setResult(res);
	}

	public Polynom(String string, IDOp opType) {
		gui = new GUI();
		parseString(string, firstPoly);
		parseString(string, secondPoly);
		oneOp(opType);
		unparseString(resultPoly);
		checkResult(res);
		gui.setResult(res);
	}

	private void checkResult(String res2) {
		if(res.isEmpty()){
			StringBuilder buffer = new StringBuilder();
			buffer.append("INCORRECT INPUT");
			res = buffer.toString();
		}
	}

	private void oneOp(IDOp opType) {
		// TODO Auto-generated method stub
		switch (opType) {
		case Integral1:
			new Integral(firstPoly, resultPoly);
			break;
		case Integral2:
			new Integral(secondPoly, resultPoly);
			break;
		case Derive1:
			new Derive(firstPoly, resultPoly);
			break;
		case Derive2:
			new Derive(secondPoly, resultPoly);
			break;
		default:
			break;
		}
	}

	private void biOp(IDOp opType) {
		// TODO Auto-generated method stub
		switch (opType) {
		case Sum:
			new Sum(firstPoly, secondPoly, resultPoly);
			break;
		case Difference:
			new Difference(firstPoly, secondPoly, resultPoly);
			break;
		case Multiply:
			new Multiplication(firstPoly, secondPoly, resultPoly);
			break;
		case Division:
			new Division(firstPoly, secondPoly, resultPoly);
			break;
		default:
			break;
		}
	}

	private void unparseString(ArrayList<Term> result) {
		// TODO Auto-generated method stub
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).coef < 0) {
				if (result.get(i).coef % 1 == 0) {
					double tempd = result.get(i).coef;
					int temp = (int) tempd;
					buffer.append(String.valueOf(temp));
				} else {
					buffer.append(String.valueOf(new DecimalFormat("####.###").format(result.get(i).coef)));
				}
			} else {
				buffer.append("+");
				if (result.get(i).coef % 1 == 0) {
					double tempd = result.get(i).coef;
					int temp = (int) tempd;
					buffer.append(String.valueOf(temp));
				} else {
					buffer.append(String.valueOf(new DecimalFormat("####.###").format(result.get(i).coef)));
				}
			}
			buffer.append("x^");
			buffer.append(String.valueOf(result.get(i).power));
		}
		res = buffer.toString();
	}

	private void parseString(String str, ArrayList<Term> poly) {
		// TODO Auto-generated method stub
		String[] output = str.split("(?=[-+])");
		for (int i = 0; i < output.length; i++) {
			// createTerms(output[i]);
			String theRegex = "([+-]?[0-9]*)x(\\^([0-9]+))?|([+-]?[0-9]+)";
			regexChecker(theRegex, output[i], poly);
		}
	}

	private void regexChecker(String theRegex, String text, ArrayList<Term> poly) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile(theRegex);
		Matcher m = p.matcher(text);
		while (m.find()) {
			Term term = new Term();
			if (m.group(1) == null) {
				term.setCoef(1);
			} else {
				term.setCoef(Integer.parseInt(m.group(1)));
			}
			if (m.group(3) == null) {
				term.setPower(Integer.parseInt(text));
			} else {
				term.setPower(Integer.parseInt(m.group(3)));
			}

			poly.add(term);

		}
	}
}
