package shop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class OPDept implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2252946041952042992L;
	Order root;

	public void write() {
		try (FileOutputStream fs = new FileOutputStream("order.ser")) {
			ObjectOutputStream os = new ObjectOutputStream(fs);

			ArrayList<Order> orderList = new ArrayList<Order>();
			orderList = toArray();

			os.writeObject(orderList);

			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void read() {
		try (FileInputStream fi = new FileInputStream("order.ser");) {
			ObjectInputStream os = new ObjectInputStream(fi);

			@SuppressWarnings("unchecked")
			ArrayList<Order> orders = ((ArrayList<Order>) os.readObject());
			for (Order temp : orders) {
				// if (temp != null) {
				// if (findNode(temp.getName()) == null) {
				addNode(temp);
				// }
				// }
			}

			os.close();
		} catch (

		FileNotFoundException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (

		IOException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (

		ClassNotFoundException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Order> toArray() {
		ArrayList<Order> result = new ArrayList<Order>();
		inOrderTraverseTree(root, result);
		return result;
	}

	public void addNode(Order order) {

		int orderNumber = order.getOrderNumber();
		String orderDate = order.getDate();
		String user = order.getUser();
		int quantity = order.getQuantity();
		String product = order.getProduct();
		int price = order.getPrice();

		Order newNode = new Order(orderNumber, orderDate, user, quantity, product, price);

		if (root == null) {
			root = newNode;
		} else {
			Order focusNode = root;
			Order parent;
			while (true) {
				parent = focusNode;
				if (user.compareToIgnoreCase(focusNode.getUser()) < 0) {
					focusNode = focusNode.leftChild;
					if (focusNode == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					focusNode = focusNode.rightChild;
					if (focusNode == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public void inOrderTraverseTree(Order focusNode, ArrayList<Order> result) {
		if (focusNode != null) {
			inOrderTraverseTree(focusNode.leftChild, result);
			result.add(focusNode);
			inOrderTraverseTree(focusNode.rightChild, result);
		}
	}

	public Order findNode(String name) {
		Order focusNode = root;
		while (focusNode.getUser().equalsIgnoreCase(name)) {
			if (name.compareToIgnoreCase(focusNode.getUser()) < 0) {
				focusNode = focusNode.leftChild;
			} else {
				focusNode = focusNode.rightChild;
			}
			if (focusNode == null)
				return null;
		}
		return focusNode;
	}

	public boolean remove(int key) {
		Order focusNode = root;
		Order parent = root;
		boolean isItALeftChild = true;
		while (focusNode.getOrderNumber() != key) {
			parent = focusNode;
			if (key < focusNode.getOrderNumber()) {
				isItALeftChild = true;
				focusNode = focusNode.leftChild;
			} else {
				isItALeftChild = false;
				focusNode = focusNode.rightChild;
			}
			if (focusNode == null)
				return false;
		}
		if (focusNode.leftChild == null && focusNode.rightChild == null) {
			if (focusNode == root)
				root = null;
			else if (isItALeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;

		} else if (focusNode.rightChild == null) {
			if (focusNode == root)
				root = focusNode.leftChild;
			else if (isItALeftChild)
				parent.leftChild = focusNode.leftChild;
			else
				parent.rightChild = focusNode.leftChild;
		}

		else if (focusNode.leftChild == null) {
			if (focusNode == root)
				root = focusNode.rightChild;
			else if (isItALeftChild)
				parent.leftChild = focusNode.rightChild;
			else
				parent.rightChild = focusNode.rightChild;
		} else {
			Order replacement = getReplacementNode(focusNode);
			if (focusNode == root)
				root = replacement;
			else if (isItALeftChild)
				parent.leftChild = replacement;
			else
				parent.rightChild = replacement;
			replacement.leftChild = focusNode.leftChild;
		}
		return true;
	}

	public Order getReplacementNode(Order replacedNode) {

		Order replacementParent = replacedNode;
		Order replacement = replacedNode;
		Order focusNode = replacedNode.rightChild;
		while (focusNode != null) {
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.leftChild;
		}
		if (replacement != replacedNode.rightChild) {
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
		}
		return replacement;
	}
}
