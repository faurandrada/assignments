package app.model.tree;

import java.io.Serializable;

/**
 * Interface that provides basic functionality for our tree elements
 * 
 * @author Bogdan
 * @see BST
 */
public interface Node extends Serializable {
	String getNodeName();

	int getNodeID();

	void updateNode(int stock);

	Node getLeft();

	Node getRight();

	void setLeft(Node left);

	void setRight(Node right);
}
