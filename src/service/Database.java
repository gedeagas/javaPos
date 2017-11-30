package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.objects.Global;
import models.inventoryModels;
import models.salesModel;
import models.searchModels;

import java.sql.*;

/**
 * Created by gedeagas on 22/11/17.
 */
public class Database {


    private String dbUser = "root";
    private String dbPassword = "root";
    private String dbPort = "9898";
    private String databaseUrl = "jdbc:mysql://localhost:" + dbPort + "/dbms?" + "user=" + dbUser + "&password=" + dbPassword;

    public java.sql.Date getCurrentDatetime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public boolean updateInventory(int inventoryId, String name, long price, int ean){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = "UPDATE product SET product_name = ?, ean_product = ?, product_price=? WHERE id_product = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setLong (3, price);
            preparedStmt.setInt (2, ean);
            preparedStmt.setInt(4,inventoryId);



            int rs =  preparedStmt.executeUpdate();
            System.out.println(rs);
            if(rs >0){
                return true;
            } else {
                return false;
            }





            // Do something with the Connection
        } catch (SQLException ex) {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;

        }
    }

    public boolean deleteInventory(int inventoryId){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = "DELETE FROM product WHERE id_product = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, inventoryId);


            int rs =  preparedStmt.executeUpdate();
            System.out.println(rs);
            if(rs >0){
                return true;
            } else {
                return false;
            }





            // Do something with the Connection
        } catch (SQLException ex) {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;

        }
    }

    public boolean insertInventory(String productName, int eanCode, long productPrice ){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = " INSERT INTO product (product_name,ean_product,product_price)" + " VALUES (?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, productName);
            preparedStmt.setInt(2,eanCode);
            preparedStmt.setLong(3,productPrice);


            int rs =  preparedStmt.executeUpdate();
            System.out.println(rs);
            if(rs >0){
                return true;
            } else {
                return false;
            }





            // Do something with the Connection
        } catch (SQLException ex) {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;

        }
    }

    public ObservableList inventoryQuery(){

        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM `product` ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);


            ResultSet rs =  preparedStmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            ObservableList<models.inventoryModels> list = FXCollections.observableArrayList ();

            if (!rs.next()) {                            //if rs.next() returns false
                //then there are no rows.
                System.out.println("No records found");
                return list;

            } else {
                while (rs.next()) {
                    list.add(new inventoryModels(rs.getInt(1),rs.getString(2),rs.getLong(4),rs.getInt(3)));
                    System.out.println(rs.getString(2));
                }
                return list;

            }




            // Do something with the Connection
        } catch (SQLException ex) {
            ObservableList<models.inventoryModels> list = FXCollections.observableArrayList ();

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return list;


        }

    }

    public boolean insertSales(long total){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = " INSERT INTO sales (date,total)" + " VALUES (?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            java.sql.Date date = getCurrentDatetime();
            preparedStmt.setDate(1, date);
            preparedStmt.setLong(2, total);


            int rs =  preparedStmt.executeUpdate();
            System.out.println(rs);
            if(rs >0){
                return true;
            } else {
                return false;
            }





            // Do something with the Connection
        } catch (SQLException ex) {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;

        }
    }

    public ObservableList searchSales(String queryUsr){

        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();

            System.out.println(queryUsr);
            String query = " select * from product where product_name like ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, "%" + queryUsr + "%");

            ResultSet rs =  preparedStmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            ObservableList<models.searchModels> list = FXCollections.observableArrayList ();

            if (!rs.next()) {                            //if rs.next() returns false
                //then there are no rows.
                System.out.println("No records found");
                return list;

            } else {
                while (rs.next()) {
                    list.add(new searchModels(rs.getInt(1),rs.getInt(3),rs.getString(2),rs.getLong(4)));
                    System.out.println(rs.getString(2));
                }
                return list;

            }




            // Do something with the Connection
        } catch (SQLException ex) {
            ObservableList<models.searchModels> list = FXCollections.observableArrayList ();

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return list;


        }

    }

    public boolean updateCategory(int categoryId, String categoryName){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = "UPDATE `dbms`.`category` SET `category_name` = ? WHERE `category`.`id_category` = ? ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, categoryName);

            preparedStmt.setInt (2, categoryId);


            int rs =  preparedStmt.executeUpdate();
            System.out.println(rs);
            if(rs >0){
                return true;
            } else {
                return false;
            }





            // Do something with the Connection
        } catch (SQLException ex) {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;

        }
    }

    public boolean deleteCategory(int categoryId){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = "DELETE FROM category WHERE id_category = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, categoryId);


            int rs =  preparedStmt.executeUpdate();
            System.out.println(rs);
            if(rs >0){
                return true;
            } else {
                return false;
            }





            // Do something with the Connection
        } catch (SQLException ex) {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;

        }
    }
    public boolean insertCategory(String categoryName){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = " INSERT INTO category (category_name)" + " VALUES (?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, categoryName);


            int rs =  preparedStmt.executeUpdate();
            System.out.println(rs);
            if(rs >0){
                return true;
            } else {
                return false;
            }





            // Do something with the Connection
        } catch (SQLException ex) {

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;

        }
    }
    public ObservableList queryCategory() {

        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = " select * from category";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            ResultSet rs =  preparedStmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            ObservableList<models.categoryModels> list = FXCollections.observableArrayList ();


            if (!rs.next()) {                            //if rs.next() returns false
                //then there are no rows.
                System.out.println("No records found");
                return list;


            } else {
                while (rs.next()) {
                    list.add(new models.categoryModels(rs.getInt(1),rs.getString(2)));
                }
                return list;

            }








            // Do something with the Connection
        } catch (SQLException ex) {
            ObservableList<models.categoryModels> list = FXCollections.observableArrayList ();

            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return list;


        }


    }

    private void printResultSets(ResultSet rs, ResultSetMetaData rsmd){

        try {
            int columnsNumber = rsmd.getColumnCount();
            if (!rs.next()) {                            //if rs.next() returns false
                //then there are no rows.
                System.out.println("No records found");

            } else {
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        System.out.print(columnValue + " -> " + rsmd.getColumnName(i));
                    }
                    System.out.println("");
                }
            }

        } catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public String loginUser(String username, String password){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = " select * from userAcc where username=? and password=?;";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, username);
            preparedStmt.setString (2, password);

            ResultSet rs =  preparedStmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            try {
                int columnsNumber = rsmd.getColumnCount();
                if (!rs.next()) {                            //if rs.next() returns false
                    //then there are no rows.
                    return "Not Found";
                } else {
                    vGlobal.username = rs.getString(2);
                    vGlobal.name = rs.getString(3);
                    return "Logged In";

                }

            } catch (SQLException ex){
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                return "Error";
            }






            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return "Error";

        }
    }

    public void addUserToDatabase(String username, String password){
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement();


            String query = " INSERT INTO userAcc (username, password)" + " VALUES (?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, username);
            preparedStmt.setString (2, password);

             int rs =  preparedStmt.executeUpdate();
            System.out.println(rs);





            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    public void getAllRecords() {
        try {

            Connection conn = DriverManager.getConnection(this.databaseUrl);
            Statement stmt = conn.createStatement() ;
            ResultSet rs = stmt.executeQuery("select * from userAcc");
            ResultSetMetaData rsmd = rs.getMetaData();
            printResultSets(rs,rsmd);


            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
