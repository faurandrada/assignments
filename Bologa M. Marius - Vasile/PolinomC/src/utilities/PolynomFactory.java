package utilities;

import models.Coefficient;
import models.IntegerCoefficient;
import models.Polynomial;
import view.View;

public class PolynomFactory {
	public Polynomial createPolynomial(View view) {
		Polynomial a = new Polynomial(0);
		Coefficient[] coeff;
		String polynom = view.getCoef();
		String[] temp = polynom.split(" ");
		coeff = new Coefficient[temp.length];
		for (int i = 0; i < temp.length; i++) {
			coeff[temp.length - 1 - i] = new IntegerCoefficient(Integer.parseInt(temp[i]));
		}
		a = new Polynomial(coeff, coeff.length - 1);
		return a;
	}

}
