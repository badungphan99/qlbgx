package b;

import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public DefaultTableModel getAllParkingActive(boolean active) {
		List<Parking> parkings = da.getAllParkingActive(active);
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
	
	public Parking update (Parking parking) {
		return null;
	}
	
	public String [] getAllParkingID(){
		return da.getAllParkingId();
	}
	
	public boolean isNotExistParkingName(String parkingName) throws SQLException {
		return da.isNotExistParkingName(parkingName);
	}
	
	public String [] getAllParkingIdActive(boolean active){
		return da.getAllParkingIdActive(active);
	}
	
	public void activeParking(int id, boolean active) {

			try {
				da.activeParking(id, active);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
	public boolean checkEditParkingName(int id, String name) throws SQLException {
		return da.checkEditParkingName(id, name);
	}
	
	public void editParking (Parking parking) {
		try {
			da.editParking(parking);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String [] getParkingLot() {
		List<String> vehicle = new ArrayList<String>();
		int [] parkingLot = da.getParkingLot(loginSession.getUser().getParking_id());
		if(parkingLot [0] > 0) {
			vehicle.add("Bicycle");
		}
		if (parkingLot [1] > 0) {
			vehicle.add("Motorbike");
		}
		if (parkingLot [2] > 0) {
			vehicle.add("Car");
		}
		
		String [] vehicleType = new String[vehicle.size()];
		for (int i = 0; i < vehicleType.length; i++) {
			vehicleType [i] = vehicle.get(i);
		}
		
		return vehicleType;
	}
}
