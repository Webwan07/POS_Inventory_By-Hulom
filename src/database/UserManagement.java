package database;

import assets.Author;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;

@Author("Josuan Leonardo Hulom")
public class UserManagement extends DbConnection{
    
    public final String table = "userstable";
    
    private final String[] columns = {"userId","firstname","lastname","username","password",
        "birthdate","gender","profileImgPath","userType"};
    
    public final String[] listOfUserType = {"Admin","Seller"};
    
    private final Component component;
    
    public UserManagement(Component component){
        this.component = component;
    }
    
    public boolean checkCurrentUser(String value) throws SQLException {
        String query = "SELECT " + columns[0] + " FROM " + table +
                       " WHERE " + columns[0] + " = ?";
        try {
            prepare = connection.prepareStatement(query);
            prepare.setString(1, value);
            result = prepare.executeQuery();

            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return false;
    }    
    
    public short checkUserCredentials(String u_n, String u_p) throws SQLException {
        String query = "SELECT * FROM " + table;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            while (result.next()) {
                String username = result.getString(columns[3]);
                String password = result.getString(columns[4]);

                if (u_n.equals(username) && u_p.equals(password)) {
                    return 1;
                } else if (u_n.equals(username)) {
                    return 2; 
                } else if (u_p.equals(password)) {
                    return 3; 
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        } catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return 0; 
    }
    
    public String getUserId(String u_name,String u_pass) throws SQLException{
        String query = "SELECT "+columns[0]+" FROM "+table+" WHERE "
                + columns[3] + " = ? AND "+columns[4]+" = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, u_name);
            prepare.setString(2, u_pass);
            
            result = prepare.executeQuery();
            
            if(result.next()){
                return result.getString(columns[0]);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return null;        
    }  
    
    public String getImagePath(String _id) throws SQLException{
        String query = "SELECT "+columns[7]+" FROM "+table+" WHERE "
                + columns[0] + " = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, _id);
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
    
    public String getFName(String _id) throws SQLException{
        String query = "SELECT "+columns[1]+" FROM "+table+" WHERE "
                + columns[0] + " = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, _id);          
            result = prepare.executeQuery();
            if(result.next()){
                return result.getString(columns[1]);
            }
        }catch(SQLException e){
           JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return null;        
    }        
        
    public String getLName(String _id) throws SQLException{
        String query = "SELECT "+columns[2]+" FROM "+table+" WHERE "
                + columns[0] + " = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, _id);
            result = prepare.executeQuery();
            
            if(result.next()){
                return result.getString(columns[2]);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return null;        
    }  
    
    public String getUName(String _id) throws SQLException{
        String query = "SELECT "+columns[3]+" FROM "+table+" WHERE "
                + columns[0] + " = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, _id);
            result = prepare.executeQuery();
            if(result.next()){
                return result.getString(columns[3]);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return null;        
    }    
    
    public String getGender(String _id) throws SQLException{
        String query = "SELECT "+columns[6]+" FROM "+table+" WHERE "
                + columns[0] + " = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, _id);
            result = prepare.executeQuery();
            if(result.next()){
                return result.getString(columns[6]);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return null;        
    }
    
    public String getUserType(String id) throws SQLException{
        String query = "SELECT "+columns[8]+" FROM "+table+
                " WHERE "+columns[0]+" = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, id);
            result = prepare.executeQuery();
            if(result.next()){
                return result.getString(columns[8]);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return null;        
    } 
    
    public void updateFname(String newVal,Object id) throws SQLException{
        String query = "UPDATE "+table+" SET "+columns[1]+" = ? WHERE "+columns[0]+" = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, newVal);
            prepare.setObject(2, id);
            prepare.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
        }
    } 
    
    public void updateLname(String newVal,Object id) throws SQLException{
        String query = "UPDATE "+table+" SET "+columns[2]+" = ? WHERE "+columns[0]+" = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, newVal);
            prepare.setObject(2, id);
            prepare.executeUpdate();
            prepare.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
        }
    }  
    
    public void updateUsername(String newVal,Object id) throws SQLException{
        String query = "UPDATE "+table+" SET "+columns[3]+" = ? WHERE "+columns[0]+" = ?";
        try{
            prepare = connection.prepareStatement(query);
            prepare.setString(1, newVal);
            prepare.setObject(2, id);
            prepare.executeUpdate();
            prepare.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
        }
    } 
}
