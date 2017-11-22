package service;

import java.sql.*;

/**
 * Created by gedeagas on 22/11/17.
 */
public class Database {


    private String dbUser = "root";
    private String dbPassword = "root";
    private String dbPort = "9898";
    private String databaseUrl = "jdbc:mysql://localhost:" + dbPort + "/dbms?" + "user=" + dbUser + "&password=" + dbPassword;


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
