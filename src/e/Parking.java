package e;

public class Parking {
	private int id;
	private String name;
	private boolean active;
	private int bicycleLot, motorbikeLot, carLot;

	public Parking(int id, String name, boolean active, int bicycleLot, int motorbikeLot, int carLot) {
		this.id = id;
		this.name = name;
		this.active = active;
		this.bicycleLot = bicycleLot;
		this.motorbikeLot = motorbikeLot;
		this.carLot = carLot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getBicycleLot() {
		return bicycleLot;
	}

	public void setBicycleLot(int bicycleLot) {
		this.bicycleLot = bicycleLot;
	}

	public int getMotorbikeLot() {
		return motorbikeLot;
	}

	public void setMotorbikeLot(int motobikeLot) {
		this.motorbikeLot = motobikeLot;
	}

	public int getCarLot() {
		return carLot;
	}

	public void setCarLot(int carLot) {
		this.carLot = carLot;
	}
}
