package sec06_ex;

// 이너 인터페이스는 static만 가능

class A7 {
	static interface B {
		void abc();
	}
}

public class EX07 {
	public static void main(String[] args) {
		// 이너 인터페이스의 객체 생성(익명 이너 클래스 이용)
		A7.B ab = new A7.B() {
			@Override
			public void abc() {
				System.out.println("11");
			}
		};
		
		ab.abc();
	}
}
