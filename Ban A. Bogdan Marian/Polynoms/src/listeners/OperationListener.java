package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import graphic.Application;
import polynomials.Polynom;

public class OperationListener implements ActionListener {

	private Application application;

	public OperationListener(Application application) {
		this.application = application;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Polynom rez;
		String whichButton = e.getActionCommand();
		if (whichButton.equals("Add")) {
			rez = application.getP1().addP(application.getP2());
			application.updateOutputText("The adding result :\n" + rez.printString());
		} else if (whichButton.equals("Subtract")) {
			rez = application.getP1().subtractP(application.getP2());
			application.updateOutputText("The subtracting result :\n" + rez.printString());
		} else if (whichButton.equals("Multiply")) {
			rez = application.getP1().multiplyP(application.getP2());
			application.updateOutputText("The multiplying result :\n" + rez.printString());
		} else if (whichButton.equals("Derivate")) {
			rez = application.getP1().derivateP();
			application.updateOutputText("The derivating result :\n" + rez.printString());
		} else if (whichButton.equals("Integrate")) {
			rez = application.getP1().integrateP();
			application.updateOutputText("The integrating result :\n" + rez.printString());
		} else if (whichButton.equals("Divide")) {
			rez = application.getP1().divideP(application.getP2());
			application.updateOutputText("The dividing result :\n" + rez.printString());

		}
		application.reset();

	}
}
