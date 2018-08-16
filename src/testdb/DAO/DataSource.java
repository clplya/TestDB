package testdb.DAO;
import java.sql.*;
import java.sql.Connection;

/**
 *
 * @author clply_000
 */
public class DataSource {
    private static String url = "jdbc:mysql://52.206.157.109:3306/U03xBv";
    private static String dataSourceDriver = "com.mysql.cj.jdbc.Driver";
    private static String user = "U03xBv";
    private static String password = "53688111925";
    private static Connection conn = null;
   
    public static String getUrl(){ return url; }
    public static String getDataSourceDriver(){ return dataSourceDriver; }
    public static String getUsername(){ return user; }
    public static String getPassword(){ return password; }

    
    
    public static Connection getConnection() throws SQLException {  
        try{
            Class.forName(dataSourceDriver);
            try{
                conn = DriverManager.getConnection(url,user,password);
            } catch (SQLException ex){
                System.out.println("Failed to create the DB connection.");
            }
            } catch (ClassNotFoundException ex){
                System.out.println("Driver not found");
            }
        return conn;
    }
    
    public static Connection getConnection(String input1, String input2) throws SQLException {  
        try{
            Class.forName(dataSourceDriver);
            try{
                conn = DriverManager.getConnection(url,user,password);
            } catch (SQLException ex){
                System.out.println("Failed to create the DB connection.");
            }
            } catch (ClassNotFoundException ex){
                System.out.println("Driver not found");
            }
        return conn;
    }

    public static ResultSet dbQuery(String stmtQuery) {
        Statement stmt;
        ResultSet rs = null;
        try {
            getConnection();
            
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(stmtQuery);
        } catch (SQLException ex){
    }
        return rs;
    }
}
