package operations;

import polynomials.Monom;

import polynomials.Polynomial;

/**
 * Has methods that allow the coumputation of different operations on two
 * polynomials
 * 
 * @author Cosmina
 *
 */
public class BiOperation implements Operation {

	private Monom m;
	private Polynomial p;
	private int[] polySorted = new int[20];

	public BiOperation() {
		p = new Polynomial();
	}

	public Polynomial add(Polynomial p1, Polynomial p2) {
		p.getArray().clear();

		if (p1.getDegree() > p2.getDegree()) {
			for (Monom m1 : p1.getArray()) {
				polySorted[m1.getPower()] = m1.getCoeff();
			}
			for (Monom m1 : p1.getArray()) {
				for (Monom m2 : p2.getArray()) {
					if (m1.getPower() == m2.getPower()) {
						polySorted[m1.getPower()] += m2.getCoeff();
					}
				}
			}
		}

		if (p2.getDegree() > p1.getDegree()) {
			for (Monom m2 : p2.getArray()) {
				polySorted[m2.getPower()] = m2.getCoeff();
			}
			for (Monom m2 : p2.getArray()) {
				for (Monom m1 : p1.getArray()) {
					if (m1.getPower() == m2.getPower()) {
						polySorted[m2.getPower()] += m1.getCoeff();
					}
				}
			}
		}

		for (int i = polySorted.length - 1; i >= 0; i--) {
			m = new Monom(polySorted[i], i);
			p.addMonom(m);
		}

		return p;
	}

	public Polynomial subtract(Polynomial p1, Polynomial p2) {
		p.getArray().clear();

		if (p1.getDegree() > p2.getDegree()) {
			for (Monom m1 : p1.getArray()) {
				polySorted[m1.getPower()] = m1.getCoeff();
			}
			for (Monom m1 : p1.getArray()) {
				for (Monom m2 : p2.getArray()) {
					if (m1.getPower() == m2.getPower()) {
						polySorted[m1.getPower()] -= m2.getCoeff();
					}
				}
			}
		}

		if (p2.getDegree() > p1.getDegree()) {
			for (Monom m2 : p2.getArray()) {
				polySorted[m2.getPower()] = m2.getCoeff();
			}
			for (Monom m2 : p2.getArray()) {
				for (Monom m1 : p1.getArray()) {
					if (m1.getPower() == m2.getPower()) {
						polySorted[m2.getPower()] -= m1.getCoeff();
					}
				}
			}
		}

		for (int i = polySorted.length - 1; i >= 0; i--) {
			m = new Monom(polySorted[i], i);
			p.addMonom(m);
		}

		return p;
	}

	public Polynomial multiply(Polynomial p1, Polynomial p2) {
		p.getArray().clear();

		for (Monom m1 : p1.getArray()) {
			for (Monom m2 : p2.getArray()) {
				m = new Monom(m1.getCoeff() * m2.getCoeff(), m1.getPower() + m2.getPower());
				polySorted[m.getPower()] += m.getCoeff();
			}
		}
		for (int i = polySorted.length - 1; i >= 0; i--) {
			m = new Monom(polySorted[i], i);
			p.addMonom(m);
		}
		return p;
	}

	@Override
	public Polynomial differentiate(Polynomial p1) {
		return null;
	}

	@Override
	public Polynomial integrate(Polynomial p1) {
		return null;
	}

	@Override
	public int evaluatePolyAtPoint(int point, Polynomial p1) {
		return 0;
	}

	/*
	 * public ArrayList<Monom> divide(Polynomial p1, Polynomial p2) {
	 * ArrayList<Monom> resultMultiplication = new ArrayList<Monom>();
	 * ArrayList<Monom> resultSubstraction = new ArrayList<Monom>(); Polynomial
	 * auxp = new Polynomial(); p.getArray().clear(); r.getArray().clear();
	 * 
	 * if (p1.getDegree() >= p2.getDegree()) { for (Monom m1 : p1.getArray()) {
	 * for (Monom m2 : p2.getArray()) { if (m2.getCoeff() != 0 && m2.getPower()
	 * != 0) { m = new Monom(m1.getCoeff() / m2.getCoeff(), m1.getPower() /
	 * m2.getPower()); p.addMonom(m);// catul resultMultiplication =
	 * multiply(p2, p); for (Monom mAux : resultMultiplication) {
	 * auxp.addMonom(mAux); } resultSubstraction = substract(p1, auxp); for
	 * (Monom mAux : resultSubstraction) { r.addMonom(mAux); } p1 = r; } } } }
	 * return p.getArray(); }
	 */

}
