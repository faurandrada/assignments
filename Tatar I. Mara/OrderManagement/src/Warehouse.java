import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Warehouse extends BinarySearchTree<Product> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Product> products;
    Product p;
	public Warehouse(){
		products=new ArrayList<Product>();
	}
    public List<Product> getList(){
    	return products;
    }
	public void addType(Product product) {
		this.insertNode(product);

	}

	public void removeType(Product product) {
	this.deleteNode(product);

	}

	public void addProduct(Product product) {
		if (this.findNode(product)) {

			int aux = product.getNumberOfProducts() + 1;
			product.setNumberOfProducts(aux);

		}
		else addType(product);
	}
	public boolean deleteProduct(Product product) {
		if (this.findNode(product)) {
            if (product.getNumberOfProducts()>0){
			int aux = product.getNumberOfProducts() - 1;
			product.setNumberOfProducts(aux);
			return true;
            }
            else removeType(product);
		}
		else removeType(product);
		return false;
	}

   public void deleteCorrespondingProduct(String item,String size,String color,Product product){
		if (product != null) {

			deleteCorrespondingProduct(item,size,color,(Product)product.getLeftChild());

			if (product.isSAmeProduct(item, color, size)){
				removeType(product);
                 return;
			}
			
			deleteCorrespondingProduct(item,size,color,(Product)product.getRightChild());

		}
   }
   public void getProductsInOrder(Product product){
		if (product != null) {

			getProductsInOrder((Product)product.getLeftChild());

			products.add(product);
			
			getProductsInOrder((Product)product.getRightChild());

		}
  }
   public void updateCorrespondingProduct(String item,String size,String color,int number,Product product){
		if (product != null) {

			deleteCorrespondingProduct(item,size,color,(Product)product.getLeftChild());

			if (product.isSAmeProduct(item, color, size)){
				 product.setNumberOfProducts(number);
                 return;
			}
			
			deleteCorrespondingProduct(item,size,color,(Product)product.getRightChild());

		}
   }
   public Product findProduct(String item,String size,String color){
	   Product root=(Product) this.getRoot();
	   traverse(item,size,color,root);
	   return p;
   }
   public void traverse(String item,String size,String color,Product focusNode){
	  
	 if (focusNode!=null){
		 
	 traverse(item,size,color,(Product)focusNode.getLeftChild());
		  
		  
	  if (focusNode.isSAmeProduct(item, color, size)){
		  if (focusNode.getNumberOfProducts()<1){
			  removeType(focusNode);
			 
			  
		  }
		  else if (focusNode.getNumberOfProducts()>=1){
		  focusNode.setNumberOfProducts(focusNode.getNumberOfProducts()-1);
		  p=focusNode;
		  return;
		 
		  }
	  	
	 }
	 traverse(item,size,color,(Product)focusNode.getRightChild());
		 
}
}}