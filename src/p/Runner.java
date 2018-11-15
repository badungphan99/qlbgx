package p;

import da.ConnectionUtil;
import e.Infor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Runner {
	public static void main(String[] args) {
		FirstFrame firstFrame = new FirstFrame();
		firstFrame.setVisible(true);

	}
//public static void main(String[] args) throws Exception{
//	Connection conn = ConnectionUtil.getConnection();
//	List<Infor> infors = new ArrayList<Infor>();
//	String sql = "SELECT * FROM infor";
//	Statement sttm = conn.createStatement();
//	ResultSet rs = sttm.executeQuery(sql);
//
//	while (rs.next()) {
//		Infor infor = new Infor(rs.getInt("id"), rs.getString("time_in"), rs.getString("vehicle_type"),
//				rs.getString("license_plate"), rs.getString("time_out"), rs.getInt("price"),
//				rs.getInt("employee_id"), rs.getInt("card_id"), rs.getInt("parking_id"));
//
//		System.out.println(infor.toString());
//	}
//}
}
