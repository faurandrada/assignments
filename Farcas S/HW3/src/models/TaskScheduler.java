package models;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import views.MainView;

/**
 * The task scheduler class
 */
public class TaskScheduler {

	private static TaskScheduler instance;
	private long simulationTime;
	private long startTime = System.currentTimeMillis();
	private int numberOfServers = 3;
	private int serverNumber = 0;
	private ArrayList<Server> servers = new ArrayList<>();
	private ArrayList<Thread> runningServers = new ArrayList<>();
	private ArrayList<Task> customersArrived = new ArrayList<>();
	private int maxLoadPerServer;
	private Date peakHour = new Date();
	private AtomicInteger currentCustomers = new AtomicInteger(0);
	private int peakHourCustomers;

	private TaskScheduler() {
	}

	/**
	 * initialization
	 */
	public void start() {
		Operations.setCustomerHistory(new ArrayBlockingQueue<Task>(
				(int) (simulationTime / TaskGenerator.getInstance().getMinArrivalInterval())));
		for (int i = 1; i <= numberOfServers; i++) {
			Server s = new Server("Queue " + ++serverNumber, maxLoadPerServer);
			servers.add(s);
			Thread t = new Thread(s);
			runningServers.add(t);
			t.start();
		}
	}

	public static TaskScheduler getInstance() {
		if (instance == null) {
			instance = new TaskScheduler();
		}
		return instance;
	}

	public static void deleteInstance() {
		instance = null;
	}

	/**
	 * get queue with least customers
	 * @return
	 */
	public Server getServerWithMinNumberOfTasks() {
		int min = maxLoadPerServer;
		Server minServer = null;
		for (Server s : servers) {
			int size = s.getTasks().size();
			if (size < min) {
				min = size;
				minServer = s;
			}
		}
		return minServer;
	}

	/**
	 * the task receiver -> delegates tasks to servers
	 * @param task
	 */
	public void receiveTask(Task task) {
		MainView.getLogging().append(task.getName() + " has arrived. \n");
		System.out.println(task.getName() + " has arrived.");
		customersArrived.add(task);
		Operations.getCustomerHistory().add(task);
		currentCustomers.getAndIncrement();
		if (currentCustomers.get() > peakHourCustomers) {
			peakHourCustomers = currentCustomers.get();
			peakHour = new Date();
		}
		Server s = getServerWithMinNumberOfTasks();
		if (s != null) {
			try {
				Task taskToBeAdded = customersArrived.get(0);
				MainView.getLogging().append(taskToBeAdded.getName() + " has entered " + s.getName() + "\n");
				taskToBeAdded.setQueue(s);
				s.getTasks().put(taskToBeAdded);
				customersArrived.remove(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public long getSimulationTime() {
		return simulationTime;
	}

	public void setSimulationTime(long simulationTime) {
		this.simulationTime = simulationTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public int getNumberOfServers() {
		return numberOfServers;
	}

	public void setNumberOfServers(int numberOfServers) {
		this.numberOfServers = numberOfServers;
	}

	public int getMaxLoadPerServer() {
		return maxLoadPerServer;
	}

	public void setMaxLoadPerServer(int maxLoadPerServer) {
		this.maxLoadPerServer = maxLoadPerServer;
	}

	public AtomicInteger getCurrentCustomers() {
		return currentCustomers;
	}

	public void setCurrentCustomers(AtomicInteger currentCustomers) {
		this.currentCustomers = currentCustomers;
	}

	public Date getPeakHour() {
		return peakHour;
	}

	public int getPeakHourCustomers() {
		return peakHourCustomers;
	}

	public ArrayList<Thread> getRunningServers() {
		return runningServers;
	}

	public ArrayList<Server> getServers() {
		return servers;
	}

}
