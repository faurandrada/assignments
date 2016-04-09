package poly.IO;

import poly.model.Polynomial;
/**
 * 
 * @author Dia
 *
 * The public class ConsolePrinter is for displaying the polynomials in the console.
 * The main purpose for using such a class is for better tracing of the content of the polynomials.
 * The class describes a single method: printPolynomial.
 *
 */
public class ConsolePrinter {

	public void printPolynomial(Polynomial p) {
		for (int i = p.degree; i >= 0; i--) {
			if(!p.coeff[i].isZero())
			System.out.printf("%d x^%d   ", p.coeff[i].getCoeff(), i);
		}
		System.out.println();
	}
}