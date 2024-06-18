package sec04_thiskeyword.EX02_ThisKeyword_2;

class A {
	// 클래스 A의 멤버 : 필드 m, n / 메서드 init()
	int m;
	int n;
	
	// 메서드 init의 지역 변수 : m, n, this(객체 위칫값 가지고 있음)
	void init(int m, int n) {
		// 지역 변수 내에서 먼저 해당하는 변수가 있는지 찾고 멤버인 필드로 넘어가는 범위
		m = m;
		n = n;
	}
}

class B {
	// 클래스 B의 멤버 : 필드 m, n / 메서드 init()
	int m;
	int n;
	
	// 메서드 init의 지역 변수 : m, n, this(객체 위칫값 가지고 있음)
	void init(int m, int n) {
		// 지역 변수 m/n의 값을 나의 객체 필드 m/n에게 할당
		this.m = m;
		this.n = n;
	}
}

public class ThisKeyword_2 {

	public static void main(String[] args) {
		// 필드명과 지역 변수명이 같고, this 키워드 사용하지 않는 경우
		A a = new A();
		a.init(2, 3);
		System.out.println(a.m); // 0
		System.out.println(a.n); // 0
		
		// 필드명과 지역 변수명이 같고, this 키워드 사용하는 경우
		B b = new B();
		b.init(2, 3);
		System.out.println(b.m); // 2
		System.out.println(b.n); // 3
		

	}

}
