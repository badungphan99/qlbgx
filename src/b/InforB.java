package b;

import java.sql.SQLException;
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
		for (Infor infor : infors) {
			String []row = new String[9];
			row[0] = String.valueOf(infor.getId());
			row[1] = infor.getTimein();
			row[2] = infor.getVehicletype();
			row[3] = infor.getLicenseplate();
			row[4] = infor.getTimeout();
			row[5] = String.valueOf(infor.getPrice());
			row[6] = String.valueOf(infor.getEmployeeid());
			row[7] = String.valueOf(infor.getCardid());
			row[8] = String.valueOf(infor.getParkingid());
			
			model.addRow(row);
		}
		
		return model;
		
	}
	
	public Infor add(Infor infor) {
		return null;
	}
	
	public Infor delete(Infor infor) {
		return null;
	}
	
	public Infor update(Infor infor) {
		return null;
	}
	
}
