package database;

import assets.Author;
import static database.DbConnection.connection;
import static database.DbConnection.prepare;
import static database.DbConnection.result;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;

@Author("Josuan Leonardo Hulom")
public class InventoryManagement {
    public final String table = "inventorytable";
    
    public final String[] columns = {"productID","Category","ProductName",
        "Description","Quantity","RetailPrice","DateOfPurchase","itemImage"};
    
    public final String[] listOfUserType = {"Admin","Seller"};
    
    private final Component component;
    
    public InventoryManagement(Component component){
        this.component = component;
    } 
    
    public String getItemImage(int _id) throws SQLException{
        String query = "SELECT "+columns[7]+" FROM "+table+" WHERE "
                + columns[0] + " = ?";
        
        try{
            prepare = connection.prepareStatement(query);
            prepare.setInt(1, _id);
            result = prepare.executeQuery();
            
            if(result.next()){
                return result.getString(columns[7]);
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();            
        }
        return null;        
    } 
}
