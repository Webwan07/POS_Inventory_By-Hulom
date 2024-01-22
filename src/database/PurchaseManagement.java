package database;

import assets.Author;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;

@Author("Josuan Leonardo Hulom")
public class PurchaseManagement extends DbConnection{
    private final Component component;
    
    public final String table = "purchasedtable";
    
    public final String[] columns = {"invoiceNumber","product","discountPercent","quantity","subtotal","total","purchasedDate","sellerfname","sellerlname"};
    
    public PurchaseManagement(Component component){
        this.component = component;
    }    
    
    public int sellerTotalSold_Item(String f, String l) throws SQLException {
        String query = "SELECT SUM(" + columns[3] + ") AS totalsold FROM " + table + " WHERE " + columns[7] + " = ? AND " + columns[8] + " = ?";
        int salesVal = 0;
        try {
            prepare = connection.prepareStatement(query);
            prepare.setString(1, f);
            prepare.setString(2, l);
            result = prepare.executeQuery();
            
            if (result.next()) {
                salesVal = result.getInt("totalsold");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return salesVal;
    }    
    
    public double sellerTotalSold(String f, String l) throws SQLException {
        String query = "SELECT SUM(" + columns[5] + ") AS totalsold FROM " + table + " WHERE " + columns[7] + " = ? AND " + columns[8] + " = ?";
        double salesVal = 0;
        try {
            prepare = connection.prepareStatement(query);
            prepare.setString(1, f);
            prepare.setString(2, l);
            result = prepare.executeQuery();
            
            if (result.next()) {
                salesVal = result.getDouble("totalsold");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return salesVal;
    }  
    
    public void updateSellerName(String oldval, String newval, int idx) throws SQLException {
        String query = "UPDATE "+table+" SET "+columns[idx]+" = ? WHERE "+columns[idx]+" = ?";
        try {
            prepare = connection.prepareStatement(query);
            prepare.setString(1, newval);
            prepare.setString(2, oldval);
            prepare.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
        }
    }  
}
