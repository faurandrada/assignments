package models;

import java.util.Date;
import java.util.Random;

import controllers.MainViewController;
import views.MainView;

/**
 * The Task Generator class
 */
public class TaskGenerator implements Runnable {

	private static TaskGenerator instance;
	private long minServiceTime;
	private long maxServiceTime;
	private long minArrivalInterval;
	private long maxArrivalInterval;
	private Random random = new Random();
	private int nrOfCustomers = 0;
	private Server serverShutdown;
	private long serverShutdownTime;
	private Thread threadServerShutdown;	

	private TaskGenerator() {
	}

	public static TaskGenerator getInstance() {
		if (instance == null) {
			instance = new TaskGenerator();
		}
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	/**
	 * The task generator
	 */
	public Task generateTask() {
		return new Task(System.currentTimeMillis(),
				((long) (random.nextDouble() * (maxServiceTime - minServiceTime + 1))) + minServiceTime,
				"Customer " + ++nrOfCustomers);
	}

	public void run() {
		long simulationTime = TaskScheduler.getInstance().getSimulationTime();
		long startTime = TaskScheduler.getInstance().getStartTime();
		TaskScheduler.getInstance().start();
		serverShutdownTime = TaskScheduler.getInstance().getServerShutdownTime();
		serverShutdown = TaskScheduler.getInstance().getServerShutdown();
		threadServerShutdown = TaskScheduler.getInstance().getThreadServerShutdown();
		while (System.currentTimeMillis() <= simulationTime + startTime) {
			if ((System.currentTimeMillis() - serverShutdownTime <= 1000)
					&& (System.currentTimeMillis() - serverShutdownTime >= 0) && !(serverShutdown.isShutdown())) {
				Thread t = new Thread(new ServerShutdownHandler());
				t.start();
			}
			TaskScheduler.getInstance().receiveTask(generateTask());
			try {
				Thread.sleep(((long) (random.nextDouble() * (maxArrivalInterval - minArrivalInterval + 1)))
						+ minArrivalInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (Thread t : TaskScheduler.getInstance().getRunningServers())
			if (t.isAlive())
				t.interrupt();
		MainViewController.setCancelled(true);
		MainView.getLogging().append(String.format("Peak hour: %tT with number of customers: %d\n",
				TaskScheduler.getInstance().getPeakHour(), TaskScheduler.getInstance().getPeakHourCustomers()));
		MainView.getLogging()
				.append("Average service time for simulation interval: " + Operations.getAverageServiceTime() + "\n");
		MainView.getLogging()
				.append("Average waiting time for simulation interval: " + Operations.getAverageWaitingTime() + "\n");
	}

	public class ServerShutdownHandler implements Runnable{
		
		public void run(){
			serverShutdown.setShutdown(true);
			MainView.getLogging().append(
					serverShutdown.getName() + " is closing at time " + String.format("%tT", new Date(System.currentTimeMillis())) + "\n");
			try {
				threadServerShutdown.join();
			} catch (InterruptedException e) {
				return;
			}
			MainView.getLogging().append(
					serverShutdown.getName() + " has been closed at time " + String.format("%tT", new Date(System.currentTimeMillis())) + "\n");
			for (Task t : serverShutdown.getTasks()) {
				t.setRescheduled(true);
				TaskScheduler.getInstance().receiveTask(t);
			}
			TaskScheduler.getInstance().getServers().remove(serverShutdown);
		}
		
	}
	
	public long getMinServiceTime() {
		return minServiceTime;
	}

	public void setMinServiceTime(long minServiceTime) {
		this.minServiceTime = minServiceTime;
	}

	public long getMaxServiceTime() {
		return maxServiceTime;
	}

	public void setMaxServiceTime(long maxServiceTime) {
		this.maxServiceTime = maxServiceTime;
	}

	public long getMinArrivalInterval() {
		return minArrivalInterval;
	}

	public void setMinArrivalInterval(long minArrivalInterval) {
		this.minArrivalInterval = minArrivalInterval;
	}

	public long getMaxArrivalInterval() {
		return maxArrivalInterval;
	}

	public void setMaxArrivalInterval(long maxArrivalInterval) {
		this.maxArrivalInterval = maxArrivalInterval;
	}

}
