package da;

import e.VehicleTypePrice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleTypePriceDA {
    private static Connection conn;

    public VehicleTypePriceDA()  {
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

    public List<VehicleTypePrice> getAll() {
		List<VehicleTypePrice> vehicleTypePrices = new ArrayList<VehicleTypePrice>();
		String sql = "SELECT * FROM vehicle_type_price";
		Statement sttm;
		try {
			sttm = conn.createStatement();

			ResultSet rs = sttm.executeQuery(sql);

			while (rs.next()) {
				VehicleTypePrice vehicleTypePrice = new VehicleTypePrice(rs.getString("vehicle_type"),
						rs.getInt("id_vehicle"), rs.getInt("price"), rs.getInt("time_period"), rs.getInt("per_hour"),
						rs.getInt("per_day"), rs.getInt("mode"));
				vehicleTypePrices.add(vehicleTypePrice);
			}
			sttm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vehicleTypePrices;
	}

    public void insertVehicleSetting (int id, int price, int timePerios, int mode, int perHour, int perDay) throws SQLException {
        PreparedStatement stmt;
        String sql = "UPDATE vehicle_type_price SET price = ?";
        switch (mode){
            case 1:
                sql = sql + ", time_period = null, per_hour = null, per_day = null, mode = 1 WHERE id_vehicle = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, price);
                stmt.setInt(2, id);
                stmt.executeUpdate();
                stmt.close();
                break;
            case 2:
                sql = sql + ", time_period = ?, per_hour = ?, per_day = null, mode = 2 WHERE id_vehicle = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,price);
                stmt.setInt(2,timePerios);
                stmt.setInt(3,perHour);
                stmt.setInt(4,id);
                stmt.executeUpdate();
                stmt.close();
                break;
            case 3:
                sql = sql + ", time_period = ?, per_hour = null, per_day = ?, mode = 3 WHERE id_vehicle = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,price);
                stmt.setInt(2,timePerios);
                stmt.setInt(3,perDay);
                stmt.setInt(4,id);
                stmt.executeUpdate();
                stmt.close();
                break;
            case 4:
                sql = sql + ", time_period = ?, per_hour = ?, per_day = ?, mode = 4 WHERE id_vehicle = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1,price);
                stmt.setInt(2,timePerios);
                stmt.setInt(3,perHour);
                stmt.setInt(4,perDay);
                stmt.setInt(5,id);
                stmt.executeUpdate();
                stmt.close();
                break;
        }
    }

    public int[] getinfo(int id_vehicle) throws Exception{
        int[] info = new int[5];
        String sql = "SELECT * FROM vehicle_type_price";
        Statement sttm = conn.createStatement();
        ResultSet rs = sttm.executeQuery(sql);
        while (rs.next()) {
            VehicleTypePrice vehicleTypePrice = new VehicleTypePrice(rs.getString("vehicle_type"), rs.getInt("id_vehicle"),rs.getInt("price"),
                    rs.getInt("time_period"),rs.getInt("per_hour"),rs.getInt("per_day"),rs.getInt("mode"));
            if(id_vehicle == vehicleTypePrice.getIdVehicle()){
                info[0] = vehicleTypePrice.getMode();
                info[1] = vehicleTypePrice.getPrice();
                info[2] = vehicleTypePrice.getTimePerios();
                info[3] = vehicleTypePrice.getPerHour();
                info[4] = vehicleTypePrice.getPerDay();
            }
        }
        return info;
    }
    
    public String[] getVehicleTPId() {
		List<String> vehicleTPIDs = new ArrayList<String>();
		String sql = "SELECT id_vehicle FROM vehicle_type_price ORDER BY id_vehicle ASC";
		Statement sttm;
		try {
			sttm = conn.createStatement();
			ResultSet rs = sttm.executeQuery(sql);

			while (rs.next()) {
				String vehicleID = String.valueOf(rs.getInt("id_vehicle"));
				vehicleTPIDs.add(vehicleID);

			}
			sttm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] vTPId = new String[vehicleTPIDs.size()];
		for (int i = 0; i < vehicleTPIDs.size(); i++) {
			vTPId[i] = vehicleTPIDs.get(i);
		}
		return vTPId;
	}
}
