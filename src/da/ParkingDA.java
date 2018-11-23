package da;

import e.Parking;
import e.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingDA {
	private Connection conn;

	public ParkingDA() {
		try {
			conn = ConnectionUtil.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Parking> getAll() {
		List<Parking> parkings = new ArrayList<Parking>();
		String sql = "SELECT * FROM parking";
		Statement sttm;
		try {
			sttm = conn.createStatement();
			ResultSet rs = sttm.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("parking_id");
				String name = rs.getString("parking_name");
				boolean active = rs.getBoolean("active");
				int bicycleLot = rs.getInt("bicycle_lot");
				int motorbikeLot = rs.getInt("motorbike_lot");
				int carLot = rs.getInt("car_lot");

				Parking parking = new Parking(id, name, active, bicycleLot, motorbikeLot, carLot);
				parkings.add(parking);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return parkings;
	}

	public List<Parking> getAllParkingActive(boolean isactive) {
		List<Parking> parkings = new ArrayList<Parking>();
		String sql = "SELECT * FROM parking WHERE active = ?";
		PreparedStatement sttm;
		try {
			sttm = conn.prepareStatement(sql);
			sttm.setBoolean(1, isactive);
			ResultSet rs = sttm.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("parking_id");
				String name = rs.getString("parking_name");
				boolean active = rs.getBoolean("active");
				int bicycleLot = rs.getInt("bicycle_lot");
				int motorbikeLot = rs.getInt("motorbike_lot");
				int carLot = rs.getInt("car_lot");

				Parking parking = new Parking(id, name, active, bicycleLot, motorbikeLot, carLot);
				parkings.add(parking);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return parkings;
	}

	
	public String[] getAllParkingId() {
		List<String> parkingIDs = new ArrayList<String>();
		String sql = "SELECT parking_id FROM parking";
		Statement sttm;
		try {
			sttm = conn.createStatement();
			ResultSet rs = sttm.executeQuery(sql);

			while (rs.next()) {
				String parkingID = String.valueOf(rs.getInt("parking_id"));
				parkingIDs.add(parkingID);

			}
			sttm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] pkId = new String[parkingIDs.size()];
		for (int i = 0; i < parkingIDs.size(); i++) {
			pkId[i] = parkingIDs.get(i);
		}
		return pkId;
	}

	
	public boolean isNotExistParkingName(String parkingName) throws SQLException {
		String sql = "SELECT * FROM parking WHERE parking_name = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setString(1, parkingName);

		ResultSet rs = sttm.executeQuery();

		if (rs.next()) {
			// đã tồn taị
			return false;
		}
		// chưa tồn tại
		return true;
	}

	public void insertParking(Parking parking) throws SQLException {
		String sql = "INSERT INTO parking (parking_name, active, bicycle_lot, motorbike_lot, car_lot)"
				+ "VALUES (?, ?, ?, ?, ?)";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setString(1, parking.getName());
		sttm.setBoolean(2, parking.isActive());
		sttm.setInt(3, parking.getBicycleLot());
		sttm.setInt(4, parking.getMotorbikeLot());
		sttm.setInt(5, parking.getCarLot());
		sttm.executeUpdate();

	}

	// active là true nghĩa là trả về danh sách những tài parking đang active và ngược
	// lại nếu active là false
	public String[] getAllParkingIdActive(boolean active) {
		List<String> parkingIDs = new ArrayList<String>();
		String sql = "SELECT parking_id FROM parking WHERE active = ?";
		PreparedStatement sttm;
		try {
			sttm = conn.prepareStatement(sql);
			sttm.setBoolean(1, active);
			ResultSet rs = sttm.executeQuery();

			while (rs.next()) {
				String parkingID = String.valueOf(rs.getInt("parking_id"));
				parkingIDs.add(parkingID);

			}
			sttm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] pkId = new String[parkingIDs.size()];
		for (int i = 0; i < parkingIDs.size(); i++) {
			pkId[i] = parkingIDs.get(i);
		}
		return pkId;
	}
	
	// active là true nghĩa là active parking đó, ngược lại là deactive parking đó
	public void activeParking(int id, boolean active) throws SQLException {
		String sql = "UPDATE parking SET active = ? WHERE parking_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setBoolean(1, active);
		sttm.setInt(2, id);
		sttm.executeUpdate();
		sttm.close();
	}
}
