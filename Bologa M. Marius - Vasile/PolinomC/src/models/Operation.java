package models;

import utilities.Utilities;

public class Operation {

	public static Polynomial addition(final Polynomial a, final Polynomial b) {
		int degree = Utilities.maxDegree(a.getDegree(), b.getDegree());
		Polynomial c = new Polynomial(degree);
		int coeffC[] = new int[c.getDegree() + 1];
		for (int i = 0; i < a.getDegree() + 1; i++) {
			coeffC[i] += Utilities.getIntElement(a, i);
		}
		for (int i = 0; i < b.getDegree() + 1; i++) {
			coeffC[i] += Utilities.getIntElement(b, i);
		}
		c = new Polynomial(Utilities.transform(coeffC), coeffC.length - 1);
		System.out.println(coeffC);
		c.setDegree(c.getDegree());
		return c;
	}

	public static Polynomial subtract(final Polynomial a, final Polynomial b) {
		int degree = Utilities.maxDegree(a.getDegree(), b.getDegree());
		Polynomial c = new Polynomial(degree);
		int coeffC[] = new int[c.getDegree() + 1];
		for (int i = 0; i < a.getDegree() + 1; i++) {
			coeffC[i] += Utilities.getIntElement(a, i);
		}
		for (int i = 0; i < b.getDegree() + 1; i++) {
			coeffC[i] -= Utilities.getIntElement(b, i);
		}
		c = new Polynomial(Utilities.transform(coeffC), coeffC.length - 1);
		c.setDegree(c.getDegree());
		System.out.println(coeffC);
		return c;
	}

	public static Polynomial multiply(final Polynomial a, final Polynomial b) {
		int degree = a.getDegree() + b.getDegree();
		Polynomial c = new Polynomial(degree);
		int[] coeffC = new int[c.getDegree() + 1];
		for (int i = 0; i <= a.getDegree(); i++) {
			for (int j = 0; j <= b.getDegree(); j++) {
				coeffC[i + j] += (Utilities.getIntElement(a, i) * Utilities.getIntElement(b, j));
			}
		}
		c = new Polynomial(Utilities.transform(coeffC), coeffC.length - 1);
		c.setDegree(c.getDegree());
		System.out.println(coeffC);
		return c;

	}

	public static Polynomial multiplyByScalar(final Polynomial a, final int x) {
		Polynomial c = new Polynomial(a.getDegree());
		int coeffC[] = new int[a.getDegree() + 1];
		for (int i = 0; i <= a.getDegree(); i++) {
			coeffC[i] += ((Utilities.getIntElement(a, i)) * x);
		}
		c = new Polynomial(Utilities.transform(coeffC), coeffC.length - 1);
		c.setDegree(c.getDegree());
		return c;
	}

	public static int evaluate(final Polynomial a, final int x) {
		int value = 0;
		for (int i = a.getDegree(); i >= 0; i--) {
			value = value + (Utilities.getIntElement(a, i)) + (x * value);
		}
		return value;
	}

	public static Polynomial derivation(Polynomial a) {
		Polynomial c = new Polynomial(a.getDegree() - 1);
		int coeffC[] = new int[a.getDegree() + 1];
		for (int i = 1; i < a.getDegree() + 1; i++) {
			coeffC[i - 1] = ((Utilities.getIntElement(a, i)) * i);
		}
		if (a.getDegree() == 0) {
			c = new Polynomial(0);
		} else {
			c = new Polynomial(Utilities.transform(coeffC), coeffC.length - 2);
		}
		c.setDegree(c.getDegree());
		return c;
	}

	public static Polynomial integration(Polynomial a) {
		Polynomial c = new Polynomial(a.getDegree() + 1);
		double coeffC[] = new double[c.getDegree() + 1];
		for (int i = 0; i < a.getDegree() + 1; i++) {
			coeffC[i + 1] = Utilities.round((double) (Utilities.getIntElement(a, i) / (double) (i + 1)), 2);
		}
		Coefficient[] coeff = new RealCoefficient[coeffC.length];
		for (int i = 0; i < coeffC.length; i++) {
			if (i == 0) {
				coeff[0] = new RealCoefficient(0);
			} else {
				coeff[i] = new RealCoefficient(coeffC[i]);
			}
		}
		c = new Polynomial(coeff, coeff.length - 1);
		c.setDegree(c.getDegree());
		return c;
	}
}
