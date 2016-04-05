package pt.processingQueues.principal;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * separate thread that knows to produce a client at a certain interval between
 * MinArrivalTime and MaxArraivalTime(given by user that performs the
 * simulation)
 * 
 * @author Chiti
 *
 */
public class ClientGenerator implements Runnable {
	private static final Logger LOGGER = Logger.getLogger(ClientGenerator.class.getName());
	private Scheduler scheduler;
	private int maxArrivalTime;
	private int minArrivalTime;
	private int maxServiceTime;
	private int minServiceTime;
	private int clientsGenerated = 0;
	private int clientsGeneratedSince = 0;
	private boolean isRunning;

	public ClientGenerator(int maxArrivalTime, int minArrivalTime, int maxServiceTime, int minServiceTime,Scheduler scheduler) {
		this.scheduler=scheduler;
		this.maxArrivalTime = maxArrivalTime;
		this.minArrivalTime = minArrivalTime;
		this.maxServiceTime = maxServiceTime;
		this.minServiceTime = minServiceTime;
		this.isRunning = true;
	}

	@Override
	public void run() {
		LOGGER.info("Logger Name: "+LOGGER.getName());
		 Random r = new Random();
	        int arrivalTime;
	        int processingTime;
	        while (true) {
	            while (isRunning) {
	                arrivalTime = r.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
	                try {
	                    Thread.sleep(arrivalTime * 1000);
	                    processingTime = r.nextInt(maxServiceTime - minServiceTime) + minServiceTime;
	                    clientsGenerated++;
	                    clientsGeneratedSince++;
	                    scheduler.addClient(new Client(clientsGenerated, processingTime));
	                } catch (InterruptedException ex) {
	                	LOGGER.log(Level.SEVERE, "Cannot add generated client", ex);
	                }
	            }
	        }
	}

	/**
	 * @return the clientsGenerated
	 */
	public int getClientsGenerated() {
		return clientsGenerated;
	}

	/**
	 * @return the clientsGeneratedSince
	 */
	public int getClientsGeneratedSince() {
		int returnNumber=clientsGeneratedSince;
		clientsGeneratedSince=0;
		return returnNumber;
	}

	/**
	 * @return the isRunning
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * @param isRunning
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
