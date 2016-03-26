package Serializing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import Model.Product;

public class Try {
	
	public Try(){
	Product p = new Product("mere", 15, 20, 200);

	  try
      {
         FileOutputStream fileOut =
         //new FileOutputStream("C:/Users/Dariana Lupea/Desktop/dd.ser");
         new FileOutputStream("C:/Users/Dariana Lupea/Desktop/DariJavaSem2/OrderManagement/SerFile/dd.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(p);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in dd.ser");
      }catch(IOException i)
      {
          i.printStackTrace();
      }
   }
}
