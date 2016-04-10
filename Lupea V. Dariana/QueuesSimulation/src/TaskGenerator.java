import java.util.concurrent.ThreadLocalRandom;

public class TaskGenerator {

	private int minArrival, maxArrival, minService, maxService;
    private int noOfQueues;
	private int simulationInterval;

	public TaskGenerator(int minArrival, int maxArrival, int minService, int maxService,int noOfQueues, int simulationInterval) {
		this.minArrival = minArrival;
		this.maxArrival = maxArrival;
		this.minService = minService;
		this.maxService = maxService;
		this.noOfQueues = noOfQueues;
		this.simulationInterval = simulationInterval;
		TaskScheduler scheduler = new TaskScheduler(noOfQueues);// here is set the number of queues --> servers

	}

	// Generate tasks for a given time
	public void generateTasks() {
		int randomArrival = ThreadLocalRandom.current().nextInt(minArrival, maxArrival);
		int randomService = ThreadLocalRandom.current().nextInt(minService, maxService);
		Task newTask = new Task(randomArrival, randomService);
	}
}
