package vn.yarnix.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQL {
	private static final String HOST = "localhost";
    private static final int PORT = 1433;
    private static final String DATABASE = "dbLTWeb";
    private static final String USER = "sa";
    private static final String PASSWORD = "ja72jrD=e-ESRY9X";

    public static Connection getConnection() throws SQLException {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found!", e);
        }
    	
        String url = String.format(
            "jdbc:sqlserver://%s:%d;databaseName=%s;encrypt=true;trustServerCertificate=true;",
            HOST, PORT, DATABASE
        );
        
        return DriverManager.getConnection(url, USER, PASSWORD);
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
//    public static void main(String[] args) {
//    	try {
//			System.out.println(new DBConnectSQL().getConnection());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    }
}
