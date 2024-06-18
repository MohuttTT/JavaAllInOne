package sec05_ex;
// 클래스 AA 내부에는 abc() 메서드와 bcd() 메서드가 있으며, bcd() 메서드는 예외 처리 구문을 포함하고 있다.
class AA {
	void abc() {
		bcd();
	}
	
	void bcd() {
		try {
			Thread.sleep(1000);
			Class.forName("java.lang.Object");
		} catch (InterruptedException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

// 이때 bcd()가 예외를 직접 처리하지 않고 전가할 때의 코드를 완성하시오.
class AAA {
	void abc() {
		try {
			bcd();
		} catch (ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void bcd() throws InterruptedException, ClassNotFoundException {
		Thread.sleep(1000);
		Class.forName("java.lang.Object");
	}
}

public class EX06 {
	public static void main(String[] args) {
	}
}
