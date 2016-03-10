package models;

import comparators.PowerComparatorInt;

public class Operation {

	float[] r;
	float[] q;
	float[] n;
	float[] d;

	public static Polynomial<IntCoeffTerm> addition(Polynomial<IntCoeffTerm> p1, Polynomial<IntCoeffTerm> p2) {
		Polynomial<IntCoeffTerm> p3 = new Polynomial<IntCoeffTerm>();
		boolean powerOnlyInOnePolynomial = false;
		for (IntCoeffTerm t1 : p1.getTerms()) {
			for (IntCoeffTerm t2 : p2.getTerms()) {
				if (t1.getPower() == t2.getPower()) {
					IntCoeffTerm term = new IntCoeffTerm(t1.getPower(), t1.getCoeff() + t2.getCoeff());
					p3.addTermPoly(term);
					powerOnlyInOnePolynomial = true;
				}
			}
			if (powerOnlyInOnePolynomial == false)
				p3.addTermPoly(t1);
			powerOnlyInOnePolynomial = false;
		}
		for (IntCoeffTerm t1 : p2.getTerms()) {
			for (IntCoeffTerm t2 : p3.getTerms()) {
				if (t1.getPower() == t2.getPower()) {
					powerOnlyInOnePolynomial = true;
				}
			}
			if (powerOnlyInOnePolynomial == false)
				p3.addTermPoly(t1);
		}
		return p3;

	}

	public static Polynomial<IntCoeffTerm> substraction(Polynomial<IntCoeffTerm> p1, Polynomial<IntCoeffTerm> p2) {
		Polynomial<IntCoeffTerm> p3 = new Polynomial<IntCoeffTerm>();
		boolean powerOnlyInOnePolynomial = false;
		for (IntCoeffTerm t1 : p1.getTerms()) {
			for (IntCoeffTerm t2 : p2.getTerms()) {
				if (t1.getPower() == t2.getPower()) {
					IntCoeffTerm term2 = new IntCoeffTerm(t1.getPower(), t1.getCoeff() - t2.getCoeff());
					p3.addTermPoly(term2);
					powerOnlyInOnePolynomial = true;
				}
			}
			if (powerOnlyInOnePolynomial == false)
				p3.addTermPoly(t1);
			powerOnlyInOnePolynomial = false;
		}
		for (IntCoeffTerm t1 : p2.getTerms()) {
			for (IntCoeffTerm t2 : p3.getTerms())
				if (t1.getPower() == t2.getPower()) {
					powerOnlyInOnePolynomial = true;
				}
			if (powerOnlyInOnePolynomial == false) {
				IntCoeffTerm term3 = new IntCoeffTerm(t1.getPower(), -t1.getCoeff());
				p3.addTermPoly(term3);
			}
		}
		return p3;

	}

	public static Polynomial<IntCoeffTerm> multiplication(Polynomial<IntCoeffTerm> p1, Polynomial<IntCoeffTerm> p2) {
		Polynomial<IntCoeffTerm> p3 = new Polynomial<IntCoeffTerm>();
		int[] mulArray = new int[100];
		for (int i = 0; i < mulArray.length; i++) {
			mulArray[i] = 0;
		}
		for (IntCoeffTerm t1 : p1.getTerms())
			for (IntCoeffTerm t2 : p2.getTerms()) {
				IntCoeffTerm mul = new IntCoeffTerm(t1.getPower() + t2.getPower(), t1.getCoeff() * t2.getCoeff());
				p3.addTermPoly(mul);

			}
		for (IntCoeffTerm t1 : p3.getTerms()) {
			int a = t1.getPower();
			mulArray[a] = mulArray[a] + t1.getCoeff();
		}
		p3=new Polynomial<IntCoeffTerm>();
		for (int i=0;i<mulArray.length;i++){
			if (mulArray[i]!=0)
			p3.addTermPoly(new IntCoeffTerm(i, mulArray[i]));
		}
		return p3;
		
	}

	public void division(Polynomial<IntCoeffTerm> p1, Polynomial<IntCoeffTerm> p2) {
		p1.sortPoly(new PowerComparatorInt());
		p2.sortPoly(new PowerComparatorInt());
		n = new float[100];
		d = new float[100];
		q = new float[100];
		r = new float[100];

		initializeArray(d);
		initializeArray(q);
		initializeArray(n);
		float[] d1;

		for (IntCoeffTerm t1 : p1.getTerms()) {
			n[t1.getPower()] = t1.getCoeff();
		}
		for (IntCoeffTerm t2 : p2.getTerms()) {
			d[t2.getPower()] = t2.getCoeff();
		}
		if (getArrayDegree(n) >= getArrayDegree(d)) {
			initializeArray(q);
			while (getArrayDegree(n) >= getArrayDegree(d)) {
				d1 = new float[100];
				for (int i = getArrayDegree(n); i >= getArrayDegree(n) - getArrayDegree(d); i--) {
					d1[i] = d[i - getArrayDegree(n) + getArrayDegree(d)];

				}
				for (int i = getArrayDegree(n) - getArrayDegree(d) - 1; i >= 0; i--) {
					d1[i] = 0;
				}
				q[getArrayDegree(n) - getArrayDegree(d)] = n[getArrayDegree(n)] / d[getArrayDegree(d)];
				for (int i = 0; i < d1.length; i++) {
					d1[i] = d1[i] * q[getArrayDegree(n) - getArrayDegree(d)];
				}

				for (int i = 0; i < n.length; i++) {
					n[i] = n[i] - d1[i];
				}
			}
			for (int i = 0; i < r.length; i++) {
				r[i] = n[i];
			}
		} else {
			for (int i = 0; i < q.length; i++)
				q[i] = 0;
			for (int j = 0; j < r.length; j++)
				r[j] = n[j];

		}
	}

	public Polynomial<RealCoeffTerm> getRemainder() {
		Polynomial<RealCoeffTerm>p3=new Polynomial<RealCoeffTerm>();
		for (int i=0;i<r.length;i++){
			if (r[i]!=0)
			p3.addTermPoly(new RealCoeffTerm(i,r[i]));
		}
		return p3;
	}

	public Polynomial<RealCoeffTerm> getQuotient() {
		Polynomial<RealCoeffTerm>p3=new Polynomial<RealCoeffTerm>();
		for (int i=0;i<q.length;i++){
			if (q[i]!=0)
			p3.addTermPoly(new RealCoeffTerm(i,q[i]));
		}
		return p3;
	}

	public static Polynomial<RealCoeffTerm> integration(Polynomial<IntCoeffTerm> p1) {
		Polynomial<RealCoeffTerm> p2 = new Polynomial<RealCoeffTerm>();
		for (IntCoeffTerm t1 : p1.getTerms()) {
			float coefficient = ((float) t1.getCoeff()) / (t1.getPower() + 1);
			RealCoeffTerm term = new RealCoeffTerm(t1.getPower() + 1, coefficient);
			p2.addTermPoly(term);
		}
		return p2;
	}

	public static Polynomial<IntCoeffTerm> derivation(Polynomial<IntCoeffTerm> p1) {
		Polynomial<IntCoeffTerm> p2 = new Polynomial<IntCoeffTerm>();
		for (IntCoeffTerm t1 : p1.getTerms()) {
			if (t1.getPower() >= 1) {
				IntCoeffTerm term1 = new IntCoeffTerm(t1.getPower() - 1, t1.getCoeff() * t1.getPower());
				p2.addTermPoly(term1);
			}
		}
		return p2;
	}

	public void initializeArray(float[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = 0;
		}
	}

	public int getArrayDegree(float[] a) {
		boolean findDegree = true;
		int i = a.length;
		while ((i >= 0) && (findDegree == true)) {
			i--;
			if(i>0){
			if (a[i] != 0)
				findDegree = false;
			}
		}
		return i;
	}

}