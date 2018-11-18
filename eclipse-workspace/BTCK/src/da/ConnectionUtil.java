package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection conn;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (conn == null) {
			Class.forName("com.mysql.jdbc.Driver");
			String db_url = "jdbc:mysql://localhost/qlbgx";
			String db_user = "quanly";
			String db_password = "123456";
			
			conn = DriverManager.getConnection(db_url, db_user, db_password);
		} else {

		}
		return conn;
	}
}
