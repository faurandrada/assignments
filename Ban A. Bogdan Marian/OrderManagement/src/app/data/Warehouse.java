package app.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import app.model.Product;
import app.model.tree.BST;
import app.model.tree.Node;
import app.model.tree.ProductNode;

/**
 * Represents the Warehouse which is responsible for keeping the Products and
 * their corresponding stock. This class uses a Binary Search Tree in order to
 * store the products. We also provide means to create, updated and delete
 * products.
 * 
 * @author Bogdan
 *
 */
public class Warehouse {

	private BST tree;

	public BST getTree() {
		return tree;
	}

	public Warehouse() {
		loadDataFromDisk();
		if (tree == null) {
			tree = new BST();
			Product whiteTShirt = new Product(1, "Milk", "great milk with low fat", 0);
			Product redTShirt = new Product(2, "Tomatoes", "big juicy and tasty tomatoes", 12);
			ProductNode p1 = new ProductNode(whiteTShirt);
			ProductNode p2 = new ProductNode(redTShirt);
			this.tree.insert(p1);
			this.tree.insert(p2);
			// this.tree.display(tree.root);
		}

	}

	private void loadDataFromDisk() {
		try {
			FileInputStream fileIn = new FileInputStream("C:\\products.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.tree = (BST) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println("FIle not found");
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Tree class was not found");
			c.printStackTrace();
			return;
		}

	}

	public void addNewProduct(Product newProduct) {
		ProductNode productNode = new ProductNode(newProduct);
		tree.insert(productNode);
	}

	public void updateProduct(int productID, int stock) {
		tree.update(productID, stock);
	}

	public List<Node> getAllProducts() {
		return tree.getAllNodes(tree.root);
	}

	public List<Node> getProductsByFilter(String filter) {
		List<Node> allItems = getAllProducts();
		List<Node> result = new ArrayList<>();
		for (Node currentNode : allItems) {
			if (currentNode.getNodeName().toLowerCase().contains(filter.toLowerCase())) {
				result.add(currentNode);
			}
		}
		return result;
	}

	public void deleteProduct(Product product) {
		tree.delete(product);
	}

	public Product getProductByName(String name) {
		for (Node current : getAllProducts()) {
			if (current.getNodeName().equals(name)) {
				if (current instanceof ProductNode) {
					return ((ProductNode) current).getProduct();
				}
			}
		}
		return null;
	}

	public int getProductStockByName(String name) {
		for (Node current : getAllProducts()) {
			if (current.getNodeName().equals(name)) {
				if (current instanceof ProductNode) {
					return ((ProductNode) current).getProduct().getStock();
				}
			}
		}
		return -1;
	}

	public int getMaxProductID() {
		int max = 0;
		for (Node current : getAllProducts()) {
			if (current.getNodeID() > max) {
				max = current.getNodeID();
			}
		}
		return max;
	}

}
