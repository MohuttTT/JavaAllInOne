
public class Person {
	
	private String name;
	private int phone;
	private String address;
	
	public Person() {
		super();
	}

	public Person(String name, int phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public Person(String name, int phone, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	// getter | setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// Builder
	public Person name(String name) {
		this.name = name;
		return this;
	}
	
	public Person phone(int phone) {
		this.phone = phone;
		return this;
	}
	
	public Person address(String address) {
		this.address = address;
		return this;
	}
	
	public Person build() {
		return new Person(name, phone, address);
	}
	
}
