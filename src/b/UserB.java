package b;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import da.UserDA;

import e.User;

public class UserB {
	private UserDA da;
	
	public UserB () {
		da = new UserDA();
	}
	
	public User checkLogin (String username, String password) throws SQLException {
		return da.checkUser(username, password);
	}
	
	public boolean checkUserExist (String username) throws SQLException {
		return da.checkUserExist(username);
	}
	
	public boolean checkBossLogin (String username, String password) throws SQLException {
		return da.checkBossUser(username, password);
	}
	
	public DefaultTableModel getAllUser() throws SQLException {
		List<User> users = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee_id");
		model.addColumn("Username");
		model.addColumn("Email");
		model.addColumn("Fullname");
		model.addColumn("Role");
		model.addColumn("Parking ID");
		
		for (User user : users) {
			String []row = new String[6];
			row[0] = String.valueOf(user.getId());
			row[1] = user.getUsername();
			row[2] = user.getEmail();
			row[3] = user.getFullname();
			row[4] = String.valueOf(user.getRole());
			row[5] = String.valueOf(user.getParking_id());
			
			model.addRow(row);
		}
		return model;
	}
	
	public void AddUser(User user) {
		try {
			da.insertUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void EditUser (User user) {
		try {
			da.editUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DeleteUser(String username) {
		try {
			da.deleteUser(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
