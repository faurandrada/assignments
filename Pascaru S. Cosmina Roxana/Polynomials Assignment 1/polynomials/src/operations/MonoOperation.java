package operations;

import polynomials.Monom;
import polynomials.Polynomial;

/**
 * Has methods that allow the computation of different operations on one
 * polynomial
 * 
 * @author Cosmina
 *
 */
public class MonoOperation implements Operation {
	private Polynomial p;

	public MonoOperation() {
		p = new Polynomial();
	}

	public Polynomial differentiate(Polynomial p1) {
		p.getArray().clear();
		for (Monom m : p1.getArray()) {
			if (m.getPower() != 0) {
				m = new Monom(m.getCoeff() * m.getPower(), m.getPower() - 1);
			} else
				m = new Monom(0, 0);
			p.addMonom(m);
		}
		return p;
	}

	public Polynomial integrate(Polynomial p1) {
		p.getArray().clear();
		for (Monom m : p1.getArray()) {
			m = new Monom(m.getCoeff(), m.getPower() + 1);
			p.addMonom(m);
		}
		return p;
	}

	public int evaluatePolyAtPoint(int point, Polynomial p1) {
		p.getArray().clear();
		int result = 0;
		for (Monom m : p1.getArray()) {
			if (m.getPower() != 0) {
				result += Math.pow(point, m.getPower()) * m.getCoeff();
			} else if (m.getPower() == 0) {
				result += m.getCoeff();
			}
		}
		return result;
	}

	@Override
	public Polynomial add(Polynomial p1, Polynomial p2) {
		return null;
	}

	@Override
	public Polynomial subtract(Polynomial p1, Polynomial p2) {
		return null;
	}

	@Override
	public Polynomial multiply(Polynomial p1, Polynomial p2) {
		return null;
	}
}
