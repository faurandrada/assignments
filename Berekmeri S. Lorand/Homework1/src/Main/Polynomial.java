package Main;

import java.util.*;

import Monomials.Monom;
import Monomials.MonomFloat;

public class Polynomial {
	protected ArrayList<Monom> polynomial;

	public Polynomial() {
		polynomial = new ArrayList<>();

	}

	protected Polynomial getDerivataPolynomial() {
		Polynomial polynomial1 = new Polynomial();
		for (int i = 0; i < polynomial.size(); i++) {
			polynomial1.polynomial.add(polynomial.get(i).getDerivataMonom());
		}

		return polynomial1;

	}

	protected Polynomial getIntegralaPolynomial() {
		Polynomial polynomial1 = new Polynomial();
		for (int i = 0; i < polynomial.size(); i++) {
			polynomial1.polynomial.add(polynomial.get(i).getIntegralaMonom());
		}

		return polynomial1;
	}

	protected Polynomial Addition(Polynomial polynomial1) {
		Polynomial Polynomialresult = new Polynomial();
		for (int i = 0; i < polynomial.size(); i++)
			Polynomialresult.polynomial.add(polynomial.get(i).getMonom());
		for (int i = 0; i < polynomial1.polynomial.size(); i++)
			Polynomialresult.polynomial.add(polynomial1.polynomial.get(i).getMonom());

		for (int i = 0; i < Polynomialresult.polynomial.size(); i++)
			for (int j = i + 1; j < Polynomialresult.polynomial.size(); j++)
				if (Polynomialresult.polynomial.get(i).getdegree() == Polynomialresult.polynomial.get(j).getdegree()) {
					Polynomialresult.polynomial.get(i).sumMonom(Polynomialresult.polynomial.get(j));
					Polynomialresult.polynomial.remove(j);
				}
		Collections.sort(Polynomialresult.polynomial);

		return Polynomialresult;

	}

	protected Polynomial Subtraction(Polynomial polynomial1) {
		Polynomial Polynomialresult = new Polynomial();
		for (int i = 0; i < polynomial.size(); i++)
			Polynomialresult.polynomial.add(polynomial.get(i).getMonom());
		for (int i = 0; i < polynomial1.polynomial.size(); i++)
			Polynomialresult.polynomial.add(polynomial1.polynomial.get(i).getcoefficientMinus());
		for (int i = 0; i < Polynomialresult.polynomial.size(); i++)
			for (int j = i + 1; j < Polynomialresult.polynomial.size(); j++)
				if (Polynomialresult.polynomial.get(i).getdegree() == Polynomialresult.polynomial.get(j).getdegree()) {
					Polynomialresult.polynomial.get(i).sumMonom(Polynomialresult.polynomial.get(j));
					Polynomialresult.polynomial.remove(j);
				}

		Collections.sort(Polynomialresult.polynomial);

		return Polynomialresult;

	}

	protected Polynomial Multiplication(Polynomial polynomial1) {
		Polynomial Polynomialresult = new Polynomial();
		Polynomial PolynomialIntermediar = new Polynomial();

		for (int i = 0; i < polynomial.size(); i++)
			PolynomialIntermediar.polynomial.add(polynomial.get(i).getMonom());
		for (int i = 0; i < polynomial.size(); i++)
			for (int j = 0; j < polynomial1.polynomial.size(); j++)
				Polynomialresult.polynomial.add(
						PolynomialIntermediar.polynomial.get(i).multiplicationMonom(polynomial1.polynomial.get(j)));

		Polynomialresult.SumEqualDegrees();

		Polynomialresult = Polynomialresult.RemoveZeroes();
		return Polynomialresult;
	}

	protected Polynomial IntegerToFloat() // changes the integer polynomial to
											// float
	{
		Polynomial pol = new Polynomial();
		for (int i = 0; i < polynomial.size(); i++)
			pol.polynomial.add(polynomial.get(i).getMonomReal());

		return pol;

	}

	protected Polynomial[] Division(Polynomial polynomial) {

		Polynomial q = new Polynomial();
		Polynomial rezz[] = new Polynomial[2];
		Polynomial r = IntegerToFloat();
		MonomFloat t;
		Polynomial Polynomial2 = polynomial.IntegerToFloat();

		if (!Polynomial2.polynomial.isEmpty())

			while (!r.polynomial.isEmpty()
					&& r.polynomial.get(0).getdegree() >= Polynomial2.polynomial.get(0).getdegree()) 
					//we compare the degrees, for avoiding the division with a polynomial with higher degree 
																										
			{
				t = r.polynomial.get(0).divisionMonom(Polynomial2.polynomial.get(0));//
				q.polynomial.add(t);
				q.SumEqualDegrees();
				Polynomial result = new Polynomial();
				for (int i = 0; i < Polynomial2.polynomial.size(); i++)
					result.polynomial.add(t.multiplicationMonom(Polynomial2.polynomial.get(i)));
				result.SumEqualDegrees();
				r = r.Subtraction(result);
				r = r.RemoveZeroes();

			}
		q.SumEqualDegrees();

		rezz[0] = q; // quotient
		rezz[1] = r; //remainder
		return rezz; //rezz = array which contains the remainder and the result  
	}

	protected Polynomial RemoveZeroes() {
		Polynomial rez = new Polynomial();

		for (int i = 0; i < polynomial.size(); i++) {
			if (polynomial.get(i).getcoefficient().intValue() != 0)
				rez.polynomial.add(polynomial.get(i).getMonom());
			else if (polynomial.get(i).getcoefficient().floatValue() != 0.0)
				rez.polynomial.add(polynomial.get(i).getMonom());
		}
		return rez;
	}

	protected void SumEqualDegrees() {
		for (int i = 0; i < polynomial.size(); i++)
			for (int j = i + 1; j < polynomial.size(); j++)
				if (polynomial.get(i).getdegree() == polynomial.get(j).getdegree()) {
					polynomial.get(i).sumMonom(polynomial.get(j));
					polynomial.remove(j);
				}
		Collections.sort(polynomial);
	}

	protected String toStringPolynomial() {
		String rez = "";
		for (int i = 0; i < polynomial.size(); i++)
			rez += polynomial.get(i).toString();
		if (rez.isEmpty())
			rez = "0";
		return rez;
	}
}