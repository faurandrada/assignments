package app.model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.model.Product;

/**
 * Class responsible for creating a Binary Search Tree. Each tree item is
 * represented by a Node instance
 * 
 * @author Bogdan
 * @see Node
 */
@SuppressWarnings("serial")
public class BST implements Serializable {
	public Node root;

	public BST() {
		root = null;
	}

	public boolean find(Node node) {
		Node current = root;
		while (current != null) {
			if (current.getNodeID() == node.getNodeID()) {
				return true;
			} else if (current.getNodeID() > node.getNodeID()) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return false;
	}

	public boolean update(int productID, int stock) {
		Node current = root;
		while (current != null) {
			if (current.getNodeID() == productID) {
				current.updateNode(stock);
				return true;
			} else if (current.getNodeID() > productID) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}
		return false;
	}

	public boolean delete(Product node) {
		Node parent = root;
		Node current = root;
		boolean isLeftChild = false;
		while (current.getNodeID() != node.getProductID()) {
			parent = current;
			if (current.getNodeID() > node.getProductID()) {
				isLeftChild = true;
				current = current.getLeft();
			} else {
				isLeftChild = false;
				current = current.getRight();
			}
			if (current == null) {
				return false;
			}
		}
		if (current.getLeft() == null && current.getRight() == null) {
			if (current == root) {
				root = null;
			}
			if (isLeftChild == true) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		} else if (current.getRight() == null) {
			if (current == root) {
				root = current.getLeft();
			} else if (isLeftChild) {
				parent.setLeft(current.getLeft());
			} else {
				parent.setRight(current.getLeft());
			}
		} else if (current.getLeft() == null) {
			if (current == root) {
				root = current.getRight();
			} else if (isLeftChild) {
				parent.setLeft(current.getRight());
			} else {
				parent.setRight(current.getRight());
			}
		} else if (current.getLeft() != null && current.getRight() != null) {

			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.setLeft(successor);
			} else {
				parent.setRight(successor);
			}
			successor.setLeft(current.getLeft());
		}
		return true;
	}

	public Node getSuccessor(Node deleleNode) {
		Node successsor = null;
		Node successsorParent = null;
		Node current = deleleNode.getRight();
		while (current != null) {
			successsorParent = successsor;
			successsor = current;
			current = current.getLeft();
		}
		if (successsor != deleleNode.getRight()) {
			successsorParent.setLeft(successsor.getLeft());
			successsor.setRight(deleleNode.getRight());
		}
		return successsor;
	}

	public void insert(Node node) {
		Node newNode = node;
		if (root == null) {
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (node.getNodeID() < current.getNodeID()) {
				current = current.getLeft();
				if (current == null) {
					parent.setLeft(newNode);
					return;
				}
			} else {
				current = current.getRight();
				if (current == null) {
					parent.setRight(newNode);
					return;
				}
			}
		}
	}

	public void display(Node root) {
		if (root != null) {
			display(root.getLeft());
			System.out.print(" " + root.getNodeID());
			display(root.getRight());
		}
	}

	public List<Node> getAllNodes(Node root) {
		List<Node> result = new ArrayList<>();
		if (root != null) {
			result.addAll(getAllNodes(root.getLeft()));
			result.add(root);
			result.addAll(getAllNodes(root.getRight()));
		}
		return result;
	}
}
