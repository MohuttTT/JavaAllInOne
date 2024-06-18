
public class User extends Person {

	public User(String name, int phone, String address) {
		super(name, phone, address);
	}

	public User(String name, int phone) {
		super(name, phone);
	}

	@Override
	public String toString() {
		return "회원 정보 [이름=" + super.getName() + ", 연락처=" + super.getPhone() + "]";
	}
	
	
	

}
