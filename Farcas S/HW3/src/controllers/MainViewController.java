package controllers;

import views.*;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import models.*;


/**
 * 
 * The class that controlls MainView
 *
 */
public class MainViewController {

	private static volatile boolean isCancelled = false;
	
	private MainView mainView;

	public MainViewController(MainView mainView) {
		this.mainView = mainView;
		this.mainView.setStartButtonActionListener(new StartButtonActionListener());
	}

	/**
	 * 
	 * The refresh swing worker
	 *
	 */
	
	public class Refresh extends SwingWorker<Void, Void> {
		@Override
        protected Void doInBackground() {
			mainView.getStart().setEnabled(false);
            while (!isCancelled) {
                publish();
            }
            mainView.getStart().setEnabled(true);
            return null;
        }
		@Override
		protected void process(List<Void> lv) {
		    mainView.refresh();
		}
	}
	
	/**
	 * 
	 * Start Button ActionListener
	 *
	 */
	public class StartButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			isCancelled = false;
			TaskGenerator.deleteInstance();
			TaskScheduler.deleteInstance();
			TaskGenerator.getInstance()
					.setMaxServiceTime(Long.parseLong(mainView.getMaxServiceTime().getText()));
			TaskGenerator.getInstance()
					.setMinServiceTime(Long.parseLong(mainView.getMinServiceTime().getText()));
			TaskGenerator.getInstance()
					.setMaxArrivalInterval(Long.parseLong(mainView.getMaxArrivalInterval().getText()));
			TaskGenerator.getInstance()
					.setMinArrivalInterval(Long.parseLong(mainView.getMinArrivalInterval().getText()));
			TaskScheduler.getInstance().setMaxLoadPerServer(Integer.parseInt(mainView.getMaxLoadPerServer().getText()));
			TaskScheduler.getInstance()
					.setSimulationTime(Long.parseLong(mainView.getSimulationInterval().getText()));
			TaskScheduler.getInstance().setNumberOfServers(Integer.parseInt(mainView.getNumberOfQueues().getText()));
			Refresh refresh = new Refresh();
			refresh.execute();
			Thread t = new Thread(TaskGenerator.getInstance());
			t.start();
		}
	}

	public static void setCancelled(boolean isCancelled) {
		MainViewController.isCancelled = isCancelled;
	}
	
}
