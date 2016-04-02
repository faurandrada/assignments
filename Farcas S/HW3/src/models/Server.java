package models;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

import views.MainView;

/**
 * The queue thread
 *
 */
public class Server implements Runnable {

	private ArrayBlockingQueue<Task> tasks;
	private String name;
	private volatile boolean isShutdown = false;

	public Server(String name, int maxLoadPerServer) {
		this.tasks = new ArrayBlockingQueue<>(maxLoadPerServer);
		this.name = name;
	}

	@Override
	public void run() {
		while (!(isShutdown)) {
			try {
				Task task = tasks.peek();
				if (task != null) {
					task.setWaitingTime(System.currentTimeMillis() - task.getArrivalTime());
					MainView.getLogging().append(task.getName() + " is being served at " + name + " at time " + String.format("%tT", new Date(System.currentTimeMillis())) + "\n");
					Thread.sleep(task.getServiceTime());
					task.setFinishTime(System.currentTimeMillis());
					MainView.getLogging().append(task.getName() + " has been served at time " + String.format("%tT", new Date(System.currentTimeMillis())) + "\n");
					tasks.poll();
					TaskScheduler.getInstance().getCurrentCustomers().getAndDecrement();
				}
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	public ArrayBlockingQueue<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayBlockingQueue<Task> tasks) {
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setShutdown(boolean isShutdown) {
		this.isShutdown = isShutdown;
	}

	public boolean isShutdown() {
		return isShutdown;
	}
	
}
