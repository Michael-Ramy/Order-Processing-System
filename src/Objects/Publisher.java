package Objects;

public class Publisher {
	
	private String Name;
	private String Address;
	private String Phone;
	
	public Publisher(String Name, String Address, String Phone) {
		this.Name = Name;
		this.Address = Address;
		this.Phone = Phone;
	}

	public String getName() {
		return Name;
	}

	public String getAddress() {
		return Address;
	}

	public String getPhone() {
		return Phone;
	}
	
}
