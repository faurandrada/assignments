package pt.processingQueues.simulation;

import pt.processingQueues.principal.Client;
import pt.processingQueues.principal.SupermarketCheckout;

public class TestQueue {
	public static void main(String args[]){
	  SupermarketCheckout queue1=new SupermarketCheckout(1);
	   SupermarketCheckout queue2=new SupermarketCheckout(2);
	   queue1.addClient(new Client(1,1));
	   queue1.addClient(new Client(2,2));
	   queue1.addClient(new Client(3,1));
	   queue2.addClient(new Client(4,1));
	   queue2.addClient(new Client(5,1));
	   queue2.addClient(new Client(6,1));
	   //System.out.println(queue1.listToString());
	   //System.out.println(queue2.listToString());
	   Thread queue1Thread=new Thread(queue1);
       Thread queue2Thread=new Thread(queue2);
	   queue1Thread.start();
      queue2Thread.start();
      System.out.println(queue1.avgServiceTime()+ " "+queue1.avgWaitingTime());
      System.out.println(queue2.avgServiceTime()+ " "+queue2.avgWaitingTime());
       }
}
