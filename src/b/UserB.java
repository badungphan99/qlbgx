package b;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

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
	
	public void DeleteUser(int id) {
		try {
			da.deleteUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkEditUsername(int id, String username) throws SQLException {
		return da.checkEditUsername(id, username);
	}


	// Cái này đẻ kiểm tra tính hợp lệ của username thôi mà
	public boolean validate(final String username) {
		return Pattern.compile("^[a-z09]{3,15}$").matcher(username).matches();
	}
	
	
	//chưa cần dùng đến nhưng đừng xóa đi vì có thể dùng đến
	public String[] getAllUsername() {
		return da.getAllUsername();
	}
	
	public String getUsernameBySelectId(int id) {
		return da.getUsernameBySelectID(id);
	}
	public String[] getAllEmployeeID() {
		return da.getAllEmployeeID();
	}
}
