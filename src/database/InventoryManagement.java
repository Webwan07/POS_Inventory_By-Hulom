package database;

import assets.Author;
import assets.FileManagement;
import static database.DbConnection.connection;
import static database.DbConnection.prepare;
import static database.DbConnection.result;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

@Author("Josuan Leonardo Hulom")
public class InventoryManagement{
    public final static String table = "inventorytable";
    
    public final static String[] columns = {"productID","Category","ProductName",
        "Description","Quantity","RetailPrice","DateOfPurchase","itemImage"};
    
    public final String[] listOfUserType = {"Admin","Seller"};
    
    private final Component component;
    
    public InventoryManagement(Component component){
        this.component = component;
    } 
    
    public ImageIcon getItemImage(int _id) throws SQLException{
        String query = "SELECT "+columns[7]+" FROM "+table+" WHERE "
                + columns[0] + " = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setInt(1, _id);
            result = prepare.executeQuery();
            if(result.next()){
                return new ImageIcon(FileManagement.PROJECT_PACKAGES[1]+"/"+result.getString(columns[7])+".png");
            }
        }finally{
            prepare.close();
            result.close();            
        }
        return null;        
    } 
}
