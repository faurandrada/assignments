package app.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import app.model.Order;
import app.model.tree.BST;
import app.model.tree.Node;
import app.model.tree.OrderNode;

/**
 * Represents the Order Processing Department which is in charge of processing
 * the order of our application. This class uses a Binary Search Tree in order
 * to store the orders. We also provide the methods in order to interact with
 * the tree.
 * 
 * @author Bogdan
 *
 */
public class OPDept {

	private BST orderTree;

	public OPDept() {
		loadDataFromDisk();
		if (orderTree == null) {
			orderTree = new BST();
		}
	}

	private void loadDataFromDisk() {
		try {
			FileInputStream fileIn = new FileInputStream("C:\\orders.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.orderTree = (BST) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println("File not found");
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("OrderTree class was not found");
			c.printStackTrace();
			return;
		}
	}

	public void addNewOrder(Order order) {
		OrderNode orderNode = new OrderNode(order);
		orderTree.insert(orderNode);
	}

	public List<Node> getAllOrders() {
		return orderTree.getAllNodes(orderTree.root);
	}

	public Order getOrderByName(String name) {
		for (Node current : getAllOrders()) {
			if (current.getNodeName().equals(name)) {
				if (current instanceof OrderNode) {
					return ((OrderNode) current).getOrder();
				}
			}
		}
		return null;
	}

	public String getOrderName(OrderNode orderNode) {
		return orderNode.getOrderName();
	}

	public int getOrderQuantity(OrderNode orderNode) {
		return orderNode.getOrderQuantity();
	}

	public BST getTree() {
		return orderTree;
	}

}
