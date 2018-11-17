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

    public void insertBicycleSetting (int price, int parkingPerios, int overdue, int perHour, int perDay){
        String sql = "UPDATE vehicle_type_price SET price = ?";
        switch (overdue){
            case 1:
                sql = sql + ", overdue = 1 WHERE id = 1";
                break;
            case 2:
                sql = sql + ", parking_perios = ?, overdue = 2, per_hour = ? WHERE id = 1";
                break;
            case 3:
                sql = sql + ", parking_perios = ?, overdue = 2, per_day = ? WHERE id = 1";
                break;
            case 4:
                sql = sql + ", parking_perios = ?, overdue = 2, per_hour = ?, per_day = ? WHERE id = 1";
                break;
                //xxxx
                ///eewewe
        }
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,price);

            stmt.setInt(2,parkingPerios);
            stmt.setInt(3,overdue);
            stmt.setInt(4,perHour);
            stmt.setInt(5,perDay);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
