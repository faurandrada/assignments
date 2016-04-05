	import java.io.*;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;
public class DeserializeDemo {

	   public Warehouse deserializeWarehouse()
	   {
	     Warehouse e=null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("E:/eclipse_workspace/OrderManagement/resources/warehouse.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (Warehouse) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	     
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Warehouse class not found");
	         c.printStackTrace();
	        
	      }
	      e.getProductsInOrder((Product) e.getRoot());
	       

			for (Product p:e.getList()) {
				Object[] row = { p.getItem(), p.getSize(), p.getColor(),p.getNumberOfProducts() };
				
					((DefaultTableModel) AdminView.getModel()).addRow(row);
			        
					
			}

		
		return e;
	      
	    }
	   public OPDept deserializeOPDept()
	   {
	     OPDept e =null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("E:/eclipse_workspace/OrderManagement/resources/orders.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (OPDept) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	     
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("OPDept class not found");
	         c.printStackTrace();
	        
	      }
		return e;
	      
	    }
}
