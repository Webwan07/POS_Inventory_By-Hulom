package database;

import assets.Author;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@Author("Josuan Leonardo Hulom")
public class DbConnection {
    private static DbConnection instance;
    
    private final static String schemaName = "pos_inv_db";
    private final static String url = "jdbc:mysql://localhost:3306/"+schemaName+"?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private final static String username = "root";
    private final static String password = "";
    
    protected static Connection connection;
    protected static Statement statement;
    protected static PreparedStatement prepare;   
    protected static ResultSet result;
    protected static ResultSetMetaData metaData;

    protected DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DbConnection.connection = DriverManager.getConnection(url, username, password);
            DbConnection.statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No database connected!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    public boolean isDatabaseConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    } 
    
    static String[] getTableColumns(String tableName,JTable component) throws SQLException {
        ArrayList<String> columnList = new ArrayList<>();
        String query = "SELECT COLUMN_NAME FROM information_schema.columns WHERE TABLE_NAME = ? AND TABLE_SCHEMA = ?";
        try{ prepare = connection.prepareStatement(query);
            prepare.setString(1, tableName);
            prepare.setString(2, schemaName);
            ResultSet resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                columnList.add(columnName);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(component, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
        return columnList.toArray(String[]::new);
    }
    
    public void tableData(JTable table,String tableName) throws SQLException {
        String[] columnsToDisplay = getTableColumns(tableName,table);
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(table, e.getMessage(), "Error Code: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }finally{
            prepare.close();
            result.close();  
        }
    }   
}