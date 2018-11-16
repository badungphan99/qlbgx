package da;

import e.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDA {
	private Connection conn;

	public UserDA()  {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> getAll() throws SQLException {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM user";
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery(sql);

		while (rs.next()) {
			User user = new User(rs.getInt("employee_id"), rs.getString("username"), rs.getString("password"),
					rs.getString("email"), rs.getString("fullname"), rs.getInt("role"), rs.getInt("parking_id"));
			users.add(user);
		}
		return users;
	}
	
//	public boolean deleteUser (int id) throws SQLException {
//		String sql = "DELETE FROM user WHERE employee_id = ?";
//		PreparedStatement sttm = conn.prepareStatement(sql);
//		sttm.setInt(1, id);
//		int result = sttm.executeUpdate();
//		return result>0;
//	}
	
	public User checkUser(String username, String password) throws SQLException {
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setString(1, username);
		sttm.setString(2, password);
		
		
		ResultSet rs = sttm.executeQuery();
		
		if (rs.next()) {
			User user = new User(rs.getInt("employee_id"), rs.getString("username"), rs.getString("password"),
					rs.getString("email"), rs.getString("fullname"), rs.getInt("role"), rs.getInt("parking_id"));
			return user;
		}
		User user = new User();
		return user;
		
		
	}
	
	public boolean checkBossUser(String username, String password) throws SQLException {
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setString(1, username);
		sttm.setString(2, password);
		
		
		ResultSet rs = sttm.executeQuery();
		
		if (rs.next()) {
			String role = rs.getString("role");
			if (role.equals("0")) {
				return true;
			}
			return false;
		}
		return false;
		
		
	}
}
