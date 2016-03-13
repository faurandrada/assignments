package models;

import java.text.DecimalFormat;

public class Converter {

	public Polynomial<IntCoeffTerm> toPolynomial(String input) {
		Polynomial<IntCoeffTerm> resultPoly = new Polynomial<IntCoeffTerm>();
		int i;
		for (i = 0; i < input.length(); i++)
			if (isLetter(input.charAt(i))) {
				int coeff = formLeftNumber(i, input);
				int power = formRightNumber(i, input);
				resultPoly.addTermPoly(new IntCoeffTerm(power, coeff));
			}
		
		i--;
		if (i < input.length()&&(i>0)) {

			if (isDigit(input.charAt(i))) {
				int coeff = formLeftNumber(i, input);
				if (coeff != 0) {
					resultPoly.addTermPoly(new IntCoeffTerm(0, coeff));
				}
			}

		}
		return resultPoly;
	}

	/*
	 * public boolean thereAreMoreTerms(int i, String input) { boolean result =
	 * false; while (i < input.length()) { if (isLetter(input.charAt(i))) return
	 * true; i++; } return result; }
	 */
	public boolean isDigit(char a) {
		if ((a <= '9') && (a >= '0'))
			return true;
		else
			return false;
	}

	public boolean isLetter(char a) {
		if ((a < 'z') && (a > 'a') || (a < 'Z') && (a > 'A'))
			return true;
		else
			return false;

	}

	public int formLeftNumber(int i, String input) {
		int nr = 0;
		int mul = 1;
		if(i==0)
			return 1;
		if ((i > 0)&&(isLetter(input.charAt(i)))) {
			if (isSign(input.charAt(i - 1)))
			{
				if(input.charAt(i-1)=='+')
					return 1;
				if(input.charAt(i-1)=='-')
					return -1;
			}
		}
		while ((isDigit(input.charAt(i)) == false) && (i > 0))
			i--;
		while (isDigit(input.charAt(i))) {
			nr = nr + mul * Character.getNumericValue(input.charAt(i));
			mul = mul * 10;
			i--;
			if (i < 0)
				break;
		}
		if (i > 0) {
			if (isSign(input.charAt(i)))
				if (input.charAt(i) == '-')
					nr = (-1) * nr;

			if (input.charAt(i) == '^')
				nr = 0;
		}
		return nr;
	}

	public int formRightNumber(int i, String input) {
		int nr = 0;
		i++;
		if (i < input.length()) {
			if (isSign(input.charAt(i)))
				return 1;
			else {
				i++;

				if (i < input.length() - 1) {
					while (isDigit(input.charAt(i))) {
						nr = nr * 10 + Character.getNumericValue(input.charAt(i));
						i++;
						if (i == input.length() - 1)
							break;
					}
				}

			}

		}
		return nr;

	}

	public boolean isSign(char a) {
		if ((a == '+') || (a == '-'))
			return true;
		else
			return false;
	}
	
public int getDegreeInt(Polynomial <IntCoeffTerm> polynomial){
		
		int degree = 0;
		for(IntCoeffTerm term: polynomial.getTerms()){
			if(term.getPower()>degree)
				degree = term.getPower();
		}
		return degree;
	}

public int getDegreeFloat(Polynomial <RealCoeffTerm> polynomial){
	
	int degree = 0;
	for(RealCoeffTerm term: polynomial.getTerms()){
		if(term.getPower()>degree)
			degree = term.getPower();
	}
	return degree;
}

public int getIntCoeff(Polynomial <IntCoeffTerm> polynomial, int pow){
	for(IntCoeffTerm term: polynomial.getTerms()){
		if(term.getPower()==pow)
			return term.getCoeff();
	}
	return 0;
}

public float getFloatCoeff(Polynomial <RealCoeffTerm> polynomial, int pow){
	for(RealCoeffTerm term: polynomial.getTerms()){
		if(term.getPower()==pow)
			return term.getCoeff();
	}
	return 0;
}
	public String toStringInt(Polynomial <IntCoeffTerm> p) {
		
		
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		String string;
		String string0 = "";
		if (getDegreeInt(p) == 0)
			return "" + decimalFormat.format(getIntCoeff(p,0));
		if (getDegreeInt(p) == 1) {
			if (getIntCoeff(p,0) > 0)
				string0 = "+" + decimalFormat.format(getIntCoeff(p,0));
			else if (getIntCoeff(p,0) < 0)
				string0 = "-" + decimalFormat.format((-1) * getIntCoeff(p,0));
			if ((getIntCoeff(p,1) == 1) || ((getIntCoeff(p,1) == -1)))
				return "x" + string0;
			else if ((getIntCoeff(p,1) == -1))
				return "-x" + string0;
			else
				return decimalFormat.format(getIntCoeff(p,1)) + "x" + string0;
		}
		
		if (getIntCoeff(p,getDegreeInt(p)) == 1)
			string = "x^" + getDegreeInt(p);
		else if (getIntCoeff(p,getDegreeInt(p)) == -1)
			string = "-x^" + getDegreeInt(p);
		else
			string = decimalFormat.format(getIntCoeff(p,getDegreeInt(p))) + "x^" + getDegreeInt(p);

		for (int i = getDegreeInt(p) - 1; i >= 0; i--) {
			if (getIntCoeff(p,i) == 0)
				continue;
			if (getIntCoeff(p,i) == 1)
			{
				if(i==0)
					string = string + "+1";
				else
					string = string + "+";
			}
			else if (getIntCoeff(p,i) == -1)
			{
				if(i==0)
					string = string + "-1";
				else
					string = string + "-";
			}
			else if (getIntCoeff(p,i) > 0)
				string = string + "+" + (decimalFormat.format(getIntCoeff(p,i)));
			else if (getIntCoeff(p,i) < 0)
				string = string + "-" + (decimalFormat.format((-1) * getIntCoeff(p,i)));
			if (i == 1)
				string = string + "x";
			else if (i > 1)
				string = string + "x^" + i;
		}
		return string;
	}
	
public String toStringFloat(Polynomial <RealCoeffTerm> p) {
		
		
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		String string;
		String string0 = "";
		if (getDegreeFloat(p) == 0)
			return "" + decimalFormat.format(getFloatCoeff(p,0));
		if (getDegreeFloat(p) == 1) {
			if (getFloatCoeff(p,0) > 0)
				string0 = "+" + decimalFormat.format(getFloatCoeff(p,0));
			else if ((getFloatCoeff(p,0)) < 0)
				string0 = "-" + decimalFormat.format((-1) * (getFloatCoeff(p,0)));
			if (((getFloatCoeff(p,1)) == 1) || (getFloatCoeff(p,1) == -1))
				return "x" + string0;
			else if (getFloatCoeff(p,1) == -1)
				return "-x" + string0;
			else
				return decimalFormat.format(getFloatCoeff(p,1)) + "x" + string0;
		}
		
		if (getFloatCoeff(p,getDegreeFloat(p)) == 1)
			string = "x^" + getDegreeFloat(p);
		else if (getFloatCoeff(p,getDegreeFloat(p)) == -1)
			string = "-x^" + getDegreeFloat(p);
		else
			string = decimalFormat.format(getFloatCoeff(p,getDegreeFloat(p))) + "x^" + getDegreeFloat(p);

		for (int i = getDegreeFloat(p) - 1; i >= 0; i--) {
			if (getFloatCoeff(p,i) == 0)
				continue;
			if (getFloatCoeff(p,i) == 1)
			{
				if(i==0)
					string = string + "+1";
				else
					string = string + "+";
			}
			else if (getFloatCoeff(p,i) == -1)
			{
				if(i==0)
					string = string + "-1";
				else
					string = string + "-";
			}
			else if (getFloatCoeff(p,i) > 0)
				string = string + "+" + (decimalFormat.format(getFloatCoeff(p,i)));
			else if (getFloatCoeff(p,i) < 0)
				string = string + "-" + (decimalFormat.format((-1) * getFloatCoeff(p,i)));
			if (i == 1)
				string = string + "x";
			else if (i > 1)
				string = string + "x^" + i;
		}
		return string;
	}
}
