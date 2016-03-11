package pt.polynomials.controllers;
/**
 * performs the divizion of 2 polynomials
 * has the polynomial rest as an attribute
 * @author Chiti
 */
import java.util.Iterator;

import pt.polynomials.models.AbstractMonom;
import pt.polynomials.models.DoubleMonom;
import pt.polynomials.models.Polynomial;

public class PolynomialDivide {
	public Polynomial<Double> rest = new Polynomial<Double>();
/**
 * true if the monom "monom" exists in polynom p
 * @param monom
 * @param p
 * @return
 */
	public boolean existsMonom(AbstractMonom<Double> monom, Polynomial<Double> p) {
		for (AbstractMonom<Double> m : p.getPolynomial())
			if (m.getDegree() == monom.getDegree())
				return true;
		return false;
	}
/**
 * gets rid of the terms with 0 coefficient
 * @param p
 */
	public void cleanPolynomial(Polynomial<Double> p) {
		@SuppressWarnings("rawtypes")
		Iterator iterator = p.getPolynomial().iterator();
		while (iterator.hasNext()) {
			DoubleMonom m = (DoubleMonom) iterator.next();
			if (m.getCoefficient() == 0)
				iterator.remove();
		}
	}
/**
 * substracts double coefficent polynoms
 * @param p1
 * @param p2
 * @return p1-p2
 */
	public Polynomial<Double> substract(Polynomial<Double> p1, Polynomial<Double> p2) {
		Polynomial<Double> resultI = new Polynomial<Double>();
		for (AbstractMonom<Double> m1 : p1.getPolynomial()) {
			for (AbstractMonom<Double> m2 : p2.getPolynomial())
				if (m1.getDegree() == m2.getDegree()) {
					resultI.addMonom(new DoubleMonom(m1.getDegree(), m1.getCoefficient() - m2.getCoefficient()));
					break;
				}
			if (!existsMonom(m1, resultI))
				resultI.addMonom(new DoubleMonom(m1.getDegree(), m1.getCoefficient()));
		}
		for (AbstractMonom<Double> m2 : p2.getPolynomial())
			if (!existsMonom(m2, resultI))
				resultI.addMonom(new DoubleMonom(m2.getDegree(), -m2.getCoefficient()));
		cleanPolynomial(resultI);
		resultI.sortPolynomial();
		return resultI;
	}
/**
 * multiplays a polynom with a single monom
 * @param p1
 * @param monom
 * @return p1*monom
 */
	public Polynomial<Double> multiplyMonom(Polynomial<Double> p1, DoubleMonom monom) {

		Polynomial<Double> resultI = new Polynomial<Double>();
		for (AbstractMonom<Double> m1 : p1.getPolynomial()) {
			DoubleMonom m = new DoubleMonom(m1.getDegree() + monom.getDegree(),
					m1.getCoefficient() * monom.getCoefficient());
			resultI.addMonom(m);
		}
		cleanPolynomial(resultI);
		resultI.sortPolynomial();
		return resultI;
	}
/**
 * the divizion of 2 polynomials
 * @param p1
 * @param p2
 * @return p1/p2
 */
	public Polynomial<Double> divide(Polynomial<Integer> p1, Polynomial<Integer> p2) {
		Polynomial<Double> middle = new Polynomial<Double>();
		Polynomial<Double> bottom = new Polynomial<Double>();
		Polynomial<Double> divizor = new Polynomial<Double>();
		DoubleMonom lastDivizorMonom = new DoubleMonom(0, 1);
		DoubleMonom lastMiddleMonom = new DoubleMonom(0, 1);
		;
		for (AbstractMonom<Integer> m : p1.getPolynomial()) {
			middle.addMonom(new DoubleMonom(m.getDegree(), m.getCoefficient()));
		}
		for (AbstractMonom<Integer> m : p2.getPolynomial()) {
			divizor.addMonom(new DoubleMonom(m.getDegree(), m.getCoefficient()));
			lastDivizorMonom = new DoubleMonom(m.getDegree(), m.getCoefficient());
		}
		int n = p1.getDegree() - p2.getDegree();
		Polynomial<Double> result = new Polynomial<Double>();
		rest= new Polynomial<Double>();
		do {
			for (AbstractMonom<Double> m : middle.getPolynomial()) {
				lastMiddleMonom = (DoubleMonom) m;
			}
			if (lastMiddleMonom.getDegree() >= lastDivizorMonom.getDegree()) {
				DoubleMonom monom = new DoubleMonom(n,
						lastMiddleMonom.getCoefficient() / lastDivizorMonom.getCoefficient());
				result.addMonom(monom);
				bottom = multiplyMonom(divizor, monom);
				middle = substract(middle, bottom);
			}
			n--;
		} while (middle.getPolynomial().size()!=0 && n >= 0);
		result.sortPolynomial();
		rest = middle;
		rest.sortPolynomial();
		return result;
	}
}
