import java.io.Serializable;

public class OPDept extends BinarySearchTree<Order> implements Serializable{

	
	public void addOrder(Order order){
		this.insertNode(order);
	}
	
}
