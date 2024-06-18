package sec01_accessmodifier.EX01_AccessModirierOrMember.pack1;

// A와 패키지 같은 경우
public class B {
	
	// 동일 패키지 내의 클래스는 import 구문 없이 사용 가능
	A a = new A();
	
	void print() {
		System.out.println(a.a);
		System.out.println(a.b);
		System.out.println(a.c);
	}

}

class F extends A {
	void print() {
		System.out.println();
	}
}
