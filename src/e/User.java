package e;

public class User {
	private int id;
	private String username, password;
	private boolean active;
	private String email, fullname;
	private int role,parking_id;

	public User(){
		this.role = -1;
	};
	
	public User(int id, String username, String password,boolean active, String email, String fullname, int role, int parking_id) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.active = active;
		this.email = email;
		this.fullname = fullname;
		this.role = role;
		this.parking_id = parking_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getParking_id() {
		return parking_id;
	}

	public void setParking_id(int parking_id) {
		this.parking_id = parking_id;
	}

}
