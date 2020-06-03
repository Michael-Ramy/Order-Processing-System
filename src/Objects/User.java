package Objects;

public class User {

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String address;
	private boolean privilege;
	
	public User(String username, String password, String firstname, String lastname, String email, String phone,
			String address, boolean privilege) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.privilege = privilege;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public boolean isPrivilege() {
		return privilege;
	}
}
