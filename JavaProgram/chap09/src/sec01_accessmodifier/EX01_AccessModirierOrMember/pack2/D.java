package sec01_accessmodifier.EX01_AccessModirierOrMember.pack2;

import sec01_accessmodifier.EX01_AccessModirierOrMember.pack1.A;

// A와 패키지 다른 경우 - 클래스 A 자식
public class D extends A {
	
	void print() {
		System.out.println(a);
		System.out.println(b);
	}
}
