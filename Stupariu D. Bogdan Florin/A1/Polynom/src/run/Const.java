package run;

public final class Const {
	public final static int HEIGHT = 600;
	public final static int WIDTH = 800;

	private Const() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}

}
