package Control;

import java.util.ArrayList;
import java.util.List;

import Model.Polynomial;

/*
 * Class for the arithmetical operations. Each method takes one ore two input polynomials as parameters
 * and return one or two(in the case of division) polynomials 
 */
public class Operations {

	private int resultDegree;
	private double[] resultCoefficients;

	public Polynomial addition(Polynomial pol1, Polynomial pol2) {

		int maxDegree = Math.max(pol1.getDegree(), pol2.getDegree());
		int minDegree = Math.min(pol1.getDegree(), pol2.getDegree());
		resultDegree = maxDegree;
		resultCoefficients = new double[resultDegree + 1];

		for (int i = 0; i <= minDegree; i++) {
			resultCoefficients[i] = (pol1.getDoubleCoefficients()[i] + pol2.getDoubleCoefficients()[i]);
		}

		if (pol1.getDegree() > pol2.getDegree()) {
			for (int i = minDegree + 1; i <= resultDegree; i++) {
				resultCoefficients[i] = pol1.getDoubleCoefficients()[i];
			}
		} else if (pol1.getDegree() < pol2.getDegree()) {
			for (int i = minDegree + 1; i <= resultDegree; i++) {
				resultCoefficients[i] = pol2.getDoubleCoefficients()[i];
			}
		}
		return (new Polynomial(resultDegree, resultCoefficients));
	}

	public Polynomial subtraction(Polynomial pol1, Polynomial pol2) {

		int maxDegree = Math.max(pol1.getDegree(), pol2.getDegree());
		int minDegree = Math.min(pol1.getDegree(), pol2.getDegree());
		resultDegree = maxDegree;
		resultCoefficients = new double[resultDegree + 1];

		for (int i = 0; i <= minDegree; i++) {
			resultCoefficients[i] = (pol1.getDoubleCoefficients()[i] - pol2.getDoubleCoefficients()[i]);
		}

		if (pol1.getDegree() > pol2.getDegree()) {
			for (int i = minDegree + 1; i <= resultDegree; i++) {
				resultCoefficients[i] = pol1.getDoubleCoefficients()[i];
			}
		} else if (pol1.getDegree() < pol2.getDegree()) {
			for (int i = minDegree + 1; i <= resultDegree; i++) {
				resultCoefficients[i] = -pol2.getDoubleCoefficients()[i];
			}
		}
		return (new Polynomial(resultDegree, resultCoefficients));
	}

	/// in order to reduce the volume of code, we use for subtraction the addition of one polynomial 
	/// with the other one but with negative coefficients//////////////////////////////////////////////

	//	public Polynomial subtraction(Polynomial pol1, Polynomial pol2) {
	//		int[] negativeUnit ={-1};
	//		Polynomial negativePol = new Polynomial(1,negativeUnit);
	//		return(addition(pol1,multiplication(pol2,negativePol)));
	//	}

	//////// but due to the use of subtraction in the division process, it will cause an infinite loop
	///so we stick to the initial approach
	
	public Polynomial multiplication(Polynomial pol1, Polynomial pol2) {
		resultDegree = pol1.getDegree() + pol2.getDegree();
		resultCoefficients = new double[resultDegree + 1];
		for (int i = 0; i <= pol1.getDegree(); i++) {
			for (int j = 0; j <= pol2.getDegree(); j++) {
				resultCoefficients[i + j] += pol1.getDoubleCoefficients()[i] * pol2.getDoubleCoefficients()[j];
			}
		}
		return (new Polynomial(resultDegree, resultCoefficients));
	}

	/// this method return a list containing the quotient polynomial and the rest polynomial////////
	public List<Polynomial> division(Polynomial pol1, Polynomial pol2) {

		double[] quotientCoefficients = new double[2];
		quotientCoefficients[0] = 0;
		quotientCoefficients[1] = 0;

		Polynomial quotient = new Polynomial(1, quotientCoefficients);
		Polynomial rest = pol1;

		double highestCoefficient = pol2.getDoubleCoefficients()[pol2.getDegree()];
		while (rest.getDegree() >= pol2.getDegree()) {
			
			// computation of each term from the quotient////////////////////////////////////
			int quotientTermDegree = rest.getDegree() - pol2.getDegree();
			double[] quotientTermCoeffs = new double[quotientTermDegree + 1];
			quotientTermCoeffs[quotientTermDegree] = (rest.getDoubleCoefficients()[rest.getDegree()])
					/ (highestCoefficient);
			Polynomial quotientTerm = new Polynomial(quotientTermDegree, quotientTermCoeffs);

			// computation of the quotient and the rest//////////////////////////////////////
			quotient = addition(quotient, quotientTerm);
			rest = subtraction(rest, multiplication(quotientTerm, pol2));
			rest.setDegree(rest.getDegree() - 1);
		}

		List<Polynomial> list = new ArrayList<Polynomial>();
		list.add(quotient);
		list.add(rest);
		return list;
	}


	public Polynomial differentiation(Polynomial pol) {
		int diffDegree = pol.getDegree() - 1;
		int[] diffCoefficients = new int[diffDegree + 1];
		for (int i = 0; i <= diffDegree; i++) {
			diffCoefficients[i] = (int) (pol.getDoubleCoefficients()[i + 1] * (i + 1));
		}

		return (new Polynomial(diffDegree, diffCoefficients));
	}

	public Polynomial integration(Polynomial pol) {

		int integralDegree = pol.getDegree() + 1;
		double[] integralCoefficients = new double[integralDegree + 1];
		for (int i = 0; i <= pol.getDegree(); i++) {
			integralCoefficients[i + 1] = (pol.getDoubleCoefficients()[i] / (i + 1));
		}

		return (new Polynomial(integralDegree, integralCoefficients));
	}

}
