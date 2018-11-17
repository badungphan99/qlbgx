package da;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import e.Infor;

public class InforDA {
	private static Connection conn;

	public InforDA()  {
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

	public List<Infor> getAll() throws SQLException {
		List<Infor> infors = new ArrayList<Infor>();
		String sql = "SELECT * FROM infor";
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery(sql);

		while (rs.next()) {
			Infor infor = new Infor(rs.getInt("card_id"), rs.getString("time_in"), rs.getString("vehicle_type"),
					rs.getString("license_plate"), rs.getString("time_out"), rs.getInt("price"),
					rs.getInt("employee_id"), rs.getInt("parking_id"));
			
			infors.add(infor);
		}
		sttm.close();

		return infors;
	}
	
	public boolean deleteInfor(int id) throws SQLException {
		String sql = "DELETE FROM infor WHERE id = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setInt(1, id);
		int result = sttm.executeUpdate();
		sttm.close();
		return result>0;
	}
	//Thêm thông tin về xe gửi vào bảng

	public void insertInfo(String time_in, String vehicle_type, String license_plate, int price, int employee_id, int parking_id){
		String sql = "INSERT INTO infor (time_in,vehicle_type,license_plate,price,employee_id,parking_id)"+"VALUES(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,time_in);
			stmt.setString(2,vehicle_type);
			stmt.setString(3,license_plate);
			stmt.setInt(4,price);
			stmt.setInt(5,employee_id);
			stmt.setInt(6,parking_id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	// trả lại số thẻ để in ra
	public Infor getCardId(String license_plate) throws SQLException{
		String sql = "SELECT * FROM infor";
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery(sql);

		while (rs.next()) {
			Infor infor = new Infor(rs.getInt("card_id"), rs.getString("time_in"), rs.getString("vehicle_type"),
					rs.getString("license_plate"), rs.getString("time_out"), rs.getInt("price"),
					rs.getInt("employee_id"), rs.getInt("parking_id"));
			if(license_plate.equalsIgnoreCase(infor.getLicenseplate())){
				return infor;
			}
		}
		sttm.close();
		return new Infor();
	}
	// kiểm tra tính hợp lệ của vé xe (vé có tồn tại hay không)
	public Infor checkCardId(int card_id) throws SQLException{
		String sql = "SELECT * FROM infor";
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery(sql);

		while (rs.next()) {
			Infor infor = new Infor(rs.getInt("card_id"), rs.getString("time_in"), rs.getString("vehicle_type"),
					rs.getString("license_plate"), rs.getString("time_out"), rs.getInt("price"),
					rs.getInt("employee_id"), rs.getInt("parking_id"));
			if(card_id == infor.getCardid()){
				return infor;
			}
		}
		sttm.close();
		return new Infor();
	}
	// kiểm tra xem vé và biển số xe có trùng nhau hay không
	public Infor checkLicensePlate(int card_id, String licensePlate) throws SQLException{
		String sql = "SELECT * FROM infor";
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery(sql);

		while (rs.next()) {
			Infor infor = new Infor(rs.getInt("card_id"), rs.getString("time_in"), rs.getString("vehicle_type"),
					rs.getString("license_plate"), rs.getString("time_out"), rs.getInt("price"),
					rs.getInt("employee_id"), rs.getInt("parking_id"));
			if((card_id == infor.getCardid()) && (licensePlate.equalsIgnoreCase(infor.getLicenseplate()))){
				return infor;
			}
		}
		sttm.close();
		return new Infor();
	}
	// Thêm thời gian check out
	public void insertTimeOut(int card_id,String time_out) throws SQLException{
		String sql = "UPDATE infor SET time_out = ? WHERE card_id = ?";
		Statement sttm = null;
		sttm = conn.prepareStatement(sql);
		((PreparedStatement) sttm).setString(1,time_out);
		((PreparedStatement) sttm).setInt(2,card_id);
		((PreparedStatement) sttm).executeUpdate();
		sttm.close();
	}
	// Tính tiền gửi xe dựa trên thời gian
	public String price(int card_id) throws SQLException{
		String sql = "SELECT * FROM infor";
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery(sql);

		while (rs.next()) {
			Infor infor = new Infor(rs.getInt("card_id"), rs.getString("time_in"), rs.getString("vehicle_type"),
					rs.getString("license_plate"), rs.getString("time_out"), rs.getInt("price"),
					rs.getInt("employee_id"), rs.getInt("parking_id"));
			if(card_id == infor.getCardid()){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date time_in = dateFormat.parse(infor.getTimein());
					Date time_out = dateFormat.parse(infor.getTimeout());
					long duration = (time_out.getTime() - time_in.getTime()) / (60 * 60 * 1000) % 24;
					return String.valueOf(duration);
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}
		sttm.close();
		return "doan xem";
	}
}