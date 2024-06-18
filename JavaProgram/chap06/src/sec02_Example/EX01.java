package sec02_Example;


public class EX01 {

	public static void main(String[] args) {
		// 참조변수 a를 선언하고 클래스 A의 객체를 생성하시오.
		A a = new A();
		
		// 객체 내부의 필드 m에 값 5를 대입하시오.
		a.m = 5;
		
		// 필드 m의 값을 콘솔에 출력하시오.
		System.out.println(a.m);
		
		// 메서드 method()를 호출하시오.
		a.method();

	}

}

class A {
	int m;
	void method() {
		System.out.println("A의 메서드");
	}
}