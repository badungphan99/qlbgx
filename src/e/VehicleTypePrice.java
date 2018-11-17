package e;

public class VehicleTypePrice {
    private String vehicleType;
    private int idVehicle, price, parkingPerios, mode, perHour, perDay;

    public VehicleTypePrice(int idVehicle, String vehicleType,  int price, int parkingPerios, int mode, int perHour, int perDay) {
        this.vehicleType = vehicleType;
        this.idVehicle = idVehicle;
        this.price = price;
        this.parkingPerios = parkingPerios;
        this.mode = mode;
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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
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
