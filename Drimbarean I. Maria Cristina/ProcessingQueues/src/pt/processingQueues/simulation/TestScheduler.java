package pt.processingQueues.simulation;

import pt.processingQueues.principal.Client;
import pt.processingQueues.principal.Scheduler;
import pt.processingQueues.principal.SupermarketCheckout;

public class TestScheduler {

	public static void main(String[] args) {
		SupermarketCheckout queue1=new SupermarketCheckout(1);
		SupermarketCheckout queue2=new SupermarketCheckout(2);
		Scheduler scheduler=new Scheduler();
		Thread schedulerThread=new Thread(scheduler);
		scheduler.addSupermarketCheckout(queue1);
		scheduler.addSupermarketCheckout(queue2);
		scheduler.addClient(new Client(1,2));
		scheduler.addClient(new Client(2,2));
		scheduler.addClient(new Client(3,2));
		scheduler.addClient(new Client(4,2));
		scheduler.addClient(new Client(5,2));
		Thread queue1Thread=new Thread(queue1);
	    Thread queue2Thread=new Thread(queue2);
	    schedulerThread.start();
	    queue1Thread.start();
	    queue2Thread.start();
	}

}
