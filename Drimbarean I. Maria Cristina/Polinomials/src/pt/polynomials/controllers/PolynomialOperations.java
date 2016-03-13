package pt.polynomials.controllers;

/**processing the polynomials: the implemented operations
 * @author Chiti
 */
import java.util.Iterator;

import pt.polynomials.models.*;

public class PolynomialOperations {
	/**
	 * 
	 * @param monom
	 * @param p
	 * @return if monom exists in polynom p
	 */
	public boolean existsMonom(AbstractMonom<Integer> monom, Polynomial<Integer> p) {
		for (AbstractMonom<Integer> m : p.getPolynomial())
			if (m.getDegree() == monom.getDegree())
				return true;
		return false;
	}

	/**
	 * removes the monoms that have 0 coefficient
	 * 
	 * @param p
	 */
	public void cleanPolynomial(Polynomial<Integer> p) {
		@SuppressWarnings("rawtypes")
		Iterator iterator = p.getPolynomial().iterator();
		while (iterator.hasNext()) {
			IntegerMonom m = (IntegerMonom) iterator.next();
			if (m.getCoefficient() == 0)
				iterator.remove();
		}
	}

	/**
	 * add polynoms
	 * @param p1
	 * @param p2
	 * @return p1+p2
	 */
	public Polynomial<Integer> add(Polynomial<Integer> p1, Polynomial<Integer> p2) {
		Polynomial<Integer> resultI = new Polynomial<Integer>();
		for (AbstractMonom<Integer> m1 : p1.getPolynomial()) {
			for (AbstractMonom<Integer> m2 : p2.getPolynomial())
				if (m1.getDegree() == m2.getDegree()) {
					resultI.addMonom(new IntegerMonom(m1.getDegree(), m1.getCoefficient() + m2.getCoefficient()));
					break;
				}
			if (!existsMonom(m1, resultI))
				resultI.addMonom(new IntegerMonom(m1.getDegree(), m1.getCoefficient()));
		}
		for (AbstractMonom<Integer> m2 : p2.getPolynomial())
			if (!existsMonom(m2, resultI))
				resultI.addMonom(new IntegerMonom(m2.getDegree(), m2.getCoefficient()));
		cleanPolynomial(resultI);
		resultI.sortPolynomial();
		return resultI;
	}

	/**
	 * substract polynoms
	 * @param p1
	 * @param p2
	 * @return p1-p2
	 */
	public Polynomial<Integer> substract(Polynomial<Integer> p1, Polynomial<Integer> p2) {
		Polynomial<Integer> resultI = new Polynomial<Integer>();
		for (AbstractMonom<Integer> m1 : p1.getPolynomial()) {
			for (AbstractMonom<Integer> m2 : p2.getPolynomial())
				if (m1.getDegree() == m2.getDegree()) {
					resultI.addMonom(new IntegerMonom(m1.getDegree(), m1.getCoefficient() - m2.getCoefficient()));
					break;
				}
			if (!existsMonom(m1, resultI))
				resultI.addMonom(new IntegerMonom(m1.getDegree(), m1.getCoefficient()));
		}
		for (AbstractMonom<Integer> m2 : p2.getPolynomial())
			if (!existsMonom(m2, resultI))
				resultI.addMonom(new IntegerMonom(m2.getDegree(), -m2.getCoefficient()));
		cleanPolynomial(resultI);
		resultI.sortPolynomial();
		return resultI;
	}
/**
 * multiply polynoms
 * @param p1
 * @param p2
 * @return p1*p2
 */
	public Polynomial<Integer> multiply(Polynomial<Integer> p1, Polynomial<Integer> p2) {

		Polynomial<Integer> resultI = new Polynomial<Integer>();
		for (AbstractMonom<Integer> m1 : p1.getPolynomial()) {
			Polynomial<Integer> result = new Polynomial<Integer>();
			for (AbstractMonom<Integer> m2 : p2.getPolynomial()) {
				IntegerMonom monom = new IntegerMonom(m1.getDegree() + m2.getDegree(),
						m1.getCoefficient() * m2.getCoefficient());
				result.addMonom(monom);
			}
			resultI = add(resultI, result);
		}
		cleanPolynomial(resultI);
		resultI.sortPolynomial();
		return resultI;
	}
	/**
	 * multiplys with scalar x
	 * @param p1
	 * @param x
	 * @return x*p1
	 */

	public Polynomial<Double> multiplyScalar(Polynomial<Integer> p1, double x) {
		Polynomial<Double> resultI = new Polynomial<Double>();
		for (AbstractMonom<Integer> m1 : p1.getPolynomial()) {
			resultI.addMonom(new DoubleMonom(m1.getDegree(), m1.getCoefficient() * x));
		}
		resultI.sortPolynomial();
		return resultI;
	}
/**
 * evaluates with x the polynom
 * @param p1
 * @param x
 * @return
 */
	public double evaluate(Polynomial<Integer> p1, double x) {
		int result = 0;
		for (AbstractMonom<Integer> m1 : p1.getPolynomial()) {
			result += m1.getCoefficient() * Math.pow(x, m1.getDegree());
		}
		return result;
	}
/**
 * integrates p1
 * @param p1
 * @return 
 */
	public Polynomial<Double> integrate(Polynomial<Integer> p1) {
		Polynomial<Double> resultI = new Polynomial<Double>();
		for (AbstractMonom<Integer> m1 : p1.getPolynomial()) {
			if (m1.getDegree() != 0)
				resultI.addMonom(new DoubleMonom(m1.getDegree() + 1, m1.getCoefficient() / m1.getDegree()));
			else
				resultI.addMonom(new DoubleMonom(m1.getDegree() + 1, m1.getCoefficient()));
		}
		resultI.sortPolynomial();
		return resultI;
	}
/**
 * differentiates p1
 * @param p1
 * @return
 */
	public Polynomial<Integer> derivate(Polynomial<Integer> p1) {
		Polynomial<Integer> resultI = new Polynomial<Integer>();
		for (AbstractMonom<Integer> m1 : p1.getPolynomial()) {
			if (m1.getDegree() != 0) {
				resultI.addMonom(new IntegerMonom(m1.getDegree() - 1, m1.getCoefficient() * m1.getDegree()));
			}
		}
		resultI.sortPolynomial();
		return resultI;
	}
}
