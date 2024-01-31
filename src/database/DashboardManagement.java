package database;

import assets.Author;
import java.sql.SQLException;

@Author("Josuan Leonardo Hulom")
public class DashboardManagement extends DbConnection{
    
    public DashboardManagement(){
    }
    
    public int countProducts() throws SQLException{
        String query = "SELECT COUNT(*) FROM " + InventoryManagement.table;
        int count = 0;
        try {
            result = statement.executeQuery(query);
            while (result.next()) {
               count = result.getInt(1);
            }
        }finally{
            result.close();            
        }
        return count;
    }
    
    public int getOutOfStocks() throws SQLException{
        String query = "SELECT COUNT(*) FROM "+InventoryManagement.table+" WHERE "+InventoryManagement.columns[4]+" = 0";
        int outOfStocks = 0;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();

            if (result.next()) {
                outOfStocks = result.getInt(1); 
            }

            result.close();
            prepare.close();
        }finally{
            prepare.close();
            result.close();            
        }
        return outOfStocks;          
    }
    
    public int getProductSold() throws SQLException {
        String query = "SELECT SUM("+PurchaseManagement.columns[3]+") AS totalquantity FROM "+PurchaseManagement.table+" WHERE DATE("+PurchaseManagement.columns[6]+") != CURDATE()";
        int totalProduct = 0;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();
            
            if (result.next()) {
                totalProduct = result.getInt("totalquantity");
            }
        }finally{
            prepare.close();
            result.close();            
        }
        return totalProduct;
    }
    
    public int getProductSoldToday() throws SQLException {
        String query = "SELECT SUM("+PurchaseManagement.columns[3]+") AS totalquantity FROM "+PurchaseManagement.table+" WHERE DATE("+PurchaseManagement.columns[6]+") = CURDATE()";
        int totalProduct = 0;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();
            
            if (result.next()) {
                totalProduct = result.getInt("totalquantity");
            }
        }finally{
            prepare.close();
            result.close();            
        }
        return totalProduct;
    }
    
    public double getTotalSales() throws SQLException {
        String query = "SELECT SUM("+PurchaseManagement.columns[5]+") AS totalSales FROM "+PurchaseManagement.table;
        double salesVal = 0;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();
            
            if (result.next()) {
                salesVal = result.getDouble("totalSales");
            }
        }finally{
            prepare.close();
            result.close();            
        }
        return salesVal;
    }
    
    public double getTotalSalesToday() throws SQLException {
        String query = "SELECT SUM("+PurchaseManagement.columns[5]+") AS totalSales FROM "+PurchaseManagement.table+" WHERE DATE("+PurchaseManagement.columns[6]+") = CURDATE()";
        double salesVal = 0;
        try {
            prepare = connection.prepareStatement(query);
            result = prepare.executeQuery();
            
            if (result.next()) {
                salesVal = result.getDouble("totalSales");
            }
        }finally{
            prepare.close();
            result.close();            
        }
        return salesVal;
    }
}
