package sec06_ex;

class A2 { // 아우터 클래스 A2
	class B2 { // A2의 인스턴스 이너 클래스 B2
		void bcd() { // B2 클래스의 멤버 bcd()
			System.out.println("이너 클래스의 메서드");
		}
	}
}

public class EX02 {
	public static void main(String[] args) {
		
		// 이너 클래스 B의 객체 생성(참조 변수명 b)
		A2 a = new A2();
		A2.B2 b = a.new B2();
		
		// 실행 결과 이너 클래스의 메서드
		b.bcd();
	}
}
