public abstract class Node <T>{
	
	private Node<T> leftChild;
	private Node<T> rightChild;

	public Node(){
		leftChild=null;
		rightChild=null;
	}
	public abstract int getId();

	public Node<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node<T> node) {
		this.leftChild = node;
	}

	public Node<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node<T> rightChild) {
		this.rightChild = rightChild;
	}
}
