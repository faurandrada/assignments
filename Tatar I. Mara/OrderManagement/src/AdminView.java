
import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;




public class AdminView {
	Warehouse wh;
	DeserializeDemo deserialize;
	SerializeDemo serialize;
	static JTable table;
	static DefaultTableModel model;
   public AdminView(){

        JFrame frame = new JFrame();
        table = new JTable(); 
        wh=new Warehouse();
        deserialize=new DeserializeDemo();
        serialize=new SerializeDemo();
        wh=deserialize.deserializeWarehouse();
        // create a table model and set a Column Identifiers to this model 
        Object[] columns = {"Item","Size","Color","Number of Products"};
         model = new DefaultTableModel();
         model.setColumnIdentifiers(columns);

         table.setModel(model);

        
         table.setBackground(Color.LIGHT_GRAY);
         table.setForeground(Color.black);

        Font font = new Font("",1,22);

        table.setFont(font);

        table.setRowHeight(30);

        

        // create JTextFields

      //  JTextField textId = new JTextField();

        JTextField textItem = new JTextField();

        JTextField textSize = new JTextField();

        JTextField textColor = new JTextField();
        
        JTextField textNoOfprod = new JTextField();

        

        // create JButtons

        JButton btnAdd = new JButton("Add");

        JButton btnDelete = new JButton("Delete");

        JButton btnUpdate = new JButton("Update");     

        

    //    textId.setBounds(20, 220, 100, 25);

        textItem.setBounds(20, 220, 100, 25);

        textSize.setBounds(20, 250, 100, 25);

        textColor.setBounds(20, 280, 100, 25);

        textNoOfprod.setBounds(20, 310, 100, 25);
        

        btnAdd.setBounds(150, 220, 100, 25);

        btnUpdate.setBounds(150, 265, 100, 25);

        btnDelete.setBounds(150, 310, 100, 25);

        

        // create JScrollPane

        JScrollPane pane = new JScrollPane(table);

        pane.setBounds(0, 0, 880, 200);

        

        frame.setLayout(null);

        

        frame.add(pane);

        

        // add JTextFields to the jframe

     //   frame.add(textId);

        frame.add(textItem);

        frame.add(textColor);

        frame.add(textSize);

        frame.add(textNoOfprod);
    

        // add JButtons to the jframe

        frame.add(btnAdd);

        frame.add(btnDelete);

        frame.add(btnUpdate);

        

        // create an array of objects to set the row data

        Object[] row = new Object[4];

        

        // button add row

        btnAdd.addActionListener(new ActionListener(){

            @Override

            public void actionPerformed(ActionEvent e) {

             

                row[0] = textItem.getText();

                row[1] = textSize.getText();

                row[2] = textColor.getText();

                row[3]=textNoOfprod.getText();

                // add row to the model

                model.addRow(row);
                Product product=new Product(row[0].toString(),row[2].toString(),row[1].toString(),Integer.parseInt(row[3].toString()));
                wh.addProduct(product);
                serialize.serializeWarehouse(wh);
            }

        });

        

        // button remove row

        btnDelete.addActionListener(new ActionListener(){

          @Override

            public void actionPerformed(ActionEvent e) {

            

                // i = the index of the selected row

                int i = table.getSelectedRow();

                if(i >= 0){

                    // remove a row from jtable
                    wh.deleteCorrespondingProduct(row[0].toString(), row[1].toString(), row[2].toString(), (Product)wh.getRoot());
                    model.removeRow(i);

                }

                else{

                    System.out.println("Delete Error");

                }

            }

        });

        

        // get selected row data From table to textfields 

        table.addMouseListener(new MouseAdapter(){
        @Override

        public void mouseClicked(MouseEvent e){

            

            // i = the index of the selected row

            int i = table.getSelectedRow();

            

            textItem.setText(model.getValueAt(i, 0).toString());

            textSize.setText(model.getValueAt(i, 1).toString());

            textColor.setText(model.getValueAt(i, 2).toString());

            textNoOfprod.setText(model.getValueAt(i, 3).toString());

        }

        });

        

        // button update row

        btnUpdate.addActionListener(new ActionListener(){

            @Override

            public void actionPerformed(ActionEvent e) {

             

                // i = the index of the selected row

                int i = table.getSelectedRow();

                

                if(i >= 0) 

                {

                   model.setValueAt(textItem.getText(), i, 0);

                   model.setValueAt(textSize.getText(), i, 1);

                   model.setValueAt(textColor.getText(), i, 2);

                   model.setValueAt(textNoOfprod.getText(), i, 3);
                   
                   row[3]=textNoOfprod.getText();
                   wh.updateCorrespondingProduct(row[0].toString(), row[1].toString(), row[2].toString(),Integer.parseInt(row[3].toString()), (Product)wh.getRoot());
                }

                else{

                    System.out.println("Update Error");

                }

            }

        });

        

        frame.setSize(900,400);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        

    }
   public static DefaultTableModel getModel() {
		
		return model;
   }

}
  

