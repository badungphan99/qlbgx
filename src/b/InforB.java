package b;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import da.InforDA;
import e.Infor;

public class InforB {

	private InforDA da;
	
	public InforB() {
		da = new InforDA();
	}
	
	public DefaultTableModel getAllInfor() throws SQLException {
		List <Infor> infors = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Time in");
		model.addColumn("Vehicle type");
		model.addColumn("Licenseplate");
		model.addColumn("Time out");
		model.addColumn("Price");
		model.addColumn("Employee id");
		model.addColumn("Card id");
		model.addColumn("Parking id");
		for (Infor infor : infors) {
			String []row = new String[9];
			row[0] = String.valueOf(infor.getId());
			row[1] = String.valueOf(infor.getTimein());
			row[2] = String.valueOf(infor.getVehicletype());
			row[3] = String.valueOf(infor.getLicenseplate());
			row[4] = String.valueOf(infor.getTimeout());
			row[5] = String.valueOf(infor.getPrice());
			row[6] = String.valueOf(infor.getEmployeeid());
			row[7] = String.valueOf(infor.getCardid());
			row[8] = String.valueOf(infor.getParkingid());
			
			model.addRow(row);
		}
		
		return model;
		
	}
	
	public Infor add(Infor infor) {return null; }
	
	public Infor delete(Infor infor) { return null; }
	
	public Infor update(Infor infor) {
		return null;
	}

	public void checkin(String vehicleType, String licensePlate){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String time_in = dateFormat.format(date);
		try {
			da.insertInfo(time_in,vehicleType,licensePlate,9876,loginSession.getUser().getId(),4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
