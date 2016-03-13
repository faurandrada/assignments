package pt.polynomials.models;
/**
 * the polynomial object that contains the list of monoms
 * @author Chiti
 * @param T
 * choose integer or double for constructing your polynomial
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Polynomial<T> {
	private ArrayList<AbstractMonom<T>> polynomial = new ArrayList<AbstractMonom<T>>();
/**
 * method for adding a monom in the list
 * uses collection implemented method list.add()
 * @param monom
 */
	public void addMonom(AbstractMonom<T> monom) {
		polynomial.add(monom);
	}
/**
 * method for removing monom from the list
 * @param monom
 */
	public void removeMonom(AbstractMonom<T> monom) {
		polynomial.remove(monom);
	}

	public ArrayList<AbstractMonom<T>> getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(ArrayList<AbstractMonom<T>> polynomial) {
		this.polynomial = polynomial;
	}
/**
 * returns the polynom representation
 * @return String
 */
	@Override
	public String toString() {
		String representation = "";
		for (AbstractMonom<T> i : polynomial) {
			if (i.getDegree() == 0)
				representation += i.toString();
			else
				representation += "+" + i.toString();
		}
		return representation;
	}
/**
 * sorts on the degree of monoms the list using Collection method
 */
	@SuppressWarnings("unchecked")
	public void sortPolynomial() {
		Collections.sort(polynomial);
	}
/**
 * gets the higher degree in the list,last one
 * they are sorted
 * @return
 */
	@SuppressWarnings("rawtypes")
	public int getDegree() {
		Iterator i = polynomial.iterator();
		int degree = 0;
		while (i.hasNext()) {
			degree = ((AbstractMonom) i.next()).getDegree();
		}
		return degree;
	}
}
