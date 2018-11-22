package b;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import da.ParkingDA;
import e.Parking;

public class ParkingB {
	private ParkingDA da;
	
	public ParkingB() {
		da = new ParkingDA();
	}
	
	public DefaultTableModel getAllParking() {
		List<Parking> parkings = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Parking_ID");
		model.addColumn("Parking_name");
		model.addColumn("Active");
		model.addColumn("Bicycle_Lot");
		model.addColumn("Motorbike_Lot");
		model.addColumn("Car_Lot");
		
		for (Parking parking : parkings) {
			String []row = new String[6];
			row[0] = String.valueOf(parking.getId());
			row[1] = String.valueOf(parking.getName());
			row[2] = String.valueOf(parking.isActive());
			row[3] = String.valueOf(parking.getBicycleLot());
			row[4] = String.valueOf(parking.getMotorbikeLot());
			row[5] = String.valueOf(parking.getCarLot());

			model.addRow(row);
		}
		return model;
	}
	
	public void addParking(Parking parking) {
		try {
			da.insertParking(parking);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Parking delete(Parking parking) {
		return null;
	}
	
	public Parking update (Parking parking) {
		return null;
	}
	
	public String [] getAllParkingID(){
		return da.getAllParkingId();
	}
	
	public boolean isNotExistParkingName(String parkingName) throws SQLException {
		return da.isNotExistParkingName(parkingName);
	}
	
	public String [] getAllParkingIdActive(){
		return da.getAllParkingIdActive();
	}
	
	public String [] getAllParkingIdNotActive(){
		return da.getAllParkingIdNotActive();
	}
	
	public void deactiveParking(int id) {
		try {
			da.deactiveParking(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activeParking(int id) {
		try {
			da.activeParking(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
