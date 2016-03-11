package program;


public class Operations extends HelpfulFunctions {

	public String add(Polynomial p1, Polynomial p2) {

		Polynomial p3 = new Polynomial();
		p3.createPolynomial(polynomialToString(p1));

		for (int i = 0; i < p2.size; i++) {
			int ok = 0;
			for (int j = 0; j < p3.size; j++)
				if (p2.power[i] == p3.power[j]) {
					p3.coefficient[j] += p2.coefficient[i];
					ok = 1;
					break;
				}
			if (ok == 0) {
				p3.coefficient[p3.size] += p2.coefficient[i];
				p3.power[p3.size] = p2.power[i];
				p3.size++;
			}
		}
		return polynomialToString(p3);

	}

	public String sub(Polynomial p1, Polynomial p2) {

		Polynomial p3 = new Polynomial();
		p3.createPolynomial(polynomialToString(p1));

		for (int i = 0; i < p2.size; i++) {
			int ok = 0;
			for (int j = 0; j < p3.size; j++)
				if (p2.power[i] == p3.power[j]) {
					p3.coefficient[j] -= p2.coefficient[i];
					ok = 1;
					break;
				}
			if (ok == 0) {
				p3.coefficient[p3.size] = -p2.coefficient[i];
				p3.power[p3.size] = p2.power[i];
				p3.size++;
			}
		}
		return polynomialToString(p3);

	}

	public String mul(Polynomial p1, Polynomial p2) {

		Polynomial p3 = new Polynomial();

		for (int i = 0; i < p1.size; i++) {
			for (int j = 0; j < p2.size; j++) {
				p3.coefficient[p3.size] = p1.coefficient[i] * p2.coefficient[j];
				p3.power[p3.size] = p1.power[i] + p2.power[j];
				p3.size++;
			}
		}
		return polynomialToString(p3);

	}

	public String diff(Polynomial p1) {
		Polynomial p3 = new Polynomial();
		p3.createPolynomial(polynomialToString(p1));

		for (int i = 0; i < p3.size; i++) {
			p3.coefficient[i] *= p3.power[i];
			p3.power[i]--;

		}
		return polynomialToString(p3);
	}

	public String integration(Polynomial p1) {

		Polynomial p3 = new Polynomial();
		p3.createPolynomial(polynomialToString(p1));

		for (int i = 0; i < p3.size; i++) {
			p3.coefficient[i] = (p3.coefficient[i] / (p3.power[i] + 1));
			p3.power[i]++;
		}
		return polynomialToString(p3);
	}


	public String divi(Polynomial p1, Polynomial p2) {

		
		Polynomial p4 = new Polynomial();
		p4.createPolynomial(polynomialToString(p2));
		
		Polynomial p3 = new Polynomial();
		p3.createPolynomial(polynomialToString(p1));
		
		Polynomial p5 = new Polynomial();
		
		sort(p3);
		sort(p2);
		
		while (p3.coefficient[0] >= p2.coefficient[0]) {

			p5.coefficient[p5.size] = p3.coefficient[0] / p2.coefficient[0];
			p5.power[p5.size] = p3.power[0] - p2.power[0];

			for (int i = 0; i < p4.size; i++) {
				p4.coefficient[i] = p4.coefficient[i] * p5.coefficient[p5.size];
				p4.power[i] = p4.power[i] + p5.power[0];
			}
			p5.size++;
			p3.createPolynomial(sub(p3,p4));

			sort(p3);
		}
		return polynomialToString(p5);
	}
	

}