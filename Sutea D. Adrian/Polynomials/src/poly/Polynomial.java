package poly;

import java.util.ArrayList;

public class Polynomial {
	private int degree;
	private ArrayList<Monomial> polynomial;

	public Polynomial() {
		polynomial = new ArrayList<Monomial>();
	}

	public void addToPoly(Monomial m) {
		polynomial.add(m);
	}

	public int getSize() {
		return polynomial.size();
	}

	public Monomial getElement(int i) {
		return polynomial.get(i);
	}

	public void removeElem(int i) {
		polynomial.remove(i);
	}

	public void setDegree(int x) {
		this.degree = x;
	}

	public int getDegree() {
		return degree;
	}
}
