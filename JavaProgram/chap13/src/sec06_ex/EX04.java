package sec06_ex;

// static의 특징 = 1. 클래스(인터페이스)명.멤버명 접근 가능 / 2. 정적인 친구들은 정적 멤버끼리만 사용할 수 있다.

class A4 { // 아우터 클래스 A4
	static class B { // 정적 이너 클래스 B
		void bcd() {
			System.out.println("정적 이너 클래스의 메서드");
		}
	}
}



public class EX04 {
	public static void main(String[] args) {
		// 정적 이너 클래스 B의 객체 생성(참조 변수명 ab)
		A4.B ab = new A4.B();
		ab.bcd();
	}
}
