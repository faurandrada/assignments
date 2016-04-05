package pt.processingQueues.simulation;

import pt.processingQueues.principal.*;

public class Simulation {

	public static void main(String[] args) {
	   SupermarketCheckout queue1=new SupermarketCheckout(1);
	   SupermarketCheckout queue2=new SupermarketCheckout(2);
	   Scheduler scheduler=new Scheduler();
	   scheduler.addSupermarketCheckout(queue1);
	   scheduler.addSupermarketCheckout(queue2);
	   ClientGenerator generator=new ClientGenerator(3,1,7,5,scheduler);
       Thread schedulerThread=new Thread(scheduler);
       Thread clientGeneratorThread=new Thread(generator);
       Thread queue1Thread=new Thread(queue1);
       Thread queue2Thread=new Thread(queue2);
       schedulerThread.start();
       queue1Thread.start();
       queue2Thread.start();
       clientGeneratorThread.start();
       
	}

}
