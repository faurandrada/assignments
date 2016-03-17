package poly.processing;

import poly.IO.DegreeFrame;

/**
 * 
 * @author Dia
 *
 *         The public class App implementing Runnable describes the way the
 *         application runs. It first uses a degreeFrame instance. Then, it
 *         instantiates a Controller object by calling its constructor with the
 *         instantiated degrreFrame as parameter.
 */
public class App implements Runnable {

	private DegreeFrame degreeFrame;

	public App() {
		this.degreeFrame = new DegreeFrame();
	}

	@Override
	public void run() {
		new Controller(degreeFrame);
	}

}
