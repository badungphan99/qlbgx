package b;

import da.VehicleTypePriceDA;

public class VehicleTypePriceB {
    private VehicleTypePriceDA vehicleTypePriceDA;

    public VehicleTypePriceB() {vehicleTypePriceDA = new VehicleTypePriceDA();}
    // Chỗ này để hiện thị ra bảng thông tin cài đặt về các loại xe trong bãu



    //Insert các cài đặt cho mỗi loại xe
    public void insertBicycleSetting (int price, int parkingPerios, int overdue, int perHour, int perDay){
        String sql = "UPDATE vehicle_type_price SET price = ?";
        switch (overdue){
            case 1:
                sql = sql + ", overdue = 1 WHERE id = 1";
                break;
            case 2:
               // sql = sql + ", parking_perios = ?, overdue = 2, "
        }
    }
}