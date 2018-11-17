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
	
	public boolean checkUserExist(String username) throws SQLException {
		String sql = "SELECT * FROM user WHERE username = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setString(1, username);

		ResultSet rs = sttm.executeQuery();

		if (rs.next()) {
			return false;
		}
		return true;

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
	
	public void insertUser(User user) throws SQLException {
		String sql = "INSERT INTO user (employee_id, username, password, email, fullname, role, parking_id)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setInt(1, user.getId());
		sttm.setString(2, user.getUsername());
		sttm.setString(3, user.getPassword());
		sttm.setString(4, user.getEmail());
		sttm.setString(5, user.getFullname());
		sttm.setInt(6, user.getRole());
		sttm.setInt(7, user.getParking_id());

		sttm.executeUpdate();

	}
	
	public void editUser(User user) throws SQLException {
		String sqlEdit = "UPDATE user SET email = ?, fullname = ?, role = ?, parking_id = ? WHERE username = ?";
		PreparedStatement sttm = conn.prepareStatement(sqlEdit);
		sttm.setString(1, user.getEmail());
		sttm.setString(2, user.getFullname());
		sttm.setInt(3, user.getRole());
		sttm.setInt(4, user.getParking_id());
		sttm.setString(5, user.getUsername());

		sttm.executeUpdate();

		sttm.close();
	}
	
	
	public void deleteUser(String username) throws SQLException {
		String sql = "DELETE FROM user WHERE username = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setString(1, username);
		sttm.executeUpdate();
		sttm.close();
	}
	
	public String[] getAllUsername(){
		List<String> usernames = new ArrayList<String>();
		String sql = "SELECT username FROM user";
		Statement sttm;
		try {
			sttm = conn.createStatement();
			ResultSet rs = sttm.executeQuery(sql);

			while (rs.next()) {
				String username = rs.getString("username");
				usernames.add(username);
			}
			sttm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String []usr = new String [usernames.size()];
		
		for (int i = 0; i < usernames.size(); i++) {
			usr[i] = usernames.get(i);
		}
		
		return usr;
	}
}
