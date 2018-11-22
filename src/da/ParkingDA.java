package da;

import e.Parking;
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
	
	public List<Parking> getAll() throws SQLException{
		List<Parking> parkings = new ArrayList<Parking>();
		String sql = "SELECT * FROM parking";
		Statement sttm = conn.createStatement();
		ResultSet rs = sttm.executeQuery(sql);
		
		while (rs.next()) {
			int id = rs.getInt("parking_id");
			String name = rs.getString("parking_name");
			boolean active = rs.getBoolean("active");
			int bicycleLot = rs.getInt("bicycle_lot");
			int motobikeLot = rs.getInt("motobike_lot");
			int carLot = rs.getInt("car_lot");
			
			Parking parking = new Parking(id, name, active, bicycleLot, motobikeLot, carLot);
			parkings.add(parking);
		}
		return parkings;
	}
	
	public boolean deleteParking(int id) throws SQLException {
		String sql = "DELETE FROM parking WHERE parking_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		
		sttm.setInt(1, id);
		int result = sttm.executeUpdate();
		return result>0;
	}

	
	public String [] getAllParkingId() {
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
		
		String [] pkId = new String[parkingIDs.size()];
		for (int i = 0; i < parkingIDs.size(); i++) {
			pkId[i] = parkingIDs.get(i);
		}
		return pkId;
	}
	
	
}
