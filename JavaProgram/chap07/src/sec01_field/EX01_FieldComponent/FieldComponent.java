package sec01_field.EX01_FieldComponent;

// 클래스 생성
class A {
	// 필드
	int m = 3;
	int n = 4;
	
	// 메서드
	// 메서드 안에 선언되는 변수 == 지역 변수
	void work1() {
		int k = 5;
		System.out.println(k);
		work2(3);
	}
	
	void work2(int i) {
		// 1. int i;
		// 2. i = 인자값 할당
		int j = 4;
		System.out.println(i + j);
	}
}

public class FieldComponent {
	public static void main(String[] args) {
		// A 클래스 사용하기 위해 생성자 사용하여 객체 생성
		A a = new A();
		// 필드값 출력
		System.out.println(a.m);
		System.out.println(a.n);
		// 메서드 호출
		a.work1();

	}

}
