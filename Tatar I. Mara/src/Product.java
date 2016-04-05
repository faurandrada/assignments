import java.util.Random;

public class Product extends Node<Product>{
    private int ID;
	private String item;
	private String color;
	private String size;
	private int numberOfProducts;
	 
	
	public Product(String item,String color,String size,int numberOfProducts){
		super();
		Random rand = new Random();
		ID= rand.nextInt(1000) + 1;
		this.item=item;
		this.size=size;
		this.color=color;
		this.numberOfProducts=numberOfProducts;
		
	}
	
	  public boolean isSAmeProduct(String item,String color,String size){
			if ((item.compareTo(this.getItem())==0)&&(size.compareTo(this.getSize())==0)&&(color.compareTo(this.getColor())==0)){
				return true;
			}
			else return false;
			
		}
	public String getItem(){
		return item;
	}
	public String getSize(){
		return size;
	}
	public String getColor(){
		return color;
	}
	public int getNumberOfProducts(){
		return numberOfProducts;
	}
	public void setNumberOfProducts(int number){
		numberOfProducts=number;
	}
	 
	 
	@Override
	public int getId() {
		return ID;
	}
 
}
