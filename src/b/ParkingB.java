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
	
	public DefaultTableModel getAllParking() throws SQLException {
		List<Parking> parkings = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Parking_ID");
		model.addColumn("Parking_name");
		model.addColumn("Parking_lot");
		
		for (Parking parking : parkings) {
			String []row = new String[3];
			row[0] = String.valueOf(parking.getId());
			row[1] = parking.getName();
			row[2] = String.valueOf(parking.getSlots());
			
			model.addRow(row);
		}
		return model;
	}
	
	public Parking add(Parking parking) {
		return null;
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
}
