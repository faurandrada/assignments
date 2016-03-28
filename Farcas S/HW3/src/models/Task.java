package models;

/**
 * The customer class
 */
public class Task {

	private long arrivalTime;
	private long finishTime = -1;
	private long serviceTime;
	private long waitingTime = -1;
	private Server queue = null;
	private String name;
	
	public Task(long arrivalTime, long serviceTime, String name){
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public long getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}

	public long getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}

	public long getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(long waitingTime) {
		this.waitingTime = waitingTime;
	}

	public Server getQueue() {
		return queue;
	}

	public void setQueue(Server queue) {
		this.queue = queue;
	}
}
