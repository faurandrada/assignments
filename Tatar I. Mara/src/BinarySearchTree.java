
public class BinarySearchTree<T> {

	private Node<T> root;
	
	public BinarySearchTree(){
		this.root=null;
	}
    public Node<T> getRoot(){
    	return root;
    }
	public void insertNode(Node<T> node){
		if (root==null){
			root=node;
			return;
		}
		else{
			Node<T> currentNode=root;
			Node<T> parent=currentNode;
			while (true){
				parent=currentNode;
				if (node.getId()<currentNode.getId()){
					currentNode=(Node<T>) currentNode.getLeftChild();
					if (currentNode==null){
						parent.setLeftChild(node);
						return;
					}
					
				}
				else{
					currentNode=(Node<T>) currentNode.getRightChild();
					if (currentNode==null){
						parent.setRightChild(node);
						return;
					}
				}
			}
		
	}
	}

	public boolean findNode(Node<T> node){
		Node<T> current=root;
		while (current!=null){
			if (current.getId()==node.getId()){
				return true;
			}
			else if (current.getId()>node.getId()){
				current=current.getLeftChild();
			}
			else{
				current=current.getRightChild();
			}
		}
		return false;
	}
	public boolean deleteNode(Node<T> node){
		Node<T> parent=root;
		Node<T> current=root;
		boolean isLeftChild=false;
		while (current.getId()!= node.getId()){
			parent=current;
			if (current.getId()>node.getId()){
				isLeftChild=true;
				current=current.getLeftChild();
			}
			else{
				isLeftChild=false;
				current=current.getRightChild();
			}
			if (current==null){
				return false;
			}
		}
		//if i am here that means we have found the node 
	    //Case 1: if node to be deleted has no children 
		if (current.getLeftChild()==null && current.getRightChild()==null){
			if (current==root){
				root=null;
			}
			if (isLeftChild==true){
				parent.setLeftChild(null);
			}
			else{
				parent.setRightChild(null);
			}
		}
		else if (current.getRightChild()==null){
			if (current==root){
				root=current.getLeftChild();
			}
			if (isLeftChild==true){
				parent.setLeftChild(current.getLeftChild());
			}
			else{
				parent.setRightChild(current.getLeftChild());
			}
		}
		else if (current.getLeftChild()==null){
			if (current==root){
				root=current.getRightChild();
			}
			if (isLeftChild==true){
				parent.setLeftChild(current.getRightChild());
			}
			else{
				parent.setRightChild(current.getRightChild());
			}
		}
		else if (current.getLeftChild()!=null && current.getRightChild()!=null){
			Node<T> successor=getSuccessor(current);
			if (current==root){
				root=successor;
			}
			else if(isLeftChild){
				parent.setLeftChild(successor);
			}
			else{
				parent.setRightChild(successor);
			}
			successor.setLeftChild(current.getLeftChild());
			
		}
		return true;	
		
		
	}
	public Node<T> getSuccessor(Node<T> deleleNode){ 
		 		Node<T> successsor =null; 
				Node<T> successsorParent =null; 
		     	Node<T> current = deleleNode.getRightChild() ;
		 		while(current!=null){ 
	      			successsorParent = successsor; 
		 			successsor = current; 
		 			current = current.getLeftChild(); 
		 		} 
		 		//check if successor has the right child, it cannot have left child for sure 
		 		// if it does have the right child, add it to the left of successorParent. 
		 //		successsorParent 
		 		if(successsor!=deleleNode.getRightChild()){ 
		 			successsorParent.setLeftChild(successsor.getRightChild()); 
		 			successsor.setRightChild(deleleNode.getRightChild()) ; 
		 		} 
				return successsor; 
		 	} 


	public void inOrderTraverseTree(Node<T> focusNode) {
			if (focusNode != null) {

			// Traverse the left node
			inOrderTraverseTree(focusNode.getLeftChild());

			// Visit the currently focused on node
			System.out.println(focusNode.toString());

			// Traverse the right node
			inOrderTraverseTree(focusNode.getRightChild());

		}

	}
	
}
