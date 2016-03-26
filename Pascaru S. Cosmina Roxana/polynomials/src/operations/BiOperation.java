package operations;

import java.util.ArrayList;

import polynomials.Monom;
import polynomials.Polynomial;

public class BiOperation extends Operation {

	public BiOperation() {
		p = new Polynomial();
	}

	public ArrayList<Monom> add(Polynomial p1, Polynomial p2) {
		p.getArray().clear();

		int difference = Math.abs(p1.getDegree() - p2.getDegree());
		System.out.println(difference);

		int i = 0;
		for (Monom m1 : p1.getArray()) {
			for (Monom m2 : p2.getArray()) {
				if (difference > 0) {
					if (i < difference) {
						if (p1.getDegree() > p2.getDegree()) {
							m = new Monom(m1.getCoeff(), m1.getPower());
							p.addMonom(m);

							i++;
						}

						if (p2.getDegree() > p1.getDegree()) {
							m = new Monom(m2.getCoeff(), m2.getPower());
							p.addMonom(m);

							i++;
						}

					} else {

						if (m1.getPower() == m2.getPower()) {
							m = new Monom(m1.getCoeff() + m2.getCoeff(), m1.getPower());
							p.addMonom(m);
						}
					}
				}
			}
		}

		return p.getArray();
	}

	public ArrayList<Monom> substract(Polynomial p1, Polynomial p2) {
		p.getArray().clear();

		int difference = Math.abs(p1.getDegree() - p2.getDegree());
		System.out.println(difference);

		int i = 0;

		for (Monom m1 : p1.getArray()) {
			for (Monom m2 : p2.getArray()) {
				if (difference > 0) {
					if (i < difference) {
						if (p1.getDegree() > p2.getDegree()) {
							m = new Monom(m1.getCoeff(), m1.getPower());
							p.addMonom(m);

							i++;
						}

						if (p2.getDegree() > p1.getDegree()) {
							m = new Monom(m2.getCoeff(), m2.getPower());
							p.addMonom(m);

							i++;
						}

					} else {

						if (m1.getPower() == m2.getPower()) {
							m = new Monom(m1.getCoeff() - m2.getCoeff(), m1.getPower());
							p.addMonom(m);
						}
					}
				}
			}
		}

		return p.getArray();
	}

	public ArrayList<Monom> multiply(Polynomial p1, Polynomial p2) {
		p.getArray().clear();

		for (Monom m1 : p1.getArray()) {
			for (Monom m2 : p2.getArray()) {
				if ((m1.getPower() != 0) && (m2.getPower() != 0)) {
					m = new Monom(m1.getCoeff() * m2.getCoeff(), m1.getPower() * m2.getPower());
					p.addMonom(m);
				} else {
					if (m1.getPower() == 0) {
						m = new Monom(m1.getCoeff() * m2.getCoeff(), m2.getPower());
						p.addMonom(m);
					}
					if (m2.getPower() == 0) {
						m = new Monom(m1.getCoeff() * m2.getCoeff(), m1.getPower());
						p.addMonom(m);
					}
				}
			}
		}
		
		return p.getArray();
	}

	/*public ArrayList<Monom> divide(Polynomial p1, Polynomial p2) {
		ArrayList<Monom> resultMultiplication = new ArrayList<Monom>();
		ArrayList<Monom> resultSubstraction = new ArrayList<Monom>();
		Polynomial auxp = new Polynomial();
		p.getArray().clear();
		r.getArray().clear();

		if (p1.getDegree() >= p2.getDegree()) {
			for (Monom m1 : p1.getArray()) {
				for (Monom m2 : p2.getArray()) {
					if (m2.getCoeff() != 0 && m2.getPower() != 0) {
						m = new Monom(m1.getCoeff() / m2.getCoeff(), m1.getPower() / m2.getPower());
						p.addMonom(m);// catul
						resultMultiplication = multiply(p2, p);
						for (Monom mAux : resultMultiplication) {
							auxp.addMonom(mAux);
						}
						resultSubstraction = substract(p1, auxp);
						for (Monom mAux : resultSubstraction) {
							r.addMonom(mAux);
						}
						p1 = r;
					}
				}
			}
		}
		return p.getArray();
	}*/

}
