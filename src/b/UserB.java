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
	
	public DefaultTableModel getAllUser(){
		List<User> users = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee_id");
		model.addColumn("Username");
		model.addColumn("Active");
		model.addColumn("Email");
		model.addColumn("Fullname");
		model.addColumn("Role");
		model.addColumn("Parking ID");
		
		for (User user : users) {
			String []row = new String[7];
			row[0] = String.valueOf(user.getId());
			row[1] = user.getUsername();
			row[2] = String.valueOf(user.isActive());
			row[3] = user.getEmail();
			row[4] = user.getFullname();
			row[5] = String.valueOf(user.getRole());
			row[6] = String.valueOf(user.getParking_id());
			
			model.addRow(row);
		}
		return model;
	}
	
	public DefaultTableModel getAllUserActive(boolean active){
		List<User> users = da.getAllUserActive(active);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Employee_id");
		model.addColumn("Username");
		model.addColumn("Active");
		model.addColumn("Email");
		model.addColumn("Fullname");
		model.addColumn("Role");
		model.addColumn("Parking ID");
		
		for (User user : users) {
			String []row = new String[7];
			row[0] = String.valueOf(user.getId());
			row[1] = user.getUsername();
			row[2] = String.valueOf(user.isActive());
			row[3] = user.getEmail();
			row[4] = user.getFullname();
			row[5] = String.valueOf(user.getRole());
			row[6] = String.valueOf(user.getParking_id());
			
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
	
	public void activeUser(int id, boolean active) {
		try {
			da.activeUser(id, active);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean checkEditUsername(int id, String username) throws SQLException {
		return da.checkEditUsername(id, username);
	}


	// Cái này đẻ kiểm tra tính hợp lệ của username thôi mà
	public boolean isValidate(final String name) {
		return Pattern.compile("^[a-z0-9]{3,15}$").matcher(name).matches();
	}
	
	//chưa cần dùng đến nhưng đừng xóa đi vì có thể dùng đến
	public String[] getAllUsername() {
		return da.getAllUsername();
	}
	
	public String getUsernameBySelectId(int id) {
		return da.getUsernameBySelectID(id);
	}
	public String[] getAllEmployeeIDActive(boolean active) {
		return da.getAllEmployeeIDActive(active);
	}
}
