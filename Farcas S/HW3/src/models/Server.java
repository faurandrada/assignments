package models;

import java.util.concurrent.ArrayBlockingQueue;

import views.MainView;

/**
 * The queue thread
 *
 */
public class Server implements Runnable {

	private ArrayBlockingQueue<Task> tasks;
	private String name;

	public Server(String name, int maxLoadPerServer) {
		this.tasks = new ArrayBlockingQueue<>(maxLoadPerServer);
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Task task = tasks.peek();
				if (task != null) {
					task.setWaitingTime(System.currentTimeMillis() - task.getArrivalTime());
					MainView.getLogging().append(task.getName() + " is being served at " + name + "\n");
					Thread.sleep(task.getServiceTime());
					task.setFinishTime(System.currentTimeMillis());
					MainView.getLogging().append(task.getName() + " has been served." + "\n");
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

}
