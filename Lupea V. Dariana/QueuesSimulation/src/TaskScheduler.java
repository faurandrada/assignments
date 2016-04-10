import java.util.ArrayList;
import java.util.List;

/*
 * This class is responsible with servers management
 */
public class TaskScheduler implements Runnable {

	private List<Server> servers;
	private int noOfQueues;
	
	public TaskScheduler(int noOfQueues) {
		servers = new ArrayList<Server>(noOfQueues);
	}

	public void scheduleTasksPerServer(Task t) {
		int minTasks = 10000;
		int currTasks;
		int availableServer = 0;

		if (servers.size() > 1) {// as long as at least 2 servers are available
			// find the server where the task can be sent
			for (int i = 0; i < servers.size(); i++) {
				currTasks = (servers.get(i)).getNumberOfTasks();
				if (currTasks < minTasks) {
					minTasks = currTasks;
					availableServer = i;
				}
			}
			servers.get(availableServer).addTaskToQueue(t);
		} else if (servers.isEmpty()) {
			servers.get(0).addTaskToQueue(t);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
