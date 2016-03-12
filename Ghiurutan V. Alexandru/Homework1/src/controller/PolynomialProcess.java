package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Ghiurutan This class transforms the input from Gui into a coefficient
 *         array.
 */
public class PolynomialProcess {
	private int[] pol;

	public int [] getPolynomial(String polynomial) {
		getDegreeOfPolynomial(polynomial);
		polynomial = polynomial.replace(regexCheckerExponent("(.*?)(-?)(\\+?)x(\\^?)([0-9]*)", polynomial), "");
		regexCheckerConstant("(.*)", polynomial);
		return pol;
	}

	private void getDegreeOfPolynomial(String polynomial) {
		Pattern checkRegex = Pattern.compile("(.*?)(-?)(\\+?)x(\\^?)([0-9]*)");
		Matcher regexMatcher = checkRegex.matcher(polynomial);
		if (regexMatcher.find()) {
			if (!regexMatcher.group(4).equals("")) {
				pol = new int[Integer.parseInt(regexMatcher.group(5)) + 1];
			} else {
				pol = new int[2];
			}
		} else {
			pol = new int[1];
		}

	}

	private void regexCheckerConstant(String theRegex, String polynomial) {
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(polynomial);
		while (regexMatcher.find()) {
			if (regexMatcher.group(0).length() != 0) {
				pol[0] = Integer.parseInt(regexMatcher.group(1));
			}
		}
	}

	// Matching the elements with x
	private String regexCheckerExponent(String theRegex, String polynomial) {
		String rezult = "";
		int coeff = 0, power = 0;
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(polynomial);
		while (regexMatcher.find()) {
			if (regexMatcher.group(0).length() != 0) {
				rezult += regexMatcher.group(0);

				if (!regexMatcher.group(1).equals("")) {
					coeff = Integer.parseInt(regexMatcher.group(1));
				} else {
					if (!regexMatcher.group(2).equals("")) {
						coeff = -1;
					} else if (!regexMatcher.group(3).equals("")) {
						coeff = 1;
					} else {
						coeff = 1;
					}
				}
				if (!regexMatcher.group(4).equals("")) {
					power = Integer.parseInt(regexMatcher.group(5));
				} else {
					power = 1;
				}
			}
			pol[power] = coeff;
		}

		return rezult;
	}
}
