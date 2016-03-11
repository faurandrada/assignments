package program;
public class Polynomial extends HelpfulFunctions{
	protected double[] coefficient = new double[100];
	protected int[] power = new int[100];
	protected int size = 0;

	private void createCoefficient(String s) {
		size = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'x') {
					coefficient[size] = getCoefficient(i,s);
					size++;
				}
			}
		}
	

	private void createPower(String s) {
		size = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == '^') {
				power[size] = getPower(i,s);
				size++;
			}
		}
	}
	
	public void createPolynomial(String s){
		createCoefficient(s);
		createPower(s);
	}

	
	
}
