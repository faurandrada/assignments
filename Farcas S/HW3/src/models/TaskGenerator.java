package models;

import java.util.Random;

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

	private TaskGenerator() {
	}

	public static TaskGenerator getInstance() {
		if (instance == null) {
			instance = new TaskGenerator();
		}
		return instance;
	}

	public static void deleteInstance(){
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
		while (System.currentTimeMillis() <= simulationTime + startTime) {
			TaskScheduler.getInstance().receiveTask(generateTask());
			try {
				Thread.sleep(((long) (random.nextDouble() * (maxArrivalInterval - minArrivalInterval + 1)))
						+ minArrivalInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(Thread t: TaskScheduler.getInstance().getRunningServers()){
				t.interrupt();
		}
		MainView.getLogging().append(String.format("Peak hour: %tT with number of customers: %d\n", TaskScheduler.getInstance().getPeakHour(), TaskScheduler.getInstance().getPeakHourCustomers()));
		MainView.getLogging().append("Average service time for simulation interval: " + Operations.getAverageServiceTime()/1000 + "\n");
		MainView.getLogging().append("Average waiting time for simulation interval: " + Operations.getAverageWaitingTime()/1000 + "\n");
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
