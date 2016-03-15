package polynomials;

public class Polynom {
	private int degree;
	private int[] coef;
	private Polynom rez;

	public Polynom(int degree, int[] coef) {
		this.degree = degree;
		this.coef = coef;
	}

	public String printString() {
		String polString = "";
		if (this.degree == 0) {
			return Integer.toString(this.coef[0]) + "\n";
		}
		for (int i = this.degree; i > 0; i--) {
			if (this.coef[i] > 0) {
				polString += this.coef[i] + "*x^" + i;
				polString += " + ";
			} else if (this.coef[i] < 0) {
				polString += this.coef[i] + "*x^" + i;
			}
		}
		if (this.coef[0] > 0) {
			polString += this.coef[0] + " \n ";
		} else if (this.coef[0] < 0) {
			polString += this.coef[0] + " \n ";
		} else {
			polString += "\n";
		}

		return polString;
	}

	public Polynom addP(Polynom p) {
		if (this.degree > p.degree) {
			rez = new Polynom(this.degree, this.coef);
			for (int i = 0; i <= degree; i++) {
				rez.coef[i] += p.coef[i];
			}
			return rez;
		} else {
			rez = new Polynom(p.degree, p.coef);
			for (int i = 0; i <= degree; i++) {
				rez.coef[i] += this.coef[i];
			}
			return rez;
		}
	}

	public Polynom subtractP(Polynom p) {
		int ok = 1;
		if (this.degree >= p.degree) {
			rez = new Polynom(this.degree, this.coef);
			for (int i = 0; i <= degree; i++) {
				rez.coef[i] = rez.coef[i] - p.coef[i];
			}

		} else {
			rez = new Polynom(p.degree, p.coef);
			for (int i = 0; i <= degree; i++) {
				rez.coef[i] = rez.coef[i] - this.coef[i];
			}
		}
		while (ok == 1) {
			if ((rez.coef[rez.degree] == 0) && (rez.degree > 0)) {
				rez.degree = rez.degree - 1;
			} else {
				ok = 0;
			}
		}
		return rez;
	}

	public Polynom multiplyP(Polynom p) {
		int i, j, degreef;
		int coeff[];
		degreef = this.degree + p.degree;
		coeff = new int[degreef + 1];

		for (i = 0; i <= degreef; i++) {
			coeff[i] = 0;
		}
		for (i = 0; i <= this.degree; i++) {
			for (j = 0; j <= p.degree; j++) {
				coeff[i + j] += this.coef[i] * p.coef[j];
			}
		}
		rez = new Polynom(degreef, coeff);
		return rez;
	}

	public Polynom derivateP() {
		int i;
		rez = new Polynom(this.degree - 1, this.coef);
		for (i = 1; i <= this.degree; i++) {
			rez.coef[i - 1] = this.coef[i] * i;
		}
		return rez;
	}

	public Polynom integrateP() {
		int i;
		rez = new Polynom(this.degree + 1, this.coef);
		for (i = 0; i <= this.degree; i++) {
			rez.coef[i+1] = this.coef[i] / (i+1);
		}
		rez.coef[0] = 0;
		return rez;
	}

	public Polynom divideP(Polynom p) {
		Polynom p1 = this;
		int i;
		int[] newCoef = new int[10];
		if ((p.degree == 0) && (p.coef[0] == 0)) {
			throw new RuntimeException("Divide by zero polynomial");
		}
		if (p1.degree <= p.degree) {
			return new Polynom(0, coef);
		}
		for (i = 0; i <= this.degree; i++) {
			newCoef[i] = p1.coef[p1.degree] / (p.coef[p.degree]);
		}

		int exponent = p1.degree - p.degree;
		Polynom p2 = new Polynom(exponent, newCoef);
		return p2.addP(p1.subtractP(p.multiplyP(p2)).divideP(p));
	}
}
