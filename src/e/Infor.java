package e;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Infor {
	private String timein;
	private String licenseplate;
	private String timeout;
	private int price, id_vehicle;
	private int employeeid, cardid, parkingid;
	
	public Infor(){
		this.cardid = -1;
	}

	public Infor(int cardid, String timein, int id_vehicle, String licenseplate, String timeout, int price,
			int employeeid, int parkingid) {
		super();
		this.timein = timein;
		this.id_vehicle = id_vehicle;
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


	public int getId_vehicle() {
		return id_vehicle;
	}


	public void setId_vehicle(int id_vehicle) {
		this.id_vehicle = id_vehicle;
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

}
