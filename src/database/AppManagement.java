package database;

import assets.Author;
import static database.DbConnection.connection;
import static database.DbConnection.prepare;
import static database.DbConnection.result;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@Author("Josuan Leonardo Hulom")
public class AppManagement extends DbConnection{
 
    public final static String table = "apptable";
    
    public final static String[] columns = {"appID","currentUser"};

    public static void tableData(JTable table,String tableName,String[] tableColumn) throws SQLException {
        String[] columnsToDisplay = tableColumn;
        String query = "SELECT " + String.join(", ", columnsToDisplay) + " FROM "+tableName;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            while (result.next()) {
                Object[] row = new Object[columnsToDisplay.length];
                for (int i = 0; i < columnsToDisplay.length; i++) {
                    row[i] = result.getObject(columnsToDisplay[i]);
                }
                model.addRow(row);
            }
        }finally{
            prepare.close();
            result.close();  
        }
    } 
 
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
        }finally{
            prepare.close();
        }
    }
}
