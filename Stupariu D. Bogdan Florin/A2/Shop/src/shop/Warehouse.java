package shop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Warehouse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8091854333175849040L;
	Product root;

	public void write() {
		try (FileOutputStream fs = new FileOutputStream("text.ser")) {
			ObjectOutputStream os = new ObjectOutputStream(fs);

			ArrayList<Product> prodList = new ArrayList<Product>();
			prodList = toArray();
			
			for(Product p : prodList){
				System.out.println(p.getID()+" "+p.getName()+" "+p.getCategory()+" "+p.getPrice()+" "+p.getQuantity());
			}

			os.writeObject(prodList);

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
		try (FileInputStream fi = new FileInputStream("text.ser");) {
			ObjectInputStream os = new ObjectInputStream(fi);

			@SuppressWarnings("unchecked")
			ArrayList<Product> products = ((ArrayList<Product>) os.readObject());
			for (Product temp : products) {
				addNode(temp);
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

	public ArrayList<Product> toArray() {
		ArrayList<Product> result = new ArrayList<Product>();
		inOrderTraverseTree(root, result);
		return result;
	}

	public void addNode(Product product) {
		int key = product.getID();
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		int quantity = product.getQuantity();
		Product newNode = new Product(key, name, category, price, quantity);
		if (root == null) {
			root = newNode;
		} else {
			Product focusNode = root;
			Product parent;
			while (true) {
				parent = focusNode;
				if (name.compareToIgnoreCase(focusNode.getName()) < 0) {
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

	public void inOrderTraverseTree(Product focusNode, ArrayList<Product> result) {
		if (focusNode != null) {
			inOrderTraverseTree(focusNode.leftChild, result);
			result.add(focusNode);
			inOrderTraverseTree(focusNode.rightChild, result);
		}
	}

	public Product findNode(String name,Product node){
		
	    if(node != null){
	        if(node.getName().equals(name)){
	           return node;
	        } else {
	            Product foundNode = findNode(name, node.leftChild);
	            if(foundNode == null) {
	                foundNode = findNode(name, node.rightChild);
	            }
	            return foundNode;
	         }
	    } else {
	        return null;
	    }
	}

	public boolean remove(int key) {
		Product focusNode = root;
		Product parent = root;
		boolean isItALeftChild = true;
		while (focusNode.getID() != key) {
			parent = focusNode;
			if (key < focusNode.getID()) {
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
			Product replacement = getReplacementNode(focusNode);
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

	public Product getReplacementNode(Product replacedNode) {

		Product replacementParent = replacedNode;
		Product replacement = replacedNode;
		Product focusNode = replacedNode.rightChild;
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
	
	public Product getRoot(){
		return root;
	}

}
