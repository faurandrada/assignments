package app.model.tree;

import app.model.Product;
/**
 * Class that implements Node for storing products in our Binary Search Tree
 * 
 * @author Bogdan
 * @see Node
 */
@SuppressWarnings("serial")
public class ProductNode implements Node {
	Product product;
	Node left;
	Node right;

	public ProductNode(Product product) {
		this.product = product;
		left = null;
		right = null;
	}

	@Override
	public int getNodeID() {
		return product.getProductID();
	}

	@Override
	public void updateNode(int stock) {
		product.setProductID(stock);
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
		return product.getName();
	}

	public Product getProduct() {
		return product;
	}
}