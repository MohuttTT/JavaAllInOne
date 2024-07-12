package sec02_method.EX01_ExternalCallMethods;

// 클래스 A 정의
class A {
	
	// 리턴 타입이 없고 : void, 입력 매개변수 없음
	void print() {
		System.out.println("안녕");
	}
	
	// 리턴 타입이 있고 : int(정수형 변환), 입력 매개변수 없음
	int data() {
		return 3;
	}
	
	// 리턴 타입이 있고 : double(실수형 반환), 입력 매개변수 int, double 2개
	double sum(int a, double b) {
		return a + b;
	}
	
	// 리턴 타입이 없고 : void, 내부에 리턴 포함(=void에서 return 함수를 종료)
	void printMonth(int m) {
		if(m < 0 || m > 12) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		System.out.printf("%월입니다.\n", m);
	}
}
public class ExternalCallMethods {

	public static void main(String[] args) {
		// 외부 클래스인 A의 멤버 사용하기 위해 A 타입의 객체 생성
		A a = new A();
		
		a.print();
		// a.data() 실행 후 반환되는 값 받아주기 위해 변수 int k 선언
		int k = a.data();
		a.data();
		System.out.println(k);
		double result = a.sum(3,  5.2);
		System.out.println(result);
		a.printMonth(5);
		a.printMonth(15);

	}

}
