package sec06_ex;

class A3 { // 아우터 클래스 A3
	
	// 아우터 클래스 A3의 멤버 print() | 인스턴스 이너 클래스 B
	void print() {
		System.out.println("클래스 A 메서드");
	}
	
	class B { // 이너 클래스 B
		// 이너 클래스 B의 멤버 print() | bcd()
		void print() {
			System.out.println("클래스 B 메서드");
		}
		
		void bcd() {
			// 동일한 멤버가 있을 경우 이너 클래스 안에서 찾기 때문에 아우터 클래스 이름을 명시하고 찾아야 한다.
			A3.this.print();
		}
	}
}

public class EX03 {
	public static void main(String[] args) {
		A3 a = new A3();
		A3.B ab = a.new B();
		ab.bcd();
		// 클래스 A 메서드 출력
	}
}
