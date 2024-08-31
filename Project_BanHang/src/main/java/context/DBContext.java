package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBContext {
	private static final String DB_SeverName = "DESKTOP-2021CFA";
	private static final String DB_Login = "sa";
	private static final String DB_password = "123456789";
	private static final String DB_databaseName = "Wish";
   
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String DB_URL = "jdbc:sqlserver://" +DB_SeverName + ":1433;DatabaseName=" + DB_databaseName + "; encrypt=true;trustServerCertificate=true";
         
			return DriverManager.getConnection(DB_URL,DB_Login,DB_password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public static void main(String[] args) {
		Connection con =DBContext.getConnection();
		if(con==null) {
			System.out.println("That bai");
		}else {
			System.out.println("Thanh cong");
		}
	}
	
}
