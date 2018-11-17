package da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehicleTypePriceDA {
    private static Connection conn;

    public VehicleTypePriceDA()  {
        try {
            conn = ConnectionUtil.getConnection();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertVehicleSetting (int id, int price, int parkingPerios, int overdue, int perHour, int perDay) throws SQLException {
        String sql = "UPDATE vehicle_type_price SET price = ?";
//        switch (overdue){
//            case 1:

        sql = sql + ", overdue = 1 WHERE id_vehicle = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, price);
        stmt.setInt(2, id);
        stmt.executeUpdate();
//                break;
//            case 2:
//                sql = sql + ", parking_perios = ?, overdue = 2, per_hour = ? WHERE id = ?";
//                break;
//            case 3:
//                sql = sql + ", parking_perios = ?, overdue = 3, per_day = ? WHERE id = ?";
//                break;
//            case 4:
//                sql = sql + ", parking_perios = ?, overdue = 4, per_hour = ?, per_day = ? WHERE id = ?";
//                break;
//        }
//        try {
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            switch (overdue){
//                case 1:
//                    stmt.setInt(1,price);
//                    stmt.setInt(2,id);
//                    break;
//                case 2:
//                    stmt.setInt(1,price);
//                    stmt.setInt(2,parkingPerios);
//                    stmt.setInt(3,perHour);
//                    stmt.setInt(4,id);
//                    break;
//                case 3:
//                    stmt.setInt(1,price);
//                    stmt.setInt(2,parkingPerios);
//                    stmt.setInt(3,perDay);
//                    stmt.setInt(4,id);
//                    break;
//                case 4:
//                    stmt.setInt(1,price);
//                    stmt.setInt(2,parkingPerios);
//                    stmt.setInt(3,overdue);
//                    stmt.setInt(4,perHour);
//                    stmt.setInt(5,perDay);
//                    stmt.setInt(6,id);
//                    break;


//            }
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    }
}
