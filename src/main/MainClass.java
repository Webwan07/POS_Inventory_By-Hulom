package main;

import com.formdev.flatlaf.IntelliJTheme;
import org.opencv.core.Core;
import assets.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import database.DbConnection;
import database.UserManagement;
import database.AppManagement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

@Author("Josuan Leonardo Hulom")
public class MainClass {
    public static final DbConnection dbConnection = DbConnection.getInstance();
    
    public static void main(String args[]) throws SQLException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        if(dbConnection.isDatabaseConnected()){
            try{
                String value = AppManagement.getCurrentUser(new MainApp());
                boolean checkUser = new UserManagement(new MainApp()).checkCurrentUser(value);

                java.awt.EventQueue.invokeLater(() -> {
                    if(!checkUser){
                        new LoginApp().setVisible(true);
                    }else{
                        new MainApp().setVisible(true);
                    }
                });
            }catch(SQLException e){
                JOptionPane.showMessageDialog(new MainApp(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error: No database connected!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }   
}
