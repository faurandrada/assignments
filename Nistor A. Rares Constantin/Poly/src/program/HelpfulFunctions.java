package program;

import java.text.DecimalFormat;

public class HelpfulFunctions {

	public String polynomialToString(Polynomial p) {
		String s = new String();
		DecimalFormat df = new DecimalFormat("###.##");
		sort(p);
		int ok = 0;
		for (int i = 0; i < p.size; i++) {
			if (p.coefficient[i] != 0) {
				ok = 1;
				s = s + df.format(p.coefficient[i]) + 'x' + '^' + p.power[i];
				for (int j = i; j < p.size - 1; j++)
					if (p.coefficient[j + 1] > 0) {
						s = s + '+';
						break;
					} else if (p.coefficient[j + 1] < 0)
						break;
			}
		}

		if (ok == 0)
			s = s + '0';
		return s;
	}

	public double getCoefficient(int i, String s) {
		int nr = 0;
		i--;
		while (Character.isDigit(s.charAt(i)) && i >= 1)
			i--;
		int sign = i;
		i++;
		if (i == 1 && s.charAt(i - 1) != '-') {
			nr = nr * 10 + (int) (s.charAt(i - 1) - '0');
		}
		while (Character.isDigit(s.charAt(i))) {
			nr = nr * 10 + (int) (s.charAt(i) - '0');
			i++;
		}
		if (s.charAt(sign) == '-')
			return (double) -nr;
		return (double) nr;

	}

	public int getPower(int i, String s) {
		int nr = 0;
		i++;
		while (Character.isDigit(s.charAt(i)) && i < s.length() - 1) {
			nr = nr * 10 + (int) (s.charAt(i) - '0');
			i++;
		}
		if (i == s.length() - 1)
			nr = nr * 10 + (int) (s.charAt(i) - '0');
		return nr;
	}

	public static void sort(Polynomial p) {
		int ok;
		do {
			ok = 0;
			for (int i = 0; i < p.size - 1; i++) {
				if (p.power[i] < p.power[i + 1]) {
					int aux1 = p.power[i];
					double aux2 = p.coefficient[i];
					p.power[i] = p.power[i + 1];
					p.power[i + 1] = aux1;
					p.coefficient[i] = p.coefficient[i + 1];
					p.coefficient[i + 1] = aux2;
					ok = 1;
				}
			}
		} while (ok == 1);
	}
}
