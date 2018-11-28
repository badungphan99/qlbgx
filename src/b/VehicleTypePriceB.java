package b;

import da.VehicleTypePriceDA;
import e.Infor;
import e.VehicleTypePrice;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class VehicleTypePriceB {
    private VehicleTypePriceDA vehicleTypePriceDA;

    public VehicleTypePriceB() {vehicleTypePriceDA = new VehicleTypePriceDA();}
    // Chỗ này để hiện thị ra bảng thông tin cài đặt về các loại xe trong bãu
    public DefaultTableModel getAllInfor() {
        List<VehicleTypePrice> vehicleTypePrices = vehicleTypePriceDA.getAll();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Vehicle id");
        model.addColumn("Vehicle type");
        model.addColumn("Price");
        model.addColumn("Time period");
        model.addColumn("Per hour");
        model.addColumn("Per Day");
        for (VehicleTypePrice vehicleTypePrice:vehicleTypePrices) {
            String []row = new String[6];
            row[0] = String.valueOf(vehicleTypePrice.getIdVehicle());
            row[1] = String.valueOf(vehicleTypePrice.getVehicleType());
            row[2] = String.valueOf(vehicleTypePrice.getPrice());
            row[3] = String.valueOf(vehicleTypePrice.getTimePerios());
            row[4] = String.valueOf(vehicleTypePrice.getPerHour());
            row[5] = String.valueOf(vehicleTypePrice.getPerDay());
            model.addRow(row);
        }

        return model;

    }


    //Insert các cài đặt cho mỗi loại xe
    public void insertVehicleSetting(int idVehicle, int price, int timePerios, int perHour, int perDay){
        int mode = -1;
        if((perHour == 0)&&(perDay==0)){
            mode = 1;
        }
        if((perHour != 0)&&(perDay == 0)){
            mode = 2;
        }
        if((perHour == 0)&&(perDay != 0)){
            mode = 3;
        }
        if((perHour != 0)&&(perDay != 0)){
            mode = 4;
        }
        try {
            vehicleTypePriceDA.insertVehicleSetting(idVehicle,price,timePerios,mode,perHour,perDay);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Tính tiền
    public int calcPrice(int id_vehicle, int time) throws Exception {
        int[] info = vehicleTypePriceDA.getinfo(id_vehicle);
        int duration;
        switch (info[0]){
            case 1:
                return info[1];
            case 2:
                duration = time - info[2];
                if(duration <= 0){
                    return info[1];
                }else{
                    return info[1] + info[3]*duration;
                }
            case 3:
                duration = time - info[2];
                if(duration <=0 ){
                    return info[1];
                }else {
                    if(duration % 24 == 0){
                        return info[1] + info[4]*(duration/24 + 1);
                    }
                }
            case 4:
                duration = time - info[2];
                if (duration <= 0 ){
                    return info[1];
                }else {
                    return info[1] + (duration/24)*info[4] + (duration%24)*info[3];
                }
        }
        return -1 ;
    }

	public String[] getAllVehicleTPID() {
		return vehicleTypePriceDA.getVehicleTPId();
	}
}