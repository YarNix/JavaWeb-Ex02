package vn.yarnix.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConnectSQL {
	private static final String HOST = "localhost";
    private static final int PORT = 1433;
    public static final String DATABASE;
    public static final String USER;
    public static final String PASSWORD;
    public static final String URL_CONN;
    
    static {
    	Dotenv env = Dotenv.load();
    	
        DATABASE = env.get("DATABASE_NAME");
        USER = env.get("DATABASE_USERNAME");
        PASSWORD = env.get("DATABASE_PASSWORD");
        
        URL_CONN = String.format(
            "jdbc:sqlserver://%s:%d;databaseName=%s;encrypt=true;trustServerCertificate=true;",
            HOST, PORT, DATABASE
        );
    }

    public static Connection getConnection() throws SQLException {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found!", e);
        }
    	return DriverManager.getConnection(URL_CONN, USER, PASSWORD);
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
