package sec05_thismethod.EX01_ThisMethod_1;


class A {
	// 클래스 A의 내부 구성 요소 : A() 기본 생성자, A(int) 일반 생성자
	
	A() {
		System.out.println("첫 번째 생성자");
	}
	
	// this() : 자기 자신의 다른 생성자 호출 => 생성자 내부에서만 사용 가능 / 반드시 첫 줄에 선언해야 함.
	A(int a) {
		this(); // this=A => this() == A() 
		System.out.println("두 번째 생성자");
	}
}


public class ThisMethod_1 {

	public static void main(String[] args) {
		
		// 객체 생성
		A a1 = new A(); // 기본 생성자 사용
		A a2 = new A(3); // 일반 생성자 사용
		/* 일반 생성자 코드 : 기본 생성자 호출 + syso("두 번째 생성자") */
	}

}
