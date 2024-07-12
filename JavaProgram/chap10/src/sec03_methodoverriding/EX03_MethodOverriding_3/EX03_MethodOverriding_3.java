package sec03_methodoverriding.EX03_MethodOverriding_3;

class A {
	void print1() {
		System.out.println("A 클래스 print1");
	}
	void print2() {
		System.out.println("A 클래스 print2");
	}
}
class B extends A {
	@Override
	void print1() {
		System.out.println("B 클래스 print1");
	}
	void print2(int a) {
		System.out.println("B 클래스 print2");
	}
}
public class EX03_MethodOverriding_3 {
	public static void main(String[] args) {
		// A 타입 선언 / A 생성자 사용
		A aa = new A();
		aa.print1();
		aa.print2();
		System.out.println();
		
		// B 타입 선언 / B 생성자 사용
		B bb = new B();
		bb.print1();
		bb.print2();
		bb.print2(3);
		System.out.println();
		
		// A 타입 선언 / B 생성자 사용
		A ab = new B();
		ab.print1();
		ab.print2();
	}
}
