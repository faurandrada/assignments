package pt.processingQueues.principal;
/**
 * Represents a client that needs to be served at a 
 * @author Chiti
 *
 */
public class Client {
  private int id;
  private int serviceTime;
  public Client(){}
  public Client(int id,int serviceTime){
	  this.id=id;
	  this.serviceTime=serviceTime;
  }
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @return the serviceTime
 */
public int getServiceTime() {
	return serviceTime;
}
/** 
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "[Client" + id + "]";
}
}
