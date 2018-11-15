package b;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import da.ParkingCardDA;
import e.Parking;
import e.ParkingCard;

public class ParkingCardB {
	private ParkingCardDA da;
	
	public ParkingCardB() {
		da = new ParkingCardDA();
	}
	
	public DefaultTableModel getAllParkingCard() throws SQLException {
		List <ParkingCard> parkingcards = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Card_ID");
		model.addColumn("Card_type");
		
		for (ParkingCard parkingcard : parkingcards) {
			String []row = new String[2];
			row[0] = String.valueOf(parkingcard.getId());
			row[1] = parkingcard.getType();
			model.addRow(row);
		}
		
		return model;
		
	}
	
	public ParkingCard add(ParkingCard parkingcard) {
		return null;
	}
	
	public ParkingCard delete(ParkingCard parkingcard) {
		return null;
	}
	
	public ParkingCard update(ParkingCard parkingcard) {
		return null;
	}
}
