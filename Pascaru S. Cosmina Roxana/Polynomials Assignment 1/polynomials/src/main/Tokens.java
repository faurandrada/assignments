package main;

import java.util.StringTokenizer;

/**
 * Used to separate the input string given by the user into tokens that are then
 * used to recreate polynomials
 * 
 * @author Cosmina
 *
 */

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
		return coeff[index];
	}

	public int getPower(int index) {
		return power[index];
	}

	public int getPolLength() {
		return coeff.length;
	}

}
