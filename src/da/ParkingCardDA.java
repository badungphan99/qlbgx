package da;

import e.ParkingCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingCardDA {
	private Connection conn;
	
	
	public ParkingCardDA() {
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
	
	
	public List<ParkingCard> getAll() throws SQLException{
		List<ParkingCard> parkingcards = new ArrayList<ParkingCard>();
		String sql = "SELECT * FROM parking_card";
		Statement sttm = conn.createStatement();
		
		ResultSet rs = sttm.executeQuery(sql);
		
		while (rs.next()) {
			ParkingCard parkingcard = new ParkingCard(rs.getInt("card_id"), rs.getString("card_type"));
			parkingcards.add(parkingcard);
		}
		return parkingcards;
		
	}
	
	public boolean deleteParkingCard (int id) throws SQLException {
		String sql = "DELETE FROM parking_card WHERE card_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setInt(1, id);
		
		int result = sttm.executeUpdate();
		return result>0;
	}
}
