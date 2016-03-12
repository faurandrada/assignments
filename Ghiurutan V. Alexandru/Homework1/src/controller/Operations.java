package controller;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import models.DoublePolynomial;

public class Operations {
	private DecimalFormat decimalFormat;
	private static final double EPSILON = 0.00001;
	private static final int INFINITY = Integer.MAX_VALUE;
	private static final int MINUS_INFINITY = Integer.MIN_VALUE;
	private static final int ITERATIONS = 888;

	public Operations() {
		decimalFormat = new DecimalFormat();
	}

	public DoublePolynomial add(DoublePolynomial p1, DoublePolynomial p2) {
		double[] pol1 = p1.getDoublePolynomial();
		double[] pol2 = p2.getDoublePolynomial();
		int degP1 = p1.getDegree();
		int degP2 = p2.getDegree();
		int degSum = (degP1 > degP2) ? degP1 : degP2;
		double[] sum = new double[degSum + 1];
		for (int i = 0; i <= degP1; i++) {
			sum[i] += pol1[i];
		}
		for (int i = 0; i <= degP2; i++) {
			sum[i] += pol2[i];
		}
		return new DoublePolynomial(sum);
	}

	public DoublePolynomial subtract(DoublePolynomial p1, DoublePolynomial p2) {
		double[] pol1 = p1.getDoublePolynomial();
		double[] pol2 = p2.getDoublePolynomial();
		int degP1 = p1.getDegree();
		int degP2 = p2.getDegree();
		int degDif = (degP1 > degP2) ? degP1 : degP2;
		double[] dif = new double[degDif + 1];
		for (int i = 0; i <= degP1; i++) {
			dif[i] = pol1[i];
		}
		for (int i = 0; i <= degP2; i++) {
			dif[i] -= pol2[i];
		}
		return new DoublePolynomial(dif);
	}

	public DoublePolynomial multiply(DoublePolynomial p1, DoublePolynomial p2) {
		double[] pol1 = p1.getDoublePolynomial();
		double[] pol2 = p2.getDoublePolynomial();
		int degP1 = p1.getDegree();
		int degP2 = p2.getDegree();
		double[] pol = new double[degP1 + degP2 + 1];
		for (int i = 0; i <= degP1; i++) {
			for (int j = 0; j <= degP2; j++) {
				pol[i + j] += pol1[i] * pol2[j];
			}
		}
		return new DoublePolynomial(pol);
	}

	public DoublePolynomial[] division(DoublePolynomial p1, DoublePolynomial p2) {

		int degP1 = p1.getDegree();
		int degP2 = p2.getDegree();
		if (degP1 < degP2 || degP2 == 0) {
			JOptionPane.showMessageDialog(null, "Error", "Illegal Polynomials entered", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		int degQ = degP1 - degP2;
		int i = degQ, j = degP1;
		double div;
		double[] quotient = new double[degQ + 1];
		double[] dividend = new double[degP1 + 1];
		dividend = ((DoublePolynomial) p1.clone()).getDoublePolynomial();
		double[] pol2 = ((DoublePolynomial) p2.clone()).getDoublePolynomial();
		do {
			div = dividend[j--] / pol2[degP2];
			double[] auxPol = new double[i + 1];
			auxPol[i] = div;
			quotient[i--] = div;
			dividend = subtract(new DoublePolynomial(dividend),
					multiply(new DoublePolynomial(pol2), new DoublePolynomial(auxPol))).getDoublePolynomial();
		} while (j >= degP2);
		DoublePolynomial[] result = new DoublePolynomial[] { new DoublePolynomial(quotient),
				new DoublePolynomial(dividend) };
		return result;
	}

	public DoublePolynomial derivation(DoublePolynomial p) {
		double[] derivate = new double[p.getDegree()];
		double[] pol = ((DoublePolynomial) p.clone()).getDoublePolynomial();
		for (int i = 0; i < derivate.length; i++) {
			derivate[i] = pol[i + 1] * (i + 1);
		}
		return new DoublePolynomial(derivate);
	}

	public DoublePolynomial integration(DoublePolynomial p) {
		double pol[] = ((DoublePolynomial) p.clone()).getDoublePolynomial();
		double[] integration = new double[pol.length + 1];
		for (int i = 1; i < integration.length; i++) {
			integration[i] = pol[i - 1] / i;
		}
		return new DoublePolynomial(integration);
	}

	public boolean areEqual(DoublePolynomial p1, DoublePolynomial p2) {
		int degP1 = p1.getDegree();
		int degP2 = p2.getDegree();
		double[] pol1 = ((DoublePolynomial) p1.clone()).getDoublePolynomial();
		double[] pol2 = ((DoublePolynomial) p2.clone()).getDoublePolynomial();
		if (degP1 != degP2) {
			return false;
		}
		for (int i = 0; i < pol1.length; i++) {
			if (pol1[i] != pol2[i]) {
				return false;
			}
		}
		return true;
	}

	public double evaluate(DoublePolynomial p, double x) {
		double[] pol = ((DoublePolynomial) p.clone()).getDoublePolynomial();
		double result = 0;
		for (int i = 0; i < pol.length; i++) {
			result += pol[i] * Math.pow(x, i);
		}
		return result;
	}

	public DoublePolynomial exponentiation(DoublePolynomial p, int n) {
		if (n == 0) {
			return new DoublePolynomial(1);
		} else if (n > 0) {
			double[] pol = ((DoublePolynomial) p.clone()).getDoublePolynomial();
			double[] result = new double[p.getDegree() * n + 1];
			System.arraycopy(pol, 0, result, 0, pol.length);
			while (n > 1) {
				result = multiply(new DoublePolynomial(result), p).getDoublePolynomial();
				n--;
			}
			return new DoublePolynomial(result);
		} else {
			return division(exponentiation(p, 0), exponentiation(p, -n))[0];
		}

	}

	// Optional
	private int[] findInterval(DoublePolynomial p) {
		int[] interval = new int[2];
		int n = 0;
		interval[0] = (int) (Math.random() * 100);
		interval[1] = (int) (Math.random() * 100) + interval[0];
		while (n < ITERATIONS) {
			if (evaluate(p, interval[0]) * evaluate(p, interval[1]) <= 0) {
				return interval;
			} else {
				interval[0]--;
				interval[1]++;
				n++;
			}
		}
		interval[0] = INFINITY;
		interval[1] = MINUS_INFINITY;
		return interval;
	}

	// finding root using the Bisection Method.
	private  Double findRootAlgorithm(DoublePolynomial p) {
		int[] interval = findInterval(p);
		double a = interval[0];
		double b = interval[1];
		double m;
		while (Math.abs(b - a) > EPSILON) {
			m = (a + b) / 2;
			if (evaluate(p, m) == 0) {
				return m;
			} else {
				if (evaluate(p, a) * evaluate(p, m) <= 0) {
					b = m;
				} else {
					a = m;
				}
			}
		}
		m = (a + b) / 2;
		return m;
	}

	public String findRoot(DoublePolynomial p) {
		double rezult = findRootAlgorithm(p);
		if (rezult >2000000000 || rezult <-2000000000) {
			return "Polynomial doesn't have real roots.";
		} else {
			return decimalFormat.format(rezult);
		}
	}

	// Graph representation methods
	public double[] computeYCoordinatesForGraph(DoublePolynomial p, double[] xCoordinates) {
		double[] yCoordinates = new double[xCoordinates.length];
		for (int i = 0; i < xCoordinates.length; i++) {
			yCoordinates[i] = evaluate(p, xCoordinates[i]);
		}
		return yCoordinates;
	}
}
