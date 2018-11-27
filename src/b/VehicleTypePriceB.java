package b;

import da.VehicleTypePriceDA;

import java.sql.SQLException;

public class VehicleTypePriceB {
    private VehicleTypePriceDA vehicleTypePriceDA;

    public VehicleTypePriceB() {vehicleTypePriceDA = new VehicleTypePriceDA();}
    // Chỗ này để hiện thị ra bảng thông tin cài đặt về các loại xe trong bãu



    //Insert các cài đặt cho mỗi loại xe
    public void VehicleSetting(int id, int price, int parkingPerios, int mode, int perHour, int perDay){
        try {
            vehicleTypePriceDA.insertVehicleSetting(id,price,parkingPerios,mode,perHour,perDay);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int calcPrice(int id_vehicle, int time) throws Exception {
        int[] info = vehicleTypePriceDA.getinfo(id_vehicle);
        int duration;
        System.out.print(info[0]);
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
}