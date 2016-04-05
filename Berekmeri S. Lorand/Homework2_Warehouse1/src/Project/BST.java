package Project;

import java.io.Serializable;

public class BST implements Serializable {

	private static final long serialVersionUID = 1L;
	BSTNode root;
	 /**
     * Initializes an empty symbol table.
     */
	public BST() {
		root = null; // radacina primeste initial valoarea 0
	}
	/**
     * Prints the values/elements in inorder way
     */
	public void print_inorder(BSTNode x) {
		if (x != null) {
			print_inorder(x.left);
			System.out.print(x.val.toString() + " ");
			print_inorder(x.right);
		}
	}

	public BSTNode tree_search_recursive(BSTNode x, Comparable k) {
		
		if ((x == null) && (k.compareTo(x.val) == 0))
			return x;
		else if (k.compareTo(x.val) < 0)
			//daca cheia e mai mica decat cea a nodului => left
			return tree_search_recursive(x.left, k);
		else
			return tree_search_recursive(x.right, k);
		//daca cheia e mai mare decat cea a nodului => dreapta
	}

	public BSTNode tree_search_iterative(BSTNode x, Comparable k) {

		while ((x != null) && (k.compareTo(x.val) != 0)) {
			if (k.compareTo(x.val) < 0)
				x = x.left;
			else
				x = x.right;
		}
		return x;
	}
	/**
     * Determines the minimum of the tree
     */
	public BSTNode tree_minimum(BSTNode x) {

		while (x.left != null)
			x = x.left;

		return x;

	}
	 /**
     * Determines the maximum of the tree
     */
	public BSTNode tree_maximum(BSTNode x) {
		while (x.right != null)
			x = x.right;

		return x;
	}

	public BSTNode tree_succesor(BSTNode x) {

		if (x.right != null)
			return tree_minimum(x.right);
		else {
			BSTNode y = x.parent;
			while ((y != null) && (x == y.right)) {
				x = y;
				y = y.parent;
			}
			return y;

		}
	}
	/**
     * Swaps the parent with the child only if the parent is null
     */
	public void transplant(BSTNode r, BSTNode u, BSTNode v) {
		//functie care interschimba parintele cu fiul 
		if (u.parent == null)
			r = v;
		else {
			if (u == u.parent.left) {
				u.parent.left = v;
			} else {
				u.parent.right = v;
			}
			if (v != null)
				v.parent = u.parent;
		}
	}
	/**
     * Removes the specified key and its associated value from the symbol table   
     * It begins by searching on the left hand side
     * If the one from left differs from null, then goes to right
     * When finds a node which equals null, it is swapped with the root
     */
	public BSTNode delete(BSTNode z) {
		if (z.left == null) {
			transplant(root, z, z.right);
			return z.right;
		} else {
			if (z.right == null) {
				transplant(root, z, z.left);
				return z.left;
			} else {
				BSTNode y = tree_minimum(z.right);
				if (y.parent != z) {
					transplant(root, y, y.right);
					y.right = z.right;
					y.right.parent = y;
				}
				transplant(root, z, y);
				y.left = z.left;
				y.left.parent = y;
				return y;

			}
		}
	}

	public void deleteValue(Comparable v) {
		if ((v != null) && (root.val != null)) {
			BSTNode z = tree_search_iterative(this.root, v);
			root = delete(root);
		}
	}
	/**
     * Inserts a key in the BST table   
     * It begins by adding on the left hand side
     * If the parent value is lower than 0 than adds on the left
     * else adds on the right 
     */
	public void insert(Comparable x) {
		if (root == null) {
			root = new BSTNode(x);
		} else {
			BSTNode aux = root;
			BSTNode parentAux = root;
			while (aux != null) {
				parentAux = aux;
				if (x.compareTo(aux.val) < 0) {
					aux = aux.left;
				} else {
					aux = aux.right;
				}
			}
			aux = new BSTNode(x, parentAux);
			if (x.compareTo(parentAux.val) < 0) {
				parentAux.left = aux;
			} else {
				parentAux.right = aux;
			}

		}
	}

}
