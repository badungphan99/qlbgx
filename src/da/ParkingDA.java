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
			int slots = rs.getInt("parking_lot");
			
			Parking parking = new Parking(id, name, slots);
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

}
