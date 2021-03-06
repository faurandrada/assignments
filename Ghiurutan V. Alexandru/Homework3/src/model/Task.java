package model;

import controller.HelperTask;

public class Task {
	private String name;
	private static int number = 0;
	private int arrivalTime;
	private int serviceTime;
	private int finishTime;
	private int waitingTime;
	private HelperTask helper;

	public Task(int arrivalTime, int serviceTime) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.name = "Task " + number++;
		helper = new HelperTask();
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public void computeWaitingTime() {
		this.arrivalTime = helper.getWaitingTime(arrivalTime, finishTime, serviceTime);
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	@Override
	public String toString() {
		return String.format("%s arrival time: %d, service time: %d, waiting time: %d, finish time: %d.\n", name,
				arrivalTime, serviceTime, waitingTime, finishTime);
	}
}
