package da;

import java.util.ArrayList;
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
			Infor infor = new Infor(rs.getInt("id"), rs.getString("time_in"), rs.getString("vehicle_type"),
					rs.getString("license_plate"), rs.getString("time_out"), rs.getInt("price"),
					rs.getInt("employee_id"), rs.getInt("card_id"), rs.getInt("parking_id"));
			
			infors.add(infor);
		}

		return infors;
	}
	
	public boolean deleteInfor(int id) throws SQLException {
		String sql = "DELETE FROM infor WHERE id = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setInt(1, id);
		int result = sttm.executeUpdate();
		
		return result>0;
		
		
	}

}