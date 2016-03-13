package controller;

import java.text.DecimalFormat;
/**
 * 
 * @author Ghiurutan
 *Helper class
 *
 */
public class Helper {

	public String toString(double[] coeffs, int degree) {
		DecimalFormat decimalFormat = new DecimalFormat();
		String rezult = "";
		for (int i = degree; i >= 0; i--) {
			if ((coeffs[i] == 0.) && (i > 0)) {
				if (coeffs[i - 1] > 0) {
					if(rezult.length()>0)
					{rezult += "+";}
				}
				continue;
			} else if (coeffs[i] != 0.) {
				if (i == 0) {
					rezult += decimalFormat.format(coeffs[i]);
				} else if (i == 1) {
					rezult += ((coeffs[i] == 1)? "" :(coeffs[i]==-1)? "-":decimalFormat.format(coeffs[i])) + "x"
							+ ((coeffs[i - 1] > 0) ?"+" : "");
				} else {
					rezult += ((coeffs[i] == 1) ?"" :(coeffs[i]==-1)? "-":decimalFormat.format(coeffs[i])) + "x^"
							+ i + ((coeffs[i - 1] > 0) ? "+" : "");
				}
			}
		}
		return rezult;
	}

	public double[] integerToDouble(int... coeffs) {
		double[] pol = new double[coeffs.length];
		for (int i = 0; i < coeffs.length; i++) {
			pol[i] = coeffs[i];
		}
		return pol;
	}

}
