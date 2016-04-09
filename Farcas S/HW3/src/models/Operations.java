package models;

import java.util.concurrent.*;

/**
 * 
 * class used for determining avg service time and avg waiting time
 *
 */
public class Operations {

	private static ArrayBlockingQueue<Task> customerHistory;

	/**
	 * Get the average service time
	 * @return the average service time
	 */
	public static long getAverageServiceTime() {
		long totalServiceTime = 0;
		long numberOfCustomers = 0;
		long startTime = TaskScheduler.getInstance().getStartTime();
		long finishTime = TaskScheduler.getInstance().getSimulationTime() + startTime;
		for (Task t : customerHistory) {
			if ((t.getArrivalTime() > startTime) && (t.getWaitingTime() + t.getArrivalTime() < finishTime)
					&& (t.getWaitingTime() != -1)) {
				totalServiceTime += t.getServiceTime();
				numberOfCustomers++;
			}
		}
		return totalServiceTime / numberOfCustomers;
	}

	/**
	 * get the average waiting time
	 * @return the average waiting time
	 */
	public static long getAverageWaitingTime() {
		long totalWaitingTime = 0;
		long numberOfCustomers = 0;
		long startTime = TaskScheduler.getInstance().getStartTime();
		long finishTime = TaskScheduler.getInstance().getSimulationTime() + startTime;
		for (Task t : customerHistory) {
			if ((t.getArrivalTime() > startTime) && (t.getWaitingTime() + t.getArrivalTime() < finishTime)
					&& (t.getWaitingTime() != -1)) {
				totalWaitingTime += t.getWaitingTime();
				numberOfCustomers++;
			}
		}
		return totalWaitingTime / numberOfCustomers;
	}

	public static ArrayBlockingQueue<Task> getCustomerHistory() {
		return customerHistory;
	}

	public static void setCustomerHistory(ArrayBlockingQueue<Task> customerHistory) {
		Operations.customerHistory = customerHistory;
	}

}
