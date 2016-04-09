package listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import graphic.Application;

public class ReadKeyListener implements KeyListener {
	private Application application;
	private int switcher;

	public ReadKeyListener(Application application, int switcher) {
		this.application = application;
		this.switcher = switcher;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (switcher == 1) {
			try {
				application.setP1(application.readPolynom((JTextField) arg0.getSource()));
			} catch (Exception ex) {
				application.getPanel().getInfoLabel().setText("Error! Please check the input");
			}
			if (application.getP1() != null) {
				if (application.getP2() != null) {
					application.getPanel().getAreaInput()
							.setText(application.getP1().printString() + "\n" + application.getP2().printString());
				} else {
					application.getPanel().getAreaInput().setText(application.getP1().printString());
				}
			}
		}
		if (switcher == 2) {
			try {
				application.setP2(application.readPolynom((JTextField) arg0.getSource()));
			} catch (Exception ex) {
				application.getPanel().getInfoLabel().setText("Error! Please check the input");
			}
			if (application.getP2() != null) {
				if (application.getP1() != null) {
					application.getPanel().getAreaInput()
							.setText(application.getP1().printString() + "\n" + application.getP2().printString());
				} else {
					application.getPanel().getAreaInput().setText(application.getP2().printString());
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
