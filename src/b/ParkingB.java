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
		model.addColumn("Active");
		model.addColumn("Bicycle Lot");
		model.addColumn("Motobike Lot");
		model.addColumn("Car Lot");
		
		for (Parking parking : parkings) {
			String []row = new String[6];
			row[0] = String.valueOf(parking.getId());
			row[1] = String.valueOf(parking.getName());
			row[2] = String.valueOf(parking.isActive());
			row[3] = String.valueOf(parking.getBicycleLot());
			row[4] = String.valueOf(parking.getMotobikeLot());
			row[5] = String.valueOf(parking.getCarLot());

			model.addRow(row);
		}
		return model;
	}
	
	public Parking add(Parking parking) { return null; }
	
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
