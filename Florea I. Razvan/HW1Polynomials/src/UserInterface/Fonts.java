package UserInterface;

import java.awt.Font;
/*
 * This class contains the fonts used for the format of the strings at the output and input representing
 * the initial polynomials and the output ones./
 */
public class Fonts {

	private Font inputFont;
	private Font outputFont;

	public Fonts() {
		inputFont = new Font(null, Font.ROMAN_BASELINE + Font.BOLD, 15);
		outputFont = new Font(null, Font.BOLD, 14);
	}

	public Font getInputFont() {
		return inputFont;
	}

	public Font getOutputFont() {
		return outputFont;
	}

}
