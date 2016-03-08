package polynomials;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Tokens {

	private String string;

	private int[] coeff;
	private int[] power;
	private StringTokenizer strTok;

	public Tokens(String string, int size) {
		this.string = string;

		coeff = new int[++size];
		power = new int[++size];

		tokenize(string);
	}

	public void tokenize(String string) {
		strTok = new StringTokenizer(string, "X^ ");

		int i = 0;
		int k = 0;
		int l = 0;
		while (strTok.hasMoreTokens()) {

			if (i % 2 == 0) {
				coeff[k] = Integer.parseInt(strTok.nextToken());
				k++;
			} else {
				power[l] = Integer.parseInt(strTok.nextToken());
				l++;
			}
			i++;
		}
	}

	public int getCoeff(int index) {
		for (int i = 0; i < coeff.length; i++) {
			if (i == index)
				return coeff[i];
		}
		return 0;
	}

	public int getPower(int index) {
		for (int i = 0; i < power.length; i++) {
			if (i == index)
				return power[i];
		}
		return 0;
	}

	public int getPolLength() {
		return coeff.length;
	}

}
