package sec03_genericmethod.EX02_AvailableMethodInGenericMethod;

class A {
	public <T> void method(T t) {
		// System.out.println(t.length()); -- 불가능(어떤 타입이 들어올지 모르기 때문에)
		System.out.println(t.equals("안녕"));	// 가능 -- Object 타입 메서드 사용
		// -- 어떤 타입이 들어오든 모든 클래스는 Object 타입의 자식이기 때문에
	}
}

public class AvailableMethodInGenericMethod {

	public static void main(String[] args) {
		A a = new A();
		a.<String>method("안녕");
	}

}
