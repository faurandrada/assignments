
public class Task {

	private int arrivalTime; // time at which the client arrives
	private int serviceTime; // duration of the service
	private int finishTime; //  f = a + w +s
	private int waitingTime; //time between arriving and leaving						
	
    //each task is generated with an arrival time and a service time
	public Task(int arrivalTime, int serviceTime) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}

	public int getWaitingTime() {
		return finishTime - arrivalTime - serviceTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
//////////////////////////////////////////////////////
	public int getFinishTime(){
		return arrivalTime + waitingTime + serviceTime;
	}
///////////////////////////////////////////////////////
	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public String toString(){
		return "Arrival time: " + arrivalTime+" "+"Waiting time: "+ waitingTime+" "+"Service time: "+ serviceTime+" "+"Finish time: "+ finishTime;
	}
}
