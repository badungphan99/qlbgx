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

	// active là true nghĩa là trả về danh sách những tài parking đang active và
	// ngược lại nếu active là false
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
	// khi deactive parking thì những tài khoản của nhân viên làm việc ở bãi gửi xe
	// đó cũng deactive
	public void activeParking(int id, boolean active) throws SQLException {
		String sql = "UPDATE parking SET active = ? WHERE parking_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		if (!active) {
			String sqlUser = "UPDATE user SET active = ? WHERE parking_id = ?";
			PreparedStatement sttm2 = conn.prepareStatement(sqlUser);
			sttm2.setBoolean(1, active);
			sttm2.setInt(2, id);
			sttm2.executeUpdate();
			sttm2.close();
		}
		sttm.setBoolean(1, active);
		sttm.setInt(2, id);
		sttm.executeUpdate();
		sttm.close();
	}

	// kiem tra parking_name dang thay doi co trung voi cac parking_name khac (ko
	// chua parking_name dang thay doi)
	public boolean checkEditParkingName(int id, String name) throws SQLException {
		String sql = "SELECT * FROM parking WHERE parking_id != ?";
		PreparedStatement sttm = conn.prepareStatement(sql);
		sttm.setInt(1, id);

		ResultSet rs = sttm.executeQuery();
		List<String> parkingNames = new ArrayList<String>();
		// co trung
		while (rs.next()) {
			String parkingName = rs.getString("parking_name");
			parkingNames.add(parkingName);
		}
		for (int i = 0; i < parkingNames.size(); i++) {
			// username ở đây là mới nhập vào khi thay đổi
			if (parkingNames.get(i).equalsIgnoreCase(name)) {
				// bị trùng
				return false;
			}
		}
		// ko bị trùng
		return true;
	}

	// chỉ có thể edit được parking đang active
	public void editParking(Parking parking) throws SQLException {
		String sqlEdit = "UPDATE parking SET parking_name = ?, bicycle_lot = ?, motorbike_lot = ?, car_lot = ? WHERE parking_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sqlEdit);
		sttm.setString(1, parking.getName());
		sttm.setInt(2, parking.getBicycleLot());
		sttm.setInt(3, parking.getMotorbikeLot());
		sttm.setInt(4, parking.getCarLot());
		sttm.setInt(5, parking.getId());
		sttm.executeUpdate();

		sttm.close();
	}

	// Tự động trừ đi một ví trí trong bãi gửi xe khi có xe vào
	public int checkInVehicle(int vehicleId, int parking_id) throws Exception {
		String sqlGetInfo = "SELECT * FROM parking WHERE parking_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sqlGetInfo);
		sttm.setInt(1, parking_id);
		ResultSet rs = sttm.executeQuery();
		String sqlUpdate;
		PreparedStatement update;
		while (rs.next()) {
			switch (vehicleId) {
			case 1:
				int bicycleLot = rs.getInt("bicycle_lot");
				if (bicycleLot == 0) {
					return -1;
				}
				sqlUpdate = "UPDATE parking SET bicycle_lot = ? WHERE parking_id = ?";
				update = conn.prepareStatement(sqlUpdate);
				update.setInt(1, (bicycleLot - 1));
				update.setInt(2, parking_id);
				update.executeUpdate();
				update.close();
				break;
			case 2:
				int motobikeLot = rs.getInt("motorbike_lot");
				if (motobikeLot == 0) {
					return -1;
				}
				sqlUpdate = "UPDATE parking SET motorbike_lot = ? WHERE parking_id = ?";
				update = conn.prepareStatement(sqlUpdate);
				update.setInt(1, (motobikeLot - 1));
				update.setInt(2, parking_id);
				update.executeUpdate();
				update.close();
				break;
			case 3:
				int carLot = rs.getInt("car_lot");
				if (carLot == 0) {
					return -1;
				}
				sqlUpdate = "UPDATE parking SET car_lot = ? WHERE parking_id = ?";
				update = conn.prepareStatement(sqlUpdate);
				update.setInt(1, (carLot - 1));
				update.setInt(2, parking_id);
				update.executeUpdate();
				update.close();
				break;
			}
		}
		return 0;
	}

	// Tự động cộng thêm một vị trí khi có xe ra ngoài
	public int checkOutVehicle(int vehicleId, int parking_id) throws Exception {
		String sqlGetInfo = "SELECT * FROM parking WHERE parking_id = ?";
		PreparedStatement sttm = conn.prepareStatement(sqlGetInfo);
		sttm.setInt(1, parking_id);
		ResultSet rs = sttm.executeQuery();
		String sqlUpdate;
		PreparedStatement update;
		while (rs.next()) {
			switch (vehicleId) {
			case 1:
				int bicycleLot = rs.getInt("bicycle_lot");
				sqlUpdate = "UPDATE parking SET bicycle_lot = ? WHERE parking_id = ?";
				update = conn.prepareStatement(sqlUpdate);
				update.setInt(1, (bicycleLot + 1));
				update.setInt(2, parking_id);
				update.executeUpdate();
				update.close();
				break;
			case 2:
				int motorbikeLot = rs.getInt("motorbike_lot");
				sqlUpdate = "UPDATE parking SET motorbike_lot = ? WHERE parking_id = ?";
				update = conn.prepareStatement(sqlUpdate);
				update.setInt(1, (motorbikeLot + 1));
				update.setInt(2, parking_id);
				update.executeUpdate();
				update.close();
				break;
			case 3:
				int carLot = rs.getInt("car_lot");
				sqlUpdate = "UPDATE parking SET car_lot = ? WHERE parking_id = ?";
				update = conn.prepareStatement(sqlUpdate);
				update.setInt(1, (carLot + 1));
				update.setInt(2, parking_id);
				update.executeUpdate();
				update.close();
				break;
			}
		}
		return 0;
	}

	public int[] getParkingLot(int id) {
		int[] parkingLot = new int[3];
		String sql = "SELECT bicycle_lot, motorbike_lot, car_lot FROM parking WHERE parking_id = ?";
		PreparedStatement sttm;
		try {
			sttm = conn.prepareStatement(sql);
			sttm.setInt(1, id);

			ResultSet rs = sttm.executeQuery();
			while (rs.next()) {
				parkingLot[0] = rs.getInt("bicycle_lot");
				parkingLot[1] = rs.getInt("motorbike_lot");
				parkingLot[2] = rs.getInt("car_lot");
			}
			sttm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parkingLot;
	}
}
