package e;

public class VehicleTypePrice {
    private String vehicleType;
    private int idVehicle, price, parkingPerios, overdue, perHour, perDay;

    public VehicleTypePrice(String vehicleType, int idVehicle, int price, int parkingPerios, int overdue, int perHour, int perDay) {
        this.vehicleType = vehicleType;
        this.idVehicle = idVehicle;
        this.price = price;
        this.parkingPerios = parkingPerios;
        this.overdue = overdue;
        this.perHour = perHour;
        this.perDay = perDay;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getParkingPerios() {
        return parkingPerios;
    }

    public void setParkingPerios(int parkingPerios) {
        this.parkingPerios = parkingPerios;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overDue) {
        this.overdue = overDue;
    }

    public int getPerHour() {
        return perHour;
    }

    public void setPerHour(int perHour) {
        this.perHour = perHour;
    }

    public int getPerDay() {
        return perDay;
    }

    public void setPerDay(int perDay) {
        this.perDay = perDay;
    }
}
