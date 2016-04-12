
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {

	private BlockingQueue<Task> tasksQueue;
	private AtomicInteger waitingTime;
	
	private final static Logger LOGGER = Logger.getLogger(Server.class.getName());

	public Server() {
		tasksQueue = new LinkedBlockingQueue<Task>();// a server has several
														// tasks
		waitingTime = new AtomicInteger(0);// each server has the initial
											// waiting time set to 0
	}

	//The server receives a task
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (tasksQueue.size() > 0){//as long as there are tasks waiting
		Task currTask = null;
		try {
			currTask = tasksQueue.take();
			LOGGER.setLevel(Level.INFO);
			LOGGER.info("Currently at server:" + currTask);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}

	public AtomicInteger getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(AtomicInteger waitingTime) {
		this.waitingTime = waitingTime;
	}

	public BlockingQueue<Task> getTasksQueue() {
		return tasksQueue;
	}

	public void setTasksQueue(BlockingQueue<Task> tasksQueue) {
		this.tasksQueue = tasksQueue;
	}

	//// to control the max number of tasks per server////
	public int getNumberOfTasks() {
		return tasksQueue.size();
	}

	public boolean isEmtpyServer() {
		return tasksQueue.isEmpty();
	}

	//// takes the first task from the queue////
	public Task getTaskFromServer() {
		try {
			return tasksQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//// the task is added at the end of the queue/////
	public void addTaskToQueue(Task t) {
		try {
			tasksQueue.put(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
