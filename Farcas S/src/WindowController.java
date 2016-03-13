package controllers;

/**
 * the main controller for the GUI
 */
import java.awt.event.*;

import views.*;

public class WindowController {

	private Window window;

	public WindowController(Window window) {
		this.window = window;
		window.setAddButtonListener(new AddButtonListener());
		window.setSubButtonListener(new SubButtonListener());
		window.setMulButtonListener(new MulButtonListener());
		window.setDivButtonListener(new DivButtonListener());
		window.setDiff1ButtonListener(new Diff1ButtonListener());
		window.setDiff2ButtonListener(new Diff2ButtonListener());
		window.setInt1ButtonListener(new Int1ButtonListener());
		window.setInt2ButtonListener(new Int2ButtonListener());
		window.setEvalP1ButtonListener(new EvalP1ButtonListener());
		window.setEvalP2ButtonListener(new EvalP2ButtonListener());
		window.setRootP1ButtonListener(new RootP1ButtonListener());
		window.setRootP2ButtonListener(new RootP2ButtonListener());
		window.setGraphP1ButtonListener(new GraphP1ButtonListener());
		window.setGraphP2ButtonListener(new GraphP2ButtonListener());
	}

	public class AddButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.add(window);
		}
	}

	public class SubButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.sub(window);
		}
	}

	public class MulButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.mul(window);
		}
	}

	public class DivButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.div(window);
		}
	}

	public class Diff1ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.diff1(window);
		}
	}

	public class Diff2ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.diff2(window);
		}
	}

	public class Int1ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.int1(window);
		}
	}

	public class Int2ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.int2(window);
		}
	}

	public class EvalP1ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.evalP1(window);
		}
	}

	public class EvalP2ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.evalP2(window);
		}
	}

	public class RootP1ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.rootP1(window);
		}
	}

	public class RootP2ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.rootP2(window);
		}
	}

	public class GraphP1ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.graphP1(window);
		}
	}

	public class GraphP2ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			InputOutput.graphP2(window);
		}
	}
}
