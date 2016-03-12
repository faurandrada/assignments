package utilities;

import models.Coefficient;
import models.IntegerCoefficient;
import models.Polynomial;
import models.RealCoefficient;
import models.TypeOfCoefficient;

public class Utilities {

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static int maxDegree(final int a, final int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	public static String toString(Polynomial poli) {
		String ret = "";
		if ((poli.getCoefficient()[0].getType() == TypeOfCoefficient.integer)) {
			System.out.println("intreg");
			if (poli.getDegree() > 0) {
				if (((IntegerCoefficient) poli.getCoefficient()[poli.getDegree()]).getCoefficient() == 1) {
					ret = "" + "X^" + poli.getDegree();
				} else {
					if (((IntegerCoefficient) poli.getCoefficient()[poli.getDegree()]).getCoefficient() == -1) {
						ret = "" + "-X^" + poli.getDegree();
					} else {
						ret = "" + ((IntegerCoefficient) poli.getCoefficient()[poli.getDegree()]).getCoefficient()
								+ "X^" + poli.getDegree();
					}
				}
			}
			for (int i = poli.getDegree() - 1; i > 0; i--) {
				if (((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() == 0) {
					continue;
				} else if (i == 1) {
					ret = ret + "+" + ((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() + "X";
				} else if (((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() != 0) {
					if (((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() > 0) {
						if (((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() == 1) {
							ret = ret + "+" + "X^" + i;
						} else {
							ret = ret + "+" + ((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() + "X^"
									+ i;
						}
					} else if (((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() < 0) {
						if (((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() == -1) {
							ret = ret + "-" + "X^" + i;
						} else {
							ret = ret + ((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() + "X^" + i;
						}
					}
				}
			}
			int i = 0;
			if (((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() >= 0) {
				if (poli.getDegree() > 0)
					ret = ret + "+";
				ret = ret + ((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient();
			} else if (((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient() < 0) {
				ret = ret + ((IntegerCoefficient) poli.getCoefficient()[i]).getCoefficient();
			}
			return ret;
		} else if (poli.getCoefficient()[0].getType() == TypeOfCoefficient.real) {
			System.out.println("real");
			if (poli.getDegree() > 0) {
				if (((RealCoefficient) poli.getCoefficient()[poli.getDegree()]).getCoefficient() == 1) {
					ret = "" + "X^" + poli.getDegree();
				} else {
					if (((RealCoefficient) poli.getCoefficient()[poli.getDegree()]).getCoefficient() == -1) {
						ret = "" + "-X^" + poli.getDegree();
					} else {
						ret = "" + ((RealCoefficient) poli.getCoefficient()[poli.getDegree()]).getCoefficient() + "X^"
								+ poli.getDegree();
					}
				}
			}
			for (int i = poli.getDegree() - 1; i > 0; i--) {
				if (((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() == 0) {
					continue;
				} else if (i == 1) {
					ret = ret + "+" + ((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() + "X";
				} else if (((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() != 0) {
					if (((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() > 0) {
						if (((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() == 1) {
							ret = ret + "+" + "X^" + i;
						} else {
							ret = ret + "+" + ((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() + "X^" + i;
						}
					} else if (((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() < 0) {
						if (((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() == -1) {
							ret = ret + "-" + "X^" + i;
						} else {
							ret = ret + ((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() + "X^" + i;
						}
					}
				}
			}
			int i = 0;
			if (((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() >= 0) {
				if (poli.getDegree() > 0)
					ret = ret + "+";
				ret = ret + ((RealCoefficient) poli.getCoefficient()[i]).getCoefficient();
			} else if (((RealCoefficient) poli.getCoefficient()[i]).getCoefficient() < 0) {
				ret = ret + ((RealCoefficient) poli.getCoefficient()[i]).getCoefficient();
			}
			return ret;
		}
		return ret;
	}

	public static Coefficient[] transform(int[] arr) {
		Coefficient[] c = new Coefficient[arr.length];
		for (int i = 0; i < arr.length; i++) {
			c[i] = new IntegerCoefficient(arr[i]);
		}
		return c;

	}

	public static int getIntElement(Polynomial p, int position) {
		return ((IntegerCoefficient) (p.getCoefficient()[position])).getCoefficient();
	}

	public static double getRealElement(Polynomial p, int position) {
		return ((RealCoefficient) (p.getCoefficient()[position])).getCoefficient();
	}
}
