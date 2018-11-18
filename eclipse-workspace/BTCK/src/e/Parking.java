package e;

public class Parking {
	private int id;
	private String name;
	private int slots;
	
	
	public Parking(int id, String name, int slots) {
		super();
		this.id = id;
		this.name = name;
		this.slots = slots;
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


	public int getSlots() {
		return slots;
	}


	public void setSlots(int slots) {
		this.slots = slots;
	}
	
	
	
	
	
	
}
