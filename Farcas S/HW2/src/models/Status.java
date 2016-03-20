package models;

public class Status {

	private long timeReceived;
	private long timeProcessed;
	private long timeDelivered;
	private long timeFailed;
	private String status;

	public Status(long timeReceived, long timeProcessed, long timeDelivered, long timeFailed) {
		this.timeReceived = timeReceived;
		this.timeProcessed = timeProcessed;
		this.timeDelivered = timeDelivered;
		this.timeFailed = timeFailed;
		status = "pending";
		processStatus();
	}

	public void processStatus() {
		int random;
		if (System.currentTimeMillis() - timeReceived > 5000){
			status = "processing";
		}
		timeProcessed = timeReceived + 5000;
		if ((System.currentTimeMillis() - timeProcessed > 5000) && (timeDelivered == 0 && timeFailed == 0)) {
			random = (int) (Math.random() * 10);
			if (random <= 8) {
				timeDelivered = timeProcessed + 5000;
				timeFailed = 0;
				status = "delivered";
			} else {
				timeDelivered = 0;
				timeFailed = timeProcessed + 5000;
				status = "failed";
			}
		}
		if (timeDelivered != 0) {
			status = "delivered";
		}
		if (timeFailed != 0) {
			status = "failed";
		}
	}

	public long getTimeFailed() {
		return timeFailed;
	}

	public void setTimeFailed(int timeFailed) {
		this.timeFailed = timeFailed;
	}

	public long getTimeReceived() {
		return timeReceived;
	}

	public void setTimeReceived(int timeReceived) {
		this.timeReceived = timeReceived;
	}

	public long getTimeProcessed() {
		return timeProcessed;
	}

	public void setTimeProcessed(int timeProcessed) {
		this.timeProcessed = timeProcessed;
	}

	public long getTimeDelivered() {
		return timeDelivered;
	}

	public void setTimeDelivered(int timeDelivered) {
		this.timeDelivered = timeDelivered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
