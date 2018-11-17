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
	
	// chua can dung den nhung dung xoa di vi co the dung den
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
	
	// chưa cần dùng đến vì đoạn edit user dùng JComboCheckBox nhưng đừng xóa đi
	public boolean checkUserIDExist(int id) throws SQLException {
		String sql = "SELECT * FROM user WHERE employee_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setInt(1, id);

		ResultSet rs = sttm.executeQuery();

		
		//chua ton tai
		if (rs.next()) {
			return false;
		}
		//co ton tai id nay roi
		return true;

	}
	
	//kiem tra username dang thay doi co trung voi cac username khac (ko chua username dang thay doi)
	public boolean checkEditUsername(int id, String username) throws SQLException {
		String sql = "SELECT * FROM user WHERE employee_id != ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setInt(1, id);

		ResultSet rs = sttm.executeQuery();
		List<String> usrnames = new ArrayList<String>();
		//co trung
		while (rs.next()) {
			String usrname = rs.getString("username");
			usrnames.add(usrname);
		}
		for (int i = 0; i < usrnames.size(); i++) {
			//username ở đây là mới nhập vào khi thay đổi
			if (usrnames.get(i).equalsIgnoreCase(username)) {
				//bị trùng
				return false;
			}
			
		}
		//ko bị trùng
		return true;

	}
	
	//chi tra lai mot username duy nhat vi id la duy nhat
	public String getUsernameBySelectID (int id) {
		String sql = "SELECT username FROM user WHERE employee_id = ?";
		PreparedStatement sttm = null;
		List <String> usernames = new ArrayList<String>();
		String username = null;
		try {
			sttm = conn.prepareStatement(sql);
			sttm.setInt(1, id);
			
			ResultSet rs = sttm.executeQuery();
			while (rs.next()) {
				username = rs.getString("username");
				usernames.add(username);
			}
			
			sttm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		;
		return usernames.get(0);
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
	
	//dù có thay đổi username hay không cũng dùng cái này
	public void editUser(User user) throws SQLException {
		String sqlEdit = "UPDATE user SET  username = ?, email = ?, fullname = ?, role = ?, parking_id = ? WHERE employee_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sqlEdit);
		sttm.setString(1, user.getUsername());
		sttm.setString(2, user.getEmail());
		sttm.setString(3, user.getFullname());
		sttm.setInt(4, user.getRole());
		sttm.setInt(5, user.getParking_id());
		sttm.setInt(6, user.getId());

		sttm.executeUpdate();

		sttm.close();
	}
	
	
	public void deleteUser(int id) throws SQLException {
		String sql = "DELETE FROM user WHERE employee_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setInt(1, id);
		sttm.executeUpdate();
		sttm.close();
	}
	
	// chua can dung den nhung dung xoa di vi co the dung den
	public String[] getAllUsername(){
		List<String> usernames = new ArrayList<String>();
		String sql = "SELECT username FROM user";
		Statement sttm = null;
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
	
	public String[] getAllEmployeeID(){
		List<String> employeeIds = new ArrayList<String>();
		String sql = "SELECT employee_id FROM user";
		Statement sttm = null;
		String employee_id = "";
		try {
			sttm = conn.createStatement();
			ResultSet rs = sttm.executeQuery(sql);

			while (rs.next()) {
				employee_id  = String.valueOf(rs.getInt("employee_id"));
				employeeIds.add(employee_id);
			}
			sttm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String [] ids = new String [employeeIds.size()];
		
		for (int i = 0; i < employeeIds.size(); i++) {
			ids[i] = employeeIds.get(i);
		}
		
		return ids;
	}
}
