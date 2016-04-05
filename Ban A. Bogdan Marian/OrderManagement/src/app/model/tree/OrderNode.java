package app.model.tree;

import app.model.Order;

/**
 * Class that implements Node for storing orders in our Binary Search Tree
 * 
 * @author Bogdan
 * @see Node
 */
@SuppressWarnings("serial")
public class OrderNode implements Node {
	Order order;
	Node left;
	Node right;

	public OrderNode(Order order) {
		this.order = order;
		left = null;
		right = null;
	}

	@Override
	public int getNodeID() {
		return order.getOrderID();
	}

	@Override
	public void updateNode(int quantity) {
		order.setQuantity(quantity);
	}

	@Override
	public Node getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public Node getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public void setLeft(Node left) {
		this.left = left;
	}

	@Override
	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String getNodeName() {

		return order.getProduct() + "";
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOrderName() {
		return order.getProduct().getName();
	}

	public int getOrderQuantity() {
		return order.getQuantity();
	}

}