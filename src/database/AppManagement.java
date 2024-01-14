/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import assets.Author;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;

@Author("Josuan Leonardo Hulom")
public class AppManagement extends DbConnection{
 
    private final static String table = "apptable";
    
    private final static String[] columns = {"appID","currentUser"};

    public static String getCurrentUser(Component p_c) throws SQLException{
        String query = "SELECT "+columns[1]+" FROM "+table+" WHERE "+ columns[0] + " = 1";
        
        try{
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();
            
            if(result.next()){
                return result.getString(columns[1]);
            }
            
            prepare.close();
            result.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(p_c, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return null;           
    }  
    
    public static void setCurrentUser(String _id,Component p_c) throws SQLException{
        String query = "UPDATE "+table+" SET "+columns[1]+" = ? WHERE "+columns[0]+" = 1";
        
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, _id);
            
            prepare.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(p_c, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
        }
    }
}
