package e;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Infor {
	private String timein;
	private String vehicletype, licenseplate;
	private String timeout;
	private int price;
	private int employeeid, cardid, parkingid;
	
	public Infor(){
		this.cardid = -1;
	}

	public Infor(int cardid, String timein, String vehicletype, String licenseplate, String timeout, int price,
			int employeeid, int parkingid) {
		super();
		this.timein = timein;
		this.vehicletype = vehicletype;
		this.licenseplate = licenseplate;
		this.timeout = timeout;
		this.price = price;
		this.employeeid = employeeid;
		this.cardid = cardid;
		this.parkingid = parkingid;
	}


	public String getTimein() {
		return timein;
	}


	public void setTimein(DateTimeFormatter timein) {
		this.timein = String.valueOf(timein);
	}


	public String getVehicletype() {
		return vehicletype;
	}


	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}


	public String getLicenseplate() {
		return licenseplate;
	}


	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}


	public String getTimeout() {
		return timeout;
	}


	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getEmployeeid() {
		return employeeid;
	}


	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}


	public int getCardid() {
		return cardid;
	}


	public void setCardid(int cardid) {
		this.cardid = cardid;
	}


	public int getParkingid() {
		return parkingid;
	}


	public void setParkingid(int parkingid) {
		this.parkingid = parkingid;
	}

	@Override
	public String toString() {
		String s =  timein +" " + vehicletype +" " + licenseplate +" " + timeout +" " + price +" " + employeeid +" " + cardid +" " + parkingid;
		return s;
	}
}
