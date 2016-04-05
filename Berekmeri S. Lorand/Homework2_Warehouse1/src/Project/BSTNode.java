package Project;

public class BSTNode {

	Comparable val;
	BSTNode left;
	BSTNode right; 
	BSTNode parent;
	/**
     * First node constructor
     */
	BSTNode(Comparable newVal) {
		val = newVal;
		parent = null;
		left = right = null;
	}
	/**
     * Constructor for the rest BST table
     */
	BSTNode(Comparable newVal, BSTNode p) {
		val = newVal;
		parent = p;
		left = right = null;
	}

}
