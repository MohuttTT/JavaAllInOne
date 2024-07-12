package sec07_review;

// 내부 클래스 : 클래스 안의 클래스

class A { // 아우터 클래스 A
	class B{ 
		// 아우터 클래스의 인스턴스 멤버 B
		// 인스턴스 이너 클래스 B
	} 
}

// 내부 클래스의 장점
// 1. 아우터 클래스로 각각 있는 경우
class A1 {
	int a = 5;
}
class B1 {
	A1 a = new A1(); // A1의 객체를 생성한 다음 A1의 멤버들을 사용할 수 있다.
	void bcd() {
		System.out.println(a.a);
	}
}

// 2. 내부 클래스로 있는 경우 - 객체 생성하지 않아도 멤버 사용 가능
class A2 {
	int a = 5;
	class B2 {
		void bcd() {
			System.out.println(a);
		}
	}
}

// 캡슐화 가능
/*
 * 밖에서 사용하지 않는, 클래스 B3가 클래스 A3에서만 사용하는 경우라고 가정해 보자.
 * 이런 경우 클래스 B3는 A3만 접근할 수 있게 만들면 되는 것이 아닌가? => 이너 클래스, 이너 인터페이스
 */
class A3 {
	private class B3 {}
}

/*
 * class C { A3 a3 = new A3(); A3.B3 b3 = a3.new B3(); }
 */

// [내부 클래스의 종류와 특징]
// 1. 종류와 유효 범위
class A4 {
	protected class InstanceInner {}
	/*
	 * 인스턴스 이너 클래스
	 * 외부 클래스의 인스턴스 멤버처럼 다뤄짐 => 객체를 생성해서 사용해야 한다.
	 * 인스턴스 멤버들과 관련된 작업에 사용할 목적으로 선언됨
	 */
	
	static class StaticInner {}
	/*
	 * 정적 이너 클래스
	 * 외부 클래스의 static 멤버, 특히 static 메서드에서 사용될 목적으로 선언됨.
	 */
	
	void aMethod () {
		class LocalInner {}
		/*
		 * 지역 이너 클래스
		 * 외부 클래스의 메서드 혹은 초기화 블록 안에 선언됨
		 * 선언된 내부에서만 사용 가능하다.
		 */
		
		LocalInner l = new LocalInner();
		
	}
}

// 2. 접근 제어자
/*
 * 보통 클래스의 경우 default와 public 접근 제어자만 붙일 수 있지만,
 * 이너 클래스의 경우 클래스의 멤버이기 때문에 모든 접근 제어자를 사용할 수 있다.
 * (public protected default private)
 * 
 * 단, 지역 이너 클래스의 경우 메서드 사용 시에만 생성됐다가 사라지기 때문에 접근 제어자를 사용하는 의미가 없다.
 * 지역 이너 클래스에 붙일 수 있는 자바 제어자는 abstract, final만 사용할 수 있다.
 */

// 3. 이너 클래스 객체 생성 및 사용법
// 인스턴스 이너 클래스 -> 아우터 클래스의 객체 생성 후, 아우터 클래스의 참조 변수를 사용하여 이너 클래스 객체 생성
// 정적 이너 클래스 -> 객체 생성하지 않고 아우터 클래스명으로 바로 접근하여 객체 생성
// 지역 이너 클래스 -> 메서드 내에서 클래스를 정의하고 클래스의 객체도 바로 생성해서 사용한다.


// 4. 이너 클래스의 this
class Outer {
	int value = 10; // Outer.this.value
	
	class Inner {
		int value = 20; // this.value
		
		void method1() {
			int value = 30; // 지역변수
			System.out.println(value); // 30
			System.out.println(this.value); // 20
			System.out.println(Outer.this.value); // 10
		}
	}
}


public class Review_Inner {

}
