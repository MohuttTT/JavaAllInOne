package sec01_accessmodifier.EX01_AccessModirierOrMember.pack1;

public class A {
	public int a = 1; // 동일 패키지 + 다른 패키지 클래스
	protected int b = 2; // 동일 패키지 + 다른 패키지의 자식 클래스
	int c = 3; // 동일 패키지 - default
	private int d = 4; // 동일 클래스
	
	void print() {
		// 같은 멤버 객체 생성 없이 접근 가능 => this. 생략
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
	
	// (접근제어자 : public? default?) A(){}
	
	// public A(){} -- public 클래스에 맞춰 public 생성자 생성
}

// 외부 클래스 - default 클래스
class E {
	// (default) E(){} -- default 클래스에 맞춰 default 생성자 생성
}
