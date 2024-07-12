package sec05_thismethod.EX02_ThisMethod_2;

class A {
	// A 클래스의 구성 요소 : m1, m2, m3, m4 (필드) / A(), A(int a), A(int a, int b) 생성자
	int m1, m2, m3, m4;
	
	A() {
		m1 = 1;
		m2 = 2;
		m3 = 3;
		m4 = 4;
	}
	
	A(int a) {
		m1 = a;
		m2 = 2;
		m3 = 3;
		m4 = 4;
		// A()에서 실행되고 있는 코드와 m2, m3, m4 초기화하는 코드가 중복됨
	}
	
	A(int a, int b) {
		m1 = a;
		m2 = b;
		m3 = 3;
		m4 = 4;
		// A()에서 실행되고 있는 코드와 m3, m4 초기화하는 코드가 중복됨
		// A(int)에서 실행되고 있는 m1 = a 초기화하는 코드가 중복됨
	}
	
	void print() {
		System.out.printf("%d %d %d %d\n", m1, m2, m3, m4);
	}
}

class B {
	// B 클래스의 구성 요소 : m1, m2, m3, m4 (필드) / B(), B(int a), B(int a, int b) 생성자
	int m1, m2, m3, m4;
	
	B() {
		m1 = 1;
		m2 = 2;
		m3 = 3;
		m4 = 4;
	}
	
	B(int a) {
		this(); // B() 먼저 실행 후
		m1 = a; // m1의 값을 a로 할당
	}
	
	B(int a, int b) {
		this(a); // B(int) 실행 후 => B() 먼저 실행한 다음 B(int)의 코드 실행
		m2 = b; // m2의 값을 b로 할당
	}
	
	void print() {
		System.out.printf("%d %d %d %d\n", m1, m2, m3, m4);
	}
}


public class ThisMethod_2 {

	public static void main(String[] args) {
		// 3가지 객체 생성 (this() 미사용) : A 클래스
		A a1 = new A();
		A a2 = new A(10);
		A a3 = new A(10, 20);
		
		System.out.println("A 타입 객체 출력");
		a1.print();
		a2.print();
		a3.print();
		System.out.println();
		
		// 3가지 객체 생성 (this() 사용) : B 클래스
		B b1 = new B();
		B b2 = new B(10);
		B b3 = new B(10, 20);
		
		System.out.println("b 타입 객체 출력");
		b1.print();
		b2.print();
		b3.print();
		System.out.println();
		
		
	}

}
