package operations;

import java.util.ArrayList;

import polynomials.Monom;
import polynomials.Polynomial;

public class MonoOperation extends Operation {

	public MonoOperation() {
		p = new Polynomial();
	}

	public ArrayList<Monom> differentiate(Polynomial p1) {
		p.getArray().clear();
		for (Monom m : p1.getArray()) {
			if (m.getPower() != 0) {
				m = new Monom(m.getCoeff() * m.getPower(), m.getPower() - 1);
			} else
				m = new Monom(0, 0);
			p.addMonom(m);
		}

		return p.getArray();
	}

	public ArrayList<Monom> integrate(Polynomial p1) {
		p.getArray().clear();
		for (Monom m : p1.getArray()) {
			m = new Monom(m.getCoeff(), m.getPower() + 1);
			p.addMonom(m);
		}

		return p.getArray();
	}

	public int evaluatePolyAtPoint(int point, Polynomial p1) {
		p.getArray().clear();
		int result = 0;
		for (Monom m : p1.getArray()) {
			if (m.getPower() != 0) {
				result += Math.pow(point, m.getPower()) * m.getCoeff();
			}
		}

		return result;
	}
}
