package controllers;

import views.*;

import java.awt.event.*;
import javax.swing.Timer;

public class HistoryController extends AbstractController{
	
	private Timer refreshHistory = new Timer(100, new Refresh());
	private HistoryView historyView;
	
	public HistoryController(HistoryView historyView, boolean hasBackButton) {
		super(historyView, hasBackButton);
		this.historyView = historyView;
		if (hasBackButton)
			frame.setBackButtonActionListener(new BackButtonListener());
		refreshHistory.start();
	}

	private class BackButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			FrameStack.getInstance().pop();
			refreshHistory.stop();
		}
	}
	
	private class Refresh implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			historyView.refresh();
		}
		
	}
	
}
