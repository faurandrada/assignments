package run;

import java.awt.Font;

public class Const {

	public final static int MAIN_HEIGHT = 640;
	public final static int MAIN_WIDTH = 960;
	public final static int NEW_USER_HEIGHT = 400;
	public final static int NEW_USER_WIDTH = 400;
	public final static int MESSAGE_HEIGHT = 100;
	public final static int MESSAGE_WIDTH = 400;
	public final static Font myButtonFont = new Font("Arial", Font.BOLD, 40);
	public final static Font myTitleFont = new Font("Arial", Font.BOLD, 100);
	public final static Font tableFont = new Font("", 1, 22);
	public final static Font orderFont = new Font("", 1, 12);
	
	private Const() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}

}
