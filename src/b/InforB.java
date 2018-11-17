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
	// Hiện thị ra thành một bảng thông tin xe trong bãi
	public DefaultTableModel getAllInfor() throws SQLException {
		List <Infor> infors = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Card ID");
		model.addColumn("Time in");
		model.addColumn("Vehicle type");
		model.addColumn("Licenseplate");
		model.addColumn("Time out");
		model.addColumn("Price");
		model.addColumn("Employee id");
		model.addColumn("Parking id");
		for (Infor infor : infors) {
			String []row = new String[8];
			row[0] = String.valueOf(infor.getCardid());
			row[1] = String.valueOf(infor.getTimein());
			row[2] = String.valueOf(infor.getVehicletype());
			row[3] = String.valueOf(infor.getLicenseplate());
			row[4] = String.valueOf(infor.getTimeout());
			row[5] = String.valueOf(infor.getPrice());
			row[6] = String.valueOf(infor.getEmployeeid());
			row[7] = String.valueOf(infor.getParkingid());
			
			model.addRow(row);
		}
		
		return model;
		
	}
	
	public Infor add(Infor infor) {return null; }
	
	public Infor delete(Infor infor) { return null; }
	
	public Infor update(Infor infor) {
		return null;
	}
    // check in xe khi vào bãi, tạm thời giá đang fix cứng thành một số
	public Infor checkin(String vehicleType, String licensePlate){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String time_in = dateFormat.format(date);
		try {
			da.insertInfo(time_in,vehicleType,licensePlate,9876,loginSession.getUser().getId(), loginSession.getUser().getParking_id());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			return da.getCardId(licensePlate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Infor();
	}
    /* Sử dụng card id = -1 để nhận biết lỗi vì card id luôn > 0
    * Ở đây có 2 lần check một lần để check xem vé xe có tồn tại hay không
    * lần thứ 2 thì kiểm tra biển số xe và vé có nằng trong cùng một hàng hay không */
	public String checkOut(int card_id,String licensePlate)throws SQLException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String time_out = dateFormat.format(date);
		if (da.checkCardId(card_id).getCardid() == -1){
		    return "Khong ton tai card id nay";
		}else if (da.checkLicensePlate(card_id, licensePlate).getCardid() == -1){
		    return "Ve xe khong dung voi bien so";
		}else {
		    da.insertTimeOut(card_id,time_out);
		}
		return da.price(card_id);

    }
}
