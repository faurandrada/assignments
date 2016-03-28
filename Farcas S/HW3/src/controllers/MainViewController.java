package controllers;

import views.*;

import java.awt.event.*;

import javax.swing.*;

import models.*;


/**
 * 
 * The class that controlls MainView
 *
 */
public class MainViewController {

	private MainView mainView;

	public MainViewController(MainView mainView) {
		this.mainView = mainView;
		this.mainView.setStartButtonActionListener(new StartButtonActionListener());
	}

	/**
	 * 
	 * The refresh thread
	 *
	 */
	public class Refresh implements Runnable {
		@Override
		public void run() {
			while(true){
				mainView.refresh();
			}
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
			Thread refresh = new Thread(new Refresh());
			refresh.start();
			TaskGenerator.deleteInstance();
			TaskScheduler.deleteInstance();
			TaskGenerator.getInstance()
					.setMaxServiceTime(Long.parseLong(mainView.getMaxServiceTime().getText()) * 1000);
			TaskGenerator.getInstance()
					.setMinServiceTime(Long.parseLong(mainView.getMinServiceTime().getText()) * 1000);
			TaskGenerator.getInstance()
					.setMaxArrivalInterval(Long.parseLong(mainView.getMaxArrivalInterval().getText()) * 1000);
			TaskGenerator.getInstance()
					.setMinArrivalInterval(Long.parseLong(mainView.getMinArrivalInterval().getText()) * 1000);
			TaskScheduler.getInstance().setMaxLoadPerServer(Integer.parseInt(mainView.getMaxLoadPerServer().getText()));
			TaskScheduler.getInstance()
					.setSimulationTime(Long.parseLong(mainView.getSimulationInterval().getText()) * 1000);
			TaskScheduler.getInstance().setNumberOfServers(Integer.parseInt(mainView.getNumberOfQueues().getText()));
			Thread t = new Thread(TaskGenerator.getInstance());
			t.start();
		}
	}

}
